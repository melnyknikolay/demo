package com.example.demo;

import com.example.demo.model.Event;

import java.util.Collections;

public class testData {

    public static Event EVENT_1 = Event.builder()
            .botId("botId")
            .isAutoSplit(true)
            .subtype("subtype")
            .text("test")
            .ts("ts")
            .username("username")
            .type("type")
            .smEvents(Collections.EMPTY_LIST)
            .build();

    public static Event EVENT_2 = Event.builder()
            .botId("botId")
            .isAutoSplit(true)
            .subtype("subtype")
            .text("test")
            .ts("ts")
            .username("username")
            .type("type")
            .smEvents(Collections.EMPTY_LIST)
            .build();

    public static String TEST_JSON = "[{\n" +
            "        \"text\": \"Events for game id:19481 apiId:10449463 CSKA Moskva vs Viktoria Plzeň:\\n\",\n" +
            "        \"ts\": \"1543339536.000200\"\n" +
            "    }]";

    public static Event TEST_EVENT = Event.builder()
            .text("Events for game id:19481 apiId:10449463 CSKA Moskva vs Viktoria Plzeň:")
            .ts("1543339536.000200")
            .smEvents(Collections.EMPTY_LIST)
            .build();
}
