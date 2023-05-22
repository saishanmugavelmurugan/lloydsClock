package com.lloyds.test.clock.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeResponseTest {
    private TimeResponse timeResponse;

    @BeforeEach
    void setUp() {
        timeResponse = new TimeResponse("One o` clock","01:00");
    }

    @Test
    public void Time_whenValidTimeGiven_thenRetunTime(){

        assertAll("Time prase & validate",
                () -> assertEquals("01:00", timeResponse.getGivenTime()),
                () -> assertEquals("One o` clock", timeResponse.getTimeText()));
    }
}
