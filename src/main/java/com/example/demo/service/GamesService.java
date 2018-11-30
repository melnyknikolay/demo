package com.example.demo.service;

import com.example.demo.model.Event;
import com.example.demo.model.Game;
import com.example.demo.model.SMEvent;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GamesService {

    public Collection<Game> getGames(List<Event> eventList){
        Map<String, Game> games = new HashMap<>();

        eventList.stream()
                .filter(event -> event.getText().startsWith("Events for game"))
                .forEach(event -> {

                    String gameId = Arrays.stream(event.getText().split(" "))
                            .filter(str -> str.trim().startsWith("id"))
                            .findFirst().get().trim().substring(3);

                    TreeSet<SMEvent> smEvents = new TreeSet<>(Comparator.comparingLong(SMEvent::getMinute));
                    smEvents.addAll(event.getSmEvents());

                    Game game = Game.builder()
                            .id(gameId)
                            .smEvents(smEvents)
                            .build();

                    games.merge(gameId, game, (ov, nv) -> {
                        ov.getSmEvents().addAll(nv.getSmEvents());
                        return ov;
                    });
                });
        return games.values();
    }
}
