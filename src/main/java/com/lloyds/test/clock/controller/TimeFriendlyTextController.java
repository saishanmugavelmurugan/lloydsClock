package com.lloyds.test.clock.controller;

import com.lloyds.test.clock.model.TimeResponse;
import com.lloyds.test.clock.service.TimeProcessingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/clock")
@Slf4j
public class TimeFriendlyTextController {

    private TimeProcessingService timeProcessingService;
    private Object time;

    public TimeFriendlyTextController(TimeProcessingService timeProcessingService){
        this.timeProcessingService = timeProcessingService;
    }


    /**
     * User inputs of time is captured and processed as Human Friendly Text is returned to user.
     * @param time - user input should be HH:mm
     * @return TimeResponse
     */
    @GetMapping("/time")
    public TimeResponse getTimeAsHumanFriendlyText(@RequestParam(required = false, name = "time") String time) {
        log.info("Time given:"+time);
        return timeProcessingService.processTimeAsHumanFriendlyText(time);
    }

}
