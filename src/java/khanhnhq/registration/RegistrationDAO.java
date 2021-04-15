/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhnhq.registration;

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
public class RegistrationDAO implements Serializable{
    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public RegistrationDAO() {
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
    
    public RegistrationDTO checkLogin(String username, String password) throws Exception
    {
        String role, user, fullname;
        RegistrationDTO dto = null;
        String sql = "Select username, fullname, role From tblRegistration Where username = ? And password = ?  Collate Latin1_General_CS_AS";
        try {
                conn = DBHelper.makeConnection();
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, username);
                preStm.setString(2, password);
                rs = preStm.executeQuery();
                if(rs.next())
                {
                    user = rs.getString("username");
                    fullname = rs.getString("fullname");
                    role = rs.getString("role");
                    dto = new RegistrationDTO(user, fullname, role);
                }
            }
        
        finally{
            closeConnection();
        }
        return dto;
    }
    
    public boolean createAccount(String username, String fullname, String password) throws Exception
    {
        boolean check = false;
        Date date = new Date();
        try 
        {
            String sql = "Insert Into tblRegistration(username, fullname, password, role, status, createDate) Values(?,?,?,?,?,?)";
            conn = DBHelper.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, username);
            preStm.setString(2, fullname);
            preStm.setString(3, password);
            preStm.setString(4, "student");
            preStm.setString(5, "new");
            preStm.setTimestamp(6, new Timestamp(date.getTime()));
            check = preStm.executeUpdate() > 0;
        }
        finally
        {
            closeConnection();
        }
        return check;
    }
    
    public List<String> getAllUsername() throws Exception
    {
        List<String> result;
        String sql = "Select username From tblRegistration";
        try
        {
            conn = DBHelper.makeConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) 
            {
                result.add(rs.getString("username"));
            }
        }
        finally
        {
            closeConnection();
        }
        return result;
    }
}
