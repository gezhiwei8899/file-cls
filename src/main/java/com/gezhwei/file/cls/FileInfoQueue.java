package com.gezhwei.file.cls;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class FileInfoQueue {
    private static final BlockingQueue<FileInfo> FILE_INFOS = new ArrayBlockingQueue<>(100000);

    public static Integer size() {
        return FILE_INFOS.size();
    }
}