package com.tysystems.file;

public interface FileStatus {
    
    // 정상 파일인 경우
    public String completed = "completed";

    // Spring Batch가 작동하고 있는 경우
    public String isRunning = "isRunning";

    // 비어있는 파일일 경우
    public String isFileNull = "isNull";

    // key값에 null이 들어있는 경우
    public String isKeyNull = "isKeyNull";
    
}
