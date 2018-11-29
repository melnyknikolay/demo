package com.example.demo.web;

import com.example.demo.model.Event;
import com.example.demo.service.Loader;
import com.example.demo.service.ParseService;
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

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static com.example.demo.testData.EVENT_1;
import static com.example.demo.testData.EVENT_2;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
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



//    @Test
//    public void shouldListAllFiles() throws Exception {
//        given(this.storageService.loadAll())
//                .willReturn(Stream.of(Paths.get("first.txt"), Paths.get("second.txt")));
//
//        this.mvc.perform(get("/")).andExpect(status().isOk())
//                .andExpect(model().attribute("files",
//                        Matchers.contains("http://localhost/files/first.txt",
//                                "http://localhost/files/second.txt")));
//    }
//
//    @Test
//    public void shouldSaveUploadedFile() throws Exception {
//        MockMultipartFile multipartFile = new MockMultipartFile("file", "test.txt",
//                "text/plain", "Spring Framework".getBytes());
//        this.mvc.perform(fileUpload("/").file(multipartFile))
//                .andExpect(status().isFound())
//                .andExpect(header().string("Location", "/"));
//
//        then(this.service).should().parseJson(multipartFile);
//    }

    @Test
    public void welcome() throws Exception {
        String testJson = "json Events";
        given(this.loader.loadEventsFromFile(any()))
                .willReturn(testJson);
        given(this.service.parseJson(testJson))
                .willReturn(Arrays.asList(EVENT_1, EVENT_2));

        this.mvc.perform(get("/")).andExpect(status().isOk())
                .andExpect(model().attribute("events",
                        Matchers.contains(Arrays.asList(EVENT_1, EVENT_2))));
    }

    @Test
    public void uploadEvents() {
    }

}