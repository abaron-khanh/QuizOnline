/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhnhq.quizdetail;

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
public class QuizDetailDAO implements Serializable {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public QuizDetailDAO() {
    }

    private void closeConnection() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (preStm != null) {
            preStm.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public String getLastQuizDetailByUser(String username, String subjectID) throws Exception {
        String quizID = null;
        try {
            String sql = "Select quizID From tblQuizResult Where date = (Select MAX(date) From tblQuizResult Where username = ? And subjectID = ?)";
            conn = DBHelper.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, username);
            preStm.setString(2, subjectID);
            rs = preStm.executeQuery();
            if (rs.next()) {
                quizID = rs.getString("quizID");
            }
        } finally {
            closeConnection();
        }
        return quizID;
    }

    public boolean createOrderDetails(String quizDetailID, String subjectID, String username, float grade) throws Exception {
        boolean check = false;
        Date date = new Date();
        try {
            String sql = "Insert Into tblQuizResult(quizID, subjectID, username, grade, date) Values(?,?,?,?,?)";
            conn = DBHelper.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, quizDetailID);
            preStm.setString(2, subjectID);
            preStm.setString(3, username);
            preStm.setFloat(4, grade);
            preStm.setTimestamp(5, new Timestamp(date.getTime()));
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public int countAllQuizResult(String username, String role) throws Exception {
        String sql = null;
        if (role.equals("admin")) {
            sql = "Select count(*) From tblQuizResult";
        } else {
            sql = "Select count(*) From tblQuizResult Where username = ?";
        }
        try {
            conn = DBHelper.makeConnection();
            preStm = conn.prepareStatement(sql);
            if (!role.equals("admin")) {
                preStm.setString(1, username);
            }
            rs = preStm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } finally {
            closeConnection();
        }
        return 0;
    }

    public List<QuizDetailDTO> getAllQuizResult(String username, String role, int index, int size) throws Exception {
        List<QuizDetailDTO> result;
        QuizDetailDTO dto;
        String quizID, subjectID, user;
        float grade;
        Date date;
        String sql;
        if (role.equals("admin")) 
        {
            sql = "With x As(Select ROW_NUMBER() Over (Order By username) as num, quizID, subjectID, username, grade, date From tblQuizResult) Select quizID, subjectID, username, grade, date From x Where num between ?*?-(?-1) And ?*?";
        } 
        else 
        {
            sql = "With x As(Select ROW_NUMBER() Over (Order By username) as num, quizID, subjectID, username, grade, date From tblQuizResult Where username = ?) Select quizID, subjectID, username, grade, date From x Where num between ?*?-(?-1) And ?*?";
        }
        try 
        {
            conn = DBHelper.makeConnection();
            preStm = conn.prepareStatement(sql);
            if (!role.equals("admin")) 
            {
                preStm.setString(1, username);
                preStm.setInt(2, index);
                preStm.setInt(3, size);
                preStm.setInt(4, size);
                preStm.setInt(5, index);
                preStm.setInt(6, size);
            } 
            else 
            {
                preStm.setInt(1, index);
                preStm.setInt(2, size);
                preStm.setInt(3, size);
                preStm.setInt(4, index);
                preStm.setInt(5, size);
            }
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) 
            {
                quizID = rs.getString("quizID");
                subjectID = rs.getString("subjectID");
                user = rs.getString("username");
                grade = rs.getFloat("grade");
                date = rs.getDate("date");
                dto = new QuizDetailDTO(quizID, subjectID, user, grade, date);
                result.add(dto);
            }
        } 
        finally 
        {
            closeConnection();
        }
        return result;
    }
    
    public int countSearchQuizResult(String username, String role, String subjectID, String usernameValue) throws Exception {
        String sql = null;
        if (role.equals("admin")) 
        {
            if(usernameValue.equals(""))
            {
                sql = "Select count(*) From tblQuizResult Where subjectID = ?";
            }
            else
            {
                sql = "Select count(*) From tblQuizResult Where username Like ?";
            }
        }
        else 
        {
            sql = "Select count(*) From tblQuizResult Where subjectID = ? And username = ?";
        }
        try {
            conn = DBHelper.makeConnection();
            preStm = conn.prepareStatement(sql);
            if (!role.equals("admin")) 
            {
                preStm.setString(1, subjectID);
                preStm.setString(2, username);
            }
            else
            {             
                if(usernameValue.equals(""))
                {
                    preStm.setString(1, subjectID);
                }
                else
                {
                    preStm.setString(1, "%" + usernameValue + "%");
                }
            }
            rs = preStm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } finally {
            closeConnection();
        }
        return 0;
    }
    
    public List<QuizDetailDTO> getSearchQuizResult(String username, String role, int index, int size, String subjectID, String usernameValue) throws Exception {
        List<QuizDetailDTO> result;
        QuizDetailDTO dto;
        String quizID, subject, user;
        float grade;
        Date date;
        String sql;
        if (role.equals("admin")) 
        {
            if(usernameValue.equals(""))
            {
                sql = "With x As(Select ROW_NUMBER() Over (Order By username) as num, quizID, subjectID, username, grade, date From tblQuizResult Where subjectID = ?) Select quizID, subjectID, username, grade, date From x Where num between ?*?-(?-1) And ?*?";
            }
            else
            {
                sql = "With x As(Select ROW_NUMBER() Over (Order By username) as num, quizID, subjectID, username, grade, date From tblQuizResult Where username Like ?) Select quizID, subjectID, username, grade, date From x Where num between ?*?-(?-1) And ?*?";
            }
        } 
        else 
        {
            sql = "With x As(Select ROW_NUMBER() Over (Order By username) as num, quizID, subjectID, username, grade, date From tblQuizResult Where username = ? And subjectID = ?) Select quizID, subjectID, username, grade, date From x Where num between ?*?-(?-1) And ?*?";
        }
        try 
        {
            conn = DBHelper.makeConnection();
            preStm = conn.prepareStatement(sql);
            if (!role.equals("admin")) 
            {
                preStm.setString(1, username);
                preStm.setString(2, subjectID);
                preStm.setInt(3, index);
                preStm.setInt(4, size);
                preStm.setInt(5, size);
                preStm.setInt(6, index);
                preStm.setInt(7, size);
            } 
            else 
            {
                if(usernameValue.equals(""))
                {
                    preStm.setString(1, subjectID);
                    preStm.setInt(2, index);
                    preStm.setInt(3, size);
                    preStm.setInt(4, size);
                    preStm.setInt(5, index);
                    preStm.setInt(6, size);
                }
                else
                {
                    preStm.setString(1, "%" + usernameValue + "%");
                    preStm.setInt(2, index);
                    preStm.setInt(3, size);
                    preStm.setInt(4, size);
                    preStm.setInt(5, index);
                    preStm.setInt(6, size);
                }
            }
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) 
            {
                quizID = rs.getString("quizID");
                subject = rs.getString("subjectID");
                user = rs.getString("username");
                grade = rs.getFloat("grade");
                date = rs.getDate("date");
                dto = new QuizDetailDTO(quizID, subject, user, grade, date);
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
