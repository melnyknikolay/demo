package com.example.demo.service;

import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class Loader {
    public String loadEventsFromFile(File file) throws IOException {
        StringBuilder result = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        while (reader.ready()){
            result.append(reader.readLine());
        }
        return result.toString();
    }
}
