package com.gezhwei.file.cls.factory;

import com.gezhwei.file.cls.api.FileCollectionApi;
import com.gezhwei.file.cls.api.FileCommndApi;
import com.gezhwei.file.cls.impl.FileCollectionApiImpl;
import com.gezhwei.file.cls.impl.FileMd5ComputerHandler;
import com.gezhwei.file.cls.impl.FileMoveHanlder;

public class FileClsFactory {

    public static FileCollectionApi createCollectHandler() {
        return new FileCollectionApiImpl();
    }

    public static FileCommndApi createCommandHandler(String command) {
        switch (command) {
            case "move":
                return new FileMoveHanlder();
            case "md5_computer":
                return new FileMd5ComputerHandler();
            default:
                return null;

        }
    }
}
