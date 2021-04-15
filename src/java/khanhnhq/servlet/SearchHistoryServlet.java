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
import khanhnhq.quizdetail.QuizDetailDAO;
import khanhnhq.quizdetail.QuizDetailDTO;
import khanhnhq.subject.SubjectDAO;
import khanhnhq.subject.SubjectDTO;

/**
 *
 * @author PC
 */
public class SearchHistoryServlet extends HttpServlet {

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
        String pageIndex = request.getParameter("pageIndex");
        String searchUsername = request.getParameter("txtSearchUsername");
        String searchSubject = request.getParameter("txtSearchSubject");
        String url = "history.jsp";
        try
        {
            if(pageIndex == null)
            {
                pageIndex = "1";
            }
            if(searchUsername == null)
            {
                searchUsername = "";
            }
            else if(!searchUsername.equals(""))
            {
                searchSubject = "";
            }
            HttpSession session = request.getSession();
            String role = (String) session.getAttribute("role");
            String username = (String) session.getAttribute("username");
            QuizDetailDAO dao = new QuizDetailDAO();
            SubjectDAO subjectDAO = new SubjectDAO();
            List<SubjectDTO> listSubject = subjectDAO.getAllSubject();
            int pageSize = 4;
            int endPage = 0;
            int count = dao.countSearchQuizResult(username, role, searchSubject, searchUsername);
            endPage = count / pageSize;
            if(count % pageSize != 0)
            {
                endPage++;
            }
            List<QuizDetailDTO> quizList = dao.getSearchQuizResult(username, role, Integer.parseInt(pageIndex), pageSize, searchSubject, searchUsername);
            request.setAttribute("quizSearchList", quizList);
            request.setAttribute("subjectList", listSubject);
            request.setAttribute("countSearchPage", endPage);
            request.setAttribute("txtSearchUsername", searchUsername);
            request.setAttribute("txtSearchSubject", searchSubject);
        }
        catch(Exception e)
        {
            log("Error at SearchHistoryServlet" + e.getMessage());
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
