package controller.item_controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Item;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class ItemFormController implements Initializable {

    ItemService service = ItemController.getInstance();

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private ComboBox<String> cmbSize;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colSize;

    @FXML
    private Label lblItemId;

    @FXML
    private TableView<Item> tblViewItem;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private JFXTextField txtQTY;

    @FXML
    private JFXTextField txtUnitPrice;

    @FXML
    void btnAddOnAction(ActionEvent event) {
        service.add(new Item(
                lblItemId.getText(),
                txtDescription.getText(),
                Double.parseDouble(txtUnitPrice.getText()),
                cmbSize.getValue(),
                Integer.parseInt(txtQTY.getText())
        ));

        loadTable();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        service.delete(lblItemId.getText());

        loadTable();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        service.update(new Item(
                lblItemId.getText(),
                txtDescription.getText(),
                Double.parseDouble(txtUnitPrice.getText()),
                cmbSize.getValue(),
                Integer.parseInt(txtQTY.getText())
        ));

        loadTable();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));

        loadTable();
        btnUpdate.setDisable(true);

        ObservableList<String> sizeList = FXCollections.observableArrayList();
        sizeList.add("XS");
        sizeList.add("S");
        sizeList.add("M");
        sizeList.add("L");
        sizeList.add("Xl");
        sizeList.add("XXl");
        sizeList.add("Free Size");

        cmbSize.setItems(sizeList);

        tblViewItem.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if(null!=newValue){
                setSelectedItem(newValue);
            }
        });

        setItemId();
    }

    private void setItemId() {
        lblItemId.setText(service.generateID());
    }

    private void setSelectedItem(Item newValue) {
        lblItemId.setText(newValue.getItemCode());
        txtDescription.setText(newValue.getDescription());
        txtUnitPrice.setText(String.valueOf(newValue.getUnitPrice()));
        txtQTY.setText(String.valueOf(newValue.getQtyOnHand()));
        cmbSize.setValue(newValue.getSize());

        btnUpdate.setDisable(false);
    }

    private void loadTable() {
        tblViewItem.setItems(service.getAll());
    }
}
