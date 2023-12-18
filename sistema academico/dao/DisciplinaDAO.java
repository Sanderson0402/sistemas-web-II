package dao;

import model.Disciplina;
import utils.ConnectionUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaDAO {
    private Connection connection;

    public DisciplinaDAO() {
        this.connection = ConnectionUtils.getConnection();
    }

    // Listagem de todas as disciplinas
    public List<Disciplina> listarTodas() throws SQLException {
        List<Disciplina> disciplinas = new ArrayList<>();

        String sql = "SELECT * FROM DISCIPLINA";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.execute();

            try (ResultSet resultSet = stmt.getResultSet()) {
                while (resultSet.next()) {
                    int codigo = resultSet.getInt("CODIGO");
                    String nome = resultSet.getString("NOME");

                    Disciplina disciplina = new Disciplina(nome, codigo);
                    disciplinas.add(disciplina);
                }
            }
        }

        return disciplinas;
    }

    // Adicionar disciplina
    public void adicionarDisciplina(String nome) throws SQLException {
        String sql = "INSERT INTO DISCIPLINA (NOME) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.execute();
        }
    }
}
