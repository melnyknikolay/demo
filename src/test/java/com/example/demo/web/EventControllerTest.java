package com.example.demo.web;

import com.example.demo.service.Loader;
import com.example.demo.service.ParseService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;

import static com.example.demo.testData.EVENT_1;
import static com.example.demo.testData.EVENT_2;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class EventControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ParseService service;

    @MockBean
    private Loader loader;

    private String testJson = "json Events";

    @Test
    public void welcome() throws Exception {
        given(this.loader.loadEventsFromFile(any()))
                .willReturn(testJson);
        given(this.service.parseJson(testJson))
                .willReturn(Arrays.asList(EVENT_1, EVENT_2));

        this.mvc.perform(get("/")).andExpect(status().isOk())
                .andExpect(model().attribute("events",
                        Matchers.contains(EVENT_1, EVENT_2)));
    }

    @Test
    public void uploadEvents() throws Exception {
        MockMultipartFile mockFile = new MockMultipartFile("file", testJson.getBytes());

        given(this.service.parseJson(testJson))
                .willReturn(Arrays.asList(EVENT_1, EVENT_2));

        this.mvc.perform(fileUpload("/")
                .file(mockFile)).andExpect(status().isOk())
                .andExpect(model().attribute("events",
                        Matchers.contains(EVENT_1, EVENT_2)));
    }

}