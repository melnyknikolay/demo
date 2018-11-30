package com.example.demo;

import com.example.demo.model.Event;
import com.example.demo.model.Game;

import java.util.Collections;
import java.util.TreeSet;

public class testData {

    public static Event EVENT_1 = Event.builder()
            .botId("botId")
            .isAutoSplit(true)
            .subtype("subtype")
            .text("Events for game id:19481 apiId:10449463 CSKA Moskva ")
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

    public static Game GAME_1 = Game.builder()
            .id("19481")
            .smEvents(Collections.EMPTY_SET)
            .build();

    public static Game GAME_2 = Game.builder()
            .id("id_2")
            .smEvents(Collections.EMPTY_SET)
            .build();
}
