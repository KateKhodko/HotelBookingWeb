package com.khodko.RoyalHotel.connection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.khodko.RoyalHotel.util.ResourcesReader;


public class ConnectionPoolFactory {

    private final String PROPERTIES_FILE = "database/database.properties";
    private final static Logger LOGGER = LogManager.getLogger(ConnectionPoolFactory.class);

    private String url;
    private String userName;
    private String password;
    private int poolSize;

    public ConnectionPool create() {
        try {
            propertiesInitialization();
            List<ProxyConnection> proxyConnections = createConnections();
            return new ConnectionPool(poolSize, proxyConnections);
        } catch (IOException | SQLException exception) {
            LOGGER.fatal(exception.getMessage(), exception);
            throw new ConnectionPoolException("Connection pool hasn't started properly", exception);
        }
    }

    private void propertiesInitialization() throws IOException, SQLException {
        Properties properties = new ResourcesReader().load(PROPERTIES_FILE);
        String driver = properties.getProperty("database.driver");
        try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new IOException();
		}		
        //DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        url = properties.getProperty("database.url");
        userName = properties.getProperty("database.userName");
        password = properties.getProperty("database.password");
        poolSize = Integer.parseInt(properties.getProperty("database.poolSize"));
    }

    private List<ProxyConnection> createConnections() throws SQLException {
        List<ProxyConnection> proxyConnections = new ArrayList<>();
        for (int index = 0; index < poolSize; index++) {
            Connection connection = DriverManager.getConnection(url, userName, password);
            ProxyConnection proxyConnection = new ProxyConnection(connection);
            proxyConnections.add(proxyConnection);
        }
        return proxyConnections;
    }
}
