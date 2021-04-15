/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhnhq.question;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import khanhnhq.db.DBHelper;

/**
 *
 * @author PC
 */
public class QuestionDAO implements Serializable{
    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public QuestionDAO() {
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
    
    public boolean createQuestion(String questionID, String content, String subject, String optionA, String optionB, String optionC, String optionD, String answer, String status) throws Exception
    {
        boolean check = false;
        Date date = new Date();
        try 
        {
            String sql = "Insert Into tblQuestion(questionID, subjectID, quizContent, OptionA, OptionB, OptionC, OptionD, Answer, createDate, status) Values(?,?,?,?,?,?,?,?,?,?)";
            conn = DBHelper.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, questionID);
            preStm.setString(2, subject);
            preStm.setString(3, content);
            preStm.setString(4, optionA);
            preStm.setString(5, optionB);
            preStm.setString(6, optionC);
            preStm.setString(7, optionD);
            preStm.setString(8, answer);
            preStm.setTimestamp(9, new Timestamp(date.getTime()));
            preStm.setString(10, status);
            check = preStm.executeUpdate() > 0;
        }
        finally
        {
            closeConnection();
        }
        return check;
    }
    
    public String getLastQuestion() throws Exception
    {
        String questionID = null;
        try 
        {
            String sql = "Select questionID From tblQuestion Where createDate = (Select MAX(createDate) From tblQuestion)";
            conn = DBHelper.makeConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            if(rs.next())
            {
                questionID = rs.getString("questionID");
            }
        } 
        finally
        {
            closeConnection();
        }
        return questionID;
    }
    
    public List<QuestionDTO> getAllQuestionBySubject(String subjectID, int index, int size) throws Exception
    {
        List<QuestionDTO> result;
        QuestionDTO dto;
        String id, content, optionA, optionB, optionC, optionD, answer, status;
//        String sql = "Select questionID, quizContent, OptionA, OptionB, OptionC, OptionD, Answer, status From tblQuestion Where subjectID = ?";
        String sql = "With x As(Select ROW_NUMBER() Over (Order By createDate) as num, questionID, quizContent, OptionA, OptionB, OptionC, OptionD, Answer, status From tblQuestion Where subjectID = ?) Select questionID, quizContent, OptionA, OptionB, OptionC, OptionD, Answer, status From x Where num between ?*?-(?-1) And ?*?";
        try
        {
            conn = DBHelper.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, subjectID);
            preStm.setInt(2, index);
            preStm.setInt(3, size);
            preStm.setInt(4, size);
            preStm.setInt(5, index);
            preStm.setInt(6, size);
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
                status = rs.getString("status");
                dto = new QuestionDTO(id, content, subjectID, optionA, optionB, optionC, optionD, answer, status);
                result.add(dto);
            }
        }
        finally
        {
            closeConnection();
        }
        return result;
    }
    
    public boolean editQuestion(String id, String content, String optionA, String optionB, String optionC, String optionD, String answer, String status) throws Exception
    {
        boolean check = false;
        Date date = new Date();
        try
        {
            String sql = "Update tblQuestion Set quizContent = ?, OptionA = ?, OptionB = ?, OptionC = ?, OptionD = ?, Answer = ?, status = ? Where questionID = ?";
            conn = DBHelper.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, content);
            preStm.setString(2, optionA);
            preStm.setString(3, optionB);
            preStm.setString(4, optionC);
            preStm.setString(5, optionD);
            preStm.setString(6, answer);
            preStm.setString(7, status);
            preStm.setString(8, id);
            check = preStm.executeUpdate() >0;
        }
        finally
        {
            closeConnection();
        }
        return check;
    }
    
    public boolean deleteQuestion(String id) throws Exception
    {
        boolean check = false;
        try
        {
            String sql = "Update tblQuestion Set status = 'Deactivate' Where questionID = ?";
            conn = DBHelper.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            check = preStm.executeUpdate() >0;
        }
        finally
        {
            closeConnection();
        }
        return check;
    }
    
    public int getNumberOfQuestion(String id) throws Exception
    {
        int count = 0;
        try
        {
            String sql = "Select questionID From tblQuestion Where subjectID = ? And status = 'Activate'";
            conn = DBHelper.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            rs = preStm.executeQuery();
            while(rs.next())
            {
                count++;
            }
        }
        finally
        {
            closeConnection();
        }
        return count;
    }
    
    public int countQuestionBySubject(String id) throws Exception
    {
        String sql = "Select count(*) From tblQuestion Where subjectID = ?";
        try
        {
            conn = DBHelper.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            rs = preStm.executeQuery();
            while(rs.next()) 
            {                
                return rs.getInt(1);
            }
        }
        finally
        {
            closeConnection();
        }
        return 0;
    }
    
    public int countQuestionByNameOrStatus(String name, String status) throws Exception
    {
        String sql;
        if(status.equals(""))
        {
            sql = "Select count(*) From tblQuestion Where quizContent Like ?";
        }
        else
        {
            sql = "Select count(*) From tblQuestion Where status = ?";
        }
        try
        {
            conn = DBHelper.makeConnection();
            preStm = conn.prepareStatement(sql);
            if(status.equals(""))
            {
                preStm.setString(1, "%" + name + "%");
            }
            else
            {
                preStm.setString(1, status);
            }
            rs = preStm.executeQuery();
            while(rs.next()) 
            {                
                return rs.getInt(1);
            }
        }
        finally
        {
            closeConnection();
        }
        return 0;
    }
    
    public List<QuestionDTO> getAllQuestionByNameOrStatus(String name, String status, int index, int size) throws Exception
    {
        List<QuestionDTO> result;
        QuestionDTO dto;
        String id, content, optionA, optionB, optionC, optionD, answer, subjectID;
        String sql;
        if(status.equals(""))
        {
            sql = "With x As(Select ROW_NUMBER() Over (Order By questionID) as num, questionID, subjectID, quizContent, OptionA, OptionB, OptionC, OptionD, Answer, status From tblQuestion Where quizContent Like ?) Select questionID, subjectID, quizContent, OptionA, OptionB, OptionC, OptionD, Answer, status From x Where num between ?*?-(?-1) And ?*?";
        }
        else
        {
            sql = "With x As(Select ROW_NUMBER() Over (Order By questionID) as num, questionID, subjectID, quizContent, OptionA, OptionB, OptionC, OptionD, Answer, status From tblQuestion Where status = ?) Select questionID, subjectID, quizContent, OptionA, OptionB, OptionC, OptionD, Answer, status From x Where num between ?*?-(?-1) And ?*?";
        }
        try
        {
            conn = DBHelper.makeConnection();
            preStm = conn.prepareStatement(sql);
            if(status.equals(""))
            {
                preStm.setString(1, "%" + name + "%");
            }
            else
            {
                preStm.setString(1, status);
            }
            preStm.setInt(2, index);
            preStm.setInt(3, size);
            preStm.setInt(4, size);
            preStm.setInt(5, index);
            preStm.setInt(6, size);
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
                status = rs.getString("status");
                subjectID = rs.getString("subjectID");
                dto = new QuestionDTO(id, content, subjectID, optionA, optionB, optionC, optionD, answer, status);
                result.add(dto);
            }
        }
        finally
        {
            closeConnection();
        }
        return result;
    }
    
    public List<QuestionDTO> getQuestionForQuiz(String subjectID, int number) throws Exception
    {
        List<QuestionDTO> result;
        QuestionDTO dto;
        String id, content, optionA, optionB, optionC, optionD, answer, status;
        String sql = "SELECT top (?) questionID, quizContent, OptionA, OptionB, OptionC, OptionD, Answer, status FROM tblQuestion Where subjectID = ? And status = 'Activate' Order by NEWID()";
        try
        {
            conn = DBHelper.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, number);
            preStm.setString(2, subjectID);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while(rs.next()) 
            {                
                id = rs.getString("questionID");
                content = rs.getString("quizContent");
                optionA = rs.getString("OptionA");
                optionB = rs.getString("OptionB");
                optionC = rs.getString("OptionC");
                optionD = rs.getString("OptionD");
                answer = rs.getString("Answer");
                status = rs.getString("status");
                dto = new QuestionDTO(id, content, subjectID, optionA, optionB, optionC, optionD, answer, status);
                result.add(dto);
            }
        }
        finally
        {
            closeConnection();
        }
        return result;
    }
}
