package exercicios.sigaa;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Login() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h1>Login<h1>");
		out.println("<form method = \"post\">");
		out.println("Nome: ");
		out.println("<input type=\"text\" id=\"username\" name=\"username\">");
		out.println("<br>");
		out.println("Senha: ");
		out.println("<input type=\"text\" id=\"password\" name=\"password\">");
		out.println("<input type=\"submit\" value=\"Logar\">");
		out.println("</form>");
        out.println("</body></html>");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		 ServletContext context = getServletContext();
		 String adminPassword = context.getInitParameter("adminPassword");
		
		if (username != null && !username.isEmpty()) {
            if(password != null && !password.isEmpty()) {
            	if(password.equals(adminPassword)) {
            	// response.sendRedirect("Home");
            }
            else {
            	response.sendRedirect("Home");
            }
        }
		else {
			response.sendRedirect("login?erro=1");
		}
	}
  }
}
