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
import DTO.Bill;
import DTO.User;

/**
 *
 * @author hoang
 */
public class BillDAO {
    public static long createBill(long userId){
        long id = 0;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnectDB();
            String query = "sp_InsertBill @UserCreate = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setLong(1, userId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                id = rs.getLong(1);
            }
            con.close();
        } catch (Exception e) {
        }
        return id;
    }
    
    public static List<Bill> getAllDESC(){
        List<Bill> lstBill;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnectDB();
            String query = "SELECT * FROM tb_BILL ORDER BY DateCreate DESC";
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            lstBill = new ArrayList<>();
            while (rs.next()) {
               Bill bill = new Bill(rs.getLong(1), rs.getLong(2), rs.getString(3));
               lstBill.add(bill);
            }
            return lstBill;
        } catch (Exception e) {
        }
        return null;
    }
    
    public static double getPrice(long id){
        double price = 0;
        try{
            DBContext db = new DBContext();
            Connection con = db.getConnectDB();
            String query = "SELECT SUM(Price * SL) FROM tb_BILLINFO WHERE BillID = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                price = rs.getDouble(1);
            }
        } catch (Exception e) {
        }
        return price;
    }
    
}
