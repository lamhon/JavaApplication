/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projectjava.DAO;

import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author hoang
 */
public class Currency {
    public static NumberFormat toCurrency() {
        Locale localeEn = new Locale("en", "EN");
        NumberFormat currency = NumberFormat.getInstance(localeEn);
        return currency;
    }
    
    public static String splitCurrency(String price) {
        String[] slitArr = price.split(",");
        String res = "";
        for (String w : slitArr) {
            res = res.concat(w);
        }
        return res;
    }
}
