package com.example.demo.service;

import com.example.demo.model.Event;
import com.example.demo.model.Game;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static com.example.demo.testData.*;
import static org.assertj.core.api.Assertions.assertThat;


public class GamesServiceTest {

    @Test
    public void getGames() {
        List<Event> expected = Arrays.asList(EVENT_1, EVENT_2);
        GamesService service = new GamesService();
        Collection<Game> actual = service.getGames(expected);
        assertThat(actual).containsExactly(GAME_1);
    }
}