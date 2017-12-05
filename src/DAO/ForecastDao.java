package DAO;

import connection.ConnectionManager;
import entities.Forecast;

import java.sql.*;

public class ForecastDao {
    private static ForecastDao INSTANCE;

    private ForecastDao() {}

    public static ForecastDao getInstance() {
        if (INSTANCE == null) {
            synchronized (ForecastDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ForecastDao();
                }
            }
        }
        return INSTANCE;
    }

    public Forecast addForecast(Forecast forecast) {
        try (Connection connection = ConnectionManager.getConnection()){
            String sql = "INSERT INTO forecasts (match_id, first_team_forecast, second_team_forecast, user_id) " +
                    "VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, forecast.getMatch().getId());
            statement.setInt(2, forecast.getFirstTeamForecast());
            statement.setInt(3, forecast.getSecondTeamForecast());
            statement.setLong(4,forecast.getUserId());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                forecast.setId(generatedKeys.getLong(1));
            }
            statement.close();
            generatedKeys.close();
            return forecast;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Forecast updateForecast(Forecast forecast) {
        try (Connection connection = ConnectionManager.getConnection()){
            String sql = "UPDATE forecasts SET first_team_forecast = ?, second_team_forecast = ? " +
                         "WHERE match_id = ? " +
                         "AND user_id = ? ";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, forecast.getFirstTeamForecast());
            statement.setInt(2, forecast.getSecondTeamForecast());
            statement.setLong(3, forecast.getMatch().getId());
            statement.setLong(4,forecast.getUserId());
            statement.executeUpdate();
            statement.close();
            return forecast;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean isExist(Forecast forecast) {
        try (Connection connection = ConnectionManager.getConnection()){
            String sql = "SELECT * FROM forecasts " +
                        "WHERE match_id = ? " +
                        "and user_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, forecast.getMatch().getId());
            statement.setLong(2,forecast.getUserId());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
            statement.clearParameters();
            resultSet.close();
            return false;
        } catch (SQLException e) {
            return false;
        }
    }

}
