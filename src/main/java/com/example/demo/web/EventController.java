package com.example.demo.web;

import com.example.demo.model.Game;
import com.example.demo.service.EventService;
import com.example.demo.service.GamesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;

@Controller
public class EventController {

    private final EventService eventService;
    private final GamesService gamesService;

    public EventController(EventService eventService, GamesService gamesService) {
        this.eventService = eventService;
        this.gamesService = gamesService;
    }

    @GetMapping("/**")
    public String welcome() {
        return "redirect:/events";
    }

    @GetMapping("/events")
    public String events(Model model) throws IOException {
        model.addAttribute("events", eventService.getEvents());
        return "eventPage";
    }

    @PostMapping("/events")
    public String uploadEvents(@RequestParam("file") MultipartFile file, Model model) throws IOException {
        String content = new String(file.getBytes(), "UTF-8");
        eventService.uploadEvents(content);
        model.addAttribute("events", eventService.getEvents());
        return "eventPage";
    }

    @GetMapping("/games")
    public String games(Model model) throws IOException {
        Collection<Game> games = gamesService.getGames(eventService.getEvents());
        model.addAttribute("games", games);
        return "gamePage";
    }

    @PostMapping("/games")
    public String uploadEventsRedirectToGames(@RequestParam("file") MultipartFile file, Model model) throws IOException {
        String content = new String(file.getBytes(), "UTF-8");
        eventService.uploadEvents(content);
        Collection<Game> games = gamesService.getGames(eventService.getEvents());
        model.addAttribute("games", games);
        return "gamePage";
    }
}
