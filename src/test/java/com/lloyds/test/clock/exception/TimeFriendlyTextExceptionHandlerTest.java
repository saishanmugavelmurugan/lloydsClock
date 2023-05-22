package com.lloyds.test.clock.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TimeFriendlyTextExceptionHandlerTest {
    @Test
    public void illegalArgumentExceptionHandler() {
        IllegalArgumentException illegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Integer.parseInt(null);
        }, "IllegalArgumentException for given Value");

        Assertions.assertEquals("Cannot parse null string", illegalArgumentException.getMessage());
    }
    @Test
    public void internalServerExceptionHandler() {
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            throw new Exception("Cannot parse null string");
        }, "IllegalArgumentException for given Value");

        Assertions.assertEquals("Cannot parse null string", exception.getMessage());
    }
}
