package com.codecool.fileshare.repository;

import org.postgresql.ds.PGSimpleDataSource;

import java.sql.SQLException;

public class DB {
    public PGSimpleDataSource getDataSource() throws SQLException {
        PGSimpleDataSource ds = new PGSimpleDataSource();

        ds.setDatabaseName(System.getenv().get("DB_NAME"));
        ds.setUser(System.getenv().get("DB_USERNAME"));
        ds.setPassword(System.getenv().get("DB_PASSWORD"));

        ds.getConnection().close();
        return ds;
    }
}


