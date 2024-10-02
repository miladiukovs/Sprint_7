package org.example;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.example.CourierGenerator.getRandomWithoutLogin;
import static org.junit.Assert.assertEquals;

public class LoginCourierTest {
    private Courier courier;
    private CourierClient courierClient = new CourierClient();
    private Courier courierWithoutSomeData;

    @Before
    public void createCourier() {
        courier = new Courier();
        courierClient.create(courier);
        courierWithoutSomeData = CourierGenerator.getRandomWithoutLogin();
    }

    @After
    public void deleteCourier() {
        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(courier));
        if (loginResponse.extract().statusCode() == 200) {
            int id = loginResponse.extract().path("id");
            courierClient.delete(id);
        }
    }

    @Test
    @DisplayName("check courier can login")
    public void courierCanLogin() {
        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(courier));
        int statusCode = loginResponse.extract().statusCode();
        assertEquals("Курьер успешно авторизован",200, statusCode);
    }

    @Test
    @DisplayName("check courier can not login without login")
    public void cantLoginWithoutDataLogin() {
        Courier courierWithoutSomeData = getRandomWithoutLogin();
        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.create(courierWithoutSomeData));
        int statusCode = loginResponse.extract().statusCode();
        assertEquals("Ошибка валидации: заполнены не все обязательные поля",400, statusCode);
    }

    @Test
    @DisplayName("check courier can not login with wrong login")
    public void cantLoginWithFalseLogin() {
        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.create("IncorrLoGIn1229", "123456"));
        int statusCode = loginResponse.extract().statusCode();
        assertEquals("Ошибка валидации: проверьте корректность данных",404, statusCode);
    }

    @Test
    @DisplayName("check return id")
    public void courierReturnId() {
        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(courier));
        int CourierId = loginResponse.extract().path("id");
        Assert.assertTrue(CourierId > 0);
    }
}