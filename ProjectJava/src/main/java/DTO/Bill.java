/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.sql.Date;
import java.time.LocalDateTime;

/**
 *
 * @author hoang
 */
public class Bill {
    private long id;
    private long idUser;
    private String dateCreate;

    public Bill() {
    }

    public Bill(long id, long idUser, String dateCreate) {
        this.id = id;
        this.idUser = idUser;
        this.dateCreate = dateCreate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }
    
}
