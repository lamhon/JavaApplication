/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.GUI;

import BLL.BillBLL;
import BLL.FormatNumber;
import BLL.ProductBLL;
import BLL.RoleBLL;
import BLL.SuplierBLL;
import BLL.UserBLL;
import com.mycompany.projectjava.DAO.BillDAO;
import com.mycompany.projectjava.DAO.MainController;
import com.mycompany.projectjava.DAO.ProductDAO;
import com.mycompany.projectjava.DAO.RoleDAO;
import com.mycompany.projectjava.DAO.SuplierDAO;
import com.mycompany.projectjava.DAO.UserDAO;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.RowFilter;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import DTO.Bill;
import DTO.BillInfo;
import DTO.Function;
import DTO.Product;
import DTO.Role;
import DTO.RoleFunction;
import DTO.Suplier;
import DTO.User;
import com.mycompany.projectjava.DAO.Currency;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;

/**
 *
 * @author hoang
 */
public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    public Main() throws SQLException {
        initComponents();
        JButton[] btns = {btn_home, btn_create, btn_product, btn_suplier, btn_customer, btn_staff, btn_user};
        for (JButton btn : btns) {
            btn.setBackground(new Color(51, 66, 87));
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
                    btn.setBackground(new Color(51, 66, 87));
                }
            });
        }

        btn_exit.setBackground(new Color(51, 66, 87));
        btn_exit.setUI(new BasicButtonUI());
        btn_exit.addMouseListener(new MouseListener() {
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
                btn_exit.setBackground(new Color(182, 25, 25));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn_exit.setBackground(new Color(51, 66, 87));
            }
        });

        JButton[] btnSearchs = {btn_BillPlus, btn_BillSubtract, btn_productCreate, btn_productRepair, btn_suplierCreate, btn_suplierRepair,
            btn_staffCreate, btn_staffRepair, btn_staffChangepass, btn_customerCreate, btn_customerRepair, btn_meChangepass, btn_meReport, btn_BillUpdate,
            btn_roleFunctionShowall, btn_roleAddFunction, btn_roleRemoveFunction, btn_roleCreate, btn_roleRepair, btn_meRole};
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

        JButton[] btnSuccess = {btn_billCreate, btn_meSave};
        for (JButton btn : btnSuccess) {
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
                    btn.setBackground(new Color(110, 203, 99));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    btn.setBackground(new Color(234, 234, 234));
                }
            });
        }

        JButton[] btnCancel = {btn_billCancel, btn_productDelete, btn_suplierDelete, btn_staffDelete, btn_customerDelete, btn_meCancel,
            btn_roleDelete};
        for (JButton btn : btnCancel) {
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
                    btn.setBackground(new Color(189, 22, 22));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    btn.setBackground(new Color(234, 234, 234));
                }
            });
        }
        //User USER = Login.ID;

        load_billListProduct();

        setDefaultUserInfo();

        //bill.setId(USER.getId());
        loadListProduct();
        loadCBSuplierProduct();
        loadListSuplier();
        loadListStaff();
        loadSTTStaff();
        loadCBRoleStaff();
        load_homeListProduct();
        load_recentBill();
        load_outOutStock();
        loadListRole();
        loadListFunction(listFunction);

    }
    private final static List<BillInfo> lstProductInCart = new ArrayList<>();
    public static User USER = Login.USER;
    private static List<User> listUser = UserDAO.getAll();
    private static List<Role> listRole = RoleDAO.getAllBySTT(true);
    private static List<Function> listFunction = RoleDAO.getListFunction();
    private static List<Function> listFunctionChange = RoleDAO.getListFunction();
    public static List<RoleFunction> listFunctionUser = RoleDAO.getListRoleFunction(USER.getRoleID());
    private static List<String> listRoleFunc = new ArrayList<>();
    private static List<Suplier> listSuplier = SuplierDAO.getAll();
    private static List<Product> listProduct = ProductDAO.getAll();
    private static NumberFormat currency = Currency.toCurrency();
    private static int soluonggoc = 0;

    // Switch panel
    private void switchPanels(JPanel panelTop, JPanel panelBot) {
        layeredPaneTop.removeAll();
        layeredPaneBot.removeAll();

        layeredPaneTop.add(panelTop);
        layeredPaneBot.add(panelBot);

        layeredPaneTop.repaint();
        layeredPaneBot.repaint();

        layeredPaneTop.revalidate();
        layeredPaneBot.revalidate();
    }

    private void setDefaultUserInfo() throws SQLException {
        txt_meUsername.setText(USER.getUserName());
        txt_meName.setText(USER.getName());
        txt_mePhone.setText(USER.getPhone());
        txt_meAddress.setText(USER.getAddress());
        txt_meEmail.setText(USER.getEmail());
        String roleName = RoleDAO.getRoleName(USER.getRoleID());
        txt_meRole.setText(roleName);
    }

    private void loadListRole() {
        DefaultListModel dm = new DefaultListModel();
        for (int i = 0; i < listRole.size(); i++) {
            if (listRole.get(i).issTT()) {
                String roleName = listRole.get(i).getRoleName();
                dm.addElement(roleName);
            }
        }
        lst_roleRoleName.setModel(dm);
    }

    private void loadListRoleFunction(List<String> lstRoleFunc) {
        lst_roleRoleFunction.removeAll();
        DefaultListModel dm = new DefaultListModel();
        for (int i = 0; i < lstRoleFunc.size(); i++) {
            dm.addElement(lstRoleFunc.get(i));
        }

        lst_roleRoleFunction.setModel(dm);
    }

    private void loadListFunction(List<Function> lstFunc) {
        //lst_roleFuction.removeAll();
        DefaultListModel dm = new DefaultListModel();
        for (int i = 0; i < lstFunc.size(); i++) {
            if (lstFunc.get(i).isSTT()) {
                String funcName = lstFunc.get(i).getFunctionName();
                dm.addElement(funcName);
            }
        }
        lst_roleFuction.setModel(dm);
    }

    private void loadListStaff() throws SQLException {
        DefaultTableModel model = (DefaultTableModel) tbl_staffLstStaff.getModel();
        model.setRowCount(0);
        Object[] row = new Object[9];
        for (int i = 0; i < listUser.size(); i++) {
            row[0] = i + 1;
            row[1] = listUser.get(i).getId();
            row[2] = listUser.get(i).getUserName();
            row[3] = listUser.get(i).getName();
            row[4] = listUser.get(i).getPhone();
            row[5] = listUser.get(i).getAddress();
            row[6] = listUser.get(i).getEmail();
            String roleName = RoleDAO.getRoleName(listUser.get(i).getRoleID());
            row[7] = roleName;
            if (listUser.get(i).issTT() == true) {
                row[8] = "Active";
            } else {
                row[8] = "In Active";
            }
            model.addRow(row);
        }
    }

    private void loadCBRoleStaff() throws SQLException {
        //List<Role> listRole = RoleDAO.getAll();
        for (int i = 0; i < listRole.size(); i++) {
            cb_staffRole.addItem(listRole.get(i).getRoleName());
        }

        String roleName = RoleDAO.getRoleName(USER.getRoleID());
        cb_staffRole.setSelectedItem(roleName);
    }

    private void loadSTTStaff() {
        //User user = UserController.getUser(userID);
        ckb_staffSTT.setSelected(USER.issTT());
    }

    private void loadListSuplier() {
        DefaultTableModel model = (DefaultTableModel) tbl_suplierList.getModel();
        model.setRowCount(0);
        Object[] row = new Object[7];
        for (int i = 0; i < listSuplier.size(); i++) {
            row[0] = i + 1;
            row[1] = listSuplier.get(i).getId();
            row[2] = listSuplier.get(i).getSuplierName();
            row[3] = listSuplier.get(i).getAddress();
            row[4] = listSuplier.get(i).getPhone();
            row[5] = listSuplier.get(i).getEmail();
            if (listSuplier.get(i).issTT()) {
                row[6] = "Active";
            } else {
                row[6] = "In Active";
            }

            model.addRow(row);
        }
    }

    private void loadListProduct() {
        DefaultTableModel model = (DefaultTableModel) tbl_productList.getModel();
        model.setRowCount(0);
        Object[] row = new Object[7];
        for (int i = 0; i < listProduct.size(); i++) {
            row[0] = i + 1;
            row[1] = listProduct.get(i).getId();
            row[2] = listProduct.get(i).getProductName();
            double price = listProduct.get(i).getPrice();
            row[3] = currency.format(price);
            row[4] = listProduct.get(i).getsL();
            String suplierName = SuplierDAO.getName(listProduct.get(i).getIdSuplier());
            row[5] = suplierName;
            if (listProduct.get(i).issTT()) {
                row[6] = "Active";
            } else {
                row[6] = "In Active";
            }
            model.addRow(row);
        }
    }

    private void load_billListProduct() {
        DefaultTableModel model = (DefaultTableModel) tbl_billProduct.getModel();
        model.setRowCount(0);
        Object[] row = new Object[5];
        for (int i = 0; i < listProduct.size(); i++) {
            if (listProduct.get(i).issTT()) {
                row[0] = i + 1;
                row[1] = listProduct.get(i).getId();
                row[2] = listProduct.get(i).getProductName();
                double price = listProduct.get(i).getPrice();
                row[3] = currency.format(price);
                row[4] = listProduct.get(i).getsL();
                model.addRow(row);
            }
        }
    }

    private void load_homeListProduct() {
        DefaultTableModel model = (DefaultTableModel) tbl_homeListProduct.getModel();
        model.setRowCount(0);
        Object[] row = new Object[5];
        for (int i = 0; i < listProduct.size(); i++) {
            if (listProduct.get(i).issTT()) {
                row[0] = i + 1;
                row[1] = listProduct.get(i).getId();
                row[2] = listProduct.get(i).getProductName();
                row[3] = listProduct.get(i).getsL();
                double price = listProduct.get(i).getPrice();
                row[4] = currency.format(price);
                model.addRow(row);
            }
        }
    }

    private void loadCBSuplierProduct() {
        cb_productSuplier.removeAllItems();
        for (int i = 0; i < listSuplier.size(); i++) {
            if (listSuplier.get(i).issTT()) {
                cb_productSuplier.addItem(listSuplier.get(i).getSuplierName());
            }
        }
        //String suplierName = SuplierController.getName(WIDTH)
    }

    private void loadListProductInCart() {
        DefaultTableModel model = (DefaultTableModel) tbl_billListItem.getModel();
        model.setRowCount(0);
        Object[] row = new Object[5];
        for (int i = 0; i < lstProductInCart.size(); i++) {
            row[0] = i + 1;
            row[1] = ProductDAO.getName(lstProductInCart.get(i).getIdProduct());
            row[2] = lstProductInCart.get(i).getsL();

            double price = lstProductInCart.get(i).getPrice();
            row[3] = currency.format(price);
            //double priceEntity = lstBillInfos.get(i).getPrice();
            //int quantityEntity = lstBillInfos.get(i).getsL();
            double total = lstProductInCart.get(i).getTotal();
            //currency = toCurrency(total);
            row[4] = currency.format(total);

            model.addRow(row);
        }
    }

    private void load_recentBill() throws SQLException {
        List<Bill> lstBills = BillDAO.getAllDESC();
        DefaultTableModel model = (DefaultTableModel) tbl_recentbill.getModel();
        model.setRowCount(0);
        Object[] row = new Object[3];
        for (int i = 0; i < lstBills.size(); i++) {
            row[0] = lstBills.get(i).getDateCreate();
            double price = BillDAO.getPrice(lstBills.get(i).getId());
            row[1] = currency.format(price);
            row[2] = UserDAO.getUser(lstBills.get(i).getIdUser()).getName();
            model.addRow(row);
        }
    }

    private void load_outOutStock() {
        List<Product> lstProducts = ProductDAO.getListOutStock();
        DefaultTableModel model = (DefaultTableModel) tbl_ListProductOutOfStock.getModel();
        model.setRowCount(0);
        Object[] row = new Object[3];
        for (int i = 0; i < lstProducts.size(); i++) {
            row[0] = i + 1;
            row[1] = lstProducts.get(i).getProductName();
            row[2] = lstProducts.get(i).getsL();

            model.addRow(row);
        }
    }

    private void searchHome(String value) {
        DefaultTableModel model = (DefaultTableModel) tbl_homeListProduct.getModel();
        TableRowSorter<TableModel> trs = new TableRowSorter<TableModel>(model);
        tbl_homeListProduct.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter(value.trim()));
    }

    private void searchBillProduct(String value) {
        DefaultTableModel model = (DefaultTableModel) tbl_billProduct.getModel();
        TableRowSorter<TableModel> trs = new TableRowSorter<TableModel>(model);
        tbl_billProduct.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter(value.trim()));
    }

    private void searchProduct(String value) {
        DefaultTableModel model = (DefaultTableModel) tbl_productList.getModel();
        TableRowSorter<TableModel> trs = new TableRowSorter<TableModel>(model);
        tbl_productList.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter(value.trim()));
    }

    private void searchSuplier(String value) {
        DefaultTableModel model = (DefaultTableModel) tbl_suplierList.getModel();
        TableRowSorter<TableModel> trs = new TableRowSorter<TableModel>(model);
        tbl_suplierList.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter(value.trim()));
    }

    private void searchCustomer(String value) {
        DefaultTableModel model = (DefaultTableModel) tbl_ListCustomer.getModel();
        TableRowSorter<TableModel> trs = new TableRowSorter<TableModel>(model);
        tbl_ListCustomer.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter(value.trim()));
    }

    private void searchStaff(String value) {
        DefaultTableModel model = (DefaultTableModel) tbl_staffLstStaff.getModel();
        TableRowSorter<TableModel> trs = new TableRowSorter<TableModel>(model);
        tbl_staffLstStaff.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter(value.trim()));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator15 = new javax.swing.JSeparator();
        pnlRoot = new javax.swing.JPanel();
        pnlSide = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btn_home = new javax.swing.JButton();
        btn_create = new javax.swing.JButton();
        btn_product = new javax.swing.JButton();
        btn_suplier = new javax.swing.JButton();
        btn_customer = new javax.swing.JButton();
        btn_staff = new javax.swing.JButton();
        btn_user = new javax.swing.JButton();
        btn_exit = new javax.swing.JButton();
        pnlCenter = new javax.swing.JPanel();
        pnlTop = new javax.swing.JPanel();
        layeredPaneTop = new javax.swing.JLayeredPane();
        pnlTopHome = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        pnlTopBill = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        pnlTopProduct = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        pnlTopCustomer = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        pnlTopStaff = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        pnlTopMe = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        pnlTopSuplier = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        pnlTopUserRole = new javax.swing.JPanel();
        jLabel85 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        pnlBottom = new javax.swing.JPanel();
        layeredPaneBot = new javax.swing.JLayeredPane();
        pnlBotHome = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        pnlBotHomeSearch = new javax.swing.JPanel();
        txt_Homesearchbox = new javax.swing.JTextField();
        jSeparator8 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_homeListProduct = new javax.swing.JTable();
        jLabel23 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_recentbill = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        txt_homeProductID = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txt_homeProductName = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        txt_homeProductPrice = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txt_homeProductQuantity = new javax.swing.JTextField();
        jSeparator18 = new javax.swing.JSeparator();
        jSeparator19 = new javax.swing.JSeparator();
        jSeparator20 = new javax.swing.JSeparator();
        jSeparator21 = new javax.swing.JSeparator();
        jLabel28 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_ListProductOutOfStock = new javax.swing.JTable();
        jLabel29 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        pnlBotBill = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel7 = new javax.swing.JPanel();
        txt_Botsearchbox = new javax.swing.JTextField();
        jSeparator9 = new javax.swing.JSeparator();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_billListItem = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        txt_billTotalPrice = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jSeparator17 = new javax.swing.JSeparator();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbl_billProduct = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        txt_billProductName = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        txt_billProductPrice = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        txt_billProductTotal = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        txt_billProductID = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        btn_BillPlus = new javax.swing.JButton();
        btn_BillSubtract = new javax.swing.JButton();
        jSeparator10 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        jSeparator12 = new javax.swing.JSeparator();
        jSeparator13 = new javax.swing.JSeparator();
        jSeparator14 = new javax.swing.JSeparator();
        btn_BillUpdate = new javax.swing.JButton();
        txt_billProductQuantity = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        btn_billCreate = new javax.swing.JButton();
        btn_billCancel = new javax.swing.JButton();
        pnlBotProduct = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbl_productList = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        txt_productID = new javax.swing.JTextField();
        jSeparator22 = new javax.swing.JSeparator();
        txt_productName = new javax.swing.JTextField();
        txt_productPrice = new javax.swing.JTextField();
        jSeparator23 = new javax.swing.JSeparator();
        jSeparator24 = new javax.swing.JSeparator();
        txt_productQuantity = new javax.swing.JTextField();
        jSeparator25 = new javax.swing.JSeparator();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        cb_productSuplier = new javax.swing.JComboBox<>();
        jLabel43 = new javax.swing.JLabel();
        ckb_productSTT = new javax.swing.JCheckBox();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jSeparator27 = new javax.swing.JSeparator();
        btn_productCreate = new javax.swing.JButton();
        btn_productRepair = new javax.swing.JButton();
        btn_productDelete = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        txt_productsearchbox = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jSeparator26 = new javax.swing.JSeparator();
        pnlBotSuplier = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbl_suplierList = new javax.swing.JTable();
        jPanel15 = new javax.swing.JPanel();
        jLabel47 = new javax.swing.JLabel();
        txt_suplierID = new javax.swing.JTextField();
        jSeparator28 = new javax.swing.JSeparator();
        txt_suplierName = new javax.swing.JTextField();
        txt_suplierAddress = new javax.swing.JTextField();
        jSeparator29 = new javax.swing.JSeparator();
        jSeparator30 = new javax.swing.JSeparator();
        txt_suplierPhone = new javax.swing.JTextField();
        jSeparator31 = new javax.swing.JSeparator();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        ckb_suplierSTT = new javax.swing.JCheckBox();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jSeparator32 = new javax.swing.JSeparator();
        btn_suplierCreate = new javax.swing.JButton();
        btn_suplierRepair = new javax.swing.JButton();
        btn_suplierDelete = new javax.swing.JButton();
        txt_suplierEmail = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel17 = new javax.swing.JPanel();
        txt_suplierSearchbox = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jSeparator33 = new javax.swing.JSeparator();
        pnlBotCustomer = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tbl_ListCustomer = new javax.swing.JTable();
        jPanel19 = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        jTextField20 = new javax.swing.JTextField();
        jSeparator34 = new javax.swing.JSeparator();
        jTextField21 = new javax.swing.JTextField();
        jTextField22 = new javax.swing.JTextField();
        jSeparator35 = new javax.swing.JSeparator();
        jSeparator36 = new javax.swing.JSeparator();
        jTextField23 = new javax.swing.JTextField();
        jSeparator37 = new javax.swing.JSeparator();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jCheckBox3 = new javax.swing.JCheckBox();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jSeparator38 = new javax.swing.JSeparator();
        btn_customerCreate = new javax.swing.JButton();
        btn_customerRepair = new javax.swing.JButton();
        btn_customerDelete = new javax.swing.JButton();
        jTextField24 = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jPanel21 = new javax.swing.JPanel();
        txt_customerSearchbox = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jSeparator39 = new javax.swing.JSeparator();
        pnlBotStaff = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tbl_staffLstStaff = new javax.swing.JTable();
        txt_staffID = new javax.swing.JPanel();
        jLabel63 = new javax.swing.JLabel();
        txt_staffID_real = new javax.swing.JTextField();
        jSeparator40 = new javax.swing.JSeparator();
        txt_staffUserName = new javax.swing.JTextField();
        txt_staffName = new javax.swing.JTextField();
        jSeparator41 = new javax.swing.JSeparator();
        jSeparator42 = new javax.swing.JSeparator();
        txt_staffPhone = new javax.swing.JTextField();
        jSeparator43 = new javax.swing.JSeparator();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        ckb_staffSTT = new javax.swing.JCheckBox();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jSeparator44 = new javax.swing.JSeparator();
        btn_staffCreate = new javax.swing.JButton();
        btn_staffRepair = new javax.swing.JButton();
        btn_staffDelete = new javax.swing.JButton();
        txt_staffAddress = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel71 = new javax.swing.JLabel();
        txt_staffEmail = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel72 = new javax.swing.JLabel();
        cb_staffRole = new javax.swing.JComboBox<>();
        btn_staffChangepass = new javax.swing.JButton();
        jPanel25 = new javax.swing.JPanel();
        txt_staffSearchbox = new javax.swing.JTextField();
        jLabel70 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        jSeparator45 = new javax.swing.JSeparator();
        pnlBotMe = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel28 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txt_meUsername = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txt_meName = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator16 = new javax.swing.JSeparator();
        jLabel20 = new javax.swing.JLabel();
        txt_mePhone = new javax.swing.JTextField();
        jSeparator46 = new javax.swing.JSeparator();
        jLabel73 = new javax.swing.JLabel();
        txt_meAddress = new javax.swing.JTextField();
        jSeparator47 = new javax.swing.JSeparator();
        txt_meEmail = new javax.swing.JTextField();
        jLabel74 = new javax.swing.JLabel();
        jSeparator48 = new javax.swing.JSeparator();
        jLabel75 = new javax.swing.JLabel();
        txt_meRole = new javax.swing.JTextField();
        jSeparator49 = new javax.swing.JSeparator();
        btn_meSave = new javax.swing.JButton();
        btn_meCancel = new javax.swing.JButton();
        btn_meChangepass = new javax.swing.JButton();
        jSeparator50 = new javax.swing.JSeparator();
        jPanel29 = new javax.swing.JPanel();
        btn_meReport = new javax.swing.JButton();
        jLabel76 = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        btn_meRole = new javax.swing.JButton();
        pnlBotUserRole = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        lst_roleRoleName = new javax.swing.JList<>();
        jScrollPane11 = new javax.swing.JScrollPane();
        lst_roleRoleFunction = new javax.swing.JList<>();
        jScrollPane12 = new javax.swing.JScrollPane();
        lst_roleFuction = new javax.swing.JList<>();
        jLabel77 = new javax.swing.JLabel();
        txt_roleRolename = new javax.swing.JTextField();
        btn_roleCreate = new javax.swing.JButton();
        btn_roleRepair = new javax.swing.JButton();
        btn_roleDelete = new javax.swing.JButton();
        jSeparator51 = new javax.swing.JSeparator();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        btn_roleFunctionShowall = new javax.swing.JButton();
        btn_roleAddFunction = new javax.swing.JButton();
        btn_roleRemoveFunction = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jLabel4.setText("jLabel4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1100, 700));
        setResizable(false);

        pnlRoot.setLayout(new java.awt.BorderLayout());

        pnlSide.setBackground(new java.awt.Color(51, 66, 87));
        pnlSide.setPreferredSize(new java.awt.Dimension(130, 0));

        jPanel1.setBackground(new java.awt.Color(51, 66, 87));
        jPanel1.setPreferredSize(new java.awt.Dimension(120, 100));

        jLabel1.setBackground(new java.awt.Color(71, 96, 114));
        jLabel1.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(238, 238, 238));
        jLabel1.setText("Sor Market");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(35, 35, 35))
        );

        pnlSide.add(jPanel1);

        btn_home.setBackground(new java.awt.Color(84, 140, 168));
        btn_home.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btn_home.setForeground(new java.awt.Color(238, 238, 238));
        btn_home.setIcon(new javax.swing.ImageIcon("D:\\1.Sor\\IT\\Study\\Java\\Project\\ProjectJava\\src\\main\\java\\com\\mycompany\\projectjava\\icon\\icon_home.png")); // NOI18N
        btn_home.setText("HOME");
        btn_home.setBorder(null);
        btn_home.setBorderPainted(false);
        btn_home.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btn_home.setPreferredSize(new java.awt.Dimension(130, 60));
        btn_home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_homeMouseClicked(evt);
            }
        });
        pnlSide.add(btn_home);

        btn_create.setBackground(new java.awt.Color(84, 140, 168));
        btn_create.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btn_create.setForeground(new java.awt.Color(238, 238, 238));
        btn_create.setIcon(new javax.swing.ImageIcon("D:\\1.Sor\\IT\\Study\\Java\\Project\\ProjectJava\\src\\main\\java\\com\\mycompany\\projectjava\\icon\\icon_create.png")); // NOI18N
        btn_create.setText("BILL");
        btn_create.setBorder(null);
        btn_create.setBorderPainted(false);
        btn_create.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btn_create.setPreferredSize(new java.awt.Dimension(130, 60));
        btn_create.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_createMouseClicked(evt);
            }
        });
        pnlSide.add(btn_create);

        btn_product.setBackground(new java.awt.Color(84, 140, 168));
        btn_product.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btn_product.setForeground(new java.awt.Color(238, 238, 238));
        btn_product.setIcon(new javax.swing.ImageIcon("D:\\1.Sor\\IT\\Study\\Java\\Project\\ProjectJava\\src\\main\\java\\com\\mycompany\\projectjava\\icon\\icon_goods.png")); // NOI18N
        btn_product.setText("PRODUCT");
        btn_product.setBorder(null);
        btn_product.setBorderPainted(false);
        btn_product.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btn_product.setPreferredSize(new java.awt.Dimension(130, 60));
        btn_product.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_productMouseClicked(evt);
            }
        });
        pnlSide.add(btn_product);

        btn_suplier.setBackground(new java.awt.Color(84, 140, 168));
        btn_suplier.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btn_suplier.setForeground(new java.awt.Color(238, 238, 238));
        btn_suplier.setIcon(new javax.swing.ImageIcon("D:\\1.Sor\\IT\\Study\\Java\\Project\\ProjectJava\\src\\main\\java\\com\\mycompany\\projectjava\\icon\\icon_suplier.png")); // NOI18N
        btn_suplier.setText("SUPLIER");
        btn_suplier.setBorder(null);
        btn_suplier.setBorderPainted(false);
        btn_suplier.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btn_suplier.setPreferredSize(new java.awt.Dimension(130, 60));
        btn_suplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_suplierMouseClicked(evt);
            }
        });
        pnlSide.add(btn_suplier);

        btn_customer.setBackground(new java.awt.Color(84, 140, 168));
        btn_customer.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        btn_customer.setForeground(new java.awt.Color(238, 238, 238));
        btn_customer.setIcon(new javax.swing.ImageIcon("D:\\1.Sor\\IT\\Study\\Java\\Project\\ProjectJava\\src\\main\\java\\com\\mycompany\\projectjava\\icon\\icon_customer.png")); // NOI18N
        btn_customer.setText("CUSTOMER");
        btn_customer.setBorder(null);
        btn_customer.setBorderPainted(false);
        btn_customer.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btn_customer.setPreferredSize(new java.awt.Dimension(130, 60));
        btn_customer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_customerMouseClicked(evt);
            }
        });
        pnlSide.add(btn_customer);

        btn_staff.setBackground(new java.awt.Color(84, 140, 168));
        btn_staff.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btn_staff.setForeground(new java.awt.Color(238, 238, 238));
        btn_staff.setIcon(new javax.swing.ImageIcon("D:\\1.Sor\\IT\\Study\\Java\\Project\\ProjectJava\\src\\main\\java\\com\\mycompany\\projectjava\\icon\\icon_staff.png")); // NOI18N
        btn_staff.setText("STAFF");
        btn_staff.setBorder(null);
        btn_staff.setBorderPainted(false);
        btn_staff.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btn_staff.setPreferredSize(new java.awt.Dimension(130, 60));
        btn_staff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_staffMouseClicked(evt);
            }
        });
        pnlSide.add(btn_staff);

        btn_user.setBackground(new java.awt.Color(84, 140, 168));
        btn_user.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btn_user.setForeground(new java.awt.Color(238, 238, 238));
        btn_user.setIcon(new javax.swing.ImageIcon("D:\\1.Sor\\IT\\Study\\Java\\Project\\ProjectJava\\src\\main\\java\\com\\mycompany\\projectjava\\icon\\icon_user.png")); // NOI18N
        btn_user.setText("ME");
        btn_user.setBorder(null);
        btn_user.setBorderPainted(false);
        btn_user.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btn_user.setPreferredSize(new java.awt.Dimension(130, 60));
        btn_user.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_userMouseClicked(evt);
            }
        });
        pnlSide.add(btn_user);

        btn_exit.setBackground(new java.awt.Color(84, 140, 168));
        btn_exit.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btn_exit.setForeground(new java.awt.Color(238, 238, 238));
        btn_exit.setIcon(new javax.swing.ImageIcon("D:\\1.Sor\\IT\\Study\\Java\\Project\\ProjectJava\\src\\main\\java\\com\\mycompany\\projectjava\\icon\\icon_exit.png")); // NOI18N
        btn_exit.setText("EXIT");
        btn_exit.setBorder(null);
        btn_exit.setBorderPainted(false);
        btn_exit.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btn_exit.setPreferredSize(new java.awt.Dimension(130, 60));
        btn_exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_exitMouseClicked(evt);
            }
        });
        pnlSide.add(btn_exit);

        pnlRoot.add(pnlSide, java.awt.BorderLayout.WEST);

        pnlCenter.setBackground(new java.awt.Color(71, 96, 114));
        pnlCenter.setPreferredSize(new java.awt.Dimension(970, 700));
        pnlCenter.setLayout(new java.awt.BorderLayout());

        pnlTop.setBackground(new java.awt.Color(71, 96, 114));
        pnlTop.setPreferredSize(new java.awt.Dimension(970, 110));
        pnlTop.setLayout(new java.awt.BorderLayout());

        layeredPaneTop.setLayout(new java.awt.CardLayout());

        pnlTopHome.setBackground(new java.awt.Color(71, 96, 114));

        jLabel2.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(238, 238, 238));
        jLabel2.setText("HOME");

        jLabel3.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(238, 238, 238));
        jLabel3.setText("Statistics data today");

        javax.swing.GroupLayout pnlTopHomeLayout = new javax.swing.GroupLayout(pnlTopHome);
        pnlTopHome.setLayout(pnlTopHomeLayout);
        pnlTopHomeLayout.setHorizontalGroup(
            pnlTopHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTopHomeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTopHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addContainerGap(819, Short.MAX_VALUE))
        );
        pnlTopHomeLayout.setVerticalGroup(
            pnlTopHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTopHomeLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layeredPaneTop.add(pnlTopHome, "card2");

        pnlTopBill.setBackground(new java.awt.Color(71, 96, 114));

        jLabel5.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(238, 238, 238));
        jLabel5.setText("BILL");

        jLabel6.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(238, 238, 238));
        jLabel6.setText("Create bill");

        javax.swing.GroupLayout pnlTopBillLayout = new javax.swing.GroupLayout(pnlTopBill);
        pnlTopBill.setLayout(pnlTopBillLayout);
        pnlTopBillLayout.setHorizontalGroup(
            pnlTopBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTopBillLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTopBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addContainerGap(888, Short.MAX_VALUE))
        );
        pnlTopBillLayout.setVerticalGroup(
            pnlTopBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTopBillLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layeredPaneTop.add(pnlTopBill, "card3");

        pnlTopProduct.setBackground(new java.awt.Color(71, 96, 114));

        jLabel7.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(238, 238, 238));
        jLabel7.setText("PRODUCT");

        jLabel8.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(238, 238, 238));
        jLabel8.setText("All product in your store");

        javax.swing.GroupLayout pnlTopProductLayout = new javax.swing.GroupLayout(pnlTopProduct);
        pnlTopProduct.setLayout(pnlTopProductLayout);
        pnlTopProductLayout.setHorizontalGroup(
            pnlTopProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTopProductLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTopProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addContainerGap(795, Short.MAX_VALUE))
        );
        pnlTopProductLayout.setVerticalGroup(
            pnlTopProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTopProductLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layeredPaneTop.add(pnlTopProduct, "card4");

        pnlTopCustomer.setBackground(new java.awt.Color(71, 96, 114));

        jLabel11.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 36)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(238, 238, 238));
        jLabel11.setText("CUSTOMER");

        jLabel12.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(238, 238, 238));
        jLabel12.setText("List customer in your store ");

        javax.swing.GroupLayout pnlTopCustomerLayout = new javax.swing.GroupLayout(pnlTopCustomer);
        pnlTopCustomer.setLayout(pnlTopCustomerLayout);
        pnlTopCustomerLayout.setHorizontalGroup(
            pnlTopCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTopCustomerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTopCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addContainerGap(777, Short.MAX_VALUE))
        );
        pnlTopCustomerLayout.setVerticalGroup(
            pnlTopCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTopCustomerLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layeredPaneTop.add(pnlTopCustomer, "card5");

        pnlTopStaff.setBackground(new java.awt.Color(71, 96, 114));

        jLabel13.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 36)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(238, 238, 238));
        jLabel13.setText("STAFF");

        jLabel14.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(238, 238, 238));
        jLabel14.setText("List store staff");

        javax.swing.GroupLayout pnlTopStaffLayout = new javax.swing.GroupLayout(pnlTopStaff);
        pnlTopStaff.setLayout(pnlTopStaffLayout);
        pnlTopStaffLayout.setHorizontalGroup(
            pnlTopStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTopStaffLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTopStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addContainerGap(863, Short.MAX_VALUE))
        );
        pnlTopStaffLayout.setVerticalGroup(
            pnlTopStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTopStaffLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layeredPaneTop.add(pnlTopStaff, "card6");

        pnlTopMe.setBackground(new java.awt.Color(71, 96, 114));

        jLabel15.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 36)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(238, 238, 238));
        jLabel15.setText("ME");

        jLabel16.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(238, 238, 238));
        jLabel16.setText("Check and change your information");

        javax.swing.GroupLayout pnlTopMeLayout = new javax.swing.GroupLayout(pnlTopMe);
        pnlTopMe.setLayout(pnlTopMeLayout);
        pnlTopMeLayout.setHorizontalGroup(
            pnlTopMeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTopMeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTopMeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16))
                .addContainerGap(715, Short.MAX_VALUE))
        );
        pnlTopMeLayout.setVerticalGroup(
            pnlTopMeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTopMeLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layeredPaneTop.add(pnlTopMe, "card7");

        pnlTopSuplier.setBackground(new java.awt.Color(71, 96, 114));

        jLabel17.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 36)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(238, 238, 238));
        jLabel17.setText("SUPLIER");

        jLabel18.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(238, 238, 238));
        jLabel18.setText("List suplier associated ");

        javax.swing.GroupLayout pnlTopSuplierLayout = new javax.swing.GroupLayout(pnlTopSuplier);
        pnlTopSuplier.setLayout(pnlTopSuplierLayout);
        pnlTopSuplierLayout.setHorizontalGroup(
            pnlTopSuplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTopSuplierLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTopSuplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18))
                .addContainerGap(803, Short.MAX_VALUE))
        );
        pnlTopSuplierLayout.setVerticalGroup(
            pnlTopSuplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTopSuplierLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layeredPaneTop.add(pnlTopSuplier, "card8");

        pnlTopUserRole.setBackground(new java.awt.Color(71, 96, 114));

        jLabel85.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 36)); // NOI18N
        jLabel85.setForeground(new java.awt.Color(238, 238, 238));
        jLabel85.setText("ROLE");

        jLabel86.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 18)); // NOI18N
        jLabel86.setForeground(new java.awt.Color(238, 238, 238));
        jLabel86.setText("Access Permissions");

        javax.swing.GroupLayout pnlTopUserRoleLayout = new javax.swing.GroupLayout(pnlTopUserRole);
        pnlTopUserRole.setLayout(pnlTopUserRoleLayout);
        pnlTopUserRoleLayout.setHorizontalGroup(
            pnlTopUserRoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTopUserRoleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTopUserRoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel85)
                    .addComponent(jLabel86))
                .addContainerGap(828, Short.MAX_VALUE))
        );
        pnlTopUserRoleLayout.setVerticalGroup(
            pnlTopUserRoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTopUserRoleLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel85)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel86)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layeredPaneTop.add(pnlTopUserRole, "card8");

        pnlTop.add(layeredPaneTop, java.awt.BorderLayout.CENTER);

        pnlCenter.add(pnlTop, java.awt.BorderLayout.NORTH);

        pnlBottom.setBackground(new java.awt.Color(71, 96, 114));
        pnlBottom.setPreferredSize(new java.awt.Dimension(970, 588));
        pnlBottom.setLayout(new java.awt.BorderLayout());

        layeredPaneBot.setLayout(new java.awt.CardLayout());

        pnlBotHome.setBackground(new java.awt.Color(238, 238, 238));

        pnlBotHomeSearch.setBackground(new java.awt.Color(238, 238, 238));
        pnlBotHomeSearch.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_Homesearchbox.setBackground(new java.awt.Color(238, 238, 238));
        txt_Homesearchbox.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txt_Homesearchbox.setForeground(new java.awt.Color(153, 153, 153));
        txt_Homesearchbox.setText("Search...");
        txt_Homesearchbox.setBorder(null);
        txt_Homesearchbox.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_HomesearchboxFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_HomesearchboxFocusLost(evt);
            }
        });
        txt_Homesearchbox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_HomesearchboxKeyReleased(evt);
            }
        });
        pnlBotHomeSearch.add(txt_Homesearchbox, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 263, -1));
        pnlBotHomeSearch.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 39, 263, 10));

        tbl_homeListProduct.setBackground(new java.awt.Color(71, 96, 114));
        tbl_homeListProduct.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tbl_homeListProduct.setForeground(new java.awt.Color(238, 238, 238));
        tbl_homeListProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "ID", "Product name", "Quantity", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_homeListProduct.setColumnSelectionAllowed(true);
        tbl_homeListProduct.setFocusable(false);
        tbl_homeListProduct.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tbl_homeListProduct.setRowHeight(25);
        tbl_homeListProduct.setSelectionBackground(new java.awt.Color(84, 140, 168));
        tbl_homeListProduct.setShowVerticalLines(false);
        tbl_homeListProduct.getTableHeader().setReorderingAllowed(false);
        tbl_homeListProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_homeListProductMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_homeListProduct);
        tbl_homeListProduct.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jLabel23.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(51, 66, 87));
        jLabel23.setText("Product Information");

        jPanel2.setBackground(new java.awt.Color(84, 140, 168));
        jPanel2.setPreferredSize(new java.awt.Dimension(140, 5));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        tbl_recentbill.setBackground(new java.awt.Color(71, 96, 114));
        tbl_recentbill.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tbl_recentbill.setForeground(new java.awt.Color(238, 238, 238));
        tbl_recentbill.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date create", "Price", "User create"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_recentbill.setRowHeight(25);
        tbl_recentbill.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tbl_recentbill);

        jLabel24.setFont(new java.awt.Font("Arial", 1, 17)); // NOI18N
        jLabel24.setText("ID:");

        txt_homeProductID.setEditable(false);
        txt_homeProductID.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        txt_homeProductID.setBorder(null);

        jLabel25.setFont(new java.awt.Font("Arial", 1, 17)); // NOI18N
        jLabel25.setText("Name:");

        txt_homeProductName.setEditable(false);
        txt_homeProductName.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        txt_homeProductName.setBorder(null);

        jLabel26.setFont(new java.awt.Font("Arial", 1, 17)); // NOI18N
        jLabel26.setText("Price:");

        txt_homeProductPrice.setEditable(false);
        txt_homeProductPrice.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        txt_homeProductPrice.setText("0");
        txt_homeProductPrice.setBorder(null);

        jLabel27.setFont(new java.awt.Font("Arial", 1, 17)); // NOI18N
        jLabel27.setText("SL:");

        txt_homeProductQuantity.setEditable(false);
        txt_homeProductQuantity.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        txt_homeProductQuantity.setText("0");
        txt_homeProductQuantity.setBorder(null);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25)
                    .addComponent(jLabel24)
                    .addComponent(jLabel26)
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator21)
                    .addComponent(txt_homeProductQuantity, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                    .addComponent(txt_homeProductID)
                    .addComponent(txt_homeProductName)
                    .addComponent(txt_homeProductPrice, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator18)
                    .addComponent(jSeparator19)
                    .addComponent(jSeparator20))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(txt_homeProductID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator18, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(txt_homeProductName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator19, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_homeProductPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator20, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_homeProductQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator21)
                .addContainerGap())
        );

        jLabel28.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(51, 66, 87));
        jLabel28.setText("Recently bill");

        jPanel5.setBackground(new java.awt.Color(84, 140, 168));
        jPanel5.setPreferredSize(new java.awt.Dimension(100, 5));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 91, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        tbl_ListProductOutOfStock.setBackground(new java.awt.Color(71, 96, 114));
        tbl_ListProductOutOfStock.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tbl_ListProductOutOfStock.setForeground(new java.awt.Color(238, 238, 238));
        tbl_ListProductOutOfStock.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Product name", "SL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_ListProductOutOfStock.setRowHeight(25);
        tbl_ListProductOutOfStock.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tbl_ListProductOutOfStock);

        jLabel29.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(51, 66, 87));
        jLabel29.setText("Quantity");

        jPanel6.setBackground(new java.awt.Color(84, 140, 168));
        jPanel6.setPreferredSize(new java.awt.Dimension(140, 5));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 82, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlBotHomeLayout = new javax.swing.GroupLayout(pnlBotHome);
        pnlBotHome.setLayout(pnlBotHomeLayout);
        pnlBotHomeLayout.setHorizontalGroup(
            pnlBotHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(pnlBotHomeLayout.createSequentialGroup()
                .addComponent(pnlBotHomeSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlBotHomeLayout.createSequentialGroup()
                .addGroup(pnlBotHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBotHomeLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlBotHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlBotHomeLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel23))
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBotHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlBotHomeLayout.createSequentialGroup()
                        .addGroup(pnlBotHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel28)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(233, 233, 233)
                        .addGroup(pnlBotHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlBotHomeLayout.createSequentialGroup()
                                .addComponent(jLabel29)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pnlBotHomeLayout.createSequentialGroup()
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 225, Short.MAX_VALUE))))
                    .addGroup(pnlBotHomeLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE))))
        );
        pnlBotHomeLayout.setVerticalGroup(
            pnlBotHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBotHomeLayout.createSequentialGroup()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlBotHomeSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBotHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBotHomeLayout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlBotHomeLayout.createSequentialGroup()
                        .addGroup(pnlBotHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlBotHomeLayout.createSequentialGroup()
                                .addGroup(pnlBotHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel28)
                                    .addComponent(jLabel29))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlBotHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE)
                            .addComponent(jScrollPane1))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layeredPaneBot.add(pnlBotHome, "card2");

        pnlBotBill.setBackground(new java.awt.Color(238, 238, 238));
        pnlBotBill.setPreferredSize(new java.awt.Dimension(970, 588));

        txt_Botsearchbox.setBackground(new java.awt.Color(238, 238, 238));
        txt_Botsearchbox.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txt_Botsearchbox.setForeground(new java.awt.Color(153, 153, 153));
        txt_Botsearchbox.setText("Search...");
        txt_Botsearchbox.setBorder(null);
        txt_Botsearchbox.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_BotsearchboxFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_BotsearchboxFocusLost(evt);
            }
        });
        txt_Botsearchbox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_BotsearchboxKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jSeparator9)
                    .addComponent(txt_Botsearchbox, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(txt_Botsearchbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tbl_billListItem.setBackground(new java.awt.Color(71, 96, 114));
        tbl_billListItem.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tbl_billListItem.setForeground(new java.awt.Color(238, 238, 238));
        tbl_billListItem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Product name", "SL", "Price", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_billListItem.setRowHeight(25);
        tbl_billListItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_billListItemMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbl_billListItem);

        jLabel22.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel22.setText("Total:");

        txt_billTotalPrice.setEditable(false);
        txt_billTotalPrice.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        txt_billTotalPrice.setForeground(new java.awt.Color(189, 22, 22));
        txt_billTotalPrice.setText("0");
        txt_billTotalPrice.setBorder(null);

        jLabel30.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel30.setText("VNĐ");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator17)
                    .addComponent(txt_billTotalPrice, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(jLabel30)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(txt_billTotalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator17, javax.swing.GroupLayout.DEFAULT_SIZE, 10, Short.MAX_VALUE)
                .addContainerGap())
        );

        tbl_billProduct.setBackground(new java.awt.Color(71, 96, 114));
        tbl_billProduct.setForeground(new java.awt.Color(238, 238, 238));
        tbl_billProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "ID", "Product name", "Price", "SL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_billProduct.setRowHeight(25);
        tbl_billProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_billProductMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbl_billProduct);

        jLabel31.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel31.setText("Product: ");

        txt_billProductName.setEditable(false);
        txt_billProductName.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txt_billProductName.setBorder(null);

        jLabel32.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel32.setText("Price:");

        txt_billProductPrice.setEditable(false);
        txt_billProductPrice.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txt_billProductPrice.setText("0");
        txt_billProductPrice.setBorder(null);

        jLabel33.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel33.setText("VNĐ");

        jLabel34.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel34.setText("Quantity:");

        jLabel35.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel35.setText("Total:");

        txt_billProductTotal.setEditable(false);
        txt_billProductTotal.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txt_billProductTotal.setForeground(new java.awt.Color(189, 22, 22));
        txt_billProductTotal.setText("0");
        txt_billProductTotal.setBorder(null);

        jLabel36.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel36.setText("VNĐ");

        txt_billProductID.setEditable(false);
        txt_billProductID.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txt_billProductID.setBorder(null);

        jLabel37.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel37.setText("ID:");

        btn_BillPlus.setForeground(new java.awt.Color(102, 255, 51));
        btn_BillPlus.setIcon(new javax.swing.ImageIcon("D:\\1.Sor\\IT\\Study\\Java\\Project\\ProjectJava\\src\\main\\java\\com\\mycompany\\projectjava\\icon\\icon_plus_15.png")); // NOI18N
        btn_BillPlus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 140, 168), 2));
        btn_BillPlus.setPreferredSize(new java.awt.Dimension(30, 30));
        btn_BillPlus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_BillPlusMouseClicked(evt);
            }
        });

        btn_BillSubtract.setIcon(new javax.swing.ImageIcon("D:\\1.Sor\\IT\\Study\\Java\\Project\\ProjectJava\\src\\main\\java\\com\\mycompany\\projectjava\\icon\\icon_subtract_15.png")); // NOI18N
        btn_BillSubtract.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 140, 168), 2));
        btn_BillSubtract.setPreferredSize(new java.awt.Dimension(30, 30));
        btn_BillSubtract.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_BillSubtractMouseClicked(evt);
            }
        });

        btn_BillUpdate.setIcon(new javax.swing.ImageIcon("D:\\1.Sor\\IT\\Study\\Java\\Project\\ProjectJava\\src\\main\\java\\com\\mycompany\\projectjava\\icon\\icon_update_15.png")); // NOI18N
        btn_BillUpdate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 140, 168), 2));
        btn_BillUpdate.setPreferredSize(new java.awt.Dimension(30, 30));

        txt_billProductQuantity.setBackground(new java.awt.Color(238, 238, 238));
        txt_billProductQuantity.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txt_billProductQuantity.setText("0");
        txt_billProductQuantity.setBorder(null);
        txt_billProductQuantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_billProductQuantityKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_billProductQuantityKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31)
                    .addComponent(jLabel32)
                    .addComponent(jLabel35)
                    .addComponent(jLabel37))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_billProductName)
                    .addComponent(jSeparator11)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txt_billProductTotal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                                    .addComponent(txt_billProductPrice, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSeparator12)
                                    .addComponent(jSeparator13))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addComponent(jLabel33)
                                        .addGap(48, 48, 48)
                                        .addComponent(jLabel34))
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addComponent(jLabel36)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btn_BillPlus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addComponent(btn_BillSubtract, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btn_BillUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txt_billProductQuantity, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jSeparator14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)))
                                .addGap(4, 4, 4))
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jSeparator10, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txt_billProductID, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)))
                        .addGap(0, 32, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_billProductID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37))
                .addGap(1, 1, 1)
                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(txt_billProductName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel34)
                            .addComponent(txt_billProductQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_billProductPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel33)
                        .addComponent(jLabel32)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator14, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_billProductTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel35)
                            .addComponent(jLabel36))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator13, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btn_BillSubtract, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_BillPlus, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_BillUpdate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        jLabel38.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel38.setText("LIST PRODUCT");

        jPanel10.setBackground(new java.awt.Color(84, 140, 168));
        jPanel10.setPreferredSize(new java.awt.Dimension(100, 10));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 6, Short.MAX_VALUE)
        );

        btn_billCreate.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btn_billCreate.setIcon(new javax.swing.ImageIcon("D:\\1.Sor\\IT\\Study\\Java\\Project\\ProjectJava\\src\\main\\java\\com\\mycompany\\projectjava\\icon\\icon_success_15.png")); // NOI18N
        btn_billCreate.setText("Pay");
        btn_billCreate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(110, 203, 99), 2));
        btn_billCreate.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btn_billCreate.setPreferredSize(new java.awt.Dimension(63, 19));
        btn_billCreate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_billCreateMouseClicked(evt);
            }
        });

        btn_billCancel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btn_billCancel.setIcon(new javax.swing.ImageIcon("D:\\1.Sor\\IT\\Study\\Java\\Project\\ProjectJava\\src\\main\\java\\com\\mycompany\\projectjava\\icon\\icon_trash_15.png")); // NOI18N
        btn_billCancel.setText("Cancel");
        btn_billCancel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(189, 22, 22), 2));
        btn_billCancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_billCancelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlBotBillLayout = new javax.swing.GroupLayout(pnlBotBill);
        pnlBotBill.setLayout(pnlBotBillLayout);
        pnlBotBillLayout.setHorizontalGroup(
            pnlBotBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBotBillLayout.createSequentialGroup()
                .addGroup(pnlBotBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBotBillLayout.createSequentialGroup()
                        .addGroup(pnlBotBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
                            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(pnlBotBillLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(pnlBotBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBotBillLayout.createSequentialGroup()
                        .addGroup(pnlBotBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlBotBillLayout.createSequentialGroup()
                                .addGroup(pnlBotBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel38)
                                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(40, 40, 40)
                                .addComponent(btn_billCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_billCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlBotBillLayout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(83, 83, 83))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBotBillLayout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        pnlBotBillLayout.setVerticalGroup(
            pnlBotBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBotBillLayout.createSequentialGroup()
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBotBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBotBillLayout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)))
                .addGroup(pnlBotBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBotBillLayout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlBotBillLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnlBotBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBotBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pnlBotBillLayout.createSequentialGroup()
                                    .addComponent(jLabel38)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(pnlBotBillLayout.createSequentialGroup()
                                    .addGap(1, 1, 1)
                                    .addComponent(btn_billCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(btn_billCreate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        layeredPaneBot.add(pnlBotBill, "card2");

        pnlBotProduct.setBackground(new java.awt.Color(238, 238, 238));

        tbl_productList.setBackground(new java.awt.Color(71, 96, 114));
        tbl_productList.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tbl_productList.setForeground(new java.awt.Color(238, 238, 238));
        tbl_productList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "ID", "Product name", "Price", "Quantity", "Suplier", "STT"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_productList.setRowHeight(30);
        tbl_productList.getTableHeader().setReorderingAllowed(false);
        tbl_productList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_productListMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tbl_productList);

        jLabel21.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel21.setText("ID:");

        txt_productID.setEditable(false);
        txt_productID.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txt_productID.setBorder(null);

        txt_productName.setBackground(new java.awt.Color(238, 238, 238));
        txt_productName.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txt_productName.setBorder(null);

        txt_productPrice.setBackground(new java.awt.Color(238, 238, 238));
        txt_productPrice.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txt_productPrice.setBorder(null);
        txt_productPrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_productPriceKeyPressed(evt);
            }
        });

        txt_productQuantity.setBackground(new java.awt.Color(238, 238, 238));
        txt_productQuantity.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txt_productQuantity.setBorder(null);
        txt_productQuantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_productQuantityKeyPressed(evt);
            }
        });

        jLabel39.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel39.setText("Name:");

        jLabel40.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel40.setText("Price:");

        jLabel42.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel42.setText("Quantity:");

        jLabel41.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel41.setText("VNĐ");

        cb_productSuplier.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel43.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel43.setText("Suplier:");

        ckb_productSTT.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        ckb_productSTT.setText("Active");

        jLabel44.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel44.setText("Status:");

        jLabel45.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel45.setText("INFORMATION");

        jPanel13.setBackground(new java.awt.Color(84, 140, 168));
        jPanel13.setPreferredSize(new java.awt.Dimension(100, 5));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jSeparator27.setOrientation(javax.swing.SwingConstants.VERTICAL);

        btn_productCreate.setIcon(new javax.swing.ImageIcon("D:\\1.Sor\\IT\\Study\\Java\\Project\\ProjectJava\\src\\main\\java\\com\\mycompany\\projectjava\\icon\\icon_plus_15.png")); // NOI18N
        btn_productCreate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 140, 168), 2));
        btn_productCreate.setPreferredSize(new java.awt.Dimension(30, 30));
        btn_productCreate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_productCreateMouseClicked(evt);
            }
        });

        btn_productRepair.setIcon(new javax.swing.ImageIcon("D:\\1.Sor\\IT\\Study\\Java\\Project\\ProjectJava\\src\\main\\java\\com\\mycompany\\projectjava\\icon\\icon_pencil_15.png")); // NOI18N
        btn_productRepair.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 140, 168), 2));
        btn_productRepair.setPreferredSize(new java.awt.Dimension(30, 30));
        btn_productRepair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_productRepairMouseClicked(evt);
            }
        });

        btn_productDelete.setIcon(new javax.swing.ImageIcon("D:\\1.Sor\\IT\\Study\\Java\\Project\\ProjectJava\\src\\main\\java\\com\\mycompany\\projectjava\\icon\\icon_trash_15.png")); // NOI18N
        btn_productDelete.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(189, 22, 22), 2));
        btn_productDelete.setPreferredSize(new java.awt.Dimension(30, 30));
        btn_productDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_productDeleteMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel45)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel21)
                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(jPanel11Layout.createSequentialGroup()
                                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel40)
                                                .addComponent(jLabel42))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel11Layout.createSequentialGroup()
                                                    .addComponent(txt_productPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(jLabel41))
                                                .addComponent(jSeparator23, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jSeparator24, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txt_productQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jSeparator25, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(jPanel11Layout.createSequentialGroup()
                                            .addComponent(jLabel39)
                                            .addGap(33, 33, 33)
                                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txt_productName, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(txt_productID, javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jSeparator22, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE))))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel11Layout.createSequentialGroup()
                                                .addComponent(jLabel43)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cb_productSuplier, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel11Layout.createSequentialGroup()
                                                .addComponent(jLabel44)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(ckb_productSTT)))
                                        .addGap(31, 31, 31))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                        .addComponent(btn_productCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btn_productRepair, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btn_productDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(47, 47, 47)))))
                        .addComponent(jSeparator27, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel45)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel21)
                                    .addComponent(txt_productID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator22, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cb_productSuplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel43)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_productName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel39))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator23, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel44)
                                .addComponent(ckb_productSTT)))
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_productPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel40)
                                    .addComponent(jLabel41))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator24, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_productQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel42)))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btn_productRepair, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_productCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_productDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(jSeparator27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator25, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        txt_productsearchbox.setBackground(new java.awt.Color(238, 238, 238));
        txt_productsearchbox.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txt_productsearchbox.setForeground(new java.awt.Color(153, 153, 153));
        txt_productsearchbox.setText("Search...");
        txt_productsearchbox.setBorder(null);
        txt_productsearchbox.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_productsearchboxFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_productsearchboxFocusLost(evt);
            }
        });
        txt_productsearchbox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_productsearchboxKeyReleased(evt);
            }
        });

        jLabel46.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel46.setText("SEARCH");

        jPanel14.setBackground(new java.awt.Color(84, 140, 168));
        jPanel14.setPreferredSize(new java.awt.Dimension(50, 5));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(62, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_productsearchbox, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                    .addComponent(jSeparator26))
                .addGap(39, 39, 39))
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel46)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel46)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(txt_productsearchbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator26, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlBotProductLayout = new javax.swing.GroupLayout(pnlBotProduct);
        pnlBotProduct.setLayout(pnlBotProductLayout);
        pnlBotProductLayout.setHorizontalGroup(
            pnlBotProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBotProductLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBotProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBotProductLayout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        pnlBotProductLayout.setVerticalGroup(
            pnlBotProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBotProductLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(pnlBotProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        layeredPaneBot.add(pnlBotProduct, "card2");

        pnlBotSuplier.setBackground(new java.awt.Color(238, 238, 238));

        tbl_suplierList.setBackground(new java.awt.Color(71, 96, 114));
        tbl_suplierList.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tbl_suplierList.setForeground(new java.awt.Color(238, 238, 238));
        tbl_suplierList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "ID", "Suplier name", "Address", "Phone", "Email", "STT"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_suplierList.setRowHeight(30);
        tbl_suplierList.getTableHeader().setReorderingAllowed(false);
        tbl_suplierList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_suplierListMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tbl_suplierList);

        jLabel47.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel47.setText("ID:");

        txt_suplierID.setEditable(false);
        txt_suplierID.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txt_suplierID.setBorder(null);

        txt_suplierName.setBackground(new java.awt.Color(238, 238, 238));
        txt_suplierName.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txt_suplierName.setBorder(null);

        txt_suplierAddress.setBackground(new java.awt.Color(238, 238, 238));
        txt_suplierAddress.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txt_suplierAddress.setBorder(null);

        txt_suplierPhone.setBackground(new java.awt.Color(238, 238, 238));
        txt_suplierPhone.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txt_suplierPhone.setBorder(null);
        txt_suplierPhone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_suplierPhoneKeyPressed(evt);
            }
        });

        jLabel48.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel48.setText("Name:");

        jLabel49.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel49.setText("Address:");

        jLabel50.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel50.setText("Phone:");

        jLabel52.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel52.setText("Email:");

        ckb_suplierSTT.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        ckb_suplierSTT.setText("Active");

        jLabel53.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel53.setText("Status:");

        jLabel54.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel54.setText("INFORMATION");

        jPanel16.setBackground(new java.awt.Color(84, 140, 168));
        jPanel16.setPreferredSize(new java.awt.Dimension(100, 5));

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jSeparator32.setOrientation(javax.swing.SwingConstants.VERTICAL);

        btn_suplierCreate.setIcon(new javax.swing.ImageIcon("D:\\1.Sor\\IT\\Study\\Java\\Project\\ProjectJava\\src\\main\\java\\com\\mycompany\\projectjava\\icon\\icon_plus_15.png")); // NOI18N
        btn_suplierCreate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 140, 168), 2));
        btn_suplierCreate.setPreferredSize(new java.awt.Dimension(30, 30));
        btn_suplierCreate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_suplierCreateMouseClicked(evt);
            }
        });

        btn_suplierRepair.setIcon(new javax.swing.ImageIcon("D:\\1.Sor\\IT\\Study\\Java\\Project\\ProjectJava\\src\\main\\java\\com\\mycompany\\projectjava\\icon\\icon_pencil_15.png")); // NOI18N
        btn_suplierRepair.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 140, 168), 2));
        btn_suplierRepair.setPreferredSize(new java.awt.Dimension(30, 30));
        btn_suplierRepair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_suplierRepairMouseClicked(evt);
            }
        });

        btn_suplierDelete.setIcon(new javax.swing.ImageIcon("D:\\1.Sor\\IT\\Study\\Java\\Project\\ProjectJava\\src\\main\\java\\com\\mycompany\\projectjava\\icon\\icon_trash_15.png")); // NOI18N
        btn_suplierDelete.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(189, 22, 22), 2));
        btn_suplierDelete.setPreferredSize(new java.awt.Dimension(30, 30));
        btn_suplierDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_suplierDeleteMouseClicked(evt);
            }
        });

        txt_suplierEmail.setBackground(new java.awt.Color(238, 238, 238));
        txt_suplierEmail.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txt_suplierEmail.setBorder(null);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel54)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel47)
                            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel15Layout.createSequentialGroup()
                                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel49)
                                        .addComponent(jLabel50))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel15Layout.createSequentialGroup()
                                            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jSeparator31, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txt_suplierPhone)
                                                .addComponent(jSeparator29, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(50, 50, 50))
                                        .addComponent(txt_suplierAddress)
                                        .addComponent(jSeparator30)))
                                .addGroup(jPanel15Layout.createSequentialGroup()
                                    .addComponent(jLabel48)
                                    .addGap(33, 33, 33)
                                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txt_suplierName, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txt_suplierID, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jSeparator28, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE))))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                                .addComponent(jLabel53)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ckb_suplierSTT)
                                .addGap(154, 154, 154))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                                .addComponent(btn_suplierCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_suplierRepair, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_suplierDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47))
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(jLabel52)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jSeparator3)
                                    .addComponent(txt_suplierEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addComponent(jSeparator32, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel54)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addComponent(jSeparator32)
                        .addGap(27, 27, 27))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel47)
                                    .addComponent(txt_suplierID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator28, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel52)
                                .addComponent(txt_suplierEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_suplierName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel48))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel15Layout.createSequentialGroup()
                                        .addComponent(jSeparator29, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txt_suplierAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel49))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jSeparator30, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel15Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel50))
                                            .addGroup(jPanel15Layout.createSequentialGroup()
                                                .addGap(12, 12, 12)
                                                .addComponent(txt_suplierPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel15Layout.createSequentialGroup()
                                        .addGap(16, 16, 16)
                                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel53)
                                            .addComponent(ckb_suplierSTT))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btn_suplierRepair, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btn_suplierCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btn_suplierDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator31, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        txt_suplierSearchbox.setBackground(new java.awt.Color(238, 238, 238));
        txt_suplierSearchbox.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txt_suplierSearchbox.setForeground(new java.awt.Color(153, 153, 153));
        txt_suplierSearchbox.setText("Search...");
        txt_suplierSearchbox.setBorder(null);
        txt_suplierSearchbox.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_suplierSearchboxFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_suplierSearchboxFocusLost(evt);
            }
        });
        txt_suplierSearchbox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_suplierSearchboxKeyReleased(evt);
            }
        });

        jLabel55.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel55.setText("SEARCH");

        jPanel18.setBackground(new java.awt.Color(84, 140, 168));
        jPanel18.setPreferredSize(new java.awt.Dimension(50, 5));

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap(62, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_suplierSearchbox, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                    .addComponent(jSeparator33))
                .addGap(39, 39, 39))
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel55)
                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel55)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(txt_suplierSearchbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator33, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlBotSuplierLayout = new javax.swing.GroupLayout(pnlBotSuplier);
        pnlBotSuplier.setLayout(pnlBotSuplierLayout);
        pnlBotSuplierLayout.setHorizontalGroup(
            pnlBotSuplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBotSuplierLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBotSuplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBotSuplierLayout.createSequentialGroup()
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        pnlBotSuplierLayout.setVerticalGroup(
            pnlBotSuplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBotSuplierLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(pnlBotSuplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        layeredPaneBot.add(pnlBotSuplier, "card2");

        pnlBotCustomer.setBackground(new java.awt.Color(238, 238, 238));

        tbl_ListCustomer.setBackground(new java.awt.Color(71, 96, 114));
        tbl_ListCustomer.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tbl_ListCustomer.setForeground(new java.awt.Color(238, 238, 238));
        tbl_ListCustomer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", "ID001", "Viettel", "Hồ Chí Minh", "0345010002", "viettellhcm@gmail.com", "Active"},
                {"2", "ID002", "VNPT", "Hà Nội", "0344885828", "vnpt@vnpt.vn", "Active"},
                {"3", "ID003", "Intel", "Đà Nẵng", "0300402000", "iintellam@gmail.com", "Active"},
                {"4", "ID004", "Vinaphone", "Hồ Chí Minh", "0300040100", "vinaphone@gmail.com", "Active"}
            },
            new String [] {
                "No", "ID", "Suplier name", "Address", "Phone", "Email", "STT"
            }
        ));
        tbl_ListCustomer.setRowHeight(30);
        tbl_ListCustomer.getTableHeader().setReorderingAllowed(false);
        jScrollPane8.setViewportView(tbl_ListCustomer);

        jLabel51.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel51.setText("ID:");

        jTextField20.setEditable(false);
        jTextField20.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jTextField20.setText("ID001");
        jTextField20.setBorder(null);

        jTextField21.setBackground(new java.awt.Color(238, 238, 238));
        jTextField21.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField21.setText("suplier name");
        jTextField21.setBorder(null);

        jTextField22.setBackground(new java.awt.Color(238, 238, 238));
        jTextField22.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField22.setText("Hồ Chí Minh");
        jTextField22.setBorder(null);

        jTextField23.setBackground(new java.awt.Color(238, 238, 238));
        jTextField23.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField23.setText("0340020100");
        jTextField23.setBorder(null);

        jLabel56.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel56.setText("Name:");

        jLabel57.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel57.setText("Address:");

        jLabel58.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel58.setText("Phone:");

        jLabel59.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel59.setText("Email:");

        jCheckBox3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jCheckBox3.setText("Active");

        jLabel60.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel60.setText("Status:");

        jLabel61.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel61.setText("INFORMATION");

        jPanel20.setBackground(new java.awt.Color(84, 140, 168));
        jPanel20.setPreferredSize(new java.awt.Dimension(100, 5));

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jSeparator38.setOrientation(javax.swing.SwingConstants.VERTICAL);

        btn_customerCreate.setIcon(new javax.swing.ImageIcon("D:\\1.Sor\\IT\\Study\\Java\\Project\\ProjectJava\\src\\main\\java\\com\\mycompany\\projectjava\\icon\\icon_plus_15.png")); // NOI18N
        btn_customerCreate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 140, 168), 2));
        btn_customerCreate.setPreferredSize(new java.awt.Dimension(30, 30));

        btn_customerRepair.setIcon(new javax.swing.ImageIcon("D:\\1.Sor\\IT\\Study\\Java\\Project\\ProjectJava\\src\\main\\java\\com\\mycompany\\projectjava\\icon\\icon_pencil_15.png")); // NOI18N
        btn_customerRepair.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 140, 168), 2));
        btn_customerRepair.setPreferredSize(new java.awt.Dimension(30, 30));

        btn_customerDelete.setIcon(new javax.swing.ImageIcon("D:\\1.Sor\\IT\\Study\\Java\\Project\\ProjectJava\\src\\main\\java\\com\\mycompany\\projectjava\\icon\\icon_trash_15.png")); // NOI18N
        btn_customerDelete.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(189, 22, 22), 2));
        btn_customerDelete.setPreferredSize(new java.awt.Dimension(30, 30));
        btn_customerDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_customerDeleteMouseClicked(evt);
            }
        });

        jTextField24.setBackground(new java.awt.Color(238, 238, 238));
        jTextField24.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField24.setText("intellam@gmail.com");
        jTextField24.setBorder(null);

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel61)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel51)
                                    .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(jPanel19Layout.createSequentialGroup()
                                            .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel57)
                                                .addComponent(jLabel58))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(jPanel19Layout.createSequentialGroup()
                                                    .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jSeparator37, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jTextField23)
                                                        .addComponent(jSeparator35, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGap(50, 50, 50))
                                                .addComponent(jTextField22)
                                                .addComponent(jSeparator36)))
                                        .addGroup(jPanel19Layout.createSequentialGroup()
                                            .addComponent(jLabel56)
                                            .addGap(33, 33, 33)
                                            .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(jTextField20, javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jSeparator34, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE))))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                                        .addComponent(jLabel60)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jCheckBox3)
                                        .addGap(154, 154, 154))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                                        .addComponent(btn_customerCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btn_customerRepair, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btn_customerDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(47, 47, 47))
                                    .addGroup(jPanel19Layout.createSequentialGroup()
                                        .addComponent(jLabel59)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jSeparator4)
                                            .addComponent(jTextField24, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))))
                        .addComponent(jSeparator38, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel61)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel51)
                                    .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator34, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel59)
                                .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel56))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel19Layout.createSequentialGroup()
                                        .addComponent(jSeparator35, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel57))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jSeparator36, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel58)))
                                    .addGroup(jPanel19Layout.createSequentialGroup()
                                        .addGap(16, 16, 16)
                                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel60)
                                            .addComponent(jCheckBox3))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btn_customerRepair, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btn_customerCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btn_customerDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                        .addComponent(jSeparator38, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator37, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        txt_customerSearchbox.setBackground(new java.awt.Color(238, 238, 238));
        txt_customerSearchbox.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txt_customerSearchbox.setForeground(new java.awt.Color(153, 153, 153));
        txt_customerSearchbox.setText("Search...");
        txt_customerSearchbox.setBorder(null);
        txt_customerSearchbox.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_customerSearchboxFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_customerSearchboxFocusLost(evt);
            }
        });
        txt_customerSearchbox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_customerSearchboxKeyReleased(evt);
            }
        });

        jLabel62.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel62.setText("SEARCH");

        jPanel22.setBackground(new java.awt.Color(84, 140, 168));
        jPanel22.setPreferredSize(new java.awt.Dimension(50, 5));

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap(62, Short.MAX_VALUE)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_customerSearchbox, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                    .addComponent(jSeparator39))
                .addGap(39, 39, 39))
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel62)
                    .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel62)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(txt_customerSearchbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator39, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlBotCustomerLayout = new javax.swing.GroupLayout(pnlBotCustomer);
        pnlBotCustomer.setLayout(pnlBotCustomerLayout);
        pnlBotCustomerLayout.setHorizontalGroup(
            pnlBotCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBotCustomerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBotCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBotCustomerLayout.createSequentialGroup()
                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        pnlBotCustomerLayout.setVerticalGroup(
            pnlBotCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBotCustomerLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(pnlBotCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        layeredPaneBot.add(pnlBotCustomer, "card2");

        pnlBotStaff.setBackground(new java.awt.Color(238, 238, 238));

        tbl_staffLstStaff.setBackground(new java.awt.Color(71, 96, 114));
        tbl_staffLstStaff.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tbl_staffLstStaff.setForeground(new java.awt.Color(238, 238, 238));
        tbl_staffLstStaff.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "ID", "Username", "Name", "Phone", "Address", "Email", "Role", "STT"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_staffLstStaff.setRowHeight(30);
        tbl_staffLstStaff.getTableHeader().setReorderingAllowed(false);
        tbl_staffLstStaff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_staffLstStaffMouseClicked(evt);
            }
        });
        tbl_staffLstStaff.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbl_staffLstStaffKeyReleased(evt);
            }
        });
        jScrollPane9.setViewportView(tbl_staffLstStaff);

        jLabel63.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel63.setText("ID:");

        txt_staffID_real.setEditable(false);
        txt_staffID_real.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txt_staffID_real.setBorder(null);

        txt_staffUserName.setBackground(new java.awt.Color(238, 238, 238));
        txt_staffUserName.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txt_staffUserName.setBorder(null);

        txt_staffName.setBackground(new java.awt.Color(238, 238, 238));
        txt_staffName.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txt_staffName.setBorder(null);

        txt_staffPhone.setBackground(new java.awt.Color(238, 238, 238));
        txt_staffPhone.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txt_staffPhone.setBorder(null);

        jLabel64.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel64.setText("Username:");

        jLabel65.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel65.setText("Name:");

        jLabel66.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel66.setText("Phone:");

        jLabel67.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel67.setText("Address:");

        ckb_staffSTT.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        ckb_staffSTT.setText("Active");

        jLabel68.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel68.setText("Status:");

        jLabel69.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel69.setText("INFORMATION");

        jPanel24.setBackground(new java.awt.Color(84, 140, 168));
        jPanel24.setPreferredSize(new java.awt.Dimension(100, 5));

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jSeparator44.setOrientation(javax.swing.SwingConstants.VERTICAL);

        btn_staffCreate.setIcon(new javax.swing.ImageIcon("D:\\1.Sor\\IT\\Study\\Java\\Project\\ProjectJava\\src\\main\\java\\com\\mycompany\\projectjava\\icon\\icon_plus_15.png")); // NOI18N
        btn_staffCreate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 140, 168), 2));
        btn_staffCreate.setPreferredSize(new java.awt.Dimension(30, 30));
        btn_staffCreate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_staffCreateMouseClicked(evt);
            }
        });

        btn_staffRepair.setIcon(new javax.swing.ImageIcon("D:\\1.Sor\\IT\\Study\\Java\\Project\\ProjectJava\\src\\main\\java\\com\\mycompany\\projectjava\\icon\\icon_pencil_15.png")); // NOI18N
        btn_staffRepair.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 140, 168), 2));
        btn_staffRepair.setPreferredSize(new java.awt.Dimension(30, 30));
        btn_staffRepair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_staffRepairMouseClicked(evt);
            }
        });

        btn_staffDelete.setIcon(new javax.swing.ImageIcon("D:\\1.Sor\\IT\\Study\\Java\\Project\\ProjectJava\\src\\main\\java\\com\\mycompany\\projectjava\\icon\\icon_trash_15.png")); // NOI18N
        btn_staffDelete.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(189, 22, 22), 2));
        btn_staffDelete.setPreferredSize(new java.awt.Dimension(30, 30));
        btn_staffDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_staffDeleteMouseClicked(evt);
            }
        });

        txt_staffAddress.setBackground(new java.awt.Color(238, 238, 238));
        txt_staffAddress.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txt_staffAddress.setBorder(null);

        jLabel71.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel71.setText("Email:");

        txt_staffEmail.setBackground(new java.awt.Color(238, 238, 238));
        txt_staffEmail.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txt_staffEmail.setBorder(null);

        jLabel72.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel72.setText("Role:");

        cb_staffRole.setBackground(new java.awt.Color(238, 238, 238));
        cb_staffRole.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        btn_staffChangepass.setIcon(new javax.swing.ImageIcon("D:\\1.Sor\\IT\\Study\\Java\\Project\\ProjectJava\\src\\main\\java\\com\\mycompany\\projectjava\\icon\\icon_key_15.png")); // NOI18N
        btn_staffChangepass.setText("Change pass");
        btn_staffChangepass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 140, 168), 2));
        btn_staffChangepass.setPreferredSize(new java.awt.Dimension(81, 25));
        btn_staffChangepass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_staffChangepassMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout txt_staffIDLayout = new javax.swing.GroupLayout(txt_staffID);
        txt_staffID.setLayout(txt_staffIDLayout);
        txt_staffIDLayout.setHorizontalGroup(
            txt_staffIDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(txt_staffIDLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(txt_staffIDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(txt_staffIDLayout.createSequentialGroup()
                        .addComponent(jLabel69)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(txt_staffIDLayout.createSequentialGroup()
                        .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(txt_staffIDLayout.createSequentialGroup()
                        .addGroup(txt_staffIDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(txt_staffIDLayout.createSequentialGroup()
                                .addGroup(txt_staffIDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel65)
                                    .addComponent(jLabel66)
                                    .addComponent(jLabel63))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(txt_staffIDLayout.createSequentialGroup()
                                .addComponent(jLabel64)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(txt_staffIDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(txt_staffIDLayout.createSequentialGroup()
                                        .addGroup(txt_staffIDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txt_staffPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jSeparator43, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btn_staffCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btn_staffRepair, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btn_staffDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18))
                                    .addGroup(txt_staffIDLayout.createSequentialGroup()
                                        .addGroup(txt_staffIDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txt_staffName, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jSeparator40, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jSeparator41, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jSeparator42, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(txt_staffIDLayout.createSequentialGroup()
                                                .addGroup(txt_staffIDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addGroup(txt_staffIDLayout.createSequentialGroup()
                                                        .addComponent(txt_staffUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                                    .addGroup(txt_staffIDLayout.createSequentialGroup()
                                                        .addComponent(txt_staffID_real)
                                                        .addGap(111, 111, 111)))
                                                .addGroup(txt_staffIDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(txt_staffIDLayout.createSequentialGroup()
                                                        .addComponent(jLabel68)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(ckb_staffSTT))
                                                    .addGroup(txt_staffIDLayout.createSequentialGroup()
                                                        .addGroup(txt_staffIDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addComponent(jLabel67)
                                                            .addComponent(jLabel72))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addGroup(txt_staffIDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(txt_staffAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(txt_staffEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGroup(txt_staffIDLayout.createSequentialGroup()
                                                                .addComponent(cb_staffRole, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(28, 28, 28)
                                                                .addComponent(btn_staffChangepass, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                    .addComponent(jLabel71))))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)))))
                        .addComponent(jSeparator44, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        txt_staffIDLayout.setVerticalGroup(
            txt_staffIDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txt_staffIDLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel69)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(txt_staffIDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txt_staffIDLayout.createSequentialGroup()
                        .addComponent(jSeparator44)
                        .addGap(27, 27, 27))
                    .addGroup(txt_staffIDLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(txt_staffIDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel63)
                            .addComponent(txt_staffID_real, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel67)
                            .addComponent(txt_staffAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(txt_staffIDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator40, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(txt_staffIDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_staffUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel64)
                            .addComponent(jLabel71)
                            .addComponent(txt_staffEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(txt_staffIDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator41, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(txt_staffIDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(txt_staffIDLayout.createSequentialGroup()
                                .addGroup(txt_staffIDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_staffName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel65)
                                    .addComponent(jLabel72)
                                    .addComponent(cb_staffRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_staffChangepass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator42, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(txt_staffIDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel66)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txt_staffIDLayout.createSequentialGroup()
                                        .addGroup(txt_staffIDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txt_staffPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel68)
                                            .addComponent(ckb_staffSTT))
                                        .addGap(3, 3, 3)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jSeparator43, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(txt_staffIDLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(txt_staffIDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btn_staffRepair, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_staffCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_staffDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap())))
        );

        txt_staffSearchbox.setBackground(new java.awt.Color(238, 238, 238));
        txt_staffSearchbox.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txt_staffSearchbox.setForeground(new java.awt.Color(153, 153, 153));
        txt_staffSearchbox.setText("Search...");
        txt_staffSearchbox.setBorder(null);
        txt_staffSearchbox.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_staffSearchboxFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_staffSearchboxFocusLost(evt);
            }
        });
        txt_staffSearchbox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_staffSearchboxKeyReleased(evt);
            }
        });

        jLabel70.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel70.setText("SEARCH");

        jPanel26.setBackground(new java.awt.Color(84, 140, 168));
        jPanel26.setPreferredSize(new java.awt.Dimension(50, 5));

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_staffSearchbox, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                    .addComponent(jSeparator45))
                .addGap(39, 39, 39))
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel70)
                    .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel70)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(txt_staffSearchbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator45, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlBotStaffLayout = new javax.swing.GroupLayout(pnlBotStaff);
        pnlBotStaff.setLayout(pnlBotStaffLayout);
        pnlBotStaffLayout.setHorizontalGroup(
            pnlBotStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBotStaffLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_staffID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jScrollPane9)
        );
        pnlBotStaffLayout.setVerticalGroup(
            pnlBotStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBotStaffLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(pnlBotStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_staffID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        layeredPaneBot.add(pnlBotStaff, "card2");

        pnlBotMe.setBackground(new java.awt.Color(238, 238, 238));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel9.setText("YOUR INFORMATION");

        jPanel28.setBackground(new java.awt.Color(84, 140, 168));
        jPanel28.setPreferredSize(new java.awt.Dimension(120, 5));

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 120, Short.MAX_VALUE)
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jLabel10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel10.setText("User name:");

        txt_meUsername.setEditable(false);
        txt_meUsername.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txt_meUsername.setText("Username");
        txt_meUsername.setBorder(null);

        jLabel19.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel19.setText("Name:");

        txt_meName.setBackground(new java.awt.Color(238, 238, 238));
        txt_meName.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txt_meName.setText("Tùng Lâm");
        txt_meName.setBorder(null);

        jLabel20.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel20.setText("Phone:");

        txt_mePhone.setBackground(new java.awt.Color(238, 238, 238));
        txt_mePhone.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txt_mePhone.setText("0345000019");
        txt_mePhone.setBorder(null);

        jLabel73.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel73.setText("Address:");

        txt_meAddress.setBackground(new java.awt.Color(238, 238, 238));
        txt_meAddress.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txt_meAddress.setText("Quận 2");
        txt_meAddress.setBorder(null);

        txt_meEmail.setBackground(new java.awt.Color(238, 238, 238));
        txt_meEmail.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txt_meEmail.setText("tunglam.sor@gmail.com");
        txt_meEmail.setBorder(null);

        jLabel74.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel74.setText("Email:");

        jLabel75.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel75.setText("Role:");

        txt_meRole.setEditable(false);
        txt_meRole.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txt_meRole.setText("Admin");
        txt_meRole.setBorder(null);

        btn_meSave.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btn_meSave.setIcon(new javax.swing.ImageIcon("D:\\1.Sor\\IT\\Study\\Java\\Project\\ProjectJava\\src\\main\\java\\com\\mycompany\\projectjava\\icon\\icon_success_15.png")); // NOI18N
        btn_meSave.setText("Save");
        btn_meSave.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(110, 203, 99), 2));
        btn_meSave.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btn_meSave.setPreferredSize(new java.awt.Dimension(63, 19));
        btn_meSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_meSaveMouseClicked(evt);
            }
        });

        btn_meCancel.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btn_meCancel.setIcon(new javax.swing.ImageIcon("D:\\1.Sor\\IT\\Study\\Java\\Project\\ProjectJava\\src\\main\\java\\com\\mycompany\\projectjava\\icon\\icon_cancel_15.png")); // NOI18N
        btn_meCancel.setText("Cancel");
        btn_meCancel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(189, 22, 22), 2));
        btn_meCancel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btn_meCancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_meCancelMouseClicked(evt);
            }
        });

        btn_meChangepass.setIcon(new javax.swing.ImageIcon("D:\\1.Sor\\IT\\Study\\Java\\Project\\ProjectJava\\src\\main\\java\\com\\mycompany\\projectjava\\icon\\icon_key_15.png")); // NOI18N
        btn_meChangepass.setText("Change pass");
        btn_meChangepass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 140, 168), 2));
        btn_meChangepass.setPreferredSize(new java.awt.Dimension(81, 25));
        btn_meChangepass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_meChangepassMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btn_meSave, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel27Layout.createSequentialGroup()
                                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel73)
                                    .addComponent(jLabel74))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel27Layout.createSequentialGroup()
                                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jSeparator46)
                                                .addComponent(txt_mePhone, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                                                .addComponent(txt_meAddress, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                                                .addComponent(jSeparator47)
                                                .addComponent(txt_meEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jSeparator48, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(116, 116, 116)
                                        .addComponent(jLabel75))
                                    .addGroup(jPanel27Layout.createSequentialGroup()
                                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txt_meName, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                                            .addComponent(jSeparator7)
                                            .addComponent(txt_meUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                                            .addComponent(jSeparator16))
                                        .addGap(54, 54, 54)
                                        .addComponent(btn_meChangepass, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel27Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jSeparator49)
                                    .addComponent(txt_meRole, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)))
                            .addGroup(jPanel27Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(btn_meCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txt_meUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel75)
                    .addComponent(txt_meRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator49, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_meName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(btn_meChangepass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator16, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_mePhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator46, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_meAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel73))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator47, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_meEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel74))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator48, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_meSave, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_meCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btn_meReport.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btn_meReport.setIcon(new javax.swing.ImageIcon("D:\\1.Sor\\IT\\Study\\Java\\Project\\ProjectJava\\src\\main\\java\\com\\mycompany\\projectjava\\icon\\icon_chart_15.png")); // NOI18N
        btn_meReport.setText("Report");
        btn_meReport.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 140, 168), 2));
        btn_meReport.setPreferredSize(new java.awt.Dimension(95, 50));
        btn_meReport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_meReportMouseClicked(evt);
            }
        });

        jLabel76.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel76.setText("MANAGE");

        jPanel30.setBackground(new java.awt.Color(84, 140, 168));
        jPanel30.setPreferredSize(new java.awt.Dimension(50, 5));

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        btn_meRole.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btn_meRole.setIcon(new javax.swing.ImageIcon("D:\\1.Sor\\IT\\Study\\Java\\Project\\ProjectJava\\src\\main\\java\\com\\mycompany\\projectjava\\icon\\icon_role_15.png")); // NOI18N
        btn_meRole.setText("Role");
        btn_meRole.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 140, 168), 2));
        btn_meRole.setPreferredSize(new java.awt.Dimension(95, 50));
        btn_meRole.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_meRoleMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel76))
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(btn_meReport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_meRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(400, Short.MAX_VALUE))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel76)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_meReport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_meRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlBotMeLayout = new javax.swing.GroupLayout(pnlBotMe);
        pnlBotMe.setLayout(pnlBotMeLayout);
        pnlBotMeLayout.setHorizontalGroup(
            pnlBotMeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBotMeLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(135, 135, 135))
            .addGroup(pnlBotMeLayout.createSequentialGroup()
                .addGroup(pnlBotMeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBotMeLayout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(jSeparator50, javax.swing.GroupLayout.PREFERRED_SIZE, 778, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlBotMeLayout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(97, Short.MAX_VALUE))
        );
        pnlBotMeLayout.setVerticalGroup(
            pnlBotMeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBotMeLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator50, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        layeredPaneBot.add(pnlBotMe, "card2");

        pnlBotUserRole.setBackground(new java.awt.Color(238, 238, 238));

        lst_roleRoleName.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lst_roleRoleName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lst_roleRoleNameMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(lst_roleRoleName);

        lst_roleRoleFunction.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jScrollPane11.setViewportView(lst_roleRoleFunction);

        lst_roleFuction.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jScrollPane12.setViewportView(lst_roleFuction);

        jLabel77.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel77.setText("Role name:");

        txt_roleRolename.setBackground(new java.awt.Color(238, 238, 238));
        txt_roleRolename.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txt_roleRolename.setBorder(null);

        btn_roleCreate.setIcon(new javax.swing.ImageIcon("D:\\1.Sor\\IT\\Study\\Java\\Project\\ProjectJava\\src\\main\\java\\com\\mycompany\\projectjava\\icon\\icon_plus_15.png")); // NOI18N
        btn_roleCreate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 140, 168), 2));
        btn_roleCreate.setPreferredSize(new java.awt.Dimension(30, 30));
        btn_roleCreate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_roleCreateMouseClicked(evt);
            }
        });
        btn_roleCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_roleCreateActionPerformed(evt);
            }
        });

        btn_roleRepair.setIcon(new javax.swing.ImageIcon("D:\\1.Sor\\IT\\Study\\Java\\Project\\ProjectJava\\src\\main\\java\\com\\mycompany\\projectjava\\icon\\icon_pencil_15.png")); // NOI18N
        btn_roleRepair.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 140, 168), 2));
        btn_roleRepair.setPreferredSize(new java.awt.Dimension(30, 30));
        btn_roleRepair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_roleRepairMouseClicked(evt);
            }
        });

        btn_roleDelete.setIcon(new javax.swing.ImageIcon("D:\\1.Sor\\IT\\Study\\Java\\Project\\ProjectJava\\src\\main\\java\\com\\mycompany\\projectjava\\icon\\icon_trash_15.png")); // NOI18N
        btn_roleDelete.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(189, 22, 22), 2));
        btn_roleDelete.setPreferredSize(new java.awt.Dimension(30, 30));
        btn_roleDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_roleDeleteMouseClicked(evt);
            }
        });

        jLabel78.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel78.setText("Role name");

        jLabel79.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel79.setText("Function");

        jLabel80.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel80.setText("List function");

        btn_roleFunctionShowall.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btn_roleFunctionShowall.setText("Show all");
        btn_roleFunctionShowall.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 140, 168), 2));
        btn_roleFunctionShowall.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_roleFunctionShowallMouseClicked(evt);
            }
        });

        btn_roleAddFunction.setText("<");
        btn_roleAddFunction.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 140, 168), 2));
        btn_roleAddFunction.setMaximumSize(new java.awt.Dimension(41, 41));
        btn_roleAddFunction.setMinimumSize(new java.awt.Dimension(41, 41));
        btn_roleAddFunction.setPreferredSize(new java.awt.Dimension(41, 41));
        btn_roleAddFunction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_roleAddFunctionActionPerformed(evt);
            }
        });

        btn_roleRemoveFunction.setText(">");
        btn_roleRemoveFunction.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 140, 168), 2));
        btn_roleRemoveFunction.setMaximumSize(new java.awt.Dimension(41, 41));
        btn_roleRemoveFunction.setMinimumSize(new java.awt.Dimension(41, 41));
        btn_roleRemoveFunction.setPreferredSize(new java.awt.Dimension(41, 41));
        btn_roleRemoveFunction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_roleRemoveFunctionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBotUserRoleLayout = new javax.swing.GroupLayout(pnlBotUserRole);
        pnlBotUserRole.setLayout(pnlBotUserRoleLayout);
        pnlBotUserRoleLayout.setHorizontalGroup(
            pnlBotUserRoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBotUserRoleLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btn_roleCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_roleRepair, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_roleDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(427, 427, 427))
            .addGroup(pnlBotUserRoleLayout.createSequentialGroup()
                .addGap(337, 337, 337)
                .addComponent(jLabel77)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBotUserRoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator51)
                    .addComponent(txt_roleRolename, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBotUserRoleLayout.createSequentialGroup()
                .addContainerGap(213, Short.MAX_VALUE)
                .addGroup(pnlBotUserRoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBotUserRoleLayout.createSequentialGroup()
                        .addComponent(btn_roleFunctionShowall, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(153, 153, 153))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBotUserRoleLayout.createSequentialGroup()
                        .addGroup(pnlBotUserRoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel78))
                        .addGap(76, 76, 76)
                        .addGroup(pnlBotUserRoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlBotUserRoleLayout.createSequentialGroup()
                                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlBotUserRoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btn_roleAddFunction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_roleRemoveFunction, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel79))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlBotUserRoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel80)
                            .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(78, 78, 78))))
        );
        pnlBotUserRoleLayout.setVerticalGroup(
            pnlBotUserRoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBotUserRoleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBotUserRoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel77)
                    .addComponent(txt_roleRolename, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBotUserRoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlBotUserRoleLayout.createSequentialGroup()
                        .addComponent(jSeparator51, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addGroup(pnlBotUserRoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_roleRepair, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_roleCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_roleDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlBotUserRoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlBotUserRoleLayout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(jLabel80))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBotUserRoleLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlBotUserRoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel79)
                                    .addComponent(jLabel78))))
                        .addGroup(pnlBotUserRoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlBotUserRoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlBotUserRoleLayout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(btn_roleAddFunction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(76, 76, 76)
                                .addComponent(btn_roleRemoveFunction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_roleFunctionShowall, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(152, Short.MAX_VALUE))
        );

        layeredPaneBot.add(pnlBotUserRole, "card2");

        pnlBottom.add(layeredPaneBot, java.awt.BorderLayout.CENTER);

        pnlCenter.add(pnlBottom, java.awt.BorderLayout.CENTER);

        pnlRoot.add(pnlCenter, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnlRoot, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_homeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_homeMouseClicked
        // TODO add your handling code here:
        switchPanels(pnlTopHome, pnlBotHome);
    }//GEN-LAST:event_btn_homeMouseClicked

    private void btn_createMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_createMouseClicked
        // TODO add your handling code here:
        boolean check = false;
        for (int i = 0; i < listFunctionUser.size(); i++) {
            if (listFunctionUser.get(i).getIdFunction().equals("btn_create")) {
                check = true;
            }
        }
        if (check)
            switchPanels(pnlTopBill, pnlBotBill);
        else
            JOptionPane.showMessageDialog(this, "You're not authorizeed", "DANGER!", JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_btn_createMouseClicked

    private void btn_productMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_productMouseClicked
        // TODO add your handling code here:
        boolean check = false;
        for (int i = 0; i < listFunctionUser.size(); i++) {
            if (listFunctionUser.get(i).getIdFunction().equals("btn_product")) {
                check = true;
            }
        }
        if (check)
            switchPanels(pnlTopProduct, pnlBotProduct);
        else
            JOptionPane.showMessageDialog(this, "You're not authorizeed", "DANGER!", JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_btn_productMouseClicked

    private void btn_suplierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_suplierMouseClicked
        // TODO add your handling code here:
        boolean check = false;
        for (int i = 0; i < listFunctionUser.size(); i++) {
            if (listFunctionUser.get(i).getIdFunction().equals("btn_suplier")) {
                check = true;
            }
        }
        if (check)
            switchPanels(pnlTopSuplier, pnlBotSuplier);
        else
            JOptionPane.showMessageDialog(this, "You're not authorizeed", "DANGER!", JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_btn_suplierMouseClicked

    private void btn_customerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_customerMouseClicked
        // TODO add your handling code here:
        if (USER.getRoleID() == 1)
            switchPanels(pnlTopCustomer, pnlBotCustomer);
        else
            JOptionPane.showMessageDialog(this, "You do not currently have permission to access this area", "Alert", JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_btn_customerMouseClicked

    private void btn_staffMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_staffMouseClicked
        // TODO add your handling code here:
        boolean check = false;
        for (int i = 0; i < listFunctionUser.size(); i++) {
            if (listFunctionUser.get(i).getIdFunction().equals("btn_staff")) {
                check = true;
            }
        }
        if (check)
            switchPanels(pnlTopStaff, pnlBotStaff);
        else
            JOptionPane.showMessageDialog(this, "You're not authorizeed", "DANGER!", JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_btn_staffMouseClicked

    private void btn_userMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_userMouseClicked
        try {
            setDefaultUserInfo();
            USER = Login.USER;
            switchPanels(pnlTopMe, pnlBotMe);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btn_userMouseClicked

    private void txt_BotsearchboxFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_BotsearchboxFocusGained
        // TODO add your handling code here:
        if (txt_Botsearchbox.getText().equals("Search...")) {
            txt_Botsearchbox.setText("");
            txt_Botsearchbox.setForeground(new Color(0, 0, 0));
        }
    }//GEN-LAST:event_txt_BotsearchboxFocusGained

    private void txt_BotsearchboxFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_BotsearchboxFocusLost
        // TODO add your handling code here:
        if (txt_Botsearchbox.getText().equals("")) {
            txt_Botsearchbox.setText("Search...");
            txt_Botsearchbox.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_txt_BotsearchboxFocusLost

    private void txt_productsearchboxFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_productsearchboxFocusGained
        // TODO add your handling code here:
        if (txt_productsearchbox.getText().equals("Search...")) {
            txt_productsearchbox.setText("");
            txt_productsearchbox.setForeground(new Color(0, 0, 0));
        }
    }//GEN-LAST:event_txt_productsearchboxFocusGained

    private void txt_productsearchboxFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_productsearchboxFocusLost
        // TODO add your handling code here:
        if (txt_productsearchbox.getText().equals("")) {
            txt_productsearchbox.setText("Search...");
            txt_productsearchbox.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_txt_productsearchboxFocusLost

    private void txt_suplierSearchboxFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_suplierSearchboxFocusGained
        // TODO add your handling code here:
        if (txt_suplierSearchbox.getText().equals("Search...")) {
            txt_suplierSearchbox.setText("");
            txt_suplierSearchbox.setForeground(new Color(0, 0, 0));
        }
    }//GEN-LAST:event_txt_suplierSearchboxFocusGained

    private void txt_suplierSearchboxFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_suplierSearchboxFocusLost
        // TODO add your handling code here:
        if (txt_suplierSearchbox.getText().equals("")) {
            txt_suplierSearchbox.setText("Search...");
            txt_suplierSearchbox.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_txt_suplierSearchboxFocusLost

    private void txt_customerSearchboxFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_customerSearchboxFocusGained
        // TODO add your handling code here:
        if (txt_customerSearchbox.getText().equals("Search...")) {
            txt_customerSearchbox.setText("");
            txt_customerSearchbox.setForeground(new Color(0, 0, 0));
        }
    }//GEN-LAST:event_txt_customerSearchboxFocusGained

    private void txt_customerSearchboxFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_customerSearchboxFocusLost
        // TODO add your handling code here:
        if (txt_customerSearchbox.getText().equals("")) {
            txt_customerSearchbox.setText("Search...");
            txt_customerSearchbox.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_txt_customerSearchboxFocusLost

    private void txt_staffSearchboxFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_staffSearchboxFocusGained
        // TODO add your handling code here:
        if (txt_staffSearchbox.getText().equals("Search...")) {
            txt_staffSearchbox.setText("");
            txt_staffSearchbox.setForeground(new Color(0, 0, 0));
        }
    }//GEN-LAST:event_txt_staffSearchboxFocusGained

    private void txt_staffSearchboxFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_staffSearchboxFocusLost
        // TODO add your handling code here:
        if (txt_staffSearchbox.getText().equals("")) {
            txt_staffSearchbox.setText("Search...");
            txt_staffSearchbox.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_txt_staffSearchboxFocusLost

    private void btn_meChangepassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_meChangepassMouseClicked
        // TODO add your handling code here:
        ChangeYourPass newF = new ChangeYourPass();
        newF.setVisible(true);
        newF.setLocationRelativeTo(null);
        //this.setEnabled(false);
        newF.setDefaultCloseOperation(ChangePass.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_btn_meChangepassMouseClicked

    private void btn_staffChangepassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_staffChangepassMouseClicked
        // TODO add your handling code here:
        //new ChangePass().setVisible(true);
        boolean check = false;
        for (int i = 0; i < listFunctionUser.size(); i++) {
            if (listFunctionUser.get(i).getIdFunction().equals("btn_staffChangepass")) {
                check = true;
            }
        }
        if (check) {
            if (txt_staffID_real.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please choose staff to change!");
            } else {
                ChangePass newF = new ChangePass(txt_staffID_real.getText());
                newF.setVisible(true);
                newF.setLocationRelativeTo(null);
                //this.setEnabled(false);
                newF.setDefaultCloseOperation(ChangePass.DISPOSE_ON_CLOSE);
            }
        } else
            JOptionPane.showMessageDialog(this, "You're not authorizeed", "DANGER!", JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_btn_staffChangepassMouseClicked

    private void btn_meReportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_meReportMouseClicked
        // TODO add your handling code here:
        boolean check = false;
        for (int i = 0; i < listFunctionUser.size(); i++) {
            if (listFunctionUser.get(i).getIdFunction().equals("btn_meReport")) {
                check = true;
            }
        }
        if (check) {
            Chart chart;
            try {
                chart = new Chart();
                chart.setVisible(true);
                chart.setLocationRelativeTo(null);
                chart.setDefaultCloseOperation(Chart.DISPOSE_ON_CLOSE);
            } catch (ParseException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "You're not authorizeed", "DANGER!", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_btn_meReportMouseClicked

    private void txt_HomesearchboxFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_HomesearchboxFocusLost
        // TODO add your handling code here:
        if (txt_Homesearchbox.getText().equals("")) {
            txt_Homesearchbox.setText("Search...");
            txt_Homesearchbox.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_txt_HomesearchboxFocusLost

    private void txt_HomesearchboxFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_HomesearchboxFocusGained
        // TODO add your handling code here:
        if (txt_Homesearchbox.getText().equals("Search...")) {
            txt_Homesearchbox.setText("");
            txt_Homesearchbox.setForeground(new Color(0, 0, 0));
        }
    }//GEN-LAST:event_txt_HomesearchboxFocusGained

    private void btn_meCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_meCancelMouseClicked
        try {
            // TODO add your handling code here:
            setDefaultUserInfo();
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_meCancelMouseClicked

    private void tbl_staffLstStaffKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbl_staffLstStaffKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_tbl_staffLstStaffKeyReleased

    private void tbl_staffLstStaffMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_staffLstStaffMouseClicked
        // TODO add your handling code here:
        int index = tbl_staffLstStaff.getSelectedRow();
        TableModel model = tbl_staffLstStaff.getModel();
        txt_staffID_real.setText(String.valueOf(model.getValueAt(index, 1)));
        txt_staffUserName.setText(String.valueOf(model.getValueAt(index, 2)));
        txt_staffName.setText(String.valueOf(model.getValueAt(index, 3)));
        txt_staffPhone.setText(String.valueOf(model.getValueAt(index, 4)));
        txt_staffAddress.setText(String.valueOf(model.getValueAt(index, 5)));
        txt_staffEmail.setText(String.valueOf(model.getValueAt(index, 6)));
        cb_staffRole.setSelectedItem(String.valueOf(model.getValueAt(index, 7)));
        if (String.valueOf(model.getValueAt(index, 8)).equals("Active"))
            ckb_staffSTT.setSelected(true);
        else
            ckb_staffSTT.setSelected(false);
    }//GEN-LAST:event_tbl_staffLstStaffMouseClicked

    private void tbl_suplierListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_suplierListMouseClicked
        // TODO add your handling code here:
        int index = tbl_suplierList.getSelectedRow();
        TableModel model = tbl_suplierList.getModel();
        txt_suplierID.setText(String.valueOf(model.getValueAt(index, 1)));
        txt_suplierName.setText(String.valueOf(model.getValueAt(index, 2)));
        txt_suplierAddress.setText(String.valueOf(model.getValueAt(index, 3)));
        txt_suplierPhone.setText(String.valueOf(model.getValueAt(index, 4)));
        txt_suplierEmail.setText(String.valueOf(model.getValueAt(index, 5)));
        if (String.valueOf(model.getValueAt(index, 6)).equals("Active"))
            ckb_suplierSTT.setSelected(true);
        else
            ckb_suplierSTT.setSelected(false);
    }//GEN-LAST:event_tbl_suplierListMouseClicked

    private void tbl_productListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_productListMouseClicked
        // TODO add your handling code here:
        int index = tbl_productList.getSelectedRow();
        TableModel model = tbl_productList.getModel();
        txt_productID.setText(String.valueOf(model.getValueAt(index, 1)));
        txt_productName.setText(String.valueOf(model.getValueAt(index, 2)));
        txt_productPrice.setText(Currency.splitCurrency(String.valueOf(model.getValueAt(index, 3))));
        txt_productQuantity.setText(String.valueOf(model.getValueAt(index, 4)));
        cb_productSuplier.setSelectedItem(String.valueOf(model.getValueAt(index, 5)));
        if (String.valueOf(model.getValueAt(index, 6)).equals("Active"))
            ckb_productSTT.setSelected(true);
        else
            ckb_productSTT.setSelected(false);
    }//GEN-LAST:event_tbl_productListMouseClicked

    private void tbl_billProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_billProductMouseClicked
        // TODO add your handling code here:
        int index = tbl_billProduct.getSelectedRow();
        TableModel model = tbl_billProduct.getModel();

        txt_billProductID.setText(String.valueOf(model.getValueAt(index, 1)));
        txt_billProductName.setText(String.valueOf(model.getValueAt(index, 2)));
        double price = Double.parseDouble(Currency.splitCurrency(model.getValueAt(index, 3).toString()));
        txt_billProductPrice.setText(currency.format(price));
        txt_billProductTotal.setText(currency.format(price));
        txt_billProductQuantity.setText(String.valueOf(1));
        soluonggoc = Integer.parseInt(model.getValueAt(index, 4).toString());
    }//GEN-LAST:event_tbl_billProductMouseClicked

    private void tbl_billListItemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_billListItemMouseClicked
        // TODO add your handling code here:
        int index = tbl_billListItem.getSelectedRow();
        TableModel model = tbl_billListItem.getModel();

        txt_billProductName.setText(String.valueOf(model.getValueAt(index, 1)));
        txt_billProductQuantity.setText(String.valueOf(model.getValueAt(index, 2)));
        double price = Double.parseDouble(model.getValueAt(index, 3).toString());
        txt_billProductPrice.setText(currency.format(price));
        txt_billProductTotal.setText(String.valueOf(model.getValueAt(index, 4)));
        long id = ProductDAO.getID(String.valueOf(model.getValueAt(index, 1)));
        txt_billProductID.setText(String.valueOf(id));
    }//GEN-LAST:event_tbl_billListItemMouseClicked

    private void tbl_homeListProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_homeListProductMouseClicked
        // TODO add your handling code here:
        int index = tbl_homeListProduct.getSelectedRow();
        TableModel model = tbl_homeListProduct.getModel();

        txt_homeProductID.setText(String.valueOf(model.getValueAt(index, 1)));
        txt_homeProductName.setText(String.valueOf(model.getValueAt(index, 2)));
        txt_homeProductPrice.setText(String.valueOf(model.getValueAt(index, 4)));
        txt_homeProductQuantity.setText(String.valueOf(model.getValueAt(index, 3)));
    }//GEN-LAST:event_tbl_homeListProductMouseClicked

    private void btn_meSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_meSaveMouseClicked
        // TODO add your handling code here:
        //boolean check = true;
        if (txt_meName.getText().equals("") || txt_mePhone.getText().equals("") || txt_meAddress.getText().equals("") || txt_meEmail.getText().equals("")) {
            //check = false;
            JOptionPane.showMessageDialog(this, "Input data in correct!", "NOTIFICATION", JOptionPane.WARNING_MESSAGE);
        } else {
            String name = txt_meName.getText();
            String phone = txt_mePhone.getText();
            String address = txt_meAddress.getText();
            String email = txt_meEmail.getText();

            boolean update = UserBLL.updateInfo(USER.getId(), name, phone, address, email);

            if (update) {
                JOptionPane.showMessageDialog(this, "Update success!");
                try {
                    USER.setName(name);
                    USER.setPhone(phone);
                    USER.setAddress(address);
                    USER.setEmail(email);
                    listUser = UserBLL.updateListUser(USER.getId(), name, phone, address, email, listUser);
                    loadListStaff();
                } catch (SQLException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(this, "ERROR", "Alert", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_btn_meSaveMouseClicked

    private void btn_staffCreateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_staffCreateMouseClicked
        // TODO add your handling code here:
        boolean check = false;
        for (int i = 0; i < listFunctionUser.size(); i++) {
            if (listFunctionUser.get(i).getIdFunction().equals("btn_staffCreate")) {
                check = true;
            }
        }
        if (check) {
            if (txt_staffUserName.getText().equals("") || txt_staffName.getText().equals("") || txt_staffPhone.getText().equals("")
                    || txt_staffAddress.getText().equals("") || txt_staffEmail.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Input data in correct", "Alert", JOptionPane.WARNING_MESSAGE);
            } else if (UserDAO.checkUsername(txt_staffUserName.getText())) {
                JOptionPane.showMessageDialog(this, "User name is already exists", "Alert", JOptionPane.WARNING_MESSAGE);
            } else {
                Object roleName = cb_staffRole.getSelectedItem();
                String selectedRole = "";
                if (roleName != null) {
                    selectedRole = roleName.toString();
                }

                listUser.add(UserBLL.createUser(
                        txt_staffUserName.getText(), txt_staffName.getText(), txt_staffPhone.getText(), txt_staffAddress.getText(),
                        txt_staffEmail.getText(), selectedRole));

                JOptionPane.showMessageDialog(this, "Password is: 123");
                try {
                    loadListStaff();    // reload table in list staff
                } catch (SQLException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else
            JOptionPane.showMessageDialog(this, "You're not authorizeed", "DANGER!", JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_btn_staffCreateMouseClicked

    private void btn_staffRepairMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_staffRepairMouseClicked
        // TODO add your handling code here:
        //boolean check = true;
        boolean check = false;
        for (int i = 0; i < listFunctionUser.size(); i++) {
            if (listFunctionUser.get(i).getIdFunction().equals("btn_staffRepair")) {
                check = true;
            }
        }
        if (check) {
            if (txt_staffID_real.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please choose staff to change!");
            } else {
                Object roleName = cb_staffRole.getSelectedItem();
                String selectedRole = "";
                if (roleName != null) {
                    selectedRole = roleName.toString();
                }
                boolean stt = ckb_staffSTT.isSelected();

                if (txt_staffName.getText().equals("") || txt_staffPhone.getText().equals("") || txt_staffAddress.getText().equals("") || txt_staffEmail.getText().equals("")) {
                    JOptionPane.showMessageDialog(this, "Input data in correct!", "NOTIFICATION", JOptionPane.WARNING_MESSAGE);
                } else {
                    boolean update = UserBLL.updateUser(Long.parseLong(txt_staffID_real.getText()), txt_staffName.getText(), txt_staffPhone.getText(),
                            txt_staffAddress.getText(), txt_staffEmail.getText(), selectedRole, stt);
                    if (update) {

                        try {
                            loadListStaff();
                            loadCBRoleStaff();
                            JOptionPane.showMessageDialog(this, "Update success!");
                        } catch (SQLException ex) {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "ERROR", "Alert", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        } else
            JOptionPane.showMessageDialog(this, "You're not authorizeed", "DANGER!", JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_btn_staffRepairMouseClicked

    private void btn_suplierCreateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_suplierCreateMouseClicked
        // TODO add your handling code here:
        boolean check = false;
        for (int i = 0; i < listFunctionUser.size(); i++) {
            if (listFunctionUser.get(i).getIdFunction().equals("btn_suplierCreate")) {
                check = true;
            }
        }
        if (check) {
            if (txt_suplierName.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please enter suplier name", "Alert", JOptionPane.WARNING_MESSAGE);
            } else if (!MainController.isNumber(txt_suplierPhone.getText().toString())) {
                JOptionPane.showMessageDialog(this, "Please enter input", "Alert", JOptionPane.WARNING_MESSAGE);
            } else {
                if (SuplierBLL.checkDB(txt_suplierName.getText())) {
                    listSuplier = SuplierBLL.createSuplier(txt_suplierName.getText(), txt_suplierAddress.getText(), txt_suplierPhone.getText(),
                            txt_suplierEmail.getText(), listSuplier);
                    JOptionPane.showMessageDialog(this, "Add new suplier success!");
                    loadListSuplier();
                    loadCBSuplierProduct();
                } else {
                    JOptionPane.showMessageDialog(this, "Suplier is already in the database", "Alert", JOptionPane.WARNING_MESSAGE);
                }
            }
        } else
            JOptionPane.showMessageDialog(this, "You're not authorizeed", "DANGER!", JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_btn_suplierCreateMouseClicked

    private void btn_suplierRepairMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_suplierRepairMouseClicked
        // TODO add your handling code here:
        boolean check = false;
        for (int i = 0; i < listFunctionUser.size(); i++) {
            if (listFunctionUser.get(i).getIdFunction().equals("btn_suplierRepair")) {
                check = true;
            }
        }
        if (check) {
            if (txt_suplierID.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please choose suplier to change!");
            } else {
                boolean stt = ckb_suplierSTT.isSelected();

                if (txt_suplierName.getText().equals("")) {
                    JOptionPane.showMessageDialog(this, "Please enter suplier name!", "NOTIFICATION", JOptionPane.WARNING_MESSAGE);
                } else {

                    listSuplier = SuplierBLL.updateSuplier(Long.parseLong(txt_suplierID.getText()), txt_suplierName.getText(), txt_suplierAddress.getText(),
                            txt_suplierPhone.getText(), txt_suplierEmail.getText(), stt, listSuplier);

                    JOptionPane.showMessageDialog(this, "Update success!");
                    loadListSuplier();
                    loadCBSuplierProduct();
                }
            }
        } else
            JOptionPane.showMessageDialog(this, "You're not authorizeed", "DANGER!", JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_btn_suplierRepairMouseClicked

    private void btn_productCreateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_productCreateMouseClicked
        // TODO add your handling code here:
        boolean check = false;
        for (int i = 0; i < listFunctionUser.size(); i++) {
            if (listFunctionUser.get(i).getIdFunction().equals("btn_productCreate")) {
                check = true;
            }
        }
        if (check) {
            if (txt_productName.getText().equals("") || txt_productPrice.getText().equals("") || txt_productQuantity.getText().equals("")) {

            } else if (ProductDAO.checkProductName(txt_productName.getText())) {
                JOptionPane.showMessageDialog(this, "the product is already in the database!", "Alert", JOptionPane.WARNING_MESSAGE);
            } else {
                if (!MainController.isNumber(txt_productQuantity.getText())) {
                    JOptionPane.showMessageDialog(this, "Please into a number", "Alert", JOptionPane.WARNING_MESSAGE);
                    txt_productQuantity.setText("");
                } else if (Double.parseDouble(txt_productQuantity.getText()) < 0) {
                    JOptionPane.showMessageDialog(this, "Quantity can not < 0", "Alert", JOptionPane.WARNING_MESSAGE);
                } else if (!MainController.isNumber(txt_productPrice.getText())) {
                    JOptionPane.showMessageDialog(this, "Please into a number", "Alert", JOptionPane.WARNING_MESSAGE);
                    txt_productPrice.setText("");
                } else if (Double.parseDouble(txt_productPrice.getText()) <= 0) {
                    JOptionPane.showMessageDialog(this, "Please enter true data", "Alert", JOptionPane.WARNING_MESSAGE);
                } else {
                    Object suplierName = cb_productSuplier.getSelectedItem();
                    String selectedSuplier = "";
                    if (suplierName != null) {
                        selectedSuplier = suplierName.toString();
                    }
                    long suplierId = SuplierDAO.getIDSuplier(selectedSuplier);

                    listProduct = ProductBLL.createProduct(txt_productName.getText(), txt_productPrice.getText(),
                            selectedSuplier, suplierId, listProduct);

                    JOptionPane.showMessageDialog(this, "Add new product success!");
                    loadListProduct();
                    load_homeListProduct();
                    load_billListProduct();
                    load_outOutStock();
                }
            }
        } else
            JOptionPane.showMessageDialog(this, "You're not authorizeed", "DANGER!", JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_btn_productCreateMouseClicked

    private void btn_productRepairMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_productRepairMouseClicked
        // TODO add your handling code here:
        boolean check = false;
        for (int i = 0; i < listFunctionUser.size(); i++) {
            if (listFunctionUser.get(i).getIdFunction().equals("btn_productRepair")) {
                check = true;
            }
        }
        if (check) {
            if (txt_productID.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please choose product to change!");
            } else if (txt_productName.getText().equals("") || txt_productPrice.getText().equals("")
                    || txt_productQuantity.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please enter true data!");
            } else {
                Object suplierName = cb_productSuplier.getSelectedItem();
                String selectedSuplier = "";
                if (suplierName != null) {
                    selectedSuplier = suplierName.toString();
                }

                boolean stt = ckb_productSTT.isSelected();

                listProduct = ProductBLL.updateProduct(txt_productID.getText(), txt_productName.getText(), txt_productPrice.getText(),
                        txt_productQuantity.getText(), selectedSuplier, stt, listProduct);

                JOptionPane.showMessageDialog(this, "Update success!");
                loadListProduct();
                load_homeListProduct();
                load_billListProduct();
            }
        } else
            JOptionPane.showMessageDialog(this, "You're not authorizeed", "DANGER!", JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_btn_productRepairMouseClicked

    private void btn_BillPlusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_BillPlusMouseClicked
        // TODO add your handling code here:
        if (FormatNumber.checkNumber(txt_billProductQuantity.getText())) {
            int soluong = Integer.parseInt(txt_billProductQuantity.getText());
            //int soluonggoc = Main.soluonggoc;
            int soluongdaco = 0;
            for (int i = 0; i < lstProductInCart.size(); i++) {
                if (lstProductInCart.get(i).getIdProduct() == Long.parseLong(txt_billProductID.getText().toString())) {
                    soluongdaco = lstProductInCart.get(i).getsL();
                }
            }
            if (txt_billProductID.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Choose product to add!");
            } else if (txt_billProductQuantity.getText().equals("0")) {
                JOptionPane.showMessageDialog(this, "Choose quantity of the product to add!");
            } else if ((soluongdaco + soluong) > soluonggoc) {
                JOptionPane.showMessageDialog(this, "Product not enough!", "Alert", JOptionPane.WARNING_MESSAGE);
            } else {
                if (BillBLL.checkProductInList(lstProductInCart, txt_billProductID.getText())) {    //Nếu SP đó đã có trong giỏ
                    for (int i = 0; i < lstProductInCart.size(); i++) {
                        if (lstProductInCart.get(i).getIdProduct() == Long.parseLong(txt_billProductID.getText())) {    //Tìm sản phẩm
                            double newAddPrice = ProductDAO.getProduct(Long.parseLong(txt_billProductID.getText())).getPrice(); //Lấy giá mới
                            int newAddQuantity = Integer.parseInt(txt_billProductQuantity.getText());   // Lấy SL mới
                            double oldPrice = Double.parseDouble(Currency.splitCurrency(txt_billTotalPrice.getText())); //Lấy tổng tiền cũ
                            oldPrice += (newAddPrice * newAddQuantity);

                            txt_billTotalPrice.setText(currency.format(oldPrice));
                            lstProductInCart.get(i).setTotal(lstProductInCart.get(i).getTotal() + (newAddPrice * newAddQuantity));
                            lstProductInCart.get(i).setsL(lstProductInCart.get(i).getsL() + newAddQuantity);
                            loadListProductInCart();
                        }
                    }
                } else {
                    BillInfo info = new BillInfo(); //Thêm mới SP
                    info.setIdProduct(Long.parseLong(txt_billProductID.getText()));
                    double price = Double.parseDouble(Currency.splitCurrency(txt_billProductPrice.getText()));
                    info.setPrice(price);
                    int sl = Integer.parseInt(txt_billProductQuantity.getText());
                    info.setsL(sl);
                    double total = price * sl;
                    info.setTotal(total);
                    lstProductInCart.add(info);
                    double totalBill = Double.parseDouble(Currency.splitCurrency(txt_billTotalPrice.getText()));
                    double priceProduct = Double.parseDouble(Currency.splitCurrency(txt_billProductTotal.getText()));
                    totalBill += priceProduct;
                    txt_billTotalPrice.setText(currency.format(totalBill));

                    loadListProductInCart();
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Input quantity muste be a number", "Alert", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btn_BillPlusMouseClicked

    private void btn_BillSubtractMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_BillSubtractMouseClicked
        // TODO add your handling code here:
        if (txt_billProductID.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "No products!", "Alert", JOptionPane.WARNING_MESSAGE);
        } else if (MainController.checkProductInList(lstProductInCart, Long.parseLong(txt_billProductID.getText()))) {  // Kiểm tra SP có trong giỏ hay không
            for (int i = 0; i < lstProductInCart.size(); i++) {
                if (lstProductInCart.get(i).getIdProduct() == Long.parseLong(txt_billProductID.getText())) {    //Tìm kiếm SP đó
                    int quanToRemove = Integer.parseInt(txt_billProductQuantity.getText()); // get SL cần remove
                    int currentQuan = lstProductInCart.get(i).getsL();  // Lấy SL hiện tại trong giỏ
                    if (currentQuan < quanToRemove) {
                        JOptionPane.showMessageDialog(this, "Quantity to remove must be less than current quantity in list!", "Alert", JOptionPane.WARNING_MESSAGE);
                    } else if (quanToRemove == currentQuan) {   // Nếu SL remove = SL trong giỏ => remove cả SP ra khỏi giỏ
                        double currentTotal = Double.parseDouble(txt_billTotalPrice.getText());
                        currentTotal -= lstProductInCart.get(i).getTotal();
                        txt_billTotalPrice.setText(String.valueOf(currentTotal));
                        lstProductInCart.remove(lstProductInCart.get(i));

                        loadListProductInCart();
                    } else {
                        double currentTotal = Double.parseDouble(txt_billTotalPrice.getText());

                        lstProductInCart.get(i).setsL(currentQuan - quanToRemove);
                        lstProductInCart.get(i).setTotal(lstProductInCart.get(i).getPrice() * lstProductInCart.get(i).getsL());

                        currentTotal -= lstProductInCart.get(i).getTotal();
                        txt_billTotalPrice.setText(String.valueOf(currentTotal));
                        loadListProductInCart();
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Not have this product in list item!", "Alert", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btn_BillSubtractMouseClicked

    private void btn_billCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_billCancelMouseClicked
        // TODO add your handling code here:
        int clear = JOptionPane.showConfirmDialog(this, "Do you want to clear cart?");
        if (clear == JOptionPane.YES_OPTION) {
            lstProductInCart.removeAll(lstProductInCart);
            loadListProductInCart();

            txt_billProductID.setText("");
            txt_billProductName.setText("");
            txt_billProductPrice.setText("0");
            txt_billProductTotal.setText("0");
            txt_billProductQuantity.setText("1");
            txt_billTotalPrice.setText("0");
        }
    }//GEN-LAST:event_btn_billCancelMouseClicked

    private void btn_billCreateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_billCreateMouseClicked
        // TODO add your handling code here:
        if (lstProductInCart.size() > 0) {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure to pay this bill?");
            if (confirm == JOptionPane.YES_OPTION) {
                listProduct = BillBLL.createBill(lstProductInCart, USER.getId());
                JOptionPane.showMessageDialog(this, "Create bill success!");

                lstProductInCart.removeAll(lstProductInCart);

                loadListProductInCart();
                load_billListProduct();
                try {
                    load_recentBill();
                } catch (SQLException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                load_outOutStock();
                load_homeListProduct();
                loadListProduct();

                txt_billTotalPrice.setText("0");
                txt_billProductID.setText("");
                txt_billProductName.setText("");
                txt_billProductPrice.setText("0");
                txt_billProductQuantity.setText("1");
                txt_billProductTotal.setText("0");
            }
        } else {
            JOptionPane.showMessageDialog(this, "No products!");
        }


    }//GEN-LAST:event_btn_billCreateMouseClicked

    private void txt_billProductQuantityKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_billProductQuantityKeyReleased
        // TODO add your handling code here:
        if (FormatNumber.checkNumber(txt_billProductQuantity.getText())) {
            if (txt_billProductQuantity.getText().equals("")) {
                txt_billProductQuantity.setText("1");
            } else {
                int sl = Integer.parseInt(txt_billProductQuantity.getText());
                String strPrice = Currency.splitCurrency(txt_billProductPrice.getText());
                double price = Double.parseDouble(strPrice);
                double total = sl * price;
                txt_billProductTotal.setText(currency.format(total));
            }
        } else {
            JOptionPane.showMessageDialog(this, "Input quantity must be a number", "Alert", JOptionPane.WARNING_MESSAGE);
            txt_billProductQuantity.setText("1");
            String strPrice = Currency.splitCurrency(txt_billProductPrice.getText());
            double price = Double.parseDouble(strPrice);
            txt_billProductTotal.setText(currency.format(price));
        }


    }//GEN-LAST:event_txt_billProductQuantityKeyReleased

    private void txt_HomesearchboxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_HomesearchboxKeyReleased
        // TODO add your handling code here:
        String value = txt_Homesearchbox.getText();
        searchHome(value);
    }//GEN-LAST:event_txt_HomesearchboxKeyReleased

    private void txt_BotsearchboxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_BotsearchboxKeyReleased
        // TODO add your handling code here:
        String value = txt_Botsearchbox.getText();
        searchBillProduct(value);
    }//GEN-LAST:event_txt_BotsearchboxKeyReleased

    private void txt_productsearchboxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_productsearchboxKeyReleased
        // TODO add your handling code here:
        String value = txt_productsearchbox.getText();
        searchProduct(value);
    }//GEN-LAST:event_txt_productsearchboxKeyReleased

    private void txt_suplierSearchboxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_suplierSearchboxKeyReleased
        // TODO add your handling code here:
        String value = txt_suplierSearchbox.getText();
        searchSuplier(value);
    }//GEN-LAST:event_txt_suplierSearchboxKeyReleased

    private void txt_customerSearchboxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_customerSearchboxKeyReleased
        // TODO add your handling code here:
        String value = txt_customerSearchbox.getText();
        searchCustomer(value);
    }//GEN-LAST:event_txt_customerSearchboxKeyReleased

    private void txt_staffSearchboxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_staffSearchboxKeyReleased
        // TODO add your handling code here:
        String value = txt_staffSearchbox.getText();
        searchStaff(value);
    }//GEN-LAST:event_txt_staffSearchboxKeyReleased

    private void btn_exitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_exitMouseClicked
        // TODO add your handling code here:
        int close = JOptionPane.showConfirmDialog(this, "Do you want to exit?");
        if (close == JOptionPane.YES_OPTION) {
            new Login().setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_btn_exitMouseClicked

    private void btn_staffDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_staffDeleteMouseClicked
        // TODO add your handling code here:
        txt_staffUserName.setText("");
        txt_staffID_real.setText("");
        txt_staffName.setText("");
        txt_staffPhone.setText("");
        txt_staffAddress.setText("");
        txt_staffEmail.setText("");
    }//GEN-LAST:event_btn_staffDeleteMouseClicked

    private void btn_customerDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_customerDeleteMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_btn_customerDeleteMouseClicked

    private void btn_suplierDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_suplierDeleteMouseClicked
        // TODO add your handling code here:
        txt_suplierID.setText("");
        txt_suplierName.setText("");
        txt_suplierAddress.setText("");
        txt_suplierPhone.setText("");
        txt_suplierEmail.setText("");
    }//GEN-LAST:event_btn_suplierDeleteMouseClicked

    private void btn_productDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_productDeleteMouseClicked
        // TODO add your handling code here:
        txt_productID.setText("");
        txt_productName.setText("");
        txt_productPrice.setText("");
        txt_productQuantity.setText("");
    }//GEN-LAST:event_btn_productDeleteMouseClicked

    private void txt_productPriceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_productPriceKeyPressed
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (Character.isLetter(c) || c == KeyEvent.VK_SPACE) {
            txt_productPrice.setEditable(false);
        } else {
            txt_productPrice.setEditable(true);
        }
    }//GEN-LAST:event_txt_productPriceKeyPressed

    private void txt_productQuantityKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_productQuantityKeyPressed
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (Character.isLetter(c) || c == KeyEvent.VK_SPACE) {
            txt_productQuantity.setEditable(false);
        } else {
            txt_productQuantity.setEditable(true);
        }
    }//GEN-LAST:event_txt_productQuantityKeyPressed

    private void txt_suplierPhoneKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_suplierPhoneKeyPressed
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (Character.isLetter(c) || c == KeyEvent.VK_SPACE) {
            txt_suplierPhone.setEditable(false);
        } else {
            txt_suplierPhone.setEditable(true);
        }
    }//GEN-LAST:event_txt_suplierPhoneKeyPressed

    private void txt_billProductQuantityKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_billProductQuantityKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_txt_billProductQuantityKeyPressed

    private void btn_meRoleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_meRoleMouseClicked
        // TODO add your handling code here:
        boolean check = false;
        for (int i = 0; i < listFunctionUser.size(); i++) {
            if (listFunctionUser.get(i).getIdFunction().equals("btn_meRole")) {
                check = true;
            }
        }
        if (check)
            switchPanels(pnlTopUserRole, pnlBotUserRole);
        else
            JOptionPane.showMessageDialog(this, "You're not authorizeed", "DANGER!", JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_btn_meRoleMouseClicked

    private void btn_roleRemoveFunctionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_roleRemoveFunctionActionPerformed
        // TODO add your handling code here:
        //int index = lst_roleFuction.getSelectedIndex();
        //ListModel model = lst_roleFuction.getModel();
        //String FuncName = String.valueOf(model.getElementAt(index));

        int indexRoleName = lst_roleRoleName.getSelectedIndex();
        ListModel modelRoleName = lst_roleRoleName.getModel();
        

        int indexRoleFunc = lst_roleRoleFunction.getSelectedIndex();
        ListModel modelFunc = lst_roleRoleFunction.getModel();
        
        
        int indexListFunc = lst_roleFuction.getSelectedIndex();

        if (indexRoleFunc < 0) {
            JOptionPane.showMessageDialog(this, "Please choose function to remove", "Alert", JOptionPane.WARNING_MESSAGE);
        } else if(indexListFunc < 0){
            
        }else if (listRoleFunc.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please choose role to remove", "Alert", JOptionPane.WARNING_MESSAGE);
        } else if (listRoleFunc.size() == 1) {
            JOptionPane.showMessageDialog(this, "Error!", "Alert", JOptionPane.WARNING_MESSAGE);
        } else {
            String FuncName = String.valueOf(modelFunc.getElementAt(indexRoleFunc));
            String roleName = String.valueOf(modelRoleName.getElementAt(indexRoleName));
            int a = JOptionPane.showConfirmDialog(this, "Do you to remove '" + String.valueOf(modelFunc.getElementAt(indexRoleFunc)) + "'?");
            if (a == JOptionPane.YES_OPTION) {
                if (!RoleBLL.removeFunction(FuncName, roleName)) {
                    JOptionPane.showMessageDialog(this, "Error!", "Alert", JOptionPane.WARNING_MESSAGE);
                } else {
                    //ListModel model = lst_roleRoleFunction.getModel();
                    //String FuncName = String.valueOf(model.getElementAt(index));
                    //System.out.println(index);
                    listRoleFunc.remove(listRoleFunc.get(indexRoleFunc));
                    loadListRoleFunction(listRoleFunc);

                    listFunctionUser = RoleDAO.getListRoleFunction(USER.getRoleID());
                    listFunctionChange = RoleDAO.getListFunction();
                    for (int i = 0; i < listFunctionChange.size(); i++) {
                        for (int j = 0; j < listRoleFunc.size(); j++) {
                            if (listFunctionChange.get(i).getFunctionName().equals(listRoleFunc.get(j))) {
                                listFunctionChange.remove(i);
                            }
                        }
                    }

                    loadListFunction(listFunctionChange);
                }

            }

        }
    }//GEN-LAST:event_btn_roleRemoveFunctionActionPerformed

    private void btn_roleAddFunctionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_roleAddFunctionActionPerformed
        // TODO add your handling code here:
        int indexFunc = lst_roleRoleFunction.getSelectedIndex();
        int indexListFunc = lst_roleFuction.getSelectedIndex();
        
        if (listRoleFunc.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please choose role to add", "Alert", JOptionPane.WARNING_MESSAGE);
        } else if(indexFunc < 0 || indexListFunc < 0){
            
        }
        else {
            int indexRoleName = lst_roleRoleName.getSelectedIndex();
            ListModel modelRoleName = lst_roleRoleName.getModel();
            String roleName = String.valueOf(modelRoleName.getElementAt(indexRoleName));

            int index = lst_roleFuction.getSelectedIndex();
            ListModel model = lst_roleFuction.getModel();
            String FuncName = String.valueOf(model.getElementAt(index));

            boolean check = true;
            for (int i = 0; i < listRoleFunc.size(); i++) {
                if (FuncName.equals(listRoleFunc.get(i))) {
                    check = false;
                }
            }
            if (check) {
                int a = JOptionPane.showConfirmDialog(this, "Do you want to add '" + FuncName + "' to this role?");
                if (a == JOptionPane.YES_OPTION) {
                    if (!RoleBLL.insertFunction(FuncName, roleName)) {
                        JOptionPane.showMessageDialog(this, "Error!", "Alert", JOptionPane.WARNING_MESSAGE);
                    } else {
                        listRoleFunc.add(FuncName);
                        loadListRoleFunction(listRoleFunc);

                        listFunctionChange.remove(listFunctionChange.get(index));

                        listFunctionUser = RoleDAO.getListRoleFunction(USER.getRoleID());
                        loadListFunction(listFunctionChange);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "This role already exist", "Alert", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_btn_roleAddFunctionActionPerformed

    private void btn_roleFunctionShowallMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_roleFunctionShowallMouseClicked
        // TODO add your handling code here:
        loadListFunction(listFunction);
    }//GEN-LAST:event_btn_roleFunctionShowallMouseClicked

    private void btn_roleDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_roleDeleteMouseClicked
        // TODO add your handling code here:
        int index = lst_roleRoleName.getSelectedIndex();
        String roleName = txt_roleRolename.getText();
        if (roleName.equals("") || index < 0) {
            JOptionPane.showMessageDialog(this, "Please choose role to delete", "Alert", JOptionPane.WARNING_MESSAGE);
        } else {
            int a = JOptionPane.showConfirmDialog(this, "Do you want to delete ' +" + roleName + "' ?");
            if (a == JOptionPane.YES_OPTION) {
                if (RoleBLL.deleteRole(roleName)) {
                    listRole = RoleDAO.getAllBySTT(true);
                    loadListRole();
                    JOptionPane.showMessageDialog(this, "Delete success", "Alert", JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "ERORR", "Alert", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_btn_roleDeleteMouseClicked

    private void btn_roleRepairMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_roleRepairMouseClicked
        // TODO add your handling code here:
        String roleName = txt_roleRolename.getText();

        int index = lst_roleRoleName.getSelectedIndex();
        ListModel model = lst_roleRoleName.getModel();

        if (roleName.equals("") || index < 0)
            JOptionPane.showMessageDialog(this, "Please choose role to update", "Alert", JOptionPane.WARNING_MESSAGE);
        else {
            String oldName = String.valueOf(model.getElementAt(index));
            int check = 0;      // 0. update    1. Đã tồn tại   -1. Không update (trùng tên với oldName
            int position = -1;
            for (int i = 0; i < listRole.size(); i++) {
                if (listRole.get(i).getRoleName().equals(roleName) && !roleName.equals(oldName)) {    // Kiểm tra tên role đó đã tồn tại chưa
                    check = 1;
                } else if (roleName.equals(oldName)) {
                    check = -1;
                }
                if (listRole.get(i).getRoleName().equals(oldName)) {
                    position = i;
                }
            }
            if (check == 1) {
                JOptionPane.showMessageDialog(this, "This role name is already exist!", "Alert", JOptionPane.WARNING_MESSAGE);
            } else if (check == 0) {
                if (RoleBLL.changeRoleName(roleName, oldName)) {
                    listRole = RoleDAO.getAllBySTT(true);
                    loadListRole();
                    JOptionPane.showMessageDialog(this, "Update success", "Alert", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_btn_roleRepairMouseClicked

    private void btn_roleCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_roleCreateActionPerformed
        // TODO add your handling code here:
        String roleName = txt_roleRolename.getText();
        if (roleName.equals(""))
            JOptionPane.showMessageDialog(this, "Please enter role name!", "Alert", JOptionPane.WARNING_MESSAGE);
        else {
            boolean check = true;
            for (int i = 0; i < listRole.size(); i++) {
                if (listRole.get(i).getRoleName().equals(roleName)) {
                    check = false;
                }
            }
            if (check) {
                if (RoleBLL.insertRole(roleName)) {
                    JOptionPane.showMessageDialog(this, "Add new role success!", "Alert", JOptionPane.WARNING_MESSAGE);
                    listRole = RoleDAO.getAllBySTT(true);
                    loadListRole();
                } else {
                    JOptionPane.showMessageDialog(this, "ERORR!", "Alert", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "This role already exist!", "Alert", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_btn_roleCreateActionPerformed

    private void btn_roleCreateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_roleCreateMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_roleCreateMouseClicked

    private void lst_roleRoleNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lst_roleRoleNameMouseClicked
        // TODO add your handling code here:
        listFunctionChange = RoleDAO.getListFunction();

        int index = lst_roleRoleName.getSelectedIndex();
        ListModel model = lst_roleRoleName.getModel();
        txt_roleRolename.setText(String.valueOf(model.getElementAt(index)));

        //DefaultListModel dm = new DefaultListModel();
        listRoleFunc = RoleBLL.getRoleFunction(String.valueOf(model.getElementAt(index)));

        loadListRoleFunction(listRoleFunc);

        for (int i = 0; i < listFunctionChange.size(); i++) {
            for (int j = 0; j < listRoleFunc.size(); j++) {
                if (listFunctionChange.get(i).getFunctionName().equals(listRoleFunc.get(j))) {
                    listFunctionChange.remove(i);
                }
            }
        }

        //for (int i = 0; i < tempListFunc.size(); i++) {
        //    System.out.println(tempListFunc.get(i).getFunctionName());
        //}
        loadListFunction(listFunctionChange);
    }//GEN-LAST:event_lst_roleRoleNameMouseClicked

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Main().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_BillPlus;
    private javax.swing.JButton btn_BillSubtract;
    private javax.swing.JButton btn_BillUpdate;
    private javax.swing.JButton btn_billCancel;
    private javax.swing.JButton btn_billCreate;
    private javax.swing.JButton btn_create;
    private javax.swing.JButton btn_customer;
    private javax.swing.JButton btn_customerCreate;
    private javax.swing.JButton btn_customerDelete;
    private javax.swing.JButton btn_customerRepair;
    private javax.swing.JButton btn_exit;
    private javax.swing.JButton btn_home;
    private javax.swing.JButton btn_meCancel;
    private javax.swing.JButton btn_meChangepass;
    private javax.swing.JButton btn_meReport;
    private javax.swing.JButton btn_meRole;
    private javax.swing.JButton btn_meSave;
    private javax.swing.JButton btn_product;
    private javax.swing.JButton btn_productCreate;
    private javax.swing.JButton btn_productDelete;
    private javax.swing.JButton btn_productRepair;
    private javax.swing.JButton btn_roleAddFunction;
    private javax.swing.JButton btn_roleCreate;
    private javax.swing.JButton btn_roleDelete;
    private javax.swing.JButton btn_roleFunctionShowall;
    private javax.swing.JButton btn_roleRemoveFunction;
    private javax.swing.JButton btn_roleRepair;
    private javax.swing.JButton btn_staff;
    private javax.swing.JButton btn_staffChangepass;
    private javax.swing.JButton btn_staffCreate;
    private javax.swing.JButton btn_staffDelete;
    private javax.swing.JButton btn_staffRepair;
    private javax.swing.JButton btn_suplier;
    private javax.swing.JButton btn_suplierCreate;
    private javax.swing.JButton btn_suplierDelete;
    private javax.swing.JButton btn_suplierRepair;
    private javax.swing.JButton btn_user;
    private javax.swing.JComboBox<String> cb_productSuplier;
    private javax.swing.JComboBox<String> cb_staffRole;
    private javax.swing.JCheckBox ckb_productSTT;
    private javax.swing.JCheckBox ckb_staffSTT;
    private javax.swing.JCheckBox ckb_suplierSTT;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator19;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator20;
    private javax.swing.JSeparator jSeparator21;
    private javax.swing.JSeparator jSeparator22;
    private javax.swing.JSeparator jSeparator23;
    private javax.swing.JSeparator jSeparator24;
    private javax.swing.JSeparator jSeparator25;
    private javax.swing.JSeparator jSeparator26;
    private javax.swing.JSeparator jSeparator27;
    private javax.swing.JSeparator jSeparator28;
    private javax.swing.JSeparator jSeparator29;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator30;
    private javax.swing.JSeparator jSeparator31;
    private javax.swing.JSeparator jSeparator32;
    private javax.swing.JSeparator jSeparator33;
    private javax.swing.JSeparator jSeparator34;
    private javax.swing.JSeparator jSeparator35;
    private javax.swing.JSeparator jSeparator36;
    private javax.swing.JSeparator jSeparator37;
    private javax.swing.JSeparator jSeparator38;
    private javax.swing.JSeparator jSeparator39;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator40;
    private javax.swing.JSeparator jSeparator41;
    private javax.swing.JSeparator jSeparator42;
    private javax.swing.JSeparator jSeparator43;
    private javax.swing.JSeparator jSeparator44;
    private javax.swing.JSeparator jSeparator45;
    private javax.swing.JSeparator jSeparator46;
    private javax.swing.JSeparator jSeparator47;
    private javax.swing.JSeparator jSeparator48;
    private javax.swing.JSeparator jSeparator49;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator50;
    private javax.swing.JSeparator jSeparator51;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JLayeredPane layeredPaneBot;
    private javax.swing.JLayeredPane layeredPaneTop;
    private javax.swing.JList<String> lst_roleFuction;
    private javax.swing.JList<String> lst_roleRoleFunction;
    private javax.swing.JList<String> lst_roleRoleName;
    private javax.swing.JPanel pnlBotBill;
    private javax.swing.JPanel pnlBotCustomer;
    private javax.swing.JPanel pnlBotHome;
    private javax.swing.JPanel pnlBotHomeSearch;
    private javax.swing.JPanel pnlBotMe;
    private javax.swing.JPanel pnlBotProduct;
    private javax.swing.JPanel pnlBotStaff;
    private javax.swing.JPanel pnlBotSuplier;
    private javax.swing.JPanel pnlBotUserRole;
    private javax.swing.JPanel pnlBottom;
    private javax.swing.JPanel pnlCenter;
    private javax.swing.JPanel pnlRoot;
    private javax.swing.JPanel pnlSide;
    private javax.swing.JPanel pnlTop;
    private javax.swing.JPanel pnlTopBill;
    private javax.swing.JPanel pnlTopCustomer;
    private javax.swing.JPanel pnlTopHome;
    private javax.swing.JPanel pnlTopMe;
    private javax.swing.JPanel pnlTopProduct;
    private javax.swing.JPanel pnlTopStaff;
    private javax.swing.JPanel pnlTopSuplier;
    private javax.swing.JPanel pnlTopUserRole;
    private javax.swing.JTable tbl_ListCustomer;
    private javax.swing.JTable tbl_ListProductOutOfStock;
    private javax.swing.JTable tbl_billListItem;
    private javax.swing.JTable tbl_billProduct;
    private javax.swing.JTable tbl_homeListProduct;
    private javax.swing.JTable tbl_productList;
    private javax.swing.JTable tbl_recentbill;
    private javax.swing.JTable tbl_staffLstStaff;
    private javax.swing.JTable tbl_suplierList;
    private javax.swing.JTextField txt_Botsearchbox;
    private javax.swing.JTextField txt_Homesearchbox;
    private javax.swing.JTextField txt_billProductID;
    private javax.swing.JTextField txt_billProductName;
    private javax.swing.JTextField txt_billProductPrice;
    private javax.swing.JTextField txt_billProductQuantity;
    private javax.swing.JTextField txt_billProductTotal;
    private javax.swing.JTextField txt_billTotalPrice;
    private javax.swing.JTextField txt_customerSearchbox;
    private javax.swing.JTextField txt_homeProductID;
    private javax.swing.JTextField txt_homeProductName;
    private javax.swing.JTextField txt_homeProductPrice;
    private javax.swing.JTextField txt_homeProductQuantity;
    private javax.swing.JTextField txt_meAddress;
    private javax.swing.JTextField txt_meEmail;
    private javax.swing.JTextField txt_meName;
    private javax.swing.JTextField txt_mePhone;
    private javax.swing.JTextField txt_meRole;
    private javax.swing.JTextField txt_meUsername;
    private javax.swing.JTextField txt_productID;
    private javax.swing.JTextField txt_productName;
    private javax.swing.JTextField txt_productPrice;
    private javax.swing.JTextField txt_productQuantity;
    private javax.swing.JTextField txt_productsearchbox;
    private javax.swing.JTextField txt_roleRolename;
    private javax.swing.JTextField txt_staffAddress;
    private javax.swing.JTextField txt_staffEmail;
    private javax.swing.JPanel txt_staffID;
    private javax.swing.JTextField txt_staffID_real;
    private javax.swing.JTextField txt_staffName;
    private javax.swing.JTextField txt_staffPhone;
    private javax.swing.JTextField txt_staffSearchbox;
    private javax.swing.JTextField txt_staffUserName;
    private javax.swing.JTextField txt_suplierAddress;
    private javax.swing.JTextField txt_suplierEmail;
    private javax.swing.JTextField txt_suplierID;
    private javax.swing.JTextField txt_suplierName;
    private javax.swing.JTextField txt_suplierPhone;
    private javax.swing.JTextField txt_suplierSearchbox;
    // End of variables declaration//GEN-END:variables
}
