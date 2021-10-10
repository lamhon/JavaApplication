/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DTO.User;
import com.mycompany.projectjava.DAO.MD5;
import com.mycompany.projectjava.DAO.RoleDAO;
import com.mycompany.projectjava.DAO.UserDAO;
import java.util.List;

/**
 *
 * @author hoang
 */
public class UserBLL {
    public static boolean updateInfo(long id, String name, String phone, String address, String email){
        User changeUS = new User();
        changeUS.setName(name);
        changeUS.setPhone(phone);
        changeUS.setAddress(address);
        changeUS.setEmail(email);
        
        return UserDAO.changeInfo(id, changeUS);
    }
    
    public static List<User> updateListUser(long id, String name, String phone, String address, String email, List<User> lstUsers){
        for (int i = 0; i < lstUsers.size(); i++) {
            if (lstUsers.get(i).getId() == id) {
                lstUsers.get(i).setName(name);
                lstUsers.get(i).setPhone(phone);
                lstUsers.get(i).setAddress(address);
                lstUsers.get(i).setEmail(email);
            }
        }
        return lstUsers;
    }
    
    public static User createUser(String usname, String name, String phone, String address, String email, String roleName){
        long idRole = RoleDAO.getRoleID(roleName);
        User user = new User();
        user.setUserName(usname);
        user.setPassword(MD5.hashMD5("123"));
        user.setName(name);
        user.setPhone(phone);
        user.setAddress(address);
        user.setEmail(email);
        user.setRoleID(idRole);
        user.setsTT(true);
        UserDAO.addUser(user);
        return user;
    }
    
    public static boolean updateUser(long id, String name, String phone, String address, String email, String roleName, boolean stt){
        long roleID = RoleDAO.getRoleID(roleName);
        User user = new User();
        
        user.setId(id);
        user.setName(name);
        user.setPhone(phone);
        user.setAddress(address);
        user.setEmail(email);
        user.setRoleID(roleID);
        user.setsTT(stt);
        return UserDAO.ChangeInfoADMIN(user.getId(), user);
    }
}
