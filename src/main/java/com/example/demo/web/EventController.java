package com.example.demo.web;

import com.example.demo.service.Loader;
import com.example.demo.service.ParseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class EventController {

    private final ParseService service;
    private final Loader loader;

    public EventController(ParseService service, Loader loader) {
        this.service = service;
        this.loader = loader;
    }

    @GetMapping("/")
    public String welcome(Model model) throws IOException {
        model.addAttribute("events", service.parseJson(loader.loadEventsFromFile(new File("./2018-11-27B.json"))));
        return "table";
    }

    @PostMapping("/")
    public String uploadEvents(@RequestParam("file") MultipartFile file, Model model) throws IOException {
        String content = new String(file.getBytes(), "UTF-8");
        model.addAttribute("events", service.parseJson(content));
        return "table";
    }
}
