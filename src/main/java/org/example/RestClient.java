package org.example;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static io.restassured.http.ContentType.JSON;

public class RestClient {
    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru";
    private static final String CANCEL_URL = "/api/v1/orders/cancel";
    private static final String ORDER_URL = "/api/v1/orders";

    protected static RequestSpecification getBaseSpec() {

        //content type - json
        //base URI

        return new RequestSpecBuilder()
                .setContentType(JSON)
                .setBaseUri(BASE_URL)
                .build();
    }

    public String getCancelOrder() {
        return CANCEL_URL;
    }

    public String getOrders() {
        return ORDER_URL;
    }
}