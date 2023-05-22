package com.lloyds.test.clock.controller;


import com.lloyds.test.clock.exception.TimeFriendlyTextExceptionHandler;
import com.lloyds.test.clock.model.TimeResponse;
import com.lloyds.test.clock.model.TimeResponseTest;
import com.lloyds.test.clock.service.TimeProcessingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


@RunWith(SpringRunner.class)
public class TimeFriendlyTextControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private TimeFriendlyTextController humanReadableTimeTextController;

    @Mock
    private TimeProcessingService timeProcessingService;

    @Before
    public void setUp() {
        this.mockMvc = standaloneSetup(humanReadableTimeTextController)
                .setControllerAdvice(new TimeFriendlyTextExceptionHandler()).build();
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void getTimeAsHumanFriendlyText_whenValidTimeGiven_thenReturnStatus200()
            throws Exception {

            when(timeProcessingService.processTimeAsHumanFriendlyText(any()))
                .thenReturn(new TimeResponse("1:00","One o'clock"));
        this.mockMvc.perform(MockMvcRequestBuilders.get("/v1/clock/time")
                .param("time","1:00")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
                //.andExpect((ResultMatcher) jsonPath("$[0].givenTime").value("1:00"));
                //.andExpect(jsonPath("$[0].givenTime", is("1:00")));
        verify(timeProcessingService, times(1)).processTimeAsHumanFriendlyText(any());
    }
    @Test
    public void getTimeAsHumanFriendlyText_whenInValidTimeGiven_thenThrowsIllegalArgumentException()
            throws Exception {

        when(timeProcessingService.processTimeAsHumanFriendlyText("55:55"))
                .thenThrow(IllegalArgumentException.class);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/v1/clock/time")
                        .param("time","55:55")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
        verify(timeProcessingService, times(1)).processTimeAsHumanFriendlyText(any());
    }
    @Test
    public void getTimeAsHumanFriendlyText_whenInvalidTimeGivenAsCharaters_thenThrowsIllegalArgumentException()
            throws Exception {

        when(timeProcessingService.processTimeAsHumanFriendlyText("aabb"))
                .thenThrow(IllegalArgumentException.class);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/v1/clock/time")
                        .param("time","aabb")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
        verify(timeProcessingService, times(1)).processTimeAsHumanFriendlyText(any());
    }

}
