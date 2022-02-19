package com.codecool.fileshare.repository;

import org.postgresql.ds.PGSimpleDataSource;

import java.sql.SQLException;

public class DB {
    public PGSimpleDataSource getDataSource() throws SQLException {
        PGSimpleDataSource ds = new PGSimpleDataSource();

//        ds.setServerNames(new String[]{"3.125.49.36"});
//        ds.setDatabaseName("postgres");
//        ds.setUser("user");
//        ds.setPassword("USER123");

        ds.setDatabaseName("warhol");
        ds.setUser("postgres");
        ds.setPassword("admin");

        ds.getConnection().close();
        return ds;
    }
}


