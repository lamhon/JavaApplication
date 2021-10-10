/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DTO.Suplier;
import com.mycompany.projectjava.DAO.SuplierDAO;
import java.util.List;

/**
 *
 * @author hoang
 */
public class SuplierBLL {

    public static List<Suplier> createSuplier(String name, String address, String phone, String email, List<Suplier> listSuplier) {
        Suplier suplier = new Suplier();
        suplier.setSuplierName(name);
        suplier.setAddress(address);
        suplier.setPhone(phone);
        suplier.setEmail(email);
        suplier.setsTT(true);

        SuplierDAO.addSuplier(suplier);
        listSuplier = SuplierDAO.getAll();
        return listSuplier;
    }

    public static boolean checkDB(String name) {
        if (SuplierDAO.checkSuplierName(name)) {
            return false;
        } else {
            return true;
        }
    }

    public static List<Suplier> updateSuplier(long id, String name, String address, String phone, String email, boolean stt, List<Suplier> listSuplier) {
        Suplier suplier = new Suplier();
        
        suplier.setId(id);
        suplier.setSuplierName(name);
        suplier.setAddress(address);
        suplier.setPhone(phone);
        suplier.setEmail(email);
        suplier.setsTT(stt);
        
        if (SuplierDAO.changeInfo(suplier)) {
            for (int i = 0; i < listSuplier.size(); i++) {
                if (listSuplier.get(i).getId() == suplier.getId()) {
                    listSuplier.set(i, suplier);
                }
            }
        }
        return listSuplier;
    }
}
