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
public class User {
    private long id;
    private String userName;
    private String password;
    private String name;
    private String phone;
    private String address;
    private String email;
    private long roleID;
    private boolean sTT;

    public User(){
        
    }
    //Constructor
    public User(long id, String userName, String password, String name, String phone, String address, String email, long roleID, boolean sTT) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.roleID = roleID;
        this.sTT = sTT;
    }
    
    //Getter
    public long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public long getRoleID() {
        return roleID;
    }

    public boolean issTT() {
        return sTT;
    }
    
    
    //Setter
    public void setId(long id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRoleID(long roleID) {
        this.roleID = roleID;
    }

    public void setsTT(boolean sTT) {
        this.sTT = sTT;
    }
}
