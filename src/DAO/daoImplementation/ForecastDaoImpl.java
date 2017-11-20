package DAO.daoImplementation;

import DAO.ForecastDao;
import connection.ConnectionManager;
import entities.Forecast;

import java.sql.*;
import java.util.List;

public class ForecastDaoImpl implements ForecastDao {
    private static ForecastDaoImpl INSTANCE;

    private ForecastDaoImpl() {}

    public static ForecastDaoImpl getInstance() {
        if (INSTANCE == null) {
            synchronized (ForecastDaoImpl.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ForecastDaoImpl();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public Forecast addForecast(Forecast forecast) {
        try (Connection connection = ConnectionManager.getConnection()){
            String sql = "INSERT INTO forecasts (match_id, first_team_forecast, second_team_forecast, user_id) " +
                    "VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, forecast.getMatchId());
            statement.setInt(2, forecast.getFirstTeamForecast());
            statement.setInt(3, forecast.getSecondTeamForecast());
            statement.setLong(4,forecast.getUserId());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                forecast.setId(generatedKeys.getLong(1));
            }
            statement.close();
            return forecast;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Forecast updateForecast(Forecast forecast) {
        return null;
    }

    @Override
    public Forecast getForecastById(Long tournamentId) {
        return null;
    }

    @Override
    public List<Forecast> getListOfForecasts() {
        return null;
    }
}
