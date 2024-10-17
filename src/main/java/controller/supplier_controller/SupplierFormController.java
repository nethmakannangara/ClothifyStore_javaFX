package controller.supplier_controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.Admin;
import model.Supplier;

import java.net.URL;
import java.util.ResourceBundle;

public class SupplierFormController implements Initializable {

    SupplierService service = SupplierController.getInstance();

    @FXML
    private JFXButton btnRegister;

    @FXML
    private Label lblSupplierID;

    @FXML
    private JFXTextField txtSupplierAddress;

    @FXML
    private JFXTextField txtSupplierCompany;

    @FXML
    private JFXTextField txtSupplierContactNo;

    @FXML
    private JFXTextField txtSupplierEmail;

    @FXML
    private JFXTextField txtSupplierName;

    @FXML
    void btnRegisterOnAction(ActionEvent event) {
        if (checkEmail()) {
            service.save(new Supplier(
                    lblSupplierID.getText(),
                    txtSupplierName.getText(),
                    txtSupplierAddress.getText(),
                    txtSupplierEmail.getText(),
                    txtSupplierCompany.getText(),
                    txtSupplierContactNo.getText()
            ));
        }
    }

    private boolean checkEmail() {
        if (service.checkDuplicateEmail(txtSupplierEmail.getText())) {
            return true;
        }
        return false;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblSupplierID.setText(service.generateId());
    }

}
