package com.lloyds.test.clock.service.impl;

import com.lloyds.test.clock.model.Time;
import com.lloyds.test.clock.model.TimeResponse;
import com.lloyds.test.clock.service.TimeProcessingService;
import com.lloyds.test.clock.utils.TimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TimeProcessingServiceImpl implements TimeProcessingService {

    private TimeUtils timeUtils;
    public TimeProcessingServiceImpl(final TimeUtils timeUtils){
        this.timeUtils = timeUtils;
    }

    /**
     * Hour and Minutes received via CLI.
     * @param givenTime - user given Time in HH:mm
     * @return String  - HumanReadable Time format
     */
    @Override
    public TimeResponse processTimeAsHumanFriendlyText(String givenTime) {
        Time time = timeUtils.parseAndValidateTime(givenTime);
        return new TimeResponse(timeUtils.processTime(time),time.getHour()+":"+time.getMinute());
    }

    /**
     *  Hour and Minutes received via restAPI call.
     * @param givenTime  - Time received from user input.
     * @return TimeResponse - Response returned to user.
     */
    @Override
    public String processCliTimeToHumanReadableText(String givenTime) {
        return timeUtils.processTime(timeUtils.parseAndValidateTime(givenTime));
    }
}
