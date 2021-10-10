/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DTO.Role;
import DTO.RoleFunction;
import com.mycompany.projectjava.DAO.RoleDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hoang
 */
public class RoleBLL {
    public static List<String> getRoleFunction(String roleName){
        List<String> lstName = new ArrayList<>();
        
        long roleID = RoleDAO.getRoleID(roleName);
        List<RoleFunction> lstRoleFunc = RoleDAO.getListRoleFunction(roleID);
        
        //System.out.println(lstRoleFunc.size());
        for (int i = 0; i < lstRoleFunc.size(); i++) {
            lstName.add(RoleDAO.getFuncName(lstRoleFunc.get(i).getIdFunction()));
        }
        
        return lstName;
    }
    
    public static boolean insertFunction(String function, String roleName){
        String functionID = RoleDAO.getIDFunction(function);
        long idRole = RoleDAO.getRoleID(roleName);
        try {
            RoleFunction roleFunc = new RoleFunction();
            roleFunc.setIdFunction(functionID);
            roleFunc.setIdRole(idRole);
            //System.out.println(roleFunc.getIdRole() + " | " + roleFunc.getIdFunction());
            if(RoleDAO.changeInfo(roleFunc, true))
               return true;
            else
                return false;
        } catch (Exception e) {
            return false;
        }
    }
    
    public static boolean removeFunction(String function, String roleName){
        String functionID = RoleDAO.getIDFunction(function);
        long idRole = RoleDAO.getRoleID(roleName);
        try {
            RoleFunction roleFunc = new RoleFunction();
            roleFunc.setIdFunction(functionID);
            roleFunc.setIdRole(idRole);
            //System.out.println(roleFunc.getIdRole() + " | " + roleFunc.getIdFunction());
            if(RoleDAO.changeInfo(roleFunc, false))
               return true;
            else
                return false;
        } catch (Exception e) {
            return false;
        }
    }
    
    public static boolean insertRole(String roleName){
        try {
            RoleDAO.insertRole(roleName);
            long roleID = RoleDAO.getRoleID(roleName);
            RoleDAO.insertAllFuncToRole(roleID);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public static boolean changeRoleName(String newName, String oldName){
        try {
            long idRole = RoleDAO.getRoleID(oldName);
            Role role = new Role(idRole, newName, true);
            RoleDAO.changeInfo(role);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public static boolean deleteRole(String roleName){
        try {
            long idRole = RoleDAO.getRoleID(roleName);
            Role role = new Role(idRole, roleName, false);
            RoleDAO.changeInfo(role);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
