/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.GUI;

import BLL.ChartBLL;
import com.mycompany.projectjava.DAO.BillDAO;
import com.mycompany.projectjava.DAO.BillInforDAO;
import com.mycompany.projectjava.DAO.FormatDate;
import com.mycompany.projectjava.DAO.ProductDAO;
import com.mycompany.projectjava.DAO.UserDAO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import DTO.Bill;
import DTO.BillInfo;
import com.mycompany.projectjava.DAO.Currency;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;

/**
 *
 * @author hoang
 */
public class Chart extends javax.swing.JFrame {

    /**
     * Creates new form Chart
     */
    public Chart() throws ParseException {
        initComponents();

        pnl_chart.setLayout(new java.awt.BorderLayout());
        JButton[] btnSearchs = {btn_searchByDate};
        for (JButton btn : btnSearchs) {
            btn.setBackground(new Color(234, 234, 234));
            btn.setUI(new BasicButtonUI());
            btn.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    btn.setBackground(new Color(84, 140, 168));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    btn.setBackground(new Color(234, 234, 234));
                }
            });
        }

        //loadListBill();
        loadDate();
        loadBillChartToday();

    }

    private static List<Bill> listBill = BillDAO.getAllDESC();
    private final static List<Bill> SearchListBill = new ArrayList<>();
    
    private void loadBillChartToday() {
        LocalDateTime now = LocalDateTime.now();
        String dateString = FormatDate.returnDateLocalDate(now.toString());
        
        loadChart(dateString, dateString, now.toString(), now.toString());
    }

    private void loadDate() throws ParseException {
        Date date = FormatDate.getDateFormat();

        date_from.setDate(date);
        date_to.setDate(date);
    }

    private void loadBill(List<Bill> lstBill) throws SQLException {
        DefaultTableModel model = (DefaultTableModel) tbl_bill.getModel();
        model.setRowCount(0);
        Object[] row = new Object[5];
        for (int i = 0; i < lstBill.size(); i++) {
            row[0] = i + 1;
            row[1] = lstBill.get(i).getId();
            row[2] = lstBill.get(i).getDateCreate();
            row[3] = UserDAO.getUser(lstBill.get(i).getIdUser()).getName();
            double price = BillDAO.getPrice(lstBill.get(i).getId());
            NumberFormat currency = Currency.toCurrency();
            row[4] = currency.format(price);

            model.addRow(row);
        }
    }

    private void loadBillInfo(List<BillInfo> info) {
        DefaultTableModel model = (DefaultTableModel) tbl_billInfo.getModel();
        model.setRowCount(0);
        Object[] row = new Object[4];
        for (int i = 0; i < info.size(); i++) {
            row[0] = i + 1;
            row[1] = ProductDAO.getProduct(info.get(i).getIdProduct()).getProductName();
            row[2] = info.get(i).getsL();
            double price = info.get(i).getPrice();
            NumberFormat currency = Currency.toCurrency();
            row[3] = currency.format(price);

            model.addRow(row);
        }
    }

    private void loadChart(String from, String to, String fromOriginal, String toOriginal) {
        JFreeChart chart = ChartFactory.createLineChart(
                "REVENUE",
                "TIME",
                "COST",
                ChartBLL.createDataset(from, to, fromOriginal, toOriginal, listBill),
                PlotOrientation.VERTICAL,
                true, true, false
        );
        //Set color
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.getRenderer().setSeriesPaint(0, Color.BLUE);

        //Create chart panel
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBackground(new Color(240, 240, 240));
        pnl_chart.removeAll();
        pnl_chart.add(chartPanel, BorderLayout.CENTER);
        pnl_chart.validate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        pnl_Search = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        date_from = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        date_to = new com.toedter.calendar.JDateChooser();
        btn_searchByDate = new javax.swing.JButton();
        pnl_Sold = new javax.swing.JPanel();
        jtextfialtf = new javax.swing.JLabel();
        num_bills = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        pnl_Revenue = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        num_revenue = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        pnl_chart = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_bill = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_billInfo = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1200, 890));
        setMinimumSize(new java.awt.Dimension(1200, 890));
        setPreferredSize(new java.awt.Dimension(1200, 890));
        setResizable(false);
        getContentPane().setLayout(new java.awt.FlowLayout());

        jPanel2.setBackground(new java.awt.Color(51, 66, 87));
        jPanel2.setMinimumSize(new java.awt.Dimension(1200, 100));
        jPanel2.setPreferredSize(new java.awt.Dimension(1200, 100));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(238, 238, 238));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("REPORT");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jPanel1.setBackground(new java.awt.Color(84, 140, 168));
        jPanel1.setPreferredSize(new java.awt.Dimension(100, 5));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(549, 549, 549)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(551, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2);

        pnl_Search.setBackground(new java.awt.Color(238, 238, 238));
        pnl_Search.setPreferredSize(new java.awt.Dimension(1200, 70));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setText("From:");

        date_from.setDateFormatString("dd/MM/yyyy");
        date_from.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setIcon(new javax.swing.ImageIcon("D:\\1.Sor\\IT\\Study\\Java\\Project\\ProjectJava\\src\\main\\java\\com\\mycompany\\projectjava\\icon\\icons_next_15.png")); // NOI18N
        jLabel4.setToolTipText("");

        date_to.setDateFormatString("dd/MM/yyyy");
        date_to.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        btn_searchByDate.setIcon(new javax.swing.ImageIcon("D:\\1.Sor\\IT\\Study\\Java\\Project\\ProjectJava\\src\\main\\java\\com\\mycompany\\projectjava\\icon\\icon_search_15.png")); // NOI18N
        btn_searchByDate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 140, 168), 2));
        btn_searchByDate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_searchByDateMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnl_SearchLayout = new javax.swing.GroupLayout(pnl_Search);
        pnl_Search.setLayout(pnl_SearchLayout);
        pnl_SearchLayout.setHorizontalGroup(
            pnl_SearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_SearchLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(date_from, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(date_to, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_searchByDate, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(772, Short.MAX_VALUE))
        );
        pnl_SearchLayout.setVerticalGroup(
            pnl_SearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_SearchLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(pnl_SearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_searchByDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6)
                    .addComponent(date_from, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(date_to, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        getContentPane().add(pnl_Search);

        pnl_Sold.setBackground(new java.awt.Color(255, 72, 72));
        pnl_Sold.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        pnl_Sold.setPreferredSize(new java.awt.Dimension(200, 140));

        jtextfialtf.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jtextfialtf.setForeground(new java.awt.Color(255, 255, 255));
        jtextfialtf.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jtextfialtf.setText("BILLS");

        num_bills.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        num_bills.setForeground(new java.awt.Color(255, 255, 255));
        num_bills.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        num_bills.setText("0");

        jLabel2.setIcon(new javax.swing.ImageIcon("D:\\1.Sor\\IT\\Study\\Java\\Project\\ProjectJava\\src\\main\\java\\com\\mycompany\\projectjava\\icon\\icon_order_30_white.png")); // NOI18N

        javax.swing.GroupLayout pnl_SoldLayout = new javax.swing.GroupLayout(pnl_Sold);
        pnl_Sold.setLayout(pnl_SoldLayout);
        pnl_SoldLayout.setHorizontalGroup(
            pnl_SoldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_SoldLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_SoldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jtextfialtf)
                    .addComponent(num_bills))
                .addContainerGap(136, Short.MAX_VALUE))
        );
        pnl_SoldLayout.setVerticalGroup(
            pnl_SoldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_SoldLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtextfialtf)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(num_bills)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        getContentPane().add(pnl_Sold);

        pnl_Revenue.setBackground(new java.awt.Color(255, 211, 113));
        pnl_Revenue.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        pnl_Revenue.setPreferredSize(new java.awt.Dimension(200, 140));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("REVENUE");

        num_revenue.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        num_revenue.setForeground(new java.awt.Color(255, 255, 255));
        num_revenue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        num_revenue.setText("0");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("$");

        jLabel5.setIcon(new javax.swing.ImageIcon("D:\\1.Sor\\IT\\Study\\Java\\Project\\ProjectJava\\src\\main\\java\\com\\mycompany\\projectjava\\icon\\icon_coin_30_white.png")); // NOI18N

        javax.swing.GroupLayout pnl_RevenueLayout = new javax.swing.GroupLayout(pnl_Revenue);
        pnl_Revenue.setLayout(pnl_RevenueLayout);
        pnl_RevenueLayout.setHorizontalGroup(
            pnl_RevenueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_RevenueLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_RevenueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_RevenueLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(num_revenue))
                    .addComponent(jLabel5)
                    .addComponent(jLabel3))
                .addContainerGap(100, Short.MAX_VALUE))
        );
        pnl_RevenueLayout.setVerticalGroup(
            pnl_RevenueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_RevenueLayout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_RevenueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(num_revenue)
                    .addComponent(jLabel7))
                .addGap(28, 28, 28))
        );

        getContentPane().add(pnl_Revenue);

        jPanel3.setPreferredSize(new java.awt.Dimension(1200, 510));

        pnl_chart.setPreferredSize(new java.awt.Dimension(530, 510));

        javax.swing.GroupLayout pnl_chartLayout = new javax.swing.GroupLayout(pnl_chart);
        pnl_chart.setLayout(pnl_chartLayout);
        pnl_chartLayout.setHorizontalGroup(
            pnl_chartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 530, Short.MAX_VALUE)
        );
        pnl_chartLayout.setVerticalGroup(
            pnl_chartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 510, Short.MAX_VALUE)
        );

        jPanel3.add(pnl_chart);

        jPanel5.setPreferredSize(new java.awt.Dimension(320, 510));

        tbl_bill.setBackground(new java.awt.Color(71, 96, 114));
        tbl_bill.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tbl_bill.setForeground(new java.awt.Color(238, 238, 238));
        tbl_bill.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "ID", "Date create", "User create", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_bill.setRowHeight(30);
        tbl_bill.getTableHeader().setReorderingAllowed(false);
        tbl_bill.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_billMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_bill);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel5);

        jPanel7.setMinimumSize(new java.awt.Dimension(285, 510));
        jPanel7.setPreferredSize(new java.awt.Dimension(330, 510));

        tbl_billInfo.setBackground(new java.awt.Color(71, 96, 114));
        tbl_billInfo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tbl_billInfo.setForeground(new java.awt.Color(238, 238, 238));
        tbl_billInfo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Product name", "Quantity", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_billInfo.setRowHeight(30);
        tbl_billInfo.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tbl_billInfo);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel7);

        getContentPane().add(jPanel3);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_searchByDateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_searchByDateMouseClicked
        // TODO add your handling code here:
        String dateFrom = FormatDate.returnDateToString(date_from.getDate());
        String dateTo = FormatDate.returnDateToString(date_to.getDate());

        LocalDateTime now = LocalDateTime.now();
        String dateString = FormatDate.returnDateLocalDate(now.toString());

        //System.out.println("TO: " + Long.parseLong(dateTo) + " _ FROM: " + Long.parseLong(dateFrom));
        if (Long.parseLong(dateTo) < Long.parseLong(dateFrom) || Long.parseLong(dateFrom) > Long.parseLong(dateString)) {
            JOptionPane.showMessageDialog(this, "Wrong date", "Alert", JOptionPane.WARNING_MESSAGE);
        } else {

            loadChart(dateFrom, dateTo, date_from.getDate().toString(), date_to.getDate().toString());      //This
            SearchListBill.removeAll(SearchListBill);

            //System.out.println(dateFrom);
            DefaultTableModel model = (DefaultTableModel) tbl_billInfo.getModel();
            model.setRowCount(0);

            for (int i = 0; i < listBill.size(); i++) {
                if (Long.parseLong(FormatDate.returnDateFromDB(listBill.get(i).getDateCreate())) >= Long.parseLong(dateFrom)
                        && Long.parseLong(FormatDate.returnDateFromDB(listBill.get(i).getDateCreate())) <= Long.parseLong(dateTo)) {
                    SearchListBill.add(listBill.get(i));
                }
            }
            try {
                loadBill(SearchListBill);
                num_revenue.setText(ChartBLL.loadSold(SearchListBill));
                num_bills.setText(ChartBLL.loadBills(SearchListBill));
            } catch (SQLException ex) {
                Logger.getLogger(Chart.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btn_searchByDateMouseClicked

    private void tbl_billMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_billMouseClicked
        // TODO add your handling code here:
        int index = tbl_bill.getSelectedRow();
        TableModel model = tbl_bill.getModel();

        long id = Long.parseLong(String.valueOf(model.getValueAt(index, 1)));
        List<BillInfo> listInfo = BillInforDAO.getBillInfo(id);
        loadBillInfo(listInfo);
    }//GEN-LAST:event_tbl_billMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Chart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Chart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Chart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Chart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Chart().setVisible(true);

                } catch (ParseException ex) {
                    Logger.getLogger(Chart.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_searchByDate;
    private com.toedter.calendar.JDateChooser date_from;
    private com.toedter.calendar.JDateChooser date_to;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel jtextfialtf;
    private javax.swing.JLabel num_bills;
    private javax.swing.JLabel num_revenue;
    private javax.swing.JPanel pnl_Revenue;
    private javax.swing.JPanel pnl_Search;
    private javax.swing.JPanel pnl_Sold;
    private javax.swing.JPanel pnl_chart;
    private javax.swing.JTable tbl_bill;
    private javax.swing.JTable tbl_billInfo;
    // End of variables declaration//GEN-END:variables
}
