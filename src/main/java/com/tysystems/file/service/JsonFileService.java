package com.tysystems.file.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tysystems.file.FilePathDTO;

@Component
@Primary
public class JsonFileService<T> implements FileService<T> {
    
    
    @Override
    public void saveFile(List<T> list, String excelFilePath) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File(excelFilePath), list);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<FilePathDTO> fileList(String filePath) {

        File file = new File(filePath);
        File[] files = file.listFiles();

        List<FilePathDTO> filePathDTOList = new ArrayList<>();

        for (File fileOne : files) {
            filePathDTOList.add(new FilePathDTO(fileOne.getPath(), fileOne.getName()));
        }

        return filePathDTOList;
    }

    @Override
    public List<String> fileContent(String pathWithoutName, String name) {

        try {
            Path pathOne = Paths.get(new File(pathWithoutName + name).getPath());
            List<String> content = Files.readAllLines(pathOne);
            return content;
        } catch (Exception e) {
            System.out.println(e);
            List<String> list = new ArrayList<>();
            return list;
        }
        
    }

    @Override
    public boolean deleteFile(String pathWithoutName, String name) {
        File file = new File(pathWithoutName + name);
        return file.delete();
    }

}
