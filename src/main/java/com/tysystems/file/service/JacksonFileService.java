package com.tysystems.file.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@Primary
public class JacksonFileService<T> implements FileService<T> {
    
    @Override
    public void saveFile(List<T> list, String path) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File(path), list);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
