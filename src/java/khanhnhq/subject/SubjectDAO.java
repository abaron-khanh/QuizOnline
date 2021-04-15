/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhnhq.subject;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import khanhnhq.db.DBHelper;

/**
 *
 * @author PC
 */
public class SubjectDAO implements Serializable{
    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public SubjectDAO() {
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
    
    public List<SubjectDTO> getAllSubject() throws Exception
    {
        List<SubjectDTO> result;
        SubjectDTO dto;
        String id, name;
        int quizQuestion, numberOfAttemp, time, numberOfQuestion;
        String sql = "Select subjectID, name, quizQuestion, numberOfAttemp, time From tblSubject";
        try
        {
            conn = DBHelper.makeConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) 
            {                
                id = rs.getString("subjectID");
                name = rs.getString("name");
                quizQuestion = rs.getInt("quizQuestion");
                numberOfAttemp = rs.getInt("numberOfAttemp");
                time = rs.getInt("time");
                dto = new SubjectDTO(id, name, quizQuestion, numberOfAttemp, time);
                result.add(dto);
            }
        }
        finally
        {
            closeConnection();
        }
        return result;
    }
    
    public boolean editSubject(String id, int question, int attemp, int time) throws Exception
    {
        boolean check = false;
        try
        {
            String sql = "Update tblSubject Set quizQuestion = ?, numberOfAttemp = ?, time = ? Where subjectID = ?";
            conn = DBHelper.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, question);
            preStm.setInt(2, attemp);
            preStm.setInt(3, time);
            preStm.setString(4, id);
            check = preStm.executeUpdate() >0;
        }
        finally
        {
            closeConnection();
        }
        return check;
    }
    
    public String getSubjectId(String name) throws Exception
    {
        String id = null;
        String sql = "Select subjectID From tblSubject Where name = ?";
        try
        {
            conn = DBHelper.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, name);
            rs = preStm.executeQuery();
            if(rs.next()) 
            {                
                id = rs.getString("subjectID");
            }
        }
        finally
        {
            closeConnection();
        }
        return id;
    }
    
    public SubjectDTO getSubjectById(String id) throws Exception
    {
        int quizQuestion, numberOfAttemp, time;
        SubjectDTO dto = null;
        String sql = "Select quizQuestion, numberOfAttemp, time From tblSubject Where subjectID = ?";
        try
        {
            conn = DBHelper.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            rs = preStm.executeQuery();
            if(rs.next()) 
            {                
                quizQuestion = rs.getInt("quizQuestion");
                numberOfAttemp = rs.getInt("numberOfAttemp");
                time = rs.getInt("time");
                dto = new SubjectDTO(id, quizQuestion, numberOfAttemp, time);
            }
        }
        finally
        {
            closeConnection();
        }
        return dto;
    }
}
