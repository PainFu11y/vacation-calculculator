package com.platform.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VacationRequest {
    @NotNull(message = "Start date cannot be null")
    private LocalDate startDate;
    @NotNull(message = "Start date cannot be null")
    private LocalDate endDate;

    @Positive(message = "Average salary must be greater than zero")
    private double averageSalary;
}
