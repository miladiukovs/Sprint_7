package org.example;

import io.restassured.response.ValidatableResponse;
import io.qameta.allure.Description;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class CreateOrderTest {

    private final Order order;
    private final int expectedStatusCode;
    private final OrderClient orderClient = new OrderClient();

    @Description("to get order")
    public CreateOrderTest(Order order, int expectedStatusCode) {
        this.order = order;
        this.expectedStatusCode = expectedStatusCode;
    }

    @Parameterized.Parameters()
    public static Object[][] getColor() {
        return new Object[][] {
                { Order.create(new String[]{"BLACK"}), 201},
                { Order.create(new String[]{"GREY"}), 201},
                { Order.create(new String[]{"BLACK", "GREY"}), 201 },
                { Order.create(new String[]{}), 201},
                { Order.create(null), 201}
        };
    }

    @Test
    @Description("to create order")
    public void createOrder () {
        ValidatableResponse response = orderClient.createOrder(order);

        int statusCode = response.extract().statusCode();
        int track = response.extract().path("track");
        orderClient.cancelOrder(track);
        Assert.assertTrue(track != 0);
    }
}