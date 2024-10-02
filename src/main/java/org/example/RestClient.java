package org.example;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static io.restassured.http.ContentType.JSON;

public class RestClient {
    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru/";

    protected static RequestSpecification getBaseSpec() {

        //content type - json
        //base URI

        return new RequestSpecBuilder()
                .setContentType(JSON)
                .setBaseUri(BASE_URL)
                .build();
    }
}