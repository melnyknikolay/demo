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
}
