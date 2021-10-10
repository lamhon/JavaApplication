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
public class Role {
    private long id;
    private String roleName;
    private boolean sTT;

    public Role(long id, String roleName, boolean sTT) {
        this.id = id;
        this.roleName = roleName;
        this.sTT = sTT;
    }

    public Role() {
        
    }

    //Getter
    public long getId() {
        return id;
    }

    public String getRoleName() {
        return roleName;
    }

    public boolean issTT() {
        return sTT;
    }

    
    //Setter
    public void setId(long id) {
        this.id = id;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void setsTT(boolean sTT) {
        this.sTT = sTT;
    }
    
    
}
