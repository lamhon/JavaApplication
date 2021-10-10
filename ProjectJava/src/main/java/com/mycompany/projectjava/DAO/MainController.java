/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projectjava.DAO;

import java.util.List;
import DTO.BillInfo;

/**
 *
 * @author hoang
 */
public class MainController {
    public static boolean checkProductInList(List<BillInfo> listBillInfos, long productId) {
        boolean check = false;
        for (int i = 0; i < listBillInfos.size(); i++) {
            if (listBillInfos.get(i).getIdProduct() == productId) {
                check = true;
            }
        }
        return check;
    }
    
    public static boolean isNumber(String in) {
        try {
            Double.parseDouble(in);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
