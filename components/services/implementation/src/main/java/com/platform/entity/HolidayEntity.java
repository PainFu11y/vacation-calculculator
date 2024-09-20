package com.platform.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "days", schema = "holidays")
public class HolidayEntity {
    @Id
    @GenericGenerator(name = "generator",strategy = "uuid2")
    @GeneratedValue(generator = "generator")
    @Column(name = "holiday_id")
    private UUID holidayId;
    @Column(name = "holiday_date")
    private String holidayDate;
    @Column(name = "holiday_month")
    private Integer holidayMonth;
}
