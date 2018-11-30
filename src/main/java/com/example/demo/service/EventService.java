package com.example.demo.service;

import com.example.demo.model.Event;
import com.example.demo.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class EventService {
    private final EventRepository repository;
    private final ParseService parseService;
    private final LoaderService loaderService;

    public EventService(EventRepository repository, ParseService parseService, LoaderService loaderService) {
        this.repository = repository;
        this.parseService = parseService;
        this.loaderService = loaderService;
    }

    public List<Event> getEvents() throws IOException {
        if (repository.isEmpty()) {
            repository.saveAll(getDefaultEvents());
        }
        return repository.getAllEvents().size() < 1500
                ?
                repository.getAllEvents()
                :
                repository.getEventsLimited(1500L);
    }

    private List<Event> getDefaultEvents() throws IOException {
        return parseService.parseJson(loaderService.loadEventsFromFile(new File("./2018-11-27B.json")));
    }

    public void uploadEvents(String content) throws IOException {
        repository.saveAll(parseService.parseJson(content));
    }
}
