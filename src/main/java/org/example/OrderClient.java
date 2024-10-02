package org.example;

import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;
import static org.example.RestClient.getBaseSpec;

public class OrderClient extends CourierClient {
    public ValidatableResponse createOrder(Order order) {
        return given()
                .spec(getBaseSpec())
                .body(order)
                .when()
                .post("/api/v1/orders")
                .then();
    }

    public ValidatableResponse cancelOrder(int track) {
        return given()
                .spec(getBaseSpec())
                .queryParam("track", track)
                .when()
                .put("/api/v1/orders/cancel")
                .then();
    }

    public ValidatableResponse getOrderList(OrderList orderList) {
        return given()
                .spec(getBaseSpec())
                .body(orderList)
                .when()
                .get("/api/v1/orders")
                .then();
    }
}