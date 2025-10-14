package ru.ifellow.alivenskiy.hw3.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("Unable to find config.properties");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static String getBaseUrl() { return getProperty("base.url"); }
    public static String getLogin() { return getProperty("login"); }
    public static String getPassword() { return getProperty("password"); }
    public static String getTestTheme() { return getProperty("test.theme"); }
    public static String getTestBugTheme() { return getProperty("test.bug.theme"); }
    public static String getTestBugType() { return getProperty("test.bug.type"); }
    public static String getTestBugDescription() { return getProperty("test.bug.description"); }
    public static String getTestTaskName() { return getProperty("test.task.name"); }
    public static String getExpectedStatus() { return getProperty("expected.status"); }
    public static String getExpectedVersion() { return getProperty("expected.version"); }
    public static String getExpectedDoneStatus() { return getProperty("expected.done.status"); }
    public static int getTimeout() { return Integer.parseInt(getProperty("timeout")); }
}