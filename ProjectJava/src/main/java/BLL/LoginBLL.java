/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DTO.Function;
import DTO.RoleFunction;
import DTO.User;
import com.mycompany.projectjava.DAO.MD5;
import com.mycompany.projectjava.DAO.RoleDAO;
import com.mycompany.projectjava.DAO.UserDAO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author hoang
 */
public class LoginBLL {
    public static boolean Login(String usName, String pwd) throws SQLException{
        String hash = MD5.hashMD5(pwd);
        boolean check = UserDAO.Login(usName, hash);
        
        return check;
    }
    
    public static User getUserLogin(String usName) throws SQLException{
        try {
            long idUs = UserDAO.getID(usName);
            User user = UserDAO.getUser(idUs);
            return user;
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
        
    }
    
    public static List<RoleFunction> getListFunction(long roleID){
        try {
            List<RoleFunction> lstFunc = RoleDAO.getListRoleFunction(roleID);
            return lstFunc;
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
        
    }
}
