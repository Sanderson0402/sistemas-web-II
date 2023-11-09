package exercicios;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/verificarPalindromo")
public class VerificarPalindromo extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html><head><title>Verificar Palíndromo</title></head><body>");
        out.println("<h2>Verificar Palíndromo</h2>");
        out.println("<form method=\"post\">");
        out.println("Digite uma palavra ou frase: <input type=\"text\" name=\"texto\">");
        out.println("<input type=\"submit\" value=\"Verificar\">");
        out.println("</form>");
        out.println("</body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html><head><title>Resultado</title></head><body>");
        out.println("<h2>Resultado</h2>");

        String texto = request.getParameter("texto");
        if (isPalindromo(texto)) {
            out.println("<p>" + texto + " é um palíndromo!</p>");
        } else {
            out.println("<p>" + texto + " não é um palíndromo.</p>");
        }

        out.println("<p><a href=\"verificarPalindromo\">Voltar</a></p>");
        out.println("</body></html>");
    }

    private boolean isPalindromo(String texto) {
        // Lógica para verificar se a string é um palíndromo
        String reverso = new StringBuilder(texto).reverse().toString();
        return texto.equalsIgnoreCase(reverso);
    }
}