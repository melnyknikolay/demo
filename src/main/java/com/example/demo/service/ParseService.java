package com.example.demo.service;

import com.example.demo.model.Event;
import com.example.demo.model.SMEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParseService {
    private final ObjectMapper mapper;

    public ParseService(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public List<Event> parseJson(String eventsJson) throws IOException {
        List<Event> events = mapper.readValue(eventsJson, mapper.getTypeFactory().constructCollectionType(List.class, Event.class));
        for (Event event : events) {
            parseTextForSMEvents(event);
        }

        return events.stream().limit(1000).collect(Collectors.toList());
    }

    private void parseTextForSMEvents(Event event) throws IOException {
        BufferedReader reader = new BufferedReader(new StringReader(event.getText()));

        String text = "";
        List<SMEvent> smEvents = new ArrayList<>();
        while (reader.ready()) {
            String line = reader.readLine();
            if (line == null){
                break;
            }else if (!line.startsWith("SMEvent")){
                if (line.startsWith("Late goal")){
                    text = line.substring(0, line.indexOf('['));
                    String[] smEvents1 = line.substring(line.indexOf('[') + 1, line.indexOf(']')).trim().split("SMEvent");
                    Arrays.stream(smEvents1)
                            .filter(str -> !str.equals(""))
                            .forEach(str -> addSMEvent(smEvents, str.trim()));
                    continue;
                }
                text = line;
                continue;
            }
            addSMEvent(smEvents, line);
        }
        event.setText(text);
        event.setSmEvents(smEvents);

    }

    private void addSMEvent(List<SMEvent> smEvents, String line) {
        HashMap<String, String> pairParameters = new HashMap<>();
        Arrays.stream(line.substring(line.indexOf('(') + 1, line.indexOf(')')).split(","))
                .forEach(str -> pairParameters.put(str.substring(0, str.indexOf('=')).trim(), str.substring(str.indexOf('=') + 1).trim()));
        SMEvent sm = SMEvent.builder()
                .id(parseLong(pairParameters.get("id")))
                .teamId(parseLong(pairParameters.get("teamId")))
                .type(validString(pairParameters.get("type")))
                .fixtureId(parseLong(pairParameters.get("fixtureId")))
                .playerId(parseLong(pairParameters.get("playerId")))
                .playerName(validString(pairParameters.get("playerName")))
                .relatedPlayerId(parseLong(pairParameters.get("relatedPlayerId")))
                .relatedPlayerName(validString(pairParameters.get("relatedPlayerName")))
                .minute(parseLong(pairParameters.get("minute")))
                .extraMinute(validString(pairParameters.get("extraMinute")))
                .reason(validString(pairParameters.get("reason")))
                .injuried(validString(pairParameters.get("injuried")))
                .result(validString(pairParameters.get("result")))
                .build();
        smEvents.add(sm);
    }

    private Long parseLong(String str){
        return "null".equals(str) ? null : Long.parseLong(str);
    }

    private String validString(String str){
        return "null".equals(str) ? null : str;
    }
}
