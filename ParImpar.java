package exercicios;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class ParImpar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ParImpar() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h1>Par ou Ímpar??<h1>");
		out.println("<form method = \"post\">");
		out.println("Digite o numero:");
		out.println("<input type=\"number\" id=\"numero\" name=\"numero\">");
		out.println("<input type=\"submit\" value=\"Verificar\">");
		out.println("</form>");
		
		String resultado = request.getParameter("resultado");
		if (resultado != null && !resultado.isEmpty()) {
            out.println("<p>O número é " + resultado + "</p>");
        }

        out.println("</body></html>");
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String numeroStr = request.getParameter("numero");
		
		String resultado;
		
		if (numeroStr != null && !numeroStr.isEmpty()) {
			try {
				int numero = Integer.parseInt(numeroStr);
				
				if(numero % 2 == 0) {
					resultado = "par";
					
				}else {
					resultado = "ímpar";
					
				}
				
			}catch(NumberFormatException e) {
				resultado = "inválido";
				
			}
			
			response.sendRedirect("/Web2/ParImpar?resultado=" + resultado);
		}
	}
	

}
