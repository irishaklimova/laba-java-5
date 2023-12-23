package com.java-5-Klimova;

import com.java-5-Klimova.injectors.Injector;
import com.java-5-Klimova.somepackage.SomeBean;
import org.junit.jupiter.api.TestInstance;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;


@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class AutoInjectableUnitTests {

    private final static ByteArrayOutputStream output = new ByteArrayOutputStream();

    public AutoInjectableUnitTests() {}

    @BeforeAll
    public static void interceptPrintStream() {
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    public void clearPrintStream() {
        output.reset();
    }

    private final static String pathOne = "src/test/resources/first.properties";
    private final static String pathTwo = "src/test/resources/second.properties";

    @Test
    public void testInjectAC() {
        SomeBean someBean = null;
        try {
            someBean = (new Injector().inject(new SomeBean(), pathOne));
        } catch (IOException e) {
            e.printStackTrace();
        }
        someBean.foo();
        String lineSeparator = System.getProperty("line.separator");
        assertEquals("A" + lineSeparator + "C" + lineSeparator, output.toString());
    }

    @Test
    public void testInjectBC() {
        SomeBean someBean = null;
        try {
            someBean = (new Injector().inject(new SomeBean(), pathTwo));
        } catch (IOException e) {
            e.printStackTrace();
        }
        someBean.foo();
        String lineSeparator = System.getProperty("line.separator");
        assertEquals("B" + lineSeparator + "C" + lineSeparator, output.toString());
    }

    @Test
    public void testFieldsNotInitialized() {
        assertThrows(NullPointerException.class, () -> {
            (new SomeBean()).foo();
        });
    }
}