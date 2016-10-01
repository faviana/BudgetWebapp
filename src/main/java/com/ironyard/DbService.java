package com.ironyard;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by favianalopez on 9/29/16.
 */
public class DbService {
    private String user="postgres";
    private String password="admin";
    private String jdbcUrl="jdbc:postgresql://localhost:5432/postgres";

    public Connection getConnection() throws SQLException{
        return DriverManager.getConnection(jdbcUrl,user,password);

    }
}
