package com.lloyds.test.clock.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class TimeResponse {
    @NotNull
    String timeText;
    @NotNull
    String givenTime;
}
