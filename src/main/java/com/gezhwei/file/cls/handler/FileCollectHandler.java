package com.gezhwei.file.cls.handler;

import com.gezhwei.file.cls.FileQueue;

import java.io.File;

public class FileCollectHandler implements FileHandler {

    private FileHandler fileHandler;

    private String path;

    public FileCollectHandler(String path, FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    @Override
    public void handler() {
        File file = new File(path);
        if (!file.isDirectory()) {
            System.err.println(path + " 不是一个文件夹");
            return;
        }
        if (file.exists()) {
            collectingFile(file);
        }
        fileHandler.handler();
    }


    private void collectingFile(File f) {
        File[] files = f.listFiles();
        if (null == files || files.length <= 0) {
            return;
        }
        for (File file : files) {
            if (file.isDirectory()) {
                collectingFile(file);
            } else {
                if (file.getName().startsWith("$RECY") || file.getName().startsWith("System V")) {
                    continue;
                }
                FileQueue.add(file);
                System.out.println("收集文件： " + file.getName());
            }
        }

    }
}
