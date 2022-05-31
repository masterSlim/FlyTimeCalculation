package com.mrslm;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

public class Processor {

    /**
     * @param flightDTOs Список с объектами, представляющими собой данные о рейсе
     * @return значение long представляющее среднюю длительность рейсов в минутах
     */

    public static long averageFlyDuration(List<FlightDTO> flightDTOs) {
        long result;
        Duration sum = Duration.of(0, ChronoUnit.MINUTES);
        for (FlightDTO flight : flightDTOs) {
            sum = sum.plus(flight.getDuration());
        }
        result = sum.toMinutes() / flightDTOs.size();
        return result;
    }

    /**
     * @param flights Список с объектами, представляющими собой данные о рейсе
     * @param percent Значение процента от 0 до 100, процентиль от которого нужно найти
     * @return значение long из набора данных, соответствующее заданному процентилю
     */
    public static long getPercentile(List<FlightDTO> flights, int percent) {
        if (!((0 <= percent)&&(percent <= 100)))
            throw new IllegalArgumentException("Значение аргумента percent должно быть в диапазоне от нуля до 100 включительно");
        List<Long> durations = flights.stream().map((flight) -> flight.getDuration().toMinutes()).sorted().collect(Collectors.toList());
        int index = (int) Math.ceil(((double) durations.size() / 100) * percent);
        return durations.get(index - 1);
    }
}
