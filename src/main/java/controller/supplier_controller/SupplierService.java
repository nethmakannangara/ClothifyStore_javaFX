package controller.supplier_controller;

import model.Admin;
import model.Supplier;

public interface SupplierService {
    void save(Supplier supplier);

    boolean checkDuplicateEmail(String email);

    String generateId();
}
