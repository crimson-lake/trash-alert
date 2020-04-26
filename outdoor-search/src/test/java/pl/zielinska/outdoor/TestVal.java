package pl.zielinska.outdoor;

import pl.zielinska.outdoor.domain.Tag;

import java.time.LocalDateTime;

public class TestVal {
    public static final String TEST_USERNAME = "patick";
    public static final String TEST_FIRST_NAME = "Patryk";
    public static final String TEST_LAST_NAME = "Trynk";
    public static final String TEST_EMAIL = "pt@outlook.com";
    public static final String TEST_PASSWORD = "123456";

    public static final String TEST_TAG_NAME = "TEST";
    public static final Tag TEST_TAG = Tag.builder().tag(TestVal.TEST_TAG_NAME).build();

    public static final int TEST_ID = 99;
    public static final String TEST_TITLE = "TEST";
    public static final String TEST_CITY = "Kraków";
    public static final String TEST_STREET = "Barska";
    public static final LocalDateTime TEST_TIME = LocalDateTime.now();

    public static final String INVALID_EMAIL = "invalid_email";
}
