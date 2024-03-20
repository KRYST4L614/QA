package org.example;

import org.junit.jupiter.api.*;

public class TestApp {
    @BeforeAll
    public static void beforeAll() {
        System.out.println("-------BeforeAll called--------");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("------AfterAll called------");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("----------BeforeEach called--------");
    }

    @AfterEach
    public void afterEach() {
        System.out.println("-----------AfterEach called-----------");
    }

    @Test
    public void calcCheck() {
        System.out.println("---------CalcCheck called--------");
        Assertions.assertEquals(9, 3 * 3);
    }

    @Test
    public void calcCheckFalse() {
        System.out.println("--------CalcCheckFalse called--------");
        Assertions.assertNotEquals(16, 4 * 3);
    }
}