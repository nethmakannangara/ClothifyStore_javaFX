package controller.employee_controller;

import model.Admin;
import model.Employee;
import util.CrudUtil;

import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

public class EmployeeController implements EmployeeService{

    private static EmployeeController instance;

    private EmployeeController(){}

    public static EmployeeController getInstance(){
        return null==instance?instance = new EmployeeController():instance;
    }

    public int employeeCount = 0;

    public String generateId(){
        String SQL = "SELECT COUNT(DISTINCT employeeId) FROM employee";
        try {
            ResultSet resultSet = CrudUtil.execute(SQL);
            while (resultSet.next()){
                employeeCount = resultSet.getInt("COUNT(DISTINCT employeeId)") +1;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return String.format("E%04d",employeeCount);
    }

    @Override
    public void save(Employee employee) {

        String SQL = "INSERT INTO employee VALUES(?,?,?,?,?,?,?)";
        try {
            CrudUtil.execute(
                    SQL,
                    employee.getEmployeeId(),
                    employee.getName(),
                    employee.getAddress(),
                    employee.getGender(),
                    employee.getEmail(),
                    employee.getContactNo(),
                    Base64.getEncoder().encodeToString(employee.getPassword().getBytes(StandardCharsets.UTF_8))
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
        String SQL = "SELECT * FROM employee";
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
