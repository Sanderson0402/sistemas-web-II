package controller;

import dao.ProfessorDAO;
import model.Professor;
import utils.ConnectionUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/professores")
public class ProfessorController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProfessorDAO professorDAO;

    public ProfessorController() {
        super();
        this.professorDAO = new ProfessorDAO(ConnectionUtils.getConnection());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Professor> professores = professorDAO.listar();
            request.setAttribute("professores", professores);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
        request.getRequestDispatcher("/WEB-INF/professor.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            switch (action) {
                case "addProfessor":
                    int id = Integer.parseInt(request.getParameter("id"));
                    String nome = request.getParameter("nome");
                    professorDAO.adicionar(new Professor(nome, id));
                    break;
                default:
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
        doGet(request, response);
    }
}



