package com.gezhwei.file.cls.handler;

import com.gezhwei.file.cls.FileInfoQueue;
import com.gezhwei.file.cls.FileQueue;

import java.io.File;
import java.util.concurrent.*;

public class FileMd5Handler implements FileHandler {

    private static final ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(4, 4, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100), r -> new Thread(r, "md5"), (r, executor) -> {
        BlockingQueue<Runnable> queue = executor.getQueue();
        queue.offer(r);
    });

    private FileHandler fileHandler;

    public FileMd5Handler(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    @Override
    public void handler() {
        Integer size = FileQueue.size();
        while (!FileQueue.isEmpty()) {
            File file = FileQueue.poll();
            THREAD_POOL_EXECUTOR.submit(new FileMdInnerTask(file));
        }
        for (; ; ) {
            if (size.equals(FileInfoQueue.size())) {
                break;
            }
        }
        fileHandler.handler();
    }

    private class FileMdInnerTask implements Runnable {

        private File file;

        public FileMdInnerTask(File file) {
            this.file = file;
        }

        @Override
        public void run() {

        }
    }
}
