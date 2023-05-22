package com.lloyds.test.clock.utils;

import com.lloyds.test.clock.model.Time;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TimeUtilsTest {

//    @Autowired
   private TimeUtils timeUtils;

    @BeforeEach
    void setUp() {
        timeUtils = new TimeUtils();
    }

    @Test
    public void parseAndValidateTime_whenValidTimeGiven_thenRetunTime(){
        //given
        Time time = timeUtils.parseAndValidateTime("01:00");
        //then
        assertAll("Time prase & validate",
                () -> assertEquals(1, time.getHour()),
                () -> assertEquals(0, time.getMinute()));
    }
    @Test
    public void parseAndValidateTime_whenInvalidTimeGiven_thenThrowError(){
        //given and then
        Throwable exception = assertThrows(IllegalArgumentException.class,
                ()->{ timeUtils.parseAndValidateTime("26:27");} );
        //then
        assertEquals("The hour should be between 0 and 23 and the minute should be between 0 and 59.", exception.getMessage());
    }
    @Test
    public void parseAndValidateTime_whenInvalidTimeFormatGiven_thenThrowError(){
        //given and then
        Throwable exception = assertThrows(IllegalArgumentException.class,
                ()->{ timeUtils.parseAndValidateTime("6263535");} );
        //then
        assertEquals("Input is not a valid 24-hour format time.", exception.getMessage());
    }
    @Test
    public void parseAndValidateTime_whenNullTimeFormatGiven_thenReturnCurrentTime(){
        //given and then
        Time time = timeUtils.parseAndValidateTime(null);
        //then
        assertAll("Current Time object",
                () -> assertNotNull(time.getHour()));
    }
    @Test
    public void parseAndValidateTime_whenEmptyTimeFormatGiven_thenReturn(){
        //given and then
        Throwable exception = assertThrows(IllegalArgumentException.class,
                ()->{ timeUtils.parseAndValidateTime(" ");} );
        //then
        assertEquals("Input is not a valid 24-hour format time.", exception.getMessage());
    }

    @Test
    public void processTime_whenValidhourminuteGiven_thenReturnTextformat(){
        assertEquals("One o'clock",
                timeUtils.processTime(new Time(1,0)));
    }

    @Test
    public void processTime_whenValidhourminuteGiven_thenReturnTextformatASTwooclock (){

        assertEquals("Two o'clock", timeUtils.processTime(new Time(2,00)));
    }
    @Test
    public void processTime_whenValidhourminuteGiven_thenReturnTextformatASFivepastone (){
        assertEquals("Five past One", timeUtils.processTime(new Time(13,05)));
        assertEquals("Ten past One", timeUtils.processTime(new Time(13,10)));
        assertEquals("Twenty five past One", timeUtils.processTime(new Time(13,25)));
        assertEquals("Half past One", timeUtils.processTime(new Time(13,30)));
        assertEquals("Five to Two", timeUtils.processTime(new Time(13,55)));
    }

}
