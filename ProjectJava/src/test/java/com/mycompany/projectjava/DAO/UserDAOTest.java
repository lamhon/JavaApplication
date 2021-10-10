/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projectjava.DAO;

import junit.framework.TestCase;

/**
 *
 * @author hoang
 */
public class UserDAOTest extends TestCase {
    
    UserDAO user;
    
    public UserDAOTest() {
        user = new UserDAO();
    }

    public void testLogin() throws Exception {
        String hash = MD5.hashMD5("123");
        boolean res = user.Login("admin", hash);
        boolean exepted = true;
        assertEquals(exepted, res);
        
    }

    public void testGetID() throws Exception {
    }

    public void testCheckUsername() {
    }

    public void testGetUser() throws Exception {
    }

    public void testGetAll() {
    }

    public void testChangePass() {
    }

    public void testGetPass() {
    }

    public void testChangeInfo() {
    }

    public void testAddUser() {
    }

    public void testChangeInfoADMIN() {
    }
    
}
