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
import khanhnhq.question.QuestionDAO;
import khanhnhq.question.QuestionDTO;

/**
 *
 * @author PC
 */
public class SearchBySubjectServlet extends HttpServlet {

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
        String subjectID = request.getParameter("subjectID");
        String index = request.getParameter("PageIndex");
        try
        {
            QuestionDAO dao = new QuestionDAO();
            /////
            if(index == null)
            {
                index = "1";
            }
            int pageSize = 3;
            int endPage = 0;
            
            int count = dao.countQuestionBySubject(subjectID);
            endPage = count / pageSize;
            if(count % pageSize != 0)
            {
                endPage++;
            }
            List<QuestionDTO> listQuestion = dao.getAllQuestionBySubject(subjectID, Integer.parseInt(index), pageSize);
            /////
            request.setAttribute("listQuestion", listQuestion);
            request.setAttribute("countHomePage", endPage);
            request.setAttribute("subjectID", subjectID);
            request.setAttribute("currentPage", index);
        }
        catch (Exception e) 
        {
            log("Error at SearchBySubjectServlet" + e.getMessage());
        }
        finally
        {
            request.getRequestDispatcher("admin.jsp").forward(request, response);
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
