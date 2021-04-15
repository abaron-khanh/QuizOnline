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
import khanhnhq.question.QuestionDTO;
import khanhnhq.quizdetail.QuizDetailDAO;
import khanhnhq.subject.SubjectDAO;
import khanhnhq.subject.SubjectDTO;

/**
 *
 * @author PC
 */
public class LoadStudentSubjectServlet extends HttpServlet {

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
        try
        {
            HttpSession session = request.getSession();
            String username = (String) session.getAttribute("username");
            SubjectDAO dao = new SubjectDAO();
            List<SubjectDTO> subjectList = dao.getAllSubject();
            QuizDetailDAO quizDAO = new QuizDetailDAO();
            String id;
            for(int i = 0; i < subjectList.size(); i++)
            {
                id = quizDAO.getLastQuizDetailByUser(username, subjectList.get(i).getSubjectID());
                if(id == null)
                {
                    subjectList.get(i).setNumberOfTimesTested(0);
                }
                else
                {
                    String[] tmp = id.split("-");
                    subjectList.get(i).setNumberOfTimesTested(Integer.parseInt(tmp[2]));
                }
            }
            request.setAttribute("subjectList", subjectList);
        }
        catch (Exception e) 
        {
            log("Error at LoadStudentSubjectServlet" + e.getMessage());
        }
        finally
        {
            request.getRequestDispatcher("student.jsp").forward(request, response);
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
