package connection;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import java.sql.*;

public final class ConnectionManager {
    private static final String URL = "jdbc:mysql://localhost:3306/forecasts?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final DataSource dataSource;

    private ConnectionManager() {}

    static {
        PoolProperties poolProperties = new PoolProperties();
        poolProperties.setUrl(URL);
        poolProperties.setUsername(USER);
        poolProperties.setPassword(PASSWORD);
        poolProperties.setDriverClassName(DRIVER);
        dataSource = new DataSource(poolProperties);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
