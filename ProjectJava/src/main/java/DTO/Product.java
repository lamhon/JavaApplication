/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author hoang
 */
public class Product {
    private long id;
    private String productName;
    private double price;
    private int sL;
    private long idSuplier;
    private boolean sTT;

    public Product() {
    }
    
    

    public Product(long id, String productName, double price, int sL, long idSuplier, boolean sTT) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.sL = sL;
        this.idSuplier = idSuplier;
        this.sTT = sTT;
    }

    //Getter
    public long getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public int getsL() {
        return sL;
    }

    public long getIdSuplier() {
        return idSuplier;
    }

    public boolean issTT() {
        return sTT;
    }
    
    
    //Setter
    public void setId(long id) {
        this.id = id;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setsL(int sL) {
        this.sL = sL;
    }

    public void setIdSuplier(long idSuplier) {
        this.idSuplier = idSuplier;
    }

    public void setsTT(boolean sTT) {
        this.sTT = sTT;
    }
}
