package com.platform.controller;

import com.platform.model.VacationRequest;
import com.platform.springJpa.CalculateSpringJpa;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/calculate")
public class CalculateController {
    @Autowired
    private CalculateSpringJpa calculateSpringJpa;

    @GetMapping("/get-salary")
    public @ResponseBody Float getVacationPay(@RequestParam Integer vacationDay, @RequestParam Float averageSalary){
        return calculateSpringJpa.getVacationPay(vacationDay, averageSalary);
    }

    @Operation(summary = "Calculate vacation pay", description = "Calculates the vacation pay based on the provided request.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful calculation of vacation pay"),
            @ApiResponse(responseCode = "400", description = "Invalid request data, such as end date before start date")
    })
    @PostMapping("/second-get-salary")
    public double calculateVacationPay(@RequestBody @Valid VacationRequest vacationRequest) {
        return calculateSpringJpa.calculateVacationPay(vacationRequest);
    }
}
