package com.example.demo.web;

import com.example.demo.service.EventService;
import com.example.demo.service.GamesService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.example.demo.testData.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class EventControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EventService eventService;
    @MockBean
    private GamesService gamesService;

    private String testJson = "json Events";

    @Test
    public void welcome() throws Exception {
        given(this.eventService.getEvents())
                .willReturn(Arrays.asList(EVENT_1, EVENT_2));

        this.mvc.perform(get("/")).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/events"));
    }

    @Test
    public void events() throws Exception {
        given(this.eventService.getEvents())
                .willReturn(Arrays.asList(EVENT_1, EVENT_2));

        this.mvc.perform(get("/events")).andExpect(status().isOk())
                .andExpect(model().attribute("events",
                        Matchers.contains(EVENT_1, EVENT_2)));
    }

    @Test
    public void uploadEvents() throws Exception {
        MockMultipartFile mockFile = new MockMultipartFile("file", testJson.getBytes());

        doNothing().when(this.eventService).uploadEvents(testJson);

        given(this.eventService.getEvents())
                .willReturn(Arrays.asList(EVENT_1, EVENT_2));

        this.mvc.perform(fileUpload("/events")
                .file(mockFile)).andExpect(status().isOk())
                .andExpect(model().attribute("events",
                        Matchers.contains(EVENT_1, EVENT_2)));
    }

    @Test
    public void games() throws Exception {
        given(this.eventService.getEvents())
                .willReturn(Arrays.asList(EVENT_1, EVENT_2));

        given(this.gamesService.getGames(eventService.getEvents()))
                .willReturn(Stream.of(GAME_1, GAME_2).collect(Collectors.toList()));

        this.mvc.perform(get("/games")).andExpect(status().isOk())
                .andExpect(model().attribute("games",
                        Matchers.contains(GAME_1, GAME_2)));
    }

    @Test
    public void uploadEventsRedirectToGames() throws Exception {
        MockMultipartFile mockFile = new MockMultipartFile("file", testJson.getBytes());

        doNothing().when(this.eventService).uploadEvents(testJson);

        given(this.eventService.getEvents())
                .willReturn(Arrays.asList(EVENT_1, EVENT_2));

        given(this.gamesService.getGames(eventService.getEvents()))
                .willReturn(Stream.of(GAME_1, GAME_2).collect(Collectors.toList()));

        this.mvc.perform(fileUpload("/games")
                .file(mockFile)).andExpect(status().isOk())
                .andExpect(model().attribute("games",
                        Matchers.contains(GAME_1, GAME_2)));
    }
}