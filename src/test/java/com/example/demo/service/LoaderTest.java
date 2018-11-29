package com.example.demo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class LoaderTest {

    private Loader loader = new Loader();

    @Test
    public void loadEventsFromFile() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("2018-11-27B.json", this.getClass().getClassLoader());
        File testFile = classPathResource.getFile();
        StringBuilder result = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(testFile));
        while (reader.ready()){
            result.append(reader.readLine());
        }
        String actual = loader.loadEventsFromFile(testFile);
        assertEquals(result.toString(), actual);

    }
}