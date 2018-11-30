package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SMEvent {
    private static final AtomicLong COUNTER = new AtomicLong();

    private Long id;
    private Long smEventId;
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

    public static Long generateId(){
        return COUNTER.incrementAndGet();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SMEvent smEvent = (SMEvent) o;
        return Objects.equals(type, smEvent.type) &&
                Objects.equals(playerId, smEvent.playerId) &&
                Objects.equals(minute, smEvent.minute);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, playerId, minute);
    }
}
