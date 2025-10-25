package ru.ifellow.alivenskiy.hw5.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestConfig {
    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream input = TestConfig.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("error 1");
                throw new RuntimeException("Файл не найден!");
            }
            PROPERTIES.load(input);
        } catch (IOException e) {
            System.out.println("error 2");
            throw new RuntimeException("Ошибка чтения файла", e);
        }
    }

    public static String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }

    public static String getBaseUrl() {
        return getProperty("base.url");
    }

    public static String getRickAndMortyUrl() {
        return getProperty("rickandmorty.url");
    }

    public static String getTestUsername() {
        return getProperty("test.user.username");
    }

    public static String getTestPassword() {
        return getProperty("test.user.password");
    }
}