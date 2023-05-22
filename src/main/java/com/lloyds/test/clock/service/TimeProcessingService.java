package com.lloyds.test.clock.service;

import com.lloyds.test.clock.model.TimeResponse;

public interface TimeProcessingService {
    /**
     *
     * @param Time  - user given Time in HH:mm
     * @return String  - HumanReadable Time format
     */
    public TimeResponse processTimeAsHumanFriendlyText(String Time);

    public String processCliTimeToHumanReadableText(String Time);
}
