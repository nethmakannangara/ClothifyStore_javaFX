package controller.admin_controller;


import model.Admin;
import util.CrudUtil;

import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

public class AdminController implements AdminService {

    private static AdminController instance;

    private AdminController() {
    }

    public static AdminController getInstance() {
        return null == instance ? instance = new AdminController() : instance;
    }

    public int adminCount = 0;

    public String generateId(){
        String SQL = "SELECT COUNT(DISTINCT adminId) FROM admin";
        try {
            ResultSet resultSet = CrudUtil.execute(SQL);
            while (resultSet.next()){
                adminCount = resultSet.getInt("COUNT(DISTINCT adminId)") +1;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return String.format("A%04d",adminCount);
    }

    @Override
    public void save(Admin admin) {

        String SQL = "INSERT INTO admin VALUES(?,?,?,?)";
        try {
            CrudUtil.execute(
                    SQL,
                    admin.getAdminId(),
                    admin.getName(),
                    admin.getEmail(),
                    Base64.getEncoder().encodeToString(admin.getPassword().getBytes(StandardCharsets.UTF_8))
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean checkPasswords(String password, String confirmationPassword) {
        if(password.equals(confirmationPassword)){
            return true;
        }
        return false;
    }

    @Override
    public boolean checkDuplicateEmail(String email) {
        String SQL = "SELECT * FROM admin";
        try {
            ResultSet resultSet = CrudUtil.execute(SQL);
            while (resultSet.next()){
               if(resultSet.getString("email").equals(email)){
                   return false;
               }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
