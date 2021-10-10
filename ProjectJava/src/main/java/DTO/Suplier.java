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
public class Suplier {
    private long id;
    private String suplierName;
    private String address;
    private String phone;
    private String email;
    private boolean sTT;
    
    public Suplier(){
        
    }

    public Suplier(long id, String suplierName, String address, String phone, String email, boolean sTT) {
        this.id = id;
        this.suplierName = suplierName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.sTT = sTT;
    }

    //Getter
    public long getId() {
        return id;
    }

    public String getSuplierName() {
        return suplierName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public boolean issTT() {
        return sTT;
    }
    
    //Setter
    public void setId(long id) {
        this.id = id;
    }

    public void setSuplierName(String suplierName) {
        this.suplierName = suplierName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setsTT(boolean sTT) {
        this.sTT = sTT;
    }
}
