package controller;

import dao.DisciplinaDAO;
import model.Disciplina;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/disciplinas")
public class DisciplinaController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DisciplinaDAO disciplinaDAO;

    public DisciplinaController() {
        super();
        this.disciplinaDAO = new DisciplinaDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Disciplina> disciplinas = disciplinaDAO.listarTodas();
            request.setAttribute("disciplinas", disciplinas);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
        request.getRequestDispatcher("/WEB-INF/disciplina.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            switch (action) {
                case "addDisciplina":
                    String nome = request.getParameter("nome");
                    disciplinaDAO.adicionarDisciplina(nome);
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
