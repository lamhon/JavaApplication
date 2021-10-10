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
import DTO.Product;

/**
 *
 * @author hoang
 */
public class ProductDAO {
    public static List<Product> getAll(){
        List<Product> list;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnectDB();
            String query = "SELECT * FROM tb_PRODUCT";
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            list = new ArrayList<>();
            while(rs.next()){
                Product product = new Product(rs.getLong(1), rs.getString(2), rs.getDouble(3), rs.getInt(4), rs.getLong(5), rs.getBoolean(6));
                list.add(product);
            }
            return list;
        } catch (Exception e) {
            
        }
        
        return null;
    }
    
    public static List<Product> getAllActive(){
        List<Product> list;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnectDB();
            String query = "SELECT * FROM tb_PRODUCT WHERE STT = 1";
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            list = new ArrayList<>();
            while(rs.next()){
                Product product = new Product(rs.getLong(1), rs.getString(2), rs.getDouble(3), rs.getInt(4), rs.getLong(5), rs.getBoolean(6));
                list.add(product);
            }
            return list;
        } catch (Exception e) {
            
        }
        
        return null;
    }
    
    public static boolean checkProductName(String productName){
        boolean check = false;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnectDB();
            String query = "SELECT * FROM tb_PRODUCT WHERE ProductName = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, productName);
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
    
    public static long getID(String productName){
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnectDB();
            String query = "SELECT * FROM tb_PRODUCT WHERE ProductName = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, productName);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getLong(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }
    
    public static void addProduct(Product product){
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnectDB();
            String query = "INSERT INTO tb_PRODUCT(ProductName, Price, SL, Suplier, STT) VALUES (?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, product.getProductName());
            pst.setDouble(2, product.getPrice());
            pst.setInt(3, product.getsL());
            pst.setLong(4, product.getIdSuplier());
            pst.setBoolean(5, product.issTT());
            
            pst.executeQuery();
        } catch (Exception e) {
        }
    }
    
    public static boolean changeInfo(Product product){
        boolean check = false;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnectDB();
            String query = "sp_UpdateInfoProduct @ID = ? , @Name = ? , @Price = ? , @SL = ? , @Suplier = ? , @STT = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setLong(1, product.getId());
            pst.setString(2, product.getProductName());
            pst.setDouble(3, product.getPrice());
            pst.setInt(4, product.getsL());
            pst.setLong(5, product.getIdSuplier());
            pst.setBoolean(6, product.issTT());
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
    
    public static String getName(long productId){
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnectDB();
            String query = "SELECT * FROM tb_PRODUCT WHERE ID = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setLong(1, productId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getString(2);
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    public static Product getProduct(long productId){
        Product product;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnectDB();
            String query = "SELECT * FROM tb_PRODUCT WHERE ID = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setLong(1, productId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
               product = new Product(rs.getLong(1), rs.getString(2), rs.getDouble(3), rs.getInt(4), rs.getLong(5), rs.getBoolean(6));
               return product;
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    public static List<Product> getListOutStock(){
        List<Product> lstProducts;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnectDB();
            String query = "SELECT * FROM tb_PRODUCT ORDER BY SL ASC";
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            lstProducts = new ArrayList<>();
            while(rs.next()){
                Product product = new Product(rs.getLong(1), rs.getString(2), rs.getDouble(3), rs.getInt(4), rs.getLong(5), rs.getBoolean(6));
                lstProducts.add(product);
            }
            return lstProducts;
        } catch (Exception e) {
            
        }
        return null;
    }
}
