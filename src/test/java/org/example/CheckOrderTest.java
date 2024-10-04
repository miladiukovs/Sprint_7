package org.example;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.Order;
import org.example.OrderClient;
import org.example.OrderList;
import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;

public class CheckOrderTest {

    private final OrderClient orderClient = new OrderClient();
    private OrderList orderList = new OrderList();

    @Test
    @DisplayName("show orders list")
    @Description("Return list of orders")
    public void returnOrderList() {
        ValidatableResponse response = orderClient.getOrderList(orderList);
        ArrayList<Order> list = response.extract().path("orders");

        Assert.assertTrue(list.size() > 0);
    }
}