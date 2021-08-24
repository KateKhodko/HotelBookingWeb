package com.khodko.RoyalHotel.connection;

public class ConnectionPoolException extends RuntimeException {
    public ConnectionPoolException(Throwable throwable) {
        super(throwable);
    }

    public ConnectionPoolException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
