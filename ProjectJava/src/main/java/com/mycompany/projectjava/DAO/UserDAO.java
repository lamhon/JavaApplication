/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projectjava.DAO;

import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import DTO.User;

/**
 *
 * @author hoang
 */
public class UserDAO {
    
    public static boolean Login(String username, String password) throws SQLException{
        boolean check = false;
        try{
            DBContext db = new DBContext();
            Connection con = db.getConnectDB();
            String query = "SELECT * FROM tb_USER WHERE UserName = ? AND Password = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            if (rs.next())
                check = true;
            else
                check = false;
            con.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return check;
    }
    
    public static long getID(String username) throws SQLException{
        long id = 0;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnectDB();
            String query = "SELECT ID FROM tb_USER WHERE UserName = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
               id = rs.getLong(1);
            }
        } catch (Exception e) {
            
        }
        return id;
    }
    
    public static boolean checkUsername(String userName){
        boolean check = false;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnectDB();
            String query = "SELECT * FROM tb_USER WHERE UserName = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, userName);
            ResultSet rs = pst.executeQuery();
            if (rs.next())
                check = true;
            else
                check = false;
            con.close();
        } catch (Exception e) {
        }
        
        return check;
    }
    
    public static User getUser(long id) throws SQLException{
        User user;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnectDB();
            String query = "SELECT * FROM tb_USER WHERE ID = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
               user = new User(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getLong(8), rs.getBoolean(9));
               return user;
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    public static List<User> getAll(){
        List<User> list;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnectDB();
            String query = "SELECT * FROM tb_USER";
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
               User user = new User(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getLong(8), rs.getBoolean(9));
               list.add(user);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }
    
    public static boolean changePass(long id, String pwd){
        boolean check = false;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnectDB();
            String query = "sp_UpdatePassword @ID = ? , @Pwd = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setLong(1, id);
            pst.setString(2, pwd);
            ResultSet rs = pst.executeQuery();
            if (rs.next())
                check = true;
            else
                check = false;
            con.close();
        } catch (Exception e) {
        }
        return check;
    }
    
    public static String getPass(long id){
        String pass;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnectDB();
            String query = "SELECT * FROM tb_USER WHERE ID = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
               return pass = rs.getString(3);
            }
        } catch (Exception e) {
            
        }
        return null;
    }
    
    public static boolean changeInfo(long id, User user ){
        boolean check = false;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnectDB();
            String query = "sp_UpdateInfoUSER @ID = ? , @Name = ? , @Phone = ? , @Address = ? , @Email = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setLong(1, id);
            pst.setString(2, user.getName());
            pst.setString(3, user.getPhone());
            pst.setString(4, user.getAddress());
            pst.setString(5, user.getEmail());
            ResultSet rs = pst.executeQuery();
            if (rs.next())
                check = true;
            else
                check = false;
            con.close();
        } catch (Exception e) {
        }
        return check;
    }
    
    public static void addUser(User user){
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnectDB();
            String query = "INSERT INTO tb_USER(UserName, Password, Name, Phone, Address, Email, RoleID, STT) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, user.getUserName());
            pst.setString(2, user.getPassword());
            pst.setString(3, user.getName());
            pst.setString(4, user.getPhone());
            pst.setString(5, user.getAddress());
            pst.setString(6, user.getEmail());
            pst.setLong(7, user.getRoleID());
            pst.setBoolean(8, true);
            
            pst.executeQuery();
        } catch (Exception e) {
        }
    } 
    
    public static boolean ChangeInfoADMIN(long id, User user){
        boolean check = false;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnectDB();
            String query = "sp_UpdateInfoUser_Admin @ID = ? , @Name = ? , @Phone = ? , @Address = ? , @Email = ? , @RoleID = ? , @STT = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setLong(1, id);
            pst.setString(2, user.getName());
            pst.setString(3, user.getPhone());
            pst.setString(4, user.getAddress());
            pst.setString(5, user.getEmail());
            pst.setLong(6, user.getRoleID());
            pst.setBoolean(7, user.issTT());
            ResultSet rs = pst.executeQuery();
            if (rs.next())
                check = true;
            else
                check = false;
            con.close();
        } catch (Exception e) {
        }
        
        return check;
    }
}
