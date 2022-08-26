package com.tysystems.file.service;

import java.util.List;

import com.tysystems.file.FilePathDTO;

public interface FileService<T> {
    
    public void saveFile(List<T> list, String path);
    
    public List<FilePathDTO> fileList(String pathWithoutName);

    public List<String> fileContent(String pathWithoutName, String name);

    public boolean deleteFile(String pathWithoutName, String name);

}
