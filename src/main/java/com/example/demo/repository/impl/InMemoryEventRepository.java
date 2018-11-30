package com.example.demo.repository.impl;

import com.example.demo.model.Event;
import com.example.demo.repository.EventRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class InMemoryEventRepository implements EventRepository {
    private final List<Event> events;

    public InMemoryEventRepository() {
        this.events = new ArrayList<>();
    }

    @Override
    public void saveAll(List<Event> events) {
        this.events.clear();
        this.events.addAll(events);
    }

    @Override
    public List<Event> getAllEvents() {
        return events;
    }

    @Override
    public List<Event> getEventsLimited(Long limit) {
        return events.stream().limit(limit).collect(Collectors.toList());
    }

    @Override
    public boolean isEmpty() {
        return events.size() == 0;
    }
}
