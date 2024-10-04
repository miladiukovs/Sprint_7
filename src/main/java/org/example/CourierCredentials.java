package org.example;

public class CourierCredentials {
    private static String login;
    private String password;

    public CourierCredentials(Courier courierWithoutSomeData) {
    }

    public CourierCredentials(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public static CourierCredentials from(Courier courier) {
        return new CourierCredentials(courier.getLogin(), courier.getPassword());
    }

    public static CourierCredentials create(String login, String password) {
        return new CourierCredentials(login, password);
    }

    public static CourierCredentials create(Courier courierWithoutSomeData) {
        return new CourierCredentials(courierWithoutSomeData);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
