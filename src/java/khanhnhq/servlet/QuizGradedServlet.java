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
import khanhnhq.quizquestion.QuizQuestionDAO;
import khanhnhq.subject.SubjectDTO;

/**
 *
 * @author PC
 */
public class QuizGradedServlet extends HttpServlet {

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
        String url = "quiz_result.jsp";
        try
        {
            HttpSession session = request.getSession();
            List<QuestionDTO> listQuestion = (List<QuestionDTO>) session.getAttribute("listQuizQuestion");
            String username = (String) session.getAttribute("username");
            SubjectDTO subject = (SubjectDTO) session.getAttribute("subject");
            QuizQuestionDAO quizQuestionDAO = new QuizQuestionDAO();
            ///cham diem
            int count = 0;
            for(int i = 0; i < listQuestion.size(); i++)
            {
                if(listQuestion.get(i).getAnswer().equalsIgnoreCase(listQuestion.get(i).getStudentAnswer()))
                {
                    count = count + 1;
                }
            }
            float grade = 0;
            grade = (float)count*10/listQuestion.size();
//            System.out.println(grade);
            ///tao thong tin bai quiz
            QuizDetailDAO dao = new QuizDetailDAO();
            String lastQuizID = dao.getLastQuizDetailByUser(username, subject.getSubjectID());
            String quizID = null;
            if(lastQuizID == null)
            {
                quizID = username + "-" + subject.getSubjectID() + "-1";
            }
            else
            {
                String[] tmp = lastQuizID.split("-");
                quizID = username + "-" + subject.getSubjectID() + "-" + (Integer.parseInt(tmp[2]) + 1);
            }
//            System.out.println(quizID);
            ///cap nhat ket qua vao database
            dao.createOrderDetails(quizID, subject.getSubjectID(), username, (float)Math.round(grade*100)/100);
//            System.out.println(added);
            for(int i = 0; i < listQuestion.size(); i++)
            {
                quizQuestionDAO.createQuizQuestion(quizID, listQuestion.get(i).getId(), listQuestion.get(i).getStudentAnswer(), listQuestion.get(i).getOptionA(), listQuestion.get(i).getOptionB(), listQuestion.get(i).getOptionC(), listQuestion.get(i).getOptionD(), listQuestion.get(i).getContent(), listQuestion.get(i).getAnswer());
            }
            request.setAttribute("quiz_result", (float)Math.round(grade*100)/100);
            request.setAttribute("correct_answer", count);
            request.setAttribute("subjectID", subject.getSubjectID());
            request.setAttribute("number_of_question", subject.getQuizQuestion());
            session.removeAttribute("listQuizQuestion");
            session.removeAttribute("subject");
            ///
        }
        catch (Exception e) 
        {
            log("Error at QuizGradedServlet" + e.getMessage());
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
