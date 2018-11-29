package com.example.demo.service;

import com.example.demo.model.Event;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.example.demo.testData.TEST_EVENT;
import static com.example.demo.testData.TEST_JSON;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class ParseServiceTest {

    private static ParseService service = new ParseService(new ObjectMapper());

    @Test
    public void parseJson() throws IOException {
        List<Event> actual = service.parseJson(TEST_JSON);
        assertThat(actual).isEqualTo(Arrays.asList(TEST_EVENT));
    }
}