/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projectjava.DAO;

import com.mycompany.GUI.Splash;

/**
 *
 * @author hoang
 */
public class SplashScreen {
    public static void main(String[] args) {
        Splash splash = new Splash();
        splash.setVisible(true);
        try {
            for (int i = 0; i <= 100; i++) {
                Thread.sleep(100);
                Splash.txt_percent.setText(i + " %");
                Splash.pgb_progressbar.setValue(i);
                if (i == 100) {
                    
                }
            }
        } catch (Exception e) {
        }
    }
}
