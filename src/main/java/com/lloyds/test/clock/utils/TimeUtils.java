package com.lloyds.test.clock.utils;

import com.lloyds.test.clock.model.Time;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;


import java.time.LocalTime;

@Service
@Slf4j
public class TimeUtils {

    private final String[] hours = {
            "Twelve", "One", "Two", "Three", "Four",
            "Five", "Six", "Seven", "Eight", "Nine",
            "Ten", "Eleven", "Twelve", "Thirteen",
            "Fourteen", "Fifteen", "Sixteen", "Seventeen",
            "Eighteen", "Nineteen", "Twenty", "Twenty one",
            "Twenty two", "Twenty three", "Twenty four"
    };

    private final String[] min = {
            "Twelve", "One", "Two", "Three", "Four",
            "Five", "Six", "Seven", "Eight", "Nine",
            "Ten", "Eleven", "Twelve", "Thirteen",
            "Fourteen", "Fifteen", "Sixteen", "Seventeen",
            "Eighteen", "Nineteen", "Twenty", "Twenty one",
            "Twenty two", "Twenty three", "Twenty four",
            "Twenty five", "Twenty six", "Twenty seven",
            "Twenty eight", "Twenty nine"
    };

    /**
     *
     * @param givenTime - User given time
     * @return Time
     */
    public Time parseAndValidateTime (String givenTime) {

        if (StringUtils.isEmpty(givenTime)) {
            log.warn("Time is null or empty or blank, using Local current time.");
            LocalTime now = LocalTime.now();
            return new Time(now.getHour(), now.getMinute());
        }
        Time time;
        try {
            String[] timeArray = givenTime.split(":");
            time = new Time(Integer.parseInt(timeArray[0]), Integer.parseInt(timeArray[1]));
        } catch (Exception e) {
            String errorMessage = "Input is not a valid 24-hour format time.";
            log.error("parseAndValidateTime:{}",errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
        if (time.getHour() > 23 || time.getMinute() > 59 || time.getHour() < 0 || time.getMinute() < 0) {
            String errorMessage = "The hour should be between 0 and 23 and the minute should be between 0 and 59.";
            log.error("parseAndValidateTime:{}",errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
        return time;
    }
    public String processTime(Time time){
        StringBuilder userFriendlyText = new StringBuilder();
        int h12 = time.getHour() % 12;
        switch (time.getMinute()) {
            case 0:
                userFriendlyText.append(hours[h12]).append(" o'clock");
                break;
            case  15:
                userFriendlyText.append("Quarter past ").append(hours[h12]);
                break;
            case  30:
                userFriendlyText.append("Half past ").append(hours[h12]);
                break;
            case  45:
                userFriendlyText.append("Quarter to ").append(hours[h12 + 1]);
                break;
            default:
                if (time.getMinute() < 30) {
                    userFriendlyText.append(min[time.getMinute()]).append(" past ").append(hours[h12]);
                }
                else {
                    userFriendlyText.append(min[60 - time.getMinute()]).append(" to ").append(hours[h12 + 1]);
                }
        }

        return StringUtils.capitalize(userFriendlyText.toString());
    }
}
