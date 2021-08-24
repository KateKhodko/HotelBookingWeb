package com.khodko.RoyalHotel.connection;

import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionPool {
	
	private final static Logger LOGGER = LogManager.getLogger(ConnectionPool.class);

    private static final AtomicReference<ConnectionPool> INSTANCE = new AtomicReference<>();
    private static final Lock INSTANCE_LOCK = new ReentrantLock();

    private final Queue<ProxyConnection> availableConnections = new ArrayDeque<>();
    private final Set<ProxyConnection> inUseConnections = new HashSet<>();
    private final Lock connectionsLock = new ReentrantLock();
    private final Semaphore connectionsSemaphore;
   
    public static ConnectionPool getInstance() {
        if (INSTANCE.get() == null) {
            try {
                INSTANCE_LOCK.lock();
                if (INSTANCE.get() == null) {
                    ConnectionPoolFactory connectionPoolFactory = new ConnectionPoolFactory();
                    ConnectionPool pool = connectionPoolFactory.create();
                    INSTANCE.getAndSet(pool);
                }
            } finally {
                INSTANCE_LOCK.unlock();
            }
        }
        return INSTANCE.get();
    }

    protected ConnectionPool(int poolSize, List<ProxyConnection> connections) {
        connectionsSemaphore = new Semaphore(poolSize);
        List<ProxyConnection> updatedConnections = connections.stream()
                .peek(connection -> connection.setConnectionPool(this))
                .collect(Collectors.toList());
        availableConnections.addAll(updatedConnections);
    }

    public ProxyConnection getConnection() {
        try {
            connectionsSemaphore.acquire();
            connectionsLock.lock();
            ProxyConnection connection = availableConnections.poll();
            inUseConnections.add(connection);
            return connection;
        } catch (InterruptedException e) {
            LOGGER.fatal(e.getMessage(), e);
            throw new ConnectionPoolException(e);
        } finally {
            connectionsLock.unlock();
        }
    }

    public void returnConnection(ProxyConnection connection) {
        try {
            connectionsLock.lock();
            if (inUseConnections.contains(connection)) {
                inUseConnections.remove(connection);
                availableConnections.add(connection);
                connectionsSemaphore.release();
            }
        } finally {
            connectionsLock.unlock();
        }
    }
}
