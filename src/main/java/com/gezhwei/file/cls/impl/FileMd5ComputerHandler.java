package com.gezhwei.file.cls.impl;

import com.gezhwei.file.cls.api.FileCommndApi;
import com.gezhwei.file.cls.common.CalacFileMd5;
import com.gezhwei.file.cls.store.FileStore;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FileMd5ComputerHandler implements FileCommndApi {

    private static final ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(1, 8, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1000), (r -> new Thread(r, "md5")), (r, e) -> {
        BlockingQueue<Runnable> queue = e.getQueue();
        queue.add(r);
    });

    @Override
    public void execute() throws IOException {
        BlockingQueue<File> store = FileStore.getStore();
        for (File file : store) {
            THREAD_POOL_EXECUTOR.submit(new FileTask(file));
        }
    }

    private class FileTask implements Runnable {

        private File file;

        FileTask(File file) {
            this.file = file;
        }

        @Override
        public void run() {
            String md5 = CalacFileMd5.getFileMD5(file);
        }
    }
}
