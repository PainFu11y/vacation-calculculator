package com.platform.springJpa;


import com.platform.entity.HolidayEntity;
import com.platform.model.VacationRequest;
import com.platform.repository.HolidayRepository;
import com.platform.service.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class CalculateSpringJpa implements VacationService {
    @Autowired
    private HolidayRepository holidayRepository;


    @Override
    public Float getVacationPay(Integer vacationDay, Float averageSalary) {

        return averageSalary / 21 * vacationDay;
    }
    public double calculateVacationPay(VacationRequest vacationRequest) {
        if (vacationRequest.getEndDate().isBefore(vacationRequest.getStartDate())) {
            throw new IllegalArgumentException("End date cannot be before start date.");
        }

        LocalDate startDate = vacationRequest.getStartDate();
        LocalDate endDate = vacationRequest.getEndDate();
        double averageSalary = vacationRequest.getAverageSalary();
        int startMonth = startDate.getMonthValue();
        int endMonth = endDate.getMonthValue();


        Set<LocalDate> holidays = new HashSet<>();
        holidays.addAll(getHolidaysForMonth(startMonth));
        if (startMonth != endMonth) {
            holidays.addAll(getHolidaysForMonth(endMonth));
        }


        Set<DayOfWeek> businessDays = Set.of(
                DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
                DayOfWeek.THURSDAY, DayOfWeek.FRIDAY
        );


        long businessDaysCount = startDate.datesUntil(endDate)
                .filter(date -> businessDays.contains(date.getDayOfWeek()))
                .filter(date -> !holidays.contains(date))
                .count();


        double dailySalary = averageSalary / 21;
        return dailySalary * businessDaysCount;
    }
    private Set<LocalDate> getHolidaysForMonth(int month) {
        List<HolidayEntity> holidaysDb = holidayRepository.findByHolidayMonth(month);
        Set<LocalDate> holidays = new HashSet<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (HolidayEntity holiday : holidaysDb) {
            String dateString = holiday.getHolidayDate();
            LocalDate holidayDate = LocalDate.parse(dateString, formatter);
            holidays.add(holidayDate);
        }
        return holidays;
    }

}
