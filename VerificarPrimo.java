package exercicios;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/verificarPrimo")
public class VerificarPrimo extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        // Obtém o parâmetro "numero" da requisição
        String numeroParam = request.getParameter("numero");

        // Verifica se o parâmetro é fornecido e é um número
        if (numeroParam != null && numeroParam.matches("\\d+")) {
            int numero = Integer.parseInt(numeroParam);

            boolean primo = isPrimo(numero);

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            out.println("<html><body>");
            out.println("<h2>O número " + numero + " " + (primo ? "é primo." : "não é primo.") + "</h2>");
            out.println("</body></html>");
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parâmetro inválido.");
        }
    }

    
    private boolean isPrimo(int numero) {
        if (numero <= 1) {
            return false;
        }
        //O loop começa com i igual a 2 e continua até a raiz quadrada de numero. Isso ocorre porque, se existir um fator maior que a raiz quadrada do número, 
        //então deve haver um fator menor que também divide o número. 
        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) {
                return false;
            }
        }
        return true;
    }
}
