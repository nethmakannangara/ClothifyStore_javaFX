package controller.employee_controller;

import model.Admin;
import model.Employee;

public interface EmployeeService {
    void save(Employee employee);

    boolean checkPasswords(String password, String confirmationPassword);

    boolean checkDuplicateEmail(String email);

    String generateId();
}
