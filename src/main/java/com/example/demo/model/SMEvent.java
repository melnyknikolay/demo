package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SMEvent {

    private Long id;
    private Long teamId;
    private String type;
    private Long fixtureId;
    private Long playerId;
    private String playerName;
    private Long relatedPlayerId;
    private String relatedPlayerName;
    private Long minute;
    private String extraMinute;
    private String reason;
    private String injuried;
    private String result;

}
