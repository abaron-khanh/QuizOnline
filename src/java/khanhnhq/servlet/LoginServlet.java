/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhnhq.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import khanhnhq.registration.RegistrationDAO;
import khanhnhq.registration.RegistrationDTO;

/**
 *
 * @author PC
 */
public class LoginServlet extends HttpServlet {
    private final String ADMIN_LOAD = "LoadSubjectServlet";
    private final String STUDENT_LOAD = "LoadStudentSubjectServlet";
    private final String ERROR = "error.html";
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
        response.setContentType("text/html;charset=UTF-8");
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String url = "";
        try 
        {
            HttpSession session = request.getSession();
            RegistrationDAO dao = new RegistrationDAO();
            /////
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes(StandardCharsets.UTF_8));
            byte[] digest = md.digest();
            password = String.format("%064x", new BigInteger(1, digest));
            /////
            RegistrationDTO dto = dao.checkLogin(username, password);
            if(dto == null)
            {
                url = ERROR;
            }
            else
            {
                if(dto.getRole().equals("admin"))
                {
                    url = ADMIN_LOAD;
                }
                else if(dto.getRole().equals("student"))
                {
                    url = STUDENT_LOAD;
                }
                session.setAttribute("role", dto.getRole());
                session.setAttribute("username", dto.getUsername());
                session.setAttribute("fullname", dto.getFullname());
            }
            
        } 
        catch (Exception e) 
        {
            log("Error at LoginServlet" + e.getMessage());
        }
        finally
        {
            request.getRequestDispatcher(url).forward(request, response);
        }
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
