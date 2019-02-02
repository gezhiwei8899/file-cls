package com.gezhwei.file.cls.impl;

import com.gezhwei.file.cls.api.FileCommndApi;
import com.gezhwei.file.cls.store.FileStore;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;

import static com.gezhwei.file.cls.Main.movePath;
import static com.gezhwei.file.cls.Main.movePathSame;

public class FileMoveHanlder implements FileCommndApi {
    @Override
    public void execute() throws IOException {
        BlockingQueue<File> store = FileStore.getStore();
        for (File file : store) {
            try {
                Files.move(Paths.get(file.getAbsolutePath()), Paths.get(movePath + file.getName()));
            } catch (FileAlreadyExistsException ex) {
                String path = movePathSame + UUID.randomUUID().toString() + "\\";
                if (!Files.exists(Paths.get(path))) {
                    Files.createDirectories(Paths.get(path));
                }
                Files.move(Paths.get(file.getAbsolutePath()), Paths.get(path + file.getName()));
            }
        }
    }
}
