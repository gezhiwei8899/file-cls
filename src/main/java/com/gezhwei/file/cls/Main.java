package com.gezhwei.file.cls;

import com.gezhwei.file.cls.handler.FileCollectHandler;
import com.gezhwei.file.cls.handler.FileHandler;
import com.gezhwei.file.cls.handler.FileMd5Handler;
import com.gezhwei.file.cls.handler.FilePrintHandler;

public class Main {
    public static void main(String[] args) {
        String path = null;
        FileHandler fileHandler = new FileCollectHandler(path, new FileMd5Handler(new FilePrintHandler()));
        fileHandler.handler();
    }
}
