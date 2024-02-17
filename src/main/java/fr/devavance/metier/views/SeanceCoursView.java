package fr.devavance.metier.views;

import fr.devavance.metier.views.interfaces.IServletView;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author marmotton
 */
@WebServlet(name = "SeanceCours", urlPatterns = {"/cours"})
public class SeanceCoursView extends HttpServlet implements IServletView {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>TP2 - Authentification</title>");
            out.println(" <meta charset=\"UTF-8\">");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");

            out.println("</head>");
            out.println("<body>");
            out.println("<header>");
            out.println("<h1>Séance de cours</h1>");
            out.println("</header>");
            out.println("<main>");
              out.println("<nav>");
            out.println("<ul> ");
            out.println("<li><a href=\"controller\">Home</a></li>");
            out.println("<li><a href=\"logout\">Logout</a></li>");
            out.println("</nav>");
            out.println("<h2>Ceci est un cours</h1>");
            out.println("</main>");
            out.println("<footer>");
            out.println("<h4>einfo-learning.fr</h4>");
            out.println("</footer>");
            out.println("</body>");
            out.println("</html>");
        }

    }
}
