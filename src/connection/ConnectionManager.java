package connection;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import utils.StaticContent;

import java.sql.*;

public class ConnectionManager {
    private static final DataSource dataSource;

    static {
        PoolProperties poolProperties = new PoolProperties();
        poolProperties.setUrl(StaticContent.URL);
        poolProperties.setUsername(StaticContent.USER);
        poolProperties.setPassword(StaticContent.PASSWORD);
        poolProperties.setDriverClassName(StaticContent.DRIVER);
        dataSource = new DataSource(poolProperties);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
