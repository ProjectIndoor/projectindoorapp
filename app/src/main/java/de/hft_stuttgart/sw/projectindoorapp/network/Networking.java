package de.hft_stuttgart.sw.projectindoorapp.network;

import okhttp3.ConnectionPool;

/**
 * Created by moritzschillinger on 05.12.17.
 */

public class Networking {

    private static final ConnectionPool globalConnectionPool = new ConnectionPool();

    public static ConnectionPool getPool() {
        return globalConnectionPool;
    }

}
