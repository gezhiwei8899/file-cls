package com.gezhwei.file.cls.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public final class DataSourceConfig {

    private static HikariDataSource dataSource;

    public static void initDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://5977377fb3f8d.sh.cdb.myqcloud.com:5174/file-info?useSSL=false&useUnicode=true&characterEncoding=utf-8&autoReconnect=true");
        config.setUsername("root");
        config.setPassword("Richie9357-+a");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.setDriverClassName("com.mysql.jdbc.Driver");
        config.setAutoCommit(true);
        config.setMaximumPoolSize(20);
        config.setIdleTimeout(60000);
        dataSource = new HikariDataSource(config);
    }

    public synchronized static Connection getConn() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
