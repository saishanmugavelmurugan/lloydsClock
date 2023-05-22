package com.lloyds.test.clock.service;

import com.lloyds.test.clock.model.Time;
import com.lloyds.test.clock.model.TimeResponse;
import com.lloyds.test.clock.model.TimeResponseTest;
import com.lloyds.test.clock.service.impl.TimeProcessingServiceImpl;
import com.lloyds.test.clock.utils.TimeUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class TimeProcessingServiceTest {

    @InjectMocks
    public TimeProcessingServiceImpl timeProcessingService;

    @Mock
    private TimeUtils timeUtils;

    @Test
    public void processCliTimeToHumanReadableText_whenValidTimeGiven_thenReturnTimeAsTextFormat(){
       //given
        when(timeUtils.parseAndValidateTime(anyString())).thenReturn(new Time(01,00));
        when(timeUtils.processTime(any())).thenReturn("One o'clock");

        // when
        String textTime = timeProcessingService.processCliTimeToHumanReadableText("01:00") ;
        //then
        assertEquals("One o'clock",textTime);
    }

    @Test
    public void processCliTimeToHumanReadableText_whenInvalidTimeGiven_thenThrowsExceptions(){
        //given
        when(timeUtils.parseAndValidateTime("55:00")).thenThrow(IllegalArgumentException.class);
        //when(timeUtils.processTime(any())).thenReturn("One o'clock");

        // when & then
        assertThrows(IllegalArgumentException.class, () -> timeProcessingService.processCliTimeToHumanReadableText("55:00") );
    }


    @Test
    public void processCliTimeToHumanReadableText_whenNullTimeGiven_thenReturnCurrentTimeAsTextForma(){
        //given
        LocalTime now = LocalTime.now();
        when(timeUtils.parseAndValidateTime(anyString())).thenReturn(new Time(now.getHour(),now.getMinute()));
        when(timeUtils.processTime(any())).thenReturn("One o'clock");
        String textTime = timeProcessingService.processCliTimeToHumanReadableText(null) ;
        //then
        assertNotNull(textTime);
    }
    @Test
    public void processTimeAsHumanFriendlyText_whenValidTimeGiven_thenReturnTimeAsTextFormat(){
        //given
        when(timeUtils.parseAndValidateTime(anyString())).thenReturn(new Time(01,00));
        when(timeUtils.processTime(any())).thenReturn("One o'clock");

        // when
        TimeResponse textTime = timeProcessingService.processTimeAsHumanFriendlyText("01:00") ;
        //then
        assertEquals("One o'clock",textTime.getTimeText());
        verify(timeUtils, times(1)).parseAndValidateTime(anyString());
        verify(timeUtils, times(1)).processTime(any());
    }

    @Test
    public void processTimeAsHumanFriendlyText_whenInvalidTimeGiven_thenThrowsExceptions(){
        //given
        when(timeUtils.parseAndValidateTime(anyString())).thenThrow(IllegalArgumentException.class);

        //then
        assertThrows(IllegalArgumentException.class, () -> timeProcessingService.processTimeAsHumanFriendlyText(anyString()) );
        verify(timeUtils, times(1)).parseAndValidateTime(anyString());
    }


    @Test
    public void processTimeAsHumanFriendlyText_whenNullTimeGiven_thenReturnCurrentTimeAsText(){
        //given
        LocalTime now = LocalTime.now();
        when(timeUtils.parseAndValidateTime(null)).thenReturn(new Time(now.getHour(),now.getMinute()));
        when(timeUtils.processTime(any())).thenReturn("One o'clock");
        TimeResponse textTime = timeProcessingService.processTimeAsHumanFriendlyText(null);
        assertNotNull(textTime);
        verify(timeUtils, times(1)).processTime(any());
        verify(timeUtils, times(1)).parseAndValidateTime(null);
    }
}
