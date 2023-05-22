package com.lloyds.test.clock.model;

import com.lloyds.test.clock.utils.TimeUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeTest {
    private Time time;

    @BeforeEach
    void setUp() {
        time = new Time(1,0);
    }

    @Test
    public void Time_whenValidTimeGiven_thenRetunTime(){

        assertAll("Time prase & validate",
                () -> assertEquals(1, time.getHour()),
                () -> assertEquals(0, time.getMinute()));
    }
}
