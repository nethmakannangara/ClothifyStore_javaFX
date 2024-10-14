package controller.admin_controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import model.Admin;

import java.net.URL;
import java.util.ResourceBundle;

public class AddAdminFormController implements Initializable {

    AdminService service = AdminController.getInstance();

    @FXML
    public Label lblAdminID;

    @FXML
    public JFXTextField txtAdminId;

    @FXML
    private JFXButton btnRegister;

    @FXML
    private JFXTextField txtAdminConfirmPassword;

    @FXML
    private JFXTextField txtAdminEmail;

    @FXML
    private JFXTextField txtAdminName;

    @FXML
    private JFXTextField txtAdminPassword;

    @FXML
    void btnRegisterOnAction(ActionEvent event) {
        if (checkPasswordConfirm() && checkEmail()) {
            service.save(new Admin(
                    lblAdminID.getText(),
                    txtAdminName.getText(),
                    txtAdminEmail.getText(),
                    txtAdminPassword.getText()
            ));
        }
    }



    private boolean checkEmail() {
        if (service.checkDuplicateEmail(txtAdminEmail.getText())) {
            return true;
        }
        return false;
    }

    private boolean checkPasswordConfirm() {
        if (service.checkPasswords(txtAdminPassword.getText(), txtAdminConfirmPassword.getText())) {
            return true;
        } else {
            //TO DO
        }
        return false;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblAdminID.setText(service.generateId());
    }
}
