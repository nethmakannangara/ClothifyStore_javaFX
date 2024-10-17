package controller.item_controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Item;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Currency;
import java.util.Scanner;

public class ItemController implements ItemService{

    private static ItemController instance;

    private ItemController(){}

    public static ItemController getInstance(){
        return null== instance?instance=new ItemController():instance;

    }

    @Override
    public ObservableList<Item> getAll() {
        String SQL = "SELECT * FROM item";
        ObservableList<Item> itemObservableList = FXCollections.observableArrayList();
        try {
            ResultSet resultSet = CrudUtil.execute(SQL);
            while (resultSet.next()){
                itemObservableList.add(new Item(
                        resultSet.getString("itemCode"),
                        resultSet.getString("description"),
                        resultSet.getDouble("unitPrice"),
                        resultSet.getString("size"),
                        resultSet.getInt("qtyOnHand")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return itemObservableList;
    }

    @Override
    public void add(Item item) {
        String SQL  = "INSERT INTO item VALUES (?,?,?,?,?)";
        try {
            CrudUtil.execute(SQL,
                    item.getItemCode(),
                    item.getDescription(),
                    item.getSize(),
                    item.getUnitPrice(),
                    item.getQtyOnHand()
                    );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //---------GENERATE ITEMID------------//
    private String checkLastId() {
        String lastId = null;
        String SQL = "SELECT itemCode from item";
        try {
            ResultSet resultSet = CrudUtil.execute(SQL);
            while (resultSet.next()){
                lastId = resultSet.getString("itemCode");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lastId;
    }

    @Override
    public String generateID() {
        String id = "001";
        String lastId = checkLastId();
        Scanner input = new Scanner(lastId);
        input.useDelimiter("[A-Z]");
        while (input.hasNext()){
            id = input.next();
        }
        int i = Integer.parseInt(id);
        return "CH0"+ ++i;
    }

    //-------------------------------------//

    @Override
    public void delete(String itemCode) {
        String SQL = "DELETE FROM item WHERE itemCode = ?";
        try {
            CrudUtil.execute(SQL,itemCode);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Item updateItem) {
        String SQL = "UPDATE item SET description = ? ,size = ? ,unitPrice = ? ,qtyOnHand = ?  WHERE itemCode = ?";

        try {
            CrudUtil.execute(SQL,
                    updateItem.getDescription(),
                    updateItem.getSize(),
                    updateItem.getUnitPrice(),
                    updateItem.getQtyOnHand(),
                    updateItem.getItemCode()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Item search(String itemCode){
        String SQL = "SELECT * FROM item WHERE itemCode = ?";
        Item searchItem = null ;
        try {
            ResultSet resultSet = CrudUtil.execute(SQL, itemCode);
            while (resultSet.next()){
                searchItem = new Item(
                        resultSet.getString("itemCode"),
                        resultSet.getString("description"),
                        resultSet.getDouble("unitPrice"),
                        resultSet.getString("size"),
                        resultSet.getInt("qtyOnHand")
                        );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return searchItem;
    }
}
