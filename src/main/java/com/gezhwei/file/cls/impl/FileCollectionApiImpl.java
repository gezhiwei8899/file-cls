package com.gezhwei.file.cls.impl;

import com.gezhwei.file.cls.api.FileCollectionApi;
import com.gezhwei.file.cls.store.FileStore;

import java.io.File;

public class FileCollectionApiImpl implements FileCollectionApi {
    @Override
    public void collectFilesByPath(String targetPath) {
        File file = new File(targetPath);
        if (!file.isDirectory()) {
            System.err.println(targetPath + " 不是一个文件夹");
            return;
        }
        if (file.exists()) {
            collectingFile(file);
        }

        FileStore.setIsCollectedOK(true);
    }

    private void collectingFile(File f) {
        File[] files = f.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                collectingFile(file);
            } else {
                if (file.getName().startsWith("$RECY") || file.getName().startsWith("System V")) {
                    continue;
                }
                FileStore.collectFile(file);
                System.out.println("收集文件： " + file.getName());
            }
        }

    }
}
