package com.gezhwei.file.cls.store;

import java.io.File;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public final class FileStore {
    private static final BlockingQueue<File> BLOCKING_QUEUE = new LinkedBlockingQueue<>();

    private static volatile boolean isCollectedOK = false;

    public static void collectFile(File file) {
        BLOCKING_QUEUE.add(file);
    }

    public static boolean isIsCollectedOK() {
        return isCollectedOK;
    }

    public static void setIsCollectedOK(boolean isCollectedOK) {
        FileStore.isCollectedOK = isCollectedOK;
        System.out.println("文件收集完毕");
    }


    public static BlockingQueue<File> getStore() {
        return BLOCKING_QUEUE;
    }
}
