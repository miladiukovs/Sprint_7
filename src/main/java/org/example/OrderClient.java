package org.example;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;
import static org.example.RestClient.getBaseSpec;

@Step("post to get orders")
public class OrderClient extends CourierClient {
    public ValidatableResponse createOrder(Order order) {
        return given()
                .spec(getBaseSpec())
                .body(order)
                .when()
                .post(getOrders())
                .then();
    }

    @Step("put to cancel make order")
    public ValidatableResponse cancelOrder(int track) {
        return given()
                .spec(getBaseSpec())
                .queryParam("track", track)
                .when()
                .put(getCancelOrder())
                .then();
    }

    @Step("to get order list")
    public ValidatableResponse getOrderList(OrderList orderList) {
        return given()
                .spec(getBaseSpec())
                .body(orderList)
                .when()
                .get(getOrders())
                .then();
    }
}