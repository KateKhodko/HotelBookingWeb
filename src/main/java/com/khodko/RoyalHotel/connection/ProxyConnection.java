package com.khodko.RoyalHotel.connection;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;


public class ProxyConnection implements Connection {

    private final Connection proxyConnection;
    private ConnectionPool connectionPool;

    public ProxyConnection(Connection connection) {
        proxyConnection = connection;
    }

    void setConnectionPool(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public Statement createStatement() throws SQLException {
        return proxyConnection.createStatement();
    }

    public PreparedStatement prepareStatement(String query) throws SQLException {
        return proxyConnection.prepareStatement(query);
    }

    @Override
    public CallableStatement prepareCall(String s) throws SQLException {
        return proxyConnection.prepareCall(s);
    }

    @Override
    public String nativeSQL(String s) throws SQLException {
        return proxyConnection.nativeSQL(s);
    }

    public void setAutoCommit(boolean autoCommitState) throws SQLException {
        proxyConnection.setAutoCommit(autoCommitState);
    }

    @Override
    public boolean getAutoCommit() throws SQLException {
        return proxyConnection.getAutoCommit();
    }

    public void commit() throws SQLException {
        proxyConnection.commit();
    }

    public void rollback() throws SQLException {
        proxyConnection.rollback();
    }

    @Override
    public void close() {
        connectionPool.returnConnection(this);
    }

    @Override
    public boolean isClosed() throws SQLException {
        return proxyConnection.isClosed();
    }

    @Override
    public DatabaseMetaData getMetaData() throws SQLException {
        return proxyConnection.getMetaData();
    }

    @Override
    public void setReadOnly(boolean b) throws SQLException {
        proxyConnection.setReadOnly(b);
    }

    @Override
    public boolean isReadOnly() throws SQLException {
        return proxyConnection.isReadOnly();
    }

    @Override
    public void setCatalog(String s) throws SQLException {
        proxyConnection.setCatalog(s);
    }

    @Override
    public String getCatalog() throws SQLException {
        return proxyConnection.getCatalog();
    }

    @Override
    public void setTransactionIsolation(int i) throws SQLException {
        proxyConnection.setTransactionIsolation(i);
    }

    @Override
    public int getTransactionIsolation() throws SQLException {
        return proxyConnection.getTransactionIsolation();
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        return proxyConnection.getWarnings();
    }

    @Override
    public void clearWarnings() throws SQLException {
        proxyConnection.clearWarnings();
    }

    @Override
    public Statement createStatement(int i, int i1) throws SQLException {
        return proxyConnection.createStatement(i, i1);
    }

    @Override
    public PreparedStatement prepareStatement(String s, int i, int i1) throws SQLException {
        return proxyConnection.prepareStatement(s, i, i1);
    }

    @Override
    public CallableStatement prepareCall(String s, int i, int i1) throws SQLException {
        return proxyConnection.prepareCall(s, i, i1);
    }

    @Override
    public Map<String, Class<?>> getTypeMap() throws SQLException {
        return proxyConnection.getTypeMap();
    }

    @Override
    public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
        proxyConnection.setTypeMap(map);
    }

    @Override
    public void setHoldability(int i) throws SQLException {
        proxyConnection.setHoldability(i);
    }

    @Override
    public int getHoldability() throws SQLException {
        return proxyConnection.getHoldability();
    }

    @Override
    public Savepoint setSavepoint() throws SQLException {
        return proxyConnection.setSavepoint();
    }

    @Override
    public Savepoint setSavepoint(String s) throws SQLException {
        return proxyConnection.setSavepoint(s);
    }

    @Override
    public void rollback(Savepoint savepoint) throws SQLException {
        proxyConnection.rollback(savepoint);
    }

    @Override
    public void releaseSavepoint(Savepoint savepoint) throws SQLException {
        proxyConnection.releaseSavepoint(savepoint);
    }

    @Override
    public Statement createStatement(int i, int i1, int i2) throws SQLException {
        return proxyConnection.createStatement(i, i1, i2);
    }

    @Override
    public PreparedStatement prepareStatement(String s, int i, int i1, int i2) throws SQLException {
        return proxyConnection.prepareStatement(s, i, i1, i2);
    }

    @Override
    public CallableStatement prepareCall(String s, int i, int i1, int i2) throws SQLException {
        return proxyConnection.prepareCall(s, i, i1, i2);
    }

    @Override
    public PreparedStatement prepareStatement(String s, int i) throws SQLException {
        return proxyConnection.prepareStatement(s, i);
    }

    @Override
    public PreparedStatement prepareStatement(String s, int[] ints) throws SQLException {
        return proxyConnection.prepareStatement(s, ints);
    }

    @Override
    public PreparedStatement prepareStatement(String s, String[] strings) throws SQLException {
        return proxyConnection.prepareStatement(s, strings);
    }

    @Override
    public Clob createClob() throws SQLException {
        return proxyConnection.createClob();
    }

    @Override
    public Blob createBlob() throws SQLException {
        return proxyConnection.createBlob();
    }

    @Override
    public NClob createNClob() throws SQLException {
        return proxyConnection.createNClob();
    }

    @Override
    public SQLXML createSQLXML() throws SQLException {
        return proxyConnection.createSQLXML();
    }

    @Override
    public boolean isValid(int i) throws SQLException {
        return proxyConnection.isValid(i);
    }

    @Override
    public void setClientInfo(String s, String s1) throws SQLClientInfoException {
        proxyConnection.setClientInfo(s, s1);
    }

    @Override
    public void setClientInfo(Properties properties) throws SQLClientInfoException {
        proxyConnection.setClientInfo(properties);
    }

    @Override
    public String getClientInfo(String s) throws SQLException {
        return proxyConnection.getClientInfo(s);
    }

    @Override
    public Properties getClientInfo() throws SQLException {
        return proxyConnection.getClientInfo();
    }

    @Override
    public Array createArrayOf(String s, Object[] objects) throws SQLException {
        return proxyConnection.createArrayOf(s, objects);
    }

    @Override
    public Struct createStruct(String s, Object[] objects) throws SQLException {
        return proxyConnection.createStruct(s, objects);
    }

    @Override
    public void setSchema(String s) throws SQLException {
        proxyConnection.setSchema(s);
    }

    @Override
    public String getSchema() throws SQLException {
        return proxyConnection.getSchema();
    }

    @Override
    public void abort(Executor executor) throws SQLException {
        proxyConnection.abort(executor);
    }

    @Override
    public void setNetworkTimeout(Executor executor, int i) throws SQLException {
        proxyConnection.setNetworkTimeout(executor, i);
    }

    @Override
    public int getNetworkTimeout() throws SQLException {
        return proxyConnection.getNetworkTimeout();
    }

    @Override
    public <T> T unwrap(Class<T> aClass) throws SQLException {
        return proxyConnection.unwrap(aClass);
    }

    @Override
    public boolean isWrapperFor(Class<?> aClass) throws SQLException {
        return proxyConnection.isWrapperFor(aClass);
    }
}
