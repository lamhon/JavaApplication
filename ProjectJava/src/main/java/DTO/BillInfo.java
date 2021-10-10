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
public class BillInfo {
    private long id;
    private long idBill;
    private long idProduct;
    private double price;
    private int sL;
    private double total;

    public BillInfo() {
    }

    public BillInfo(long id, long idBill, long idProduct, double price, int sL, double total) {
        this.id = id;
        this.idBill = idBill;
        this.idProduct = idProduct;
        this.price = price;
        this.sL = sL;
        this.total = total;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdBill() {
        return idBill;
    }

    public void setIdBill(long idBill) {
        this.idBill = idBill;
    }

    public long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(long idProduct) {
        this.idProduct = idProduct;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getsL() {
        return sL;
    }

    public void setsL(int sL) {
        this.sL = sL;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    
}
