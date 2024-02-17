/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package fr.devavance.metier.views;

import fr.devavance.metier.views.interfaces.IServletView;
import fr.devavance.metier.controllers.Controller;
import fr.devavance.metier.controllers.IController;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author bouchaib.lemaire
 */
@WebServlet(name = "SeanceChoice", urlPatterns = {"/choice"})
public class SeanceChoiceView extends HttpServlet implements IServletView {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
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
            out.println("<h1>Séance</h1>");
            out.println("</header>");
            out.println("<main>");
            out.println("<nav>");
            out.println("<ul> ");
            out.println("<li><a href=\"controller\">Home</a></li>");
            out.println("<li><a href=\"logout\">Logout</a></li>");
            out.println("</nav>");
            out.println("<form method=\"POST\" action=\"" + IController.CONTROLLER + "\">");
            out.println("<select name=\"action\">");

            out.println("<option value=\"COURS\">cours</option>");
            out.println("<option value=\"TD\">TD</option>");
            out.println("<option value=\"TP\">TP</option>");
            out.println("</select>");
            out.println("<input type=\"hidden\" name=\"action\" value=\"" + Controller.ACTION_SEANCE + "\" />");
            out.println("<p><input type=\"submit\" value=\"Valider\" /></p>");
            out.println("</form>");
            out.println("</main>");
            out.println("<footer>");
            out.println("<h4>einfo-learning.fr</h4>");
            out.println("</footer>");
            out.println("</body>");
            out.println("</html>");
        }

         
        //include_footer(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, 
            HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, 
            HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
}
