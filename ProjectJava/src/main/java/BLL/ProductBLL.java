/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DTO.Product;
import com.mycompany.projectjava.DAO.ProductDAO;
import com.mycompany.projectjava.DAO.SuplierDAO;
import java.util.List;

/**
 *
 * @author hoang
 */
public class ProductBLL {

    public static List<Product> createProduct(String name, String price, String quantity, long idSuplier, List<Product> listProduct) {
        Product product = new Product();

        product.setProductName(name);
        product.setPrice(Double.parseDouble(price));
        product.setsL(Integer.parseInt(quantity));
        product.setIdSuplier(idSuplier);
        product.setsTT(true);

        ProductDAO.addProduct(product);
        listProduct = ProductDAO.getAll();
        return listProduct;
    }

    public static List<Product> updateProduct(String id, String name, String price, String quantity, String suplierName, boolean stt, List<Product> lst) {
        long idSuplier = SuplierDAO.getIDSuplier(suplierName);
        
        Product product = new Product();
        
        product.setId(Long.parseLong(id));
        product.setProductName(name);
        product.setPrice(Double.parseDouble(price));
        product.setsL(Integer.parseInt(quantity));
        product.setIdSuplier(idSuplier);
        product.setsTT(stt);

        if (ProductDAO.changeInfo(product)) {
            lst = ProductDAO.getAll();
        }
        return lst;
    }
}
