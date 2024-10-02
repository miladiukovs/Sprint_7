package org.example;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CourierCreateTest {

    private CourierClient courierClient;
    private Courier courier;
    private int courierId;
    private Courier courierWithoutSomeData;


    @Before
    public void setUp() {
        courierClient = new CourierClient();
        courier = CourierGenerator.getRandom();
        courierWithoutSomeData = CourierGenerator.getRandomWithoutLogin();
    }

    @After
    public void CleanUp() {
        courierClient.delete(courierId);
    }

    @Test
    @DisplayName("check courier cam be created")
    public void courierCanBeCreated() {
//create: status code, body. ok == true
        ValidatableResponse createResponse = courierClient.create(courier);
        int createStatusCode = createResponse.extract().statusCode();
        assertEquals("Статус код не соответствует", HttpStatus.SC_CREATED, createStatusCode);
        boolean created = createResponse.extract().path("ok");
        assertTrue("Курьер не создан", created);

//login: status code, body: id != 0
        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(courier));
        int loginStatusCode = loginResponse.extract().statusCode();
        assertEquals(201, loginStatusCode, HttpStatus.SC_OK);
        int courierId = loginResponse.extract().path("id");
        assertNotEquals(courierId, 0);
    }

    @Test
    @DisplayName("check courier con not be the same")
    public void courierCantBeTheSame() {
        ValidatableResponse response = courierClient.create(courier);
        ValidatableResponse responseEqual = courierClient.create(courier);
        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(courier));

        if (loginResponse.extract().statusCode() == 200) {
            courierId = loginResponse.extract().path("id");
        }

        int statusCode = responseEqual.extract().statusCode();

        assertEquals("Нельзя создать двух одинаковых курьеров",409, statusCode);
    }

    @Test
    @DisplayName("check courier can not created without login")
    public void courierWithoutSomeData() {
        ValidatableResponse createResponse = courierClient.create(courierWithoutSomeData);
        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(courierWithoutSomeData));
        int statusCode = createResponse.extract().statusCode();
        assertEquals("Ошибка валидации. Не заполнены обязательные поля", 400, statusCode);
    }
}