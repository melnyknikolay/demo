package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Event {

    private String text;
    private String username;
    @JsonProperty("bot_id")
    private String botId;
    @JsonProperty("is_auto_split")
    private Boolean isAutoSplit;
    private String type;
    private String subtype;
    private String ts;
    private List<SMEvent> smEvents;

}