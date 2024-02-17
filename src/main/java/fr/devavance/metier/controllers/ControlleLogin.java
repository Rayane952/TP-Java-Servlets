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
 * Contrôleur gérant l'authentification
 * @author B. LEMAIRE
 * @version 2023
 */
@WebServlet(name = "ControllerLogin", urlPatterns = {"/controller_login"})
public class ControlleLogin extends HttpServlet implements IController{
   

   

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                  
        response.setCharacterEncoding("utf-8");
                  
        if (! is_connected(request))
            dispatch(VUE_LOGIN, request, response);

          
      
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setCharacterEncoding("utf-8");    
        
        try {
            process_login_user(request, response);
        } catch (LoginException | CredentialException ex) {
            dispatch(VUE_LOGIN,request, response);      
            return;
        }
        
        dispatch(CONTROLLER, request, response);  
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

    
     public void dispatch(String path, HttpServletRequest request, 
             HttpServletResponse response) 
             throws ServletException, IOException {
         
        request.getRequestDispatcher(path)
                .forward(request, response);
        
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
        
        String login = request.getParameter( "login" );
        String password = request.getParameter( "password" );
        
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
            throw new CredentialException("login/password empty !");
        }
        if (! login.equals("admin") 
                || ! password.equals("admin")){
             throw 
               new CredentialException("login/password not correct !");
        }
    }
    
             
    
}

  
