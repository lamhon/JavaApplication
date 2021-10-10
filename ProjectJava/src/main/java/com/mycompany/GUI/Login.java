/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.GUI;

import com.mycompany.projectjava.DAO.MD5;
import com.mycompany.projectjava.DAO.UserDAO;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicButtonUI;
import DTO.User;
import com.mycompany.projectjava.DAO.RoleDAO;

/**
 *
 * @author hoang
 */
public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    
    public Login() {
        initComponents();
        JButton bntLogin = btn_Login;
            bntLogin.setUI(new BasicButtonUI());
            bntLogin.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    bntLogin.setBackground(new Color(84, 140, 168));
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    bntLogin.setBackground(new Color(71, 96, 114));
                }
            });
        JButton btnExit = btn_Exit;
        btnExit.setUI(new BasicButtonUI());
        btnExit.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                btnExit.setBackground(new Color(255, 107, 107));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnExit.setBackground(new Color(182, 25, 25));
            }
        });
        
        warning_username.setVisible(false);
        warning_password_real.setVisible(false);
    }
    public static User USER;
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        warning_password = new javax.swing.JPanel();
        btn_Login = new javax.swing.JButton();
        btn_Exit = new javax.swing.JButton();
        lb_login = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_password = new javax.swing.JPasswordField();
        txt_username = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        warning_username = new javax.swing.JLabel();
        warning_password_real = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        warning_password.setBackground(new java.awt.Color(51, 66, 87));

        btn_Login.setBackground(new java.awt.Color(71, 96, 114));
        btn_Login.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btn_Login.setForeground(new java.awt.Color(255, 255, 255));
        btn_Login.setText("LOGIN");
        btn_Login.setBorder(null);
        btn_Login.setBorderPainted(false);
        btn_Login.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_LoginMouseClicked(evt);
            }
        });

        btn_Exit.setBackground(new java.awt.Color(182, 25, 25));
        btn_Exit.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btn_Exit.setForeground(new java.awt.Color(255, 255, 255));
        btn_Exit.setText("EXIT");
        btn_Exit.setBorder(null);
        btn_Exit.setBorderPainted(false);
        btn_Exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_ExitMouseClicked(evt);
            }
        });

        lb_login.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lb_login.setForeground(new java.awt.Color(238, 238, 238));
        lb_login.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_login.setText("LOGIN");
        lb_login.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lb_login.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(238, 238, 238));
        jLabel1.setText("Username:");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(238, 238, 238));
        jLabel2.setText("Password:");

        txt_password.setBackground(new java.awt.Color(51, 66, 87));
        txt_password.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txt_password.setForeground(new java.awt.Color(153, 153, 153));
        txt_password.setBorder(null);

        txt_username.setBackground(new java.awt.Color(51, 66, 87));
        txt_username.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txt_username.setForeground(new java.awt.Color(153, 153, 153));
        txt_username.setText("enter your username");
        txt_username.setToolTipText("");
        txt_username.setBorder(null);
        txt_username.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_usernameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_usernameFocusLost(evt);
            }
        });

        jSeparator3.setMinimumSize(new java.awt.Dimension(50, 10));
        jSeparator3.setPreferredSize(new java.awt.Dimension(50, 10));

        warning_username.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        warning_username.setForeground(new java.awt.Color(255, 51, 51));
        warning_username.setText("*");
        warning_username.setToolTipText("");

        warning_password_real.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        warning_password_real.setForeground(new java.awt.Color(255, 51, 51));
        warning_password_real.setText("*");
        warning_password_real.setToolTipText("");

        javax.swing.GroupLayout warning_passwordLayout = new javax.swing.GroupLayout(warning_password);
        warning_password.setLayout(warning_passwordLayout);
        warning_passwordLayout.setHorizontalGroup(
            warning_passwordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(warning_passwordLayout.createSequentialGroup()
                .addGroup(warning_passwordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(warning_passwordLayout.createSequentialGroup()
                        .addGroup(warning_passwordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(warning_passwordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(warning_passwordLayout.createSequentialGroup()
                                    .addGap(98, 98, 98)
                                    .addComponent(btn_Login, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(33, 33, 33)
                                    .addComponent(btn_Exit, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, warning_passwordLayout.createSequentialGroup()
                                    .addGap(103, 103, 103)
                                    .addComponent(jLabel1)
                                    .addGap(18, 18, 18)
                                    .addComponent(txt_username, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(warning_passwordLayout.createSequentialGroup()
                                    .addGap(112, 112, 112)
                                    .addComponent(jLabel2)
                                    .addGap(18, 18, 18)
                                    .addGroup(warning_passwordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txt_password)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(warning_passwordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(warning_username)
                            .addComponent(warning_password_real))
                        .addGap(0, 43, Short.MAX_VALUE))
                    .addGroup(warning_passwordLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lb_login, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        warning_passwordLayout.setVerticalGroup(
            warning_passwordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(warning_passwordLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(lb_login, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(warning_passwordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(warning_passwordLayout.createSequentialGroup()
                        .addGroup(warning_passwordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txt_username, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(warning_username))
                .addGroup(warning_passwordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(warning_passwordLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(warning_password_real))
                    .addGroup(warning_passwordLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(warning_passwordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addGroup(warning_passwordLayout.createSequentialGroup()
                                .addComponent(txt_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(38, 38, 38)
                .addGroup(warning_passwordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Login, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Exit, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(warning_password, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(warning_password, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ExitMouseClicked
        // TODO add your handling code here:
        int close = JOptionPane.showConfirmDialog(this, "Do you want to exit?");
        if (close == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
        
    }//GEN-LAST:event_btn_ExitMouseClicked

    private void btn_LoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_LoginMouseClicked
        // TODO add your handling code here
        USER = new User();
        if (txt_username.getText().equals("") || txt_username.getText().equals("enter your username")) {
            warning_username.setVisible(true);
            if (txt_password.getText().equals("")) {
                warning_password_real.setVisible(true);
            }
            else{
                warning_password_real.setVisible(false);
            }
        } else if(txt_password.getText().equals("")){
            warning_password_real.setVisible(true);
        }
        else{
            boolean check;
            try {
                String hash = MD5.hashMD5(txt_password.getText());
                check = UserDAO.Login(txt_username.getText(), hash);
                if (check) {
                    //Main main = new Main();
                    //main.setVisible(true);
                    //main.pack();
                    //main.setLocationRelativeTo(null);
                    USER = UserDAO.getUser(UserDAO.getID(txt_username.getText()));
                    
                    Main frame = new Main();
                    Main.USER = USER;
                    Main.listFunctionUser = RoleDAO.getListRoleFunction(USER.getRoleID());
                    frame.setVisible(true);
                    //SwingUtilities.updateComponentTreeUI(frame);
                    frame.invalidate();
                    frame.validate();
                    //frame.repaint();
                    this.setVisible(false);
                    
                }
                else{
                    JOptionPane.showMessageDialog(null, "Wrong password or username");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btn_LoginMouseClicked

    private void txt_usernameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_usernameFocusGained
        // TODO add your handling code here:
        if(txt_username.getText().equals("enter your username")){
            txt_username.setText("");
            txt_username.setForeground(new Color(238, 238, 238));
        }
    }//GEN-LAST:event_txt_usernameFocusGained

    private void txt_usernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_usernameFocusLost
        // TODO add your handling code here:
        if(txt_username.getText().equals("")){
            txt_username.setText("enter your username");
            txt_username.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_txt_usernameFocusLost

    
    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String args[]) {
        

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Exit;
    private javax.swing.JButton btn_Login;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lb_login;
    private javax.swing.JPasswordField txt_password;
    private javax.swing.JTextField txt_username;
    private javax.swing.JPanel warning_password;
    private javax.swing.JLabel warning_password_real;
    private javax.swing.JLabel warning_username;
    // End of variables declaration//GEN-END:variables
}