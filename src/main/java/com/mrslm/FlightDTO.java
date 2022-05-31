package com.mrslm;

import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Представляет собой простой Data Transfer Object для удобного хранения информации о рейсе.
 */
public class FlightDTO {
    private LocalDateTime departureDateTime;
    private LocalDateTime arrivalDateTime;
    private Duration duration;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy H:mm");

    public FlightDTO(String departureDateTime, String arrivalDateTime) throws ParseException {
        setDepartureDateTime(departureDateTime);
        setArrivalDateTime(arrivalDateTime);
        duration = Duration.between(getDepartureDateTime(), getArrivalDateTime());
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(String departureDateTime) {
        this.departureDateTime = LocalDateTime.parse(departureDateTime, formatter);
    }

    public LocalDateTime getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(String arrivalDateTime) {
        this.arrivalDateTime = LocalDateTime.parse(arrivalDateTime, formatter);
    }

    @Override
    public String toString() {
        return "Flight " +
                "departure " + departureDateTime +
                ", arrival " + arrivalDateTime;
    }

    public Duration getDuration() {
        return duration;
    }
}
