package controller.supplier_controller;

import model.Admin;
import model.Supplier;
import util.CrudUtil;

import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

public class SupplierController implements SupplierService{

    private static SupplierController instance;

    private SupplierController(){}

    public static SupplierController getInstance(){
        return null==instance?instance = new SupplierController():instance;
    }

    public int supplierCount = 0;

    public String generateId(){
        String SQL = "SELECT COUNT(DISTINCT supplierId) FROM supplier";
        try {
            ResultSet resultSet = CrudUtil.execute(SQL);
            while (resultSet.next()){
                supplierCount = resultSet.getInt("COUNT(DISTINCT supplierId)") +1;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return String.format("A%04d",supplierCount);
    }

    @Override
    public void save(Supplier supplier) {

        String SQL = "INSERT INTO supplier VALUES(?,?,?,?,?,?)";
        try {
            CrudUtil.execute(
                    SQL,
                    supplier.getSupplierId(),
                    supplier.getName(),
                    supplier.getAddress(),
                    supplier.getCompany(),
                    supplier.getEmail(),
                    supplier.getContactNO()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean checkDuplicateEmail(String email) {
        String SQL = "SELECT * FROM supplier";
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
