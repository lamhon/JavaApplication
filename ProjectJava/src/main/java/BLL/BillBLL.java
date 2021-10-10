/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DTO.BillInfo;
import DTO.Product;
import com.mycompany.projectjava.DAO.BillDAO;
import com.mycompany.projectjava.DAO.BillInforDAO;
import com.mycompany.projectjava.DAO.ProductDAO;
import java.util.List;

/**
 *
 * @author hoang
 */
public class BillBLL {

    public static boolean checkProductInList(List<BillInfo> listBillInfos, String productId) {
        boolean check = false;
        for (int i = 0; i < listBillInfos.size(); i++) {
            if (listBillInfos.get(i).getIdProduct() == Long.parseLong(productId)) {
                check = true;
            }
        }
        return check;
    }

    public static List<Product> createBill(List<BillInfo> lstProductInCart, long userID) {
        long id = BillDAO.createBill(userID);
        for (int i = 0; i < lstProductInCart.size(); i++) {
            BillInforDAO.addBillInfo(lstProductInCart.get(i), id);
            Product product = new Product();
            product = ProductDAO.getProduct(lstProductInCart.get(i).getIdProduct());
            product.setsL(product.getsL() - lstProductInCart.get(i).getsL());
            ProductDAO.changeInfo(product);
        }
        List<Product> lst = ProductDAO.getAll();
        return  lst;
    }
}
