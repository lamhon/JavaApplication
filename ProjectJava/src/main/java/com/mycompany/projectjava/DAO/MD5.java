/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projectjava.DAO;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 *
 * @author hoang
 */
public class MD5 {
    public static String hashMD5(String strHash){
        String res = "";
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
            digest.update(strHash.getBytes());
            BigInteger bigInteger = new BigInteger(1, digest.digest());
            res = bigInteger.toString(16);
        } catch (Exception e) {
            System.out.println(e);
        }
        return res;
    }
}
