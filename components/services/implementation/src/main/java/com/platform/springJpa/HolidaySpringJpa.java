package com.platform.springJpa;

import com.platform.entity.HolidayEntity;
import java.time.format.DateTimeFormatter;

import com.platform.model.HolidayDate;
import com.platform.repository.HolidayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;
@Service
public class HolidaySpringJpa {
    @Autowired
    HolidayRepository holidayRepository;
    public HolidayEntity addHoliday(HolidayDate date) {

        try{
            Optional<HolidayEntity> existingHoliday = holidayRepository.findByHolidayDate(date.getHolidayDate());
            if (existingHoliday.isPresent()) {
                throw new IllegalArgumentException("Holiday on this date already exists.");
            }
        }catch(Exception e){
            throw new IllegalArgumentException("Holiday on this date does not exist.");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date.getHolidayDate(), formatter);
        int month = localDate.getMonthValue();

        HolidayEntity holiday = new HolidayEntity();
        holiday.setHolidayId(UUID.randomUUID());
        holiday.setHolidayDate(date.getHolidayDate());
        holiday.setHolidayMonth(month);

        return holidayRepository.save(holiday);
    }
}
