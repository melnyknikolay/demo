package com.example.demo.repository;

import com.example.demo.model.Event;

import java.util.List;

public interface EventRepository {

    void saveAll(List<Event> events);
    List<Event> getAllEvents();
    List<Event> getEventsLimited(Long limit);
    boolean isEmpty();

}
