/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhnhq.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import khanhnhq.question.QuestionDAO;
import khanhnhq.subject.SubjectDAO;
import khanhnhq.subject.SubjectDTO;

/**
 *
 * @author PC
 */
public class DeleteQuestionServlet extends HttpServlet {

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
        String id = request.getParameter("questionID");
        String subject = request.getParameter("subject");
        String subjectID = request.getParameter("subjectID");
        String url = null;
        try
        {
            QuestionDAO dao = new QuestionDAO();
            SubjectDAO subjectDAO = new SubjectDAO();
            dao.deleteQuestion(id);
            SubjectDTO dto = subjectDAO.getSubjectById(subject);
            int numberOfQuestion = dao.getNumberOfQuestion(subject);
            if(dto.getQuizQuestion() > numberOfQuestion)
            {
                dto.setQuizQuestion(numberOfQuestion);
                subjectDAO.editSubject(subject, numberOfQuestion, dto.getNumberOfAttemp(), dto.getTime());
            }
            if(subjectID.equals(""))
            {
                url = "SearchNameOrStatusServlet";
            }
            else
            {
                url = "SearchBySubjectServlet";
            }
        }
        catch (Exception e) 
        {
            log("Error at DeleteQuestionServlet" + e.getMessage());
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
