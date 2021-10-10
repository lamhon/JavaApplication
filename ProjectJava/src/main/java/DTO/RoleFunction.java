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
public class RoleFunction {
    private long id;
    private long idRole;
    private String idFunction;
    private boolean STT;

    public RoleFunction() {
    }

    public RoleFunction(long id, long idRole, String idFunction, boolean STT) {
        this.id = id;
        this.idRole = idRole;
        this.idFunction = idFunction;
        this.STT = STT;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdRole() {
        return idRole;
    }

    public void setIdRole(long idRole) {
        this.idRole = idRole;
    }

    public String getIdFunction() {
        return idFunction;
    }

    public void setIdFunction(String idFunction) {
        this.idFunction = idFunction;
    }

    public boolean isSTT() {
        return STT;
    }

    public void setSTT(boolean STT) {
        this.STT = STT;
    }
    
    
}
