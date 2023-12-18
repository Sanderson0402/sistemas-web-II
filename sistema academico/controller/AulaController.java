package controller;

import dao.AlunoDAO;
import dao.AulaDAO;
import dao.DisciplinaDAO;
import model.Aluno;
import model.Aula;
import model.Disciplina;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpSession;

@WebServlet("/aulas")
public class AulaController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AlunoDAO alunoDAO;
    private DisciplinaDAO disciplinaDAO; 
    private AulaDAO aulaDAO; 

    public AulaController() {
        super();
        this.alunoDAO = new AlunoDAO();
        this.disciplinaDAO = new DisciplinaDAO();
        this.aulaDAO = new AulaDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String disciplina = request.getParameter("disciplina");
            int disciplinaCodigo;
            if (disciplina != null && !disciplina.isEmpty()) {
                disciplinaCodigo = Integer.parseInt(disciplina);
            } else {
                // Definindo o valor padrão para disciplinaCodigo como 1 (Desenvolvimento Web I)
                disciplinaCodigo = 1;
            }
            
            HttpSession session = request.getSession();
            session.setAttribute("disciplinaCodigo", disciplinaCodigo);
            List<Aluno> alunos = alunoDAO.listar(disciplinaCodigo);
            request.setAttribute("alunos", alunos);

            Map<Boolean, List<Aluno>> presenca = alunoDAO.obterPresenca(disciplinaCodigo, new java.sql.Date(System.currentTimeMillis()));
            request.setAttribute("presentes", presenca.get(true));
            request.setAttribute("faltantes", presenca.get(false));
            
            // Obtendo a lista de disciplinas do banco de dados
            List<Disciplina> disciplinas = disciplinaDAO.listarTodas();
            request.setAttribute("disciplinas", disciplinas);
            
            // Obtendo a lista de aulas do banco de dados
            Map<Aula, Map<Boolean, List<Aluno>>> aulas = aulaDAO.obterAulas(); 
            request.setAttribute("aulas", aulas); 
            
        } catch (SQLException e) {
            throw new ServletException(e);
        }
        request.getRequestDispatcher("/WEB-INF/aula.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            switch (action) {
                case "addFalta":
                    int matricula = Integer.parseInt(request.getParameter("matricula"));
                    alunoDAO.inserirFalta(matricula);
                    break;
                case "addAula":
                    HttpSession session = request.getSession();
                    int disciplinaCodigo = (Integer) session.getAttribute("disciplinaCodigo");
                    Aula aula = new Aula();
                    aula.setData(new java.sql.Date(System.currentTimeMillis())); 
                    aula.setTurmaNumero(disciplinaCodigo); 
                    aulaDAO.adicionarAula(aula, disciplinaCodigo);
                    break;
                case "selectDisciplina":
                    doGet(request, response);
                    return;
                default:
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
        doGet(request, response);
    }
}


