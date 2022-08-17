package com.tysystems.file.service;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JacksonFileService implements FileService {
    
    @Override
    public void saveFile() {
        String path = "./filestorage/testtest";
        TestClass testClass = new TestClass("sh", 27);

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File(path), testClass);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

class TestClass {

    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public TestClass(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}