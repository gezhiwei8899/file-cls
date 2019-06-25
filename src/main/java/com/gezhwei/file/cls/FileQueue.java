package com.gezhwei.file.cls;

import java.io.File;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class FileQueue {
    private static final BlockingQueue<File> FILE_BLOCKING_QUEUE = new ArrayBlockingQueue<>(1000000);


    public static void add(File file) {
        FILE_BLOCKING_QUEUE.add(file);
    }

    public static boolean isEmpty() {
        return FILE_BLOCKING_QUEUE.isEmpty();
    }

    public static File poll() {
        return FILE_BLOCKING_QUEUE.poll();
    }

    public static Integer size() {
        return FILE_BLOCKING_QUEUE.size();
    }
}
