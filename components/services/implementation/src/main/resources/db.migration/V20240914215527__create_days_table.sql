CREATE TABLE holidays.days
(
    day_id UUID primary key not null,
    holiday_date varchar(45) not null,
    holiday_month integer not null
);