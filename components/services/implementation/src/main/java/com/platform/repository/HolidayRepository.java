package com.platform.repository;

import com.platform.entity.HolidayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface HolidayRepository extends JpaRepository<HolidayEntity, UUID> {
    List<HolidayEntity> findByHolidayMonth(int month);
    Optional<HolidayEntity> findByHolidayDate(String holidayDate);
}
