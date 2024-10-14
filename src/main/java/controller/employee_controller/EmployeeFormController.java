package controller.employee_controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import model.Employee;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeFormController implements Initializable {

    EmployeeService service = EmployeeController.getInstance();

    @FXML
    public JFXRadioButton btnRadioFemale;

    @FXML
    public JFXRadioButton btnRadioMale;

    @FXML
    private JFXButton btnRegister;

    @FXML
    private Label lblEmployeeId;

    @FXML
    private JFXTextField txtEmployeeAddress;

    @FXML
    private JFXTextField txtEmployeeConfirmPassword;

    @FXML
    private JFXTextField txtEmployeeContactNo;

    @FXML
    private JFXTextField txtEmployeeEmail;

    @FXML
    private JFXTextField txtEmployeeName;

    @FXML
    private JFXTextField txtEmployeePassword;

    @FXML
    void btnRegisterOnAction(ActionEvent event) {
        String gender = null;
        if (btnRadioMale.isSelected()){
            gender = btnRadioMale.getText();
        }else {
            gender = btnRadioFemale.getText();
        }
        if (checkPasswordConfirm() && checkEmail()) {
            service.save(new Employee(
                    lblEmployeeId.getText(),
                    txtEmployeeName.getText(),
                    txtEmployeeAddress.getText(),
                    txtEmployeeContactNo.getText(),
                    txtEmployeeEmail.getText(),
                    txtEmployeePassword.getText(),
                    gender
            ));
        }
    }

    private boolean checkEmail() {
        if (service.checkDuplicateEmail(txtEmployeeEmail.getText())) {
            return true;
        }
        return false;
    }

    private boolean checkPasswordConfirm() {
        if (service.checkPasswords(txtEmployeePassword.getText(), txtEmployeeConfirmPassword.getText())) {
            return true;
        } else {
            //TO DO
        }
        return false;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblEmployeeId.setText(service.generateId());

        ToggleGroup genderGroup = new ToggleGroup();
        btnRadioFemale.setToggleGroup(genderGroup);
        btnRadioMale.setToggleGroup(genderGroup);
    }

}
