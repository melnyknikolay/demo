package com.example.demo.service;

import com.example.demo.model.Event;
import com.example.demo.repository.EventRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.example.demo.testData.EVENT_1;
import static com.example.demo.testData.EVENT_2;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class EventServiceTest {

    @MockBean
    private EventRepository repository;
    @MockBean
    private ParseService parseService;
    @MockBean
    private LoaderService loaderService;

    private static EventService service;

    @Before
    public void init(){
        service = new EventService(repository, parseService, loaderService);
    }

    @Test
    public void getEvents() throws IOException {
        List<Event> expected = Arrays.asList(EVENT_1, EVENT_2);

        given(this.repository.isEmpty())
                .willReturn(false);

        given(this.repository.getAllEvents())
                .willReturn(expected);

        List<Event> actual = service.getEvents();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void getLimitedEvents() throws IOException {
        List<Event> mock = Mockito.mock(List.class);
        List<Event> expected = Arrays.asList(EVENT_1, EVENT_2);

        given(this.repository.isEmpty())
                .willReturn(false);

        given(this.repository.getAllEvents())
                .willReturn(mock);
        given(this.repository.getEventsLimited(eq(1500L)))
                .willReturn(expected);

        given(mock.size())
                .willReturn(1600);

        List<Event> actual = service.getEvents();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void getDefaultEvents() throws IOException {
        List<Event> expected = Arrays.asList(EVENT_1, EVENT_2);

        given(this.repository.isEmpty())
                .willReturn(true);

        String testString = "testString";

        given(this.loaderService.loadEventsFromFile(any()))
                .willReturn(testString);
        given(this.parseService.parseJson(eq(testString)))
                .willReturn(expected);

        given(this.repository.getAllEvents())
                .willReturn(expected);

        List<Event> actual = service.getEvents();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void uploadEvents() throws IOException {
        String testString = "testString";
        List<Event> expected = Arrays.asList(EVENT_1, EVENT_2);

        given(this.parseService.parseJson(eq(testString)))
                .willReturn(expected);
        doNothing().when(this.repository).saveAll(expected);

        service.uploadEvents(testString);

        verify(parseService, times(1)).parseJson(testString);
        verify(repository, times(1)).saveAll(expected);
    }
}