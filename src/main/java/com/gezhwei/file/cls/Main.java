package com.gezhwei.file.cls;

import com.gezhwei.file.cls.api.FileCollectionApi;
import com.gezhwei.file.cls.api.FileCommndApi;
import com.gezhwei.file.cls.dao.DataSourceConfig;
import com.gezhwei.file.cls.factory.FileClsFactory;
import com.gezhwei.file.cls.store.FileStore;

public class Main {

    public static final String targetPath = "G:\\BaiduNetdiskDownload\\tar";
    public static final String movePath = "G:\\BaiduNetdiskDownload\\clean\\";
    public static final String movePathSame = "G:\\BaiduNetdiskDownload\\clean\\same\\";

    public static final String command = "d";


    public static void main(String[] args) {
        DataSourceConfig.initDataSource();
        try {
            FileCollectionApi fileCollectionApi = FileClsFactory.createCollectHandler();
            fileCollectionApi.collectFilesByPath(targetPath);
            if (FileStore.isIsCollectedOK()) {
                FileCommndApi fileCommndApi = FileClsFactory.createCommandHandler(command);
                if (null == fileCommndApi) {
                    System.err.println("文件命令类是空的");
                    return;
                }
                fileCommndApi.execute();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
