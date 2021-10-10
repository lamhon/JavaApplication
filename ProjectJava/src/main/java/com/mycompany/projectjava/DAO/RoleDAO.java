/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projectjava.DAO;

import DTO.Function;
import DTO.Product;
import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import DTO.Role;
import DTO.RoleFunction;

/**
 *
 * @author hoang
 */
public class RoleDAO {
    public static void insertRole(String roleName){
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnectDB();
            String query = "INSERT INTO tb_ROLE(RoleName, STT) VALUES (?,?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, roleName);
            pst.setBoolean(2, true);
            
            pst.executeQuery();
        } catch (Exception e) {
        }
    }
    
    public static void insertAllFuncToRole(long roleID){
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnectDB();
            String query = "sp_InsertRoleFunc @IDRole = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setLong(1, roleID);
            
            pst.executeQuery();
        } catch (Exception e) {
        }
    }
    
    public static boolean changeInfo(Role role){
        boolean check = false;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnectDB();
            String query = "sp_UpdateRole @IDRole = ? , @NewName = ? , @STT = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setLong(1, role.getId());
            pst.setString(2, role.getRoleName());
            pst.setBoolean(3, role.issTT());
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
    
    public static String getRoleName(long id) throws SQLException{
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnectDB();
            String query = "SELECT * FROM tb_ROLE WHERE ID = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getString(2);
            }
        } catch (Exception e) {
            
        }
        return null;
    }
    
    public static List<Role> getAllBySTT(boolean stt){
        List<Role> list;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnectDB();
            String query = "SELECT * FROM tb_ROLE WHERE STT = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setBoolean(1, stt);
            ResultSet rs = pst.executeQuery();
            list = new ArrayList<>();
            while(rs.next()){
                Role role = new Role(rs.getLong(1), rs.getString(2), rs.getBoolean(3));
                list.add(role);
            }
            return list;
        } catch (Exception e) {
            
        }
        return null;
    }
    
    public static long getRoleID(String roleName){
        long id = 0;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnectDB();
            String query = "SELECT ID FROM tb_ROLE WHERE RoleName = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, roleName);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
               id = rs.getLong(1);
            }
        } catch (Exception e) {
            
        }
        return id;
    }
    
    public static List<Function> getListFunction(){
        List<Function> list;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnectDB();
            String query = "SELECT * FROM tb_FUNCTION";
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            list = new ArrayList<>();
            while(rs.next()){
                Function function = new Function(rs.getString(1), rs.getString(2), rs.getBoolean(3));
                list.add(function);
            }
            return list;
        } catch (Exception e) {
            
        }
        return null;
    }
    
    public static List<RoleFunction> getListRoleFunction(long roleID){
        List<RoleFunction> list;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnectDB();
            String query = "SELECT * FROM tb_ROLEFUNCTION WHERE IDRole = ? AND STT = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setLong(1, roleID);
            pst.setBoolean(2, true);
            ResultSet rs = pst.executeQuery();
            list = new ArrayList<>();
            while(rs.next()){
                RoleFunction function = new RoleFunction(rs.getLong(1), rs.getLong(2), rs.getString(3), rs.getBoolean(4));
                list.add(function);
            }
            return list;
        } catch (Exception e) {
            
        }
        return null;
    }
    
    public static String getFuncName(String functionID){
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnectDB();
            String query = "SELECT * FROM tb_FUNCTION WHERE ID = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, functionID);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getString(2);
            }
        } catch (Exception e) {
            
        }
        return null;
    }
    
    public static String getIDFunction(String funcName){
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnectDB();
            String query = "SELECT * FROM tb_FUNCTION WHERE FunctionName = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, funcName);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            
        }
        return null;
    }
    
    public static boolean changeInfo(RoleFunction roleFunc, boolean stt){
        boolean check = false;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnectDB();
            String query = "sp_UpdateRoleFunction @IDRole = ?, @IDFunction = ?,  @STT = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setLong(1, roleFunc.getIdRole());
            pst.setString(2, roleFunc.getIdFunction());
             pst.setBoolean(3, stt);
            ResultSet rs = pst.executeQuery();
            if (rs.next())
                check = true;
                //System.out.println("Success");
            else
                check = false;
                //System.out.println("Fail");
            con.close();
        } catch (Exception e) {
        }
        
        return check;
    }
}
