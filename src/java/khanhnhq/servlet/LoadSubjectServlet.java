/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhnhq.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import khanhnhq.question.QuestionDAO;
import khanhnhq.subject.SubjectDAO;
import khanhnhq.subject.SubjectDTO;

/**
 *
 * @author PC
 */
public class LoadSubjectServlet extends HttpServlet {
    private final String ADMIN = "admin.jsp";
    private final String CREATE_QUESTION = "create_question.jsp";

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
        String button = request.getParameter("actionBtn");
        String url = ADMIN;
        try 
        {
            SubjectDAO dao = new SubjectDAO();
            QuestionDAO questionDAO = new QuestionDAO();
            List<SubjectDTO> subjectList = dao.getAllSubject();
            for(SubjectDTO dto : subjectList)
            {
                dto.setNumberOfQuestion(questionDAO.getNumberOfQuestion(dto.getSubjectID()));
            }
            request.setAttribute("subjectList", subjectList);
            if(button == null)
            {
                url = ADMIN;
            }
            else if(button.equals("Create new Question"))
            {
                url = CREATE_QUESTION;
            }
            else
            {
                url = ADMIN;
            }
        } 
        catch (Exception e) 
        {
            log("Error at LoadSubjectServlet" + e.getMessage());
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
