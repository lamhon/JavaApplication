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
import DTO.BillInfo;

/**
 *
 * @author hoang
 */
public class BillInforDAO {
    public static void addBillInfo(BillInfo info, long billID){
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnectDB();
            String query = "INSERT INTO tb_BILLINFO(BillID, Product, Price, SL) VALUES (?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setLong(1, billID);
            pst.setLong(2, info.getIdProduct());
            pst.setDouble(3, info.getPrice());
            pst.setInt(4, info.getsL());
            
            pst.executeQuery();
        } catch (Exception e) {
        }
    }
    
    public static List<BillInfo> getBillInfo(long idBill){
        List<BillInfo> list;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnectDB();
            String query = "SELECT * FROM tb_BILLINFO WHERE BillID = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setLong(1, idBill);
            ResultSet rs = pst.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
               double total = rs.getLong(4) * rs.getInt(5);
               BillInfo billInfo = new BillInfo(rs.getLong(1), rs.getLong(2), rs.getLong(3), rs.getDouble(4), rs.getInt(5), total);
               list.add(billInfo);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }
}
