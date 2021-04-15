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
import khanhnhq.subject.SubjectDTO;

/**
 *
 * @author PC
 */
public class UpdateQuizProgressServlet extends HttpServlet {

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
        String index = request.getParameter("questionIndex");
        String remainingTime = request.getParameter("remainingTime");
        String action = request.getParameter("quizAction");
        String url = "quiz.jsp";
        try
        {
            HttpSession session = request.getSession();
            List<QuestionDTO> listQuestion = (List<QuestionDTO>) session.getAttribute("listQuizQuestion");
            SubjectDTO dto = (SubjectDTO) session.getAttribute("subject");
            if(index == null)
            {
                index = "0";
            }
            if(remainingTime == null)
            {
                remainingTime = String.valueOf(dto.getTime());
                request.setAttribute("time", Integer.parseInt(remainingTime)*60);
                request.setAttribute("currentQuestion", listQuestion.get(Integer.parseInt(index)));
                request.setAttribute("currentIndex", Integer.parseInt(index));
            }
            else
            {
                //Cap nhat dong ho
                int totalSecond = Integer.parseInt(remainingTime.substring(0, remainingTime.indexOf("minutes") - 1))*60 + Integer.parseInt(remainingTime.substring(remainingTime.indexOf("s") + 2, remainingTime.indexOf("seconds") - 1));
                request.setAttribute("time", totalSecond);
                //Cap nhat dap an cau hoi
                listQuestion.get(Integer.parseInt(index)).setStudentAnswer(request.getParameter("answer"));
//                System.out.println(listQuestion.get(Integer.parseInt(index)).getContent() + " - " + listQuestion.get(Integer.parseInt(index)).getStudentAnswer());
                //Load cau hoi moi
                if(action.equals("Next"))
                {
                    request.setAttribute("currentQuestion", listQuestion.get(Integer.parseInt(index) + 1));
                    request.setAttribute("currentIndex", Integer.parseInt(index) + 1);
                }
                else if(action.equals("Previous"))
                {
                    request.setAttribute("currentQuestion", listQuestion.get(Integer.parseInt(index) - 1));
                    request.setAttribute("currentIndex", Integer.parseInt(index) - 1);
                }
                else if(action.equals("Submit"))
                {
                    url = "QuizGradedServlet";
                }
                else
                {
                    request.setAttribute("currentQuestion", listQuestion.get(Integer.parseInt(action) - 1));
                    request.setAttribute("currentIndex", Integer.parseInt(action) - 1);
                }
            }
            request.setAttribute("quizSize", dto.getQuizQuestion());
        }
        catch (Exception e)
        {
            log("Error at UpdateQuizProgressServlet" + e.getMessage());
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
