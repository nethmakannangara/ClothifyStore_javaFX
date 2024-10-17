package controller.placeOrder_controller;

import controller.item_controller.ItemController;
import controller.item_controller.ItemService;
import model.Item;

public class PlaceOrderController implements PlaceOrderService{

    private static PlaceOrderController instance;

    private PlaceOrderController(){}

    public static PlaceOrderController getInstance(){
        return null==instance?instance=new PlaceOrderController():instance;
    }

    ItemService itemService = ItemController.getInstance();

    @Override
    public Item loadItemInfo(String itemCode) {
        return itemService.search(itemCode);
    }
}
