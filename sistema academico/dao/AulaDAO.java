package dao;

import model.Aula;
import utils.ConnectionUtils;
import model.Aluno;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class AulaDAO {
    private Connection connection;

    public AulaDAO() {
    	this.connection = ConnectionUtils.getConnection();
    }

    public Map<Aula, Map<Boolean, List<Aluno>>> obterAulas() throws SQLException {
        Map<Aula, Map<Boolean, List<Aluno>>> aulas = new HashMap<>();

        String sql = "SELECT * FROM AULAS";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.execute();

            try (ResultSet resultSet = stmt.getResultSet()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("ID");
                    Date data = resultSet.getDate("DATA");
                    int turmaNumero = resultSet.getInt("TURMA_NUMERO");
                    int professorId = resultSet.getInt("PROFESSOR_ID");

                    Aula aula = new Aula(id, data, turmaNumero, professorId);

                    AlunoDAO alunoDAO = new AlunoDAO();
                    Map<Boolean, List<Aluno>> presenca = alunoDAO.obterPresenca(turmaNumero, data);

                    aulas.put(aula, presenca);
                }
            }
        }

        return aulas;
    }
    
    public void adicionarAula(Aula aula, int disciplinaCodigo) throws SQLException {
        String sql = "SELECT PROFESSOR_ID FROM PROFESSOR_TURMA WHERE TURMA_NUMERO = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, disciplinaCodigo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int professorId = rs.getInt("PROFESSOR_ID");

                sql = "INSERT INTO AULAS (DATA, TURMA_NUMERO, PROFESSOR_ID) VALUES (?, ?, ?)";
                try (PreparedStatement insertStmt = connection.prepareStatement(sql)) {
                    insertStmt.setDate(1, new java.sql.Date(aula.getData().getTime()));
                    insertStmt.setInt(2, aula.getTurmaNumero());
                    insertStmt.setInt(3, professorId);
                    insertStmt.executeUpdate();
                }
            } else {
                throw new SQLException("Não foi possível encontrar um professor para a disciplina com código: " + disciplinaCodigo);
            }
        }
    }
}

