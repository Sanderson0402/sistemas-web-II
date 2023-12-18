package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Aluno;
import utils.ConnectionUtils;

public class AlunoDAO {
    private Connection connection;

    public AlunoDAO() {
    	 this.connection = ConnectionUtils.getConnection();
    }

    // Listagem de alunos
    public List<Aluno> listar(int disciplinaCodigo) throws SQLException {
        List<Aluno> alunos = new ArrayList<>();

        String sql = "SELECT A.MATRICULA, A.NOME, (SELECT COUNT(*) FROM FALTAS F WHERE F.ALUNO_MATRICULA = A.MATRICULA) AS FALTAS " +
                "FROM ALUNO A JOIN ALUNO_TURMA AT ON A.MATRICULA = AT.ALUNO_MATRICULA " +
                "JOIN TURMA T ON AT.TURMA_NUMERO = T.NUMERO " +
                "WHERE T.DISCIPLINA_CODIGO = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        	stmt.setInt(1, disciplinaCodigo);
            stmt.execute();

            try (ResultSet resultSet = stmt.getResultSet()) {
                while (resultSet.next()) {
                    int matricula = resultSet.getInt("MATRICULA");
                    String nome = resultSet.getString("NOME");
                    int faltas = resultSet.getInt("FALTAS");

                    Aluno aluno = new Aluno(matricula, nome, faltas);
                    alunos.add(aluno);
                }
            }
        }

        return alunos;
    }
    
    public List<Aluno> listarTodos() throws SQLException {
        List<Aluno> alunos = new ArrayList<>();

        String sql = "SELECT MATRICULA, NOME, FALTAS FROM ALUNO";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.execute();

            try (ResultSet resultSet = stmt.getResultSet()) {
                while (resultSet.next()) {
                    int matricula = resultSet.getInt("MATRICULA");
                    String nome = resultSet.getString("NOME");
                    int faltas = resultSet.getInt("FALTAS");

                    Aluno aluno = new Aluno(matricula, nome, faltas);
                    alunos.add(aluno);
                }
            }
        }

        return alunos;
    }

    // Inserção de faltas com a data de hoje
    public void inserirFalta(int matricula) throws SQLException {
        String sqlTurma = "SELECT TURMA_NUMERO FROM ALUNO_TURMA WHERE ALUNO_MATRICULA = ?";
        int turmaNumero = 0;
        try (PreparedStatement stmt = connection.prepareStatement(sqlTurma)) {
            stmt.setInt(1, matricula);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                turmaNumero = rs.getInt("TURMA_NUMERO");
            }
        }

        String sqlFalta = "INSERT INTO FALTAS (ALUNO_MATRICULA, DATA, TURMA_NUMERO, FALTA) VALUES (?, CURDATE(), ?, TRUE)";
        try (PreparedStatement stmt = connection.prepareStatement(sqlFalta)) {
            stmt.setInt(1, matricula);
            stmt.setInt(2, turmaNumero);
            stmt.execute();
        }
    }

    // Adicionar alunos
    public void adicionarAluno(int matricula, String nome, int disciplinaCodigo) throws SQLException {
        // Inserir aluno
        String sqlAluno = "INSERT INTO ALUNO (MATRICULA, NOME, FALTAS) VALUES (?, ?, 0)";
        try (PreparedStatement stmt = connection.prepareStatement(sqlAluno)) {
            stmt.setInt(1, matricula);
            stmt.setString(2, nome);
            stmt.execute();
        }

        // Inserir aluno na turma
        String sqlTurma = "INSERT INTO ALUNO_TURMA (ALUNO_MATRICULA, TURMA_NUMERO) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sqlTurma)) {
            stmt.setInt(1, matricula);
            stmt.setInt(2, disciplinaCodigo);
            stmt.execute();
        }
    }
    
    public Map<Boolean, List<Aluno>> obterPresenca(int disciplinaCodigo, Date data) throws SQLException {
        Map<Boolean, List<Aluno>> presenca = new HashMap<>();
        presenca.put(true, new ArrayList<>());  // Lista de alunos presentes
        presenca.put(false, new ArrayList<>()); // Lista de alunos faltantes

        String sql = "SELECT A.MATRICULA, A.NOME, A.FALTAS, F.DATA IS NULL AS PRESENTE " +
                "FROM ALUNO A JOIN ALUNO_TURMA AT ON A.MATRICULA = AT.ALUNO_MATRICULA " +
                "LEFT JOIN FALTAS F ON A.MATRICULA = F.ALUNO_MATRICULA AND F.DATA = ? " +
                "WHERE AT.TURMA_NUMERO = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, data);
            stmt.setInt(2, disciplinaCodigo);
            stmt.execute();

            try (ResultSet resultSet = stmt.getResultSet()) {
                while (resultSet.next()) {
                    int matricula = resultSet.getInt("MATRICULA");
                    String nome = resultSet.getString("NOME");
                    int faltas = resultSet.getInt("FALTAS");
                    boolean presente = resultSet.getBoolean("PRESENTE");

                    Aluno aluno = new Aluno(matricula, nome, faltas);
                    presenca.get(presente).add(aluno);
                }
            }
        }

        return presenca;
    }

}
