package com.tysystems.file.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tysystems.project_management.dto.PL_CUSTVO;

@Component
public class JacksonFileService implements FileService {
    
    @Override
    public void saveFile(List<PL_CUSTVO> list) {
        String path = "./filestorage/test";

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File(path), list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

