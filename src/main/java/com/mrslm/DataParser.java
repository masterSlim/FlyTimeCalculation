package com.mrslm;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class DataParser {

    /**
     * @param path Путь к файлу с данными о рейсах
     * @return List объектов FlightDTO хранящих информацию об одном полёте
     * @throws IOException
     */
    public static List<FlightDTO> parse(String path) throws IOException {
        File source = new File(path);
        List<FlightDTO> flights = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(source).get("tickets");
        node.forEach((ticket) ->
        {
            try {
                flights.add(new FlightDTO(
                        ticket.get("departure_date").asText() + " " + ticket.get("departure_time").asText(),
                        ticket.get("arrival_date").asText() + " " + ticket.get("arrival_time").asText()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });

        return flights;
    }
}
