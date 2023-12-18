package dao;

import model.Professor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDAO {
    private Connection connection;

    public ProfessorDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Professor> listar() throws SQLException {
        List<Professor> professores = new ArrayList<>();

        String sql = "SELECT * FROM PROFESSOR";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.execute();
            try (ResultSet rs = stmt.getResultSet()) {
                while (rs.next()) {
                    int id = rs.getInt("ID");
                    String nome = rs.getString("NOME");
                    Professor professor = new Professor(nome, id);
                    professores.add(professor);
                }
            }
        }

        return professores;
    }

    public void adicionar(Professor professor) throws SQLException {
        String sql = "INSERT INTO PROFESSOR (NOME) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, professor.getNome());
            stmt.execute();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    professor.setId(id);
                }
            }
        }
    }
}

