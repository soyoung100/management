package com.tysystems.file.service;

import java.util.List;

public interface FileService<T> {
    
    public void saveFile(List<T> list, String path);
    
}
