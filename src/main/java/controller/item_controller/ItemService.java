package controller.item_controller;

import javafx.collections.ObservableList;
import model.Item;

public interface ItemService {
    ObservableList<Item> getAll();

    void add(Item item);

    String generateID();

    void delete(String itemCode);

    void update(Item updateItem);
}
