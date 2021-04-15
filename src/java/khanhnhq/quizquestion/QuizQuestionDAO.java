/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhnhq.quizquestion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import khanhnhq.db.DBHelper;
import khanhnhq.question.QuestionDTO;

/**
 *
 * @author PC
 */
public class QuizQuestionDAO {
    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public QuizQuestionDAO() {
    }
    
    private void closeConnection() throws Exception
    {
        if(rs != null)
            rs.close();
        if(preStm != null)
            preStm.close();
        if(conn != null)
            conn.close();
    }
    
    public List<QuestionDTO> getQuizQuestions(String quizID) throws Exception
    {
        List<QuestionDTO> result;
        QuestionDTO dto;
        String id, content, optionA, optionB, optionC, optionD, answer, studentAnswer;
        String sql = "Select questionID, quizContent, OptionA, OptionB, OptionC, OptionD, Answer, studentAnswer From tblQuizQuestion Where quizID = ?";
        try
        {
            conn = DBHelper.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, quizID);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) 
            {                
                id = rs.getString("questionID");
                content = rs.getString("quizContent");
                optionA = rs.getString("OptionA");
                optionB = rs.getString("OptionB");
                optionC = rs.getString("OptionC");
                optionD = rs.getString("OptionD");
                answer = rs.getString("Answer");
                studentAnswer = rs.getString("studentAnswer");
                dto = new QuestionDTO(id, content, optionA, optionB, optionC, optionD, answer, studentAnswer);
                result.add(dto);
            }
        }
        finally
        {
            closeConnection();
        }
        return result;
    }
    
    public boolean createQuizQuestion (String quizID, String questionID, String studentAnswer, String optionA, String optionB, String optionC, String optionD, String quizContent, String answer) throws Exception
    {
        boolean check = false;
        try 
        {
            String sql = "Insert Into tblQuizQuestion(quizID, questionID, studentAnswer, OptionA, OptionB, OptionC, OptionD, quizContent, Answer) Values(?,?,?,?,?,?,?,?,?)";
            conn = DBHelper.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, quizID);
            preStm.setString(2, questionID);
            preStm.setString(3, studentAnswer);
            preStm.setString(4, optionA);
            preStm.setString(5, optionB);
            preStm.setString(6, optionC);
            preStm.setString(7, optionD);
            preStm.setString(8, quizContent);
            preStm.setString(9, answer);
            check = preStm.executeUpdate() > 0;
        }
        finally
        {
            closeConnection();
        }
        return check;
    }
}
