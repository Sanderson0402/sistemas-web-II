package controller;

import dao.AlunoDAO;
import dao.DisciplinaDAO;
import model.Aluno;
import model.Disciplina;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/alunos")
public class AlunoController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AlunoDAO alunoDAO;
    private DisciplinaDAO disciplinaDAO;

    public AlunoController() {
        super();
        this.alunoDAO = new AlunoDAO();
        this.disciplinaDAO = new DisciplinaDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Aluno> alunos = alunoDAO.listarTodos();
            request.setAttribute("alunos", alunos);

            List<Disciplina> disciplinas = disciplinaDAO.listarTodas();
            request.setAttribute("disciplinas", disciplinas);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
        request.getRequestDispatcher("/WEB-INF/aluno.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            switch (action) {
                case "addAluno":
                    int matricula = Integer.parseInt(request.getParameter("matricula"));
                    String nome = request.getParameter("nome");
                    int disciplinaCodigo = Integer.parseInt(request.getParameter("disciplina"));
                    alunoDAO.adicionarAluno(matricula, nome, disciplinaCodigo);
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
