package com.codecool.fileshare.repository;

import org.postgresql.ds.PGSimpleDataSource;
import java.sql.SQLException;


public class DB {

    public PGSimpleDataSource getDataSource() throws SQLException {
        PGSimpleDataSource ds = new PGSimpleDataSource();

        String name = "postgres";
        ds.setDatabaseName(name);
        String userName = "user";
        ds.setUser(userName);
        String password = "USER123";
        ds.setPassword(password);

        ds.getConnection().close();
        return ds;
    }

}


