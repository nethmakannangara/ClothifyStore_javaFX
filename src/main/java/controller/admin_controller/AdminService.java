package controller.admin_controller;

import entity.AdminEntity;
import model.Admin;

public interface AdminService {
    void save(Admin admin);

    boolean checkPasswords(String password, String confirmationPassword);

    boolean checkDuplicateEmail(String email);

    String generateId();
}
