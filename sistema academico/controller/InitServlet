
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import dao.DisciplinaDAO;
import model.Disciplina;

import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/init", loadOnStartup = 1)
public class InitServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    public void init() throws ServletException {
        super.init();

        try {
            // Crie uma instância do DAO
            DisciplinaDAO disciplinaDAO = new DisciplinaDAO();

            // Busque todas as disciplinas
            List<Disciplina> disciplinas = disciplinaDAO.listarTodas();

            // Obtenha o ServletContext
            ServletContext servletContext = getServletContext();

            // Adicione as disciplinas ao ServletContext
            servletContext.setAttribute("disciplinas", disciplinas);
        } catch (SQLException e) {
            throw new ServletException("Não foi possível buscar as disciplinas", e);
        }
    }
}
