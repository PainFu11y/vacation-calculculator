package com.platform.controller;

import com.platform.entity.HolidayEntity;
import com.platform.model.HolidayDate;
import com.platform.springJpa.HolidaySpringJpa;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/holiday")
public class HolidayController {
    @Autowired
    HolidaySpringJpa holidaySpringJpa;

    @Operation(summary = "Add holiday", description = "Adds a holiday to the base date.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Holiday successfully added"),
            @ApiResponse(responseCode = "400", description = "Something went wrong")
    })
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody HolidayEntity createHoliday(@RequestBody @Valid HolidayDate holidayDate) {

        return holidaySpringJpa.addHoliday(holidayDate);
    }

}
