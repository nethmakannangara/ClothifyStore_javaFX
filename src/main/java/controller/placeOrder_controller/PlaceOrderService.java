package controller.placeOrder_controller;

import model.Item;

public interface PlaceOrderService {
    Item loadItemInfo(String itemCode);
}
