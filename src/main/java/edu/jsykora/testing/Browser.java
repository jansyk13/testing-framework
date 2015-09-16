package edu.jsykora.testing;

public class Browser {

    private static CustomFirefoxDriver driver = null;

    private static String baseUrl = "http://localhost:8080/";

    public static CustomFirefoxDriver driver() {
        return driver;
    }

    public static void setDriver(CustomFirefoxDriver driver2) {
        driver = driver2;
    }

    public static String url() {
        return baseUrl;
    }

    public static void setUrl(String url) {
        baseUrl = url;
    }

}
