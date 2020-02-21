package se.ecutb.peter;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Database {

    //old url: jdbc:mysql://localhost:3306/todo_db?&autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
    //new url: jdbc:mysql://localhost:3306/todo_db?&autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Europe/Berlin

    // This connection method exposes your password:
    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/world?&autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Europe/Berlin",
                "root",
                "1234");
        return connection;
    }
}

