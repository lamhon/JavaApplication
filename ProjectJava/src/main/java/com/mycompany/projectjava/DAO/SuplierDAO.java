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
import java.util.ArrayList;
import java.util.List;
import DTO.Role;
import DTO.Suplier;

/**
 *
 * @author hoang
 */
public class SuplierDAO {
    public static List<Suplier> getAll(){
        List<Suplier> list;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnectDB();
            String query = "SELECT * FROM tb_SUPLIER";
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            list = new ArrayList<>();
            while(rs.next()){
                Suplier suplier = new Suplier(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getBoolean(6));
                list.add(suplier);
            }
            return list;
        } catch (Exception e) {
            
        }
        
        return null;
    }
    
    public static List<Suplier> getAllActive(){
        List<Suplier> list;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnectDB();
            String query = "SELECT * FROM tb_SUPLIER WHERE STT = 1";
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            list = new ArrayList<>();
            while(rs.next()){
                Suplier suplier = new Suplier(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getBoolean(6));
                list.add(suplier);
            }
            return list;
        } catch (Exception e) {
            
        }
        
        return null;
    }
    
    public static boolean checkSuplierName(String suplierName){
        boolean check = false;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnectDB();
            String query = "SELECT * FROM tb_SUPLIER WHERE SuplierName = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, suplierName);
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
    
    public static String getName(long id){
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnectDB();
            String query = "SELECT * FROM tb_SUPLIER WHERE ID = ?";
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
    
    public static void addSuplier(Suplier suplier){
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnectDB();
            String query = "INSERT INTO tb_SUPLIER(SuplierName, Address, Phone, Email, STT) VALUES (?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, suplier.getSuplierName());
            pst.setString(2, suplier.getAddress());
            pst.setString(3, suplier.getPhone());
            pst.setString(4, suplier.getEmail());
            pst.setBoolean(5, suplier.issTT());
            
            pst.executeQuery();
        } catch (Exception e) {
        }
    } 
    
    public static boolean changeInfo(Suplier suplier){
        boolean check = false;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnectDB();
            String query = "sp_UpdateInfoSuplier @ID = ? , @Name = ? , @Address = ? , @Phone = ? , @Email = ? , @STT = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setLong(1, suplier.getId());
            pst.setString(2, suplier.getSuplierName());
            pst.setString(3, suplier.getAddress());
            pst.setString(4, suplier.getPhone());
            pst.setString(5, suplier.getEmail());
            pst.setBoolean(6, suplier.issTT());
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
    
    public static long getIDSuplier(String suplerName){
        long id = 0;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnectDB();
            String query = "SELECT ID FROM tb_SUPLIER WHERE SuplierName = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, suplerName);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
               id = rs.getLong(1);
            }
        } catch (Exception e) {
            
        }
        return id;
    }
}
