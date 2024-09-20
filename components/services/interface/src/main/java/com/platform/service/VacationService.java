package com.platform.service;

import com.platform.model.VacationRequest;

import java.time.LocalDate;

public interface VacationService {
    Float getVacationPay(Integer vacationDay,  Float averageSalary);
    double calculateVacationPay(VacationRequest vacationRequest);
}
