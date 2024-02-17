/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package fr.devavance.metier.controllers;

import fr.devavance.metier.exceptions.CredentialException;
import fr.devavance.metier.exceptions.LoginException;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Contrôleur gérant les séances
 * @author B. LEMAIRE
 * @version 2023
 */
@WebServlet(name = "Controller", urlPatterns = {"/controller"})
public class Controller extends HttpServlet implements IController{
   



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
        
        response.setCharacterEncoding("utf-8");
                  
        if (! is_connected(request)){
              
          request.getRequestDispatcher(VUE_LOGIN).include(request, response);     
          return;
        }
  
        
    
        String action = request.getParameter(KEY_ACTION);
        
  
        try {
            switch (action) {
                case SEANCE_COURS:
                    dispatch(VUE_COURS, request, response);
                    return;
                case SEANCE_TD:
                    dispatch(VUE_TD, request, response);
                    return;
   
                case SEANCE_TP:
                    dispatch(VUE_TP, request, response);
                    return;
                default: 
                    dispatch(VUE_CHOICE, request, response);
                    return;
            }
        }
        
        catch(NullPointerException e){
            dispatch(VUE_CHOICE, request, response);

        }
    }

      @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setCharacterEncoding("utf-8");
        
        if (! is_connected(request)){
              
          dispatch(VUE_LOGIN, request, response);     
          return;
        }
        
         dispatch(VUE_CHOICE, request, response);
  
    }
  
    /**
          * Verifie si l'utilisateur est coonecté
     * @param session (HttpSession)
     * @return (boolean) : true if user connected exist else false
     */
    private boolean is_connected(HttpServletRequest request) {
            
       HttpSession session = request.getSession( true );
       
      Object  connection_state =  session.getAttribute(KEY_IS_CONNECTED);
      
      if (connection_state == null ) return false;
      

      return connection_state.toString().equals("connected");     
       
    }

    /**
     * Loggue l'utilisateur
     * @param request
     * @param response
     * @throws ServletException
     * @throws LoginException
     * @throws CredentialException 
     */
    private void process_login_user(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, LoginException, CredentialException{
        
        String login = request.getParameter( KEY_LOGIN );
        String password = request.getParameter( KEY_LOGIN );
        
        verify_credential(login, password);
    
        
        HttpSession session = request.getSession( true );
        session.setAttribute( "KEY_LOGIN", login );
        session.setAttribute( KEY_PASSWORD, password );
        session.setAttribute(KEY_IS_CONNECTED, "connected");     
        
    }

    /**
     * Valide les identifiants de l'utilissateur
     * @param login
     * @param password
     * @throws CredentialException : l'utilisateur n'a pas pu tre identifié
     */
    private void verify_credential(String login, String password) 
            throws CredentialException {
        
        if (login.isEmpty() || password.isEmpty()){
            throw new CredentialException(CREDENTIALS_EMPTY_MESSAGE);
        }
        if (! login.equals("admin") 
                || ! password.equals("admin")){
             throw 
               new CredentialException(ERROR_CREDENTIALS_MESSAGE);
        }
    }
    
    @Override
     public void dispatch(String path, HttpServletRequest request, 
             HttpServletResponse response) 
             throws ServletException, IOException {
         
        request.getRequestDispatcher(path)
                .forward(request, response);
        
    }
                
}

  
