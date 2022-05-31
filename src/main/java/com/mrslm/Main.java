package com.mrslm;

import java.io.IOException;
import java.util.List;

public class Main {
    private static final String defaultPath = "src/main/resources/tickets.json";
    private static final int defaultPercent = 90;

    public static void main(String[] args) {
        String path = defaultPath;
        int percent = defaultPercent;
        try {
            if (args.length == 1) {
                try {
                    percent = Integer.parseInt(args[0]);
                }catch (NumberFormatException e){
                    path = args[0];
                }
            }
            if (args.length == 2){
                path = args[0];
                percent = Integer.parseInt(args[1]);
            }
            List<FlightDTO> flightDTOS = DataParser.parse(path);
            long average = Processor.averageFlyDuration(flightDTOS);
            long percentile = Processor.getPercentile(flightDTOS, percent);
            System.out.printf("Среднее время полёта \"Владивосток — Тель-Авив\" составляет %d ч %d мин (%d мин)\n",
                    average/60, average%60, average);
            System.out.printf("%d-й процентиль по упрощённому расчёту составляет %d ч %d мин (%d мин)\n",
                    percent , percentile/60, percentile%60, percentile);
        } catch (IOException e) {
            System.err.println("Ошибка в аргументах запуска: " + e.getMessage() +
                    " Программа запустится со значениями по-умолчанию.");
            main(new String[0]);
        }

    }
}
