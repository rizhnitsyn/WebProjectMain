package DAO.daoImplementation;

import DAO.MatchDao;
import entities.Match;
import connection.ConnectionManager;
import entities.Forecast;
import entities.Tournament;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatchDaoImpl implements MatchDao {
    private static MatchDaoImpl INSTANCE;

    private MatchDaoImpl() {}

    public static MatchDaoImpl getInstance() {
        if (INSTANCE == null) {
            synchronized (MatchDaoImpl.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MatchDaoImpl();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public Match addMatch(Match match) {
        try (Connection connection = ConnectionManager.getConnection()){
            String sql = "INSERT INTO matches (match_datetime, match_state_id, match_type_id, first_team_id, second_team_id, tournament_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setDate(1, match.getMatchDateTime());
            statement.setInt(2, match.getMatchState());
            statement.setInt(3, match.getMatchType());
            statement.setInt(4, match.getFirstTeamId());
            statement.setInt(5, match.getSecondTeamId());
            statement.setLong(6, match.getTournament().getId());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                match.setId(generatedKeys.getLong(1));
            }
            statement.close();
            return match;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Match updateMatch(Match match) {
        try (Connection connection = ConnectionManager.getConnection()){
            String sql = "UPDATE matches SET first_team_result = ?, second_team_result = ?, match_datetime = ?, match_state_id = ? , match_type_id = ?," +
                    " first_team_id = ?, second_team_id= ?  WHERE tournament_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, match.getFirstTeamResult());
            statement.setInt(2, match.getSecondTeamResult());
            statement.setDate(3, match.getMatchDateTime());
            statement.setInt(4, match.getMatchState());
            statement.setInt(5, match.getMatchType());
            statement.setInt(6, match.getFirstTeamId());
            statement.setInt(7, match.getSecondTeamId());
            statement.setLong(8, match.getTournament().getId());
            statement.executeUpdate();
            statement.close();
            return match;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Match getMatchById(Long matchId) {
        Match match = null;
        try (Connection connection = ConnectionManager.getConnection()){
            String sql = "SELECT * FROM matches a " +
                    "LEFT JOIN tournaments b ON a.tournament_id = b.tournament_id " +
                    "LEFT JOIN forecasts c ON a.match_id = c.match_id " +
                    "WHERE a.match_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, matchId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Tournament tournament = new Tournament(resultSet.getLong("b.tournament_id"), resultSet.getString("b.tournament_name"),
                        resultSet.getInt("b.team_organizer_id"), resultSet.getDate("b.tournament_start_date"),
                        resultSet.getInt("b.tournament_state_id"));
                match = new Match(resultSet.getLong("a.match_id"), resultSet.getInt("a.first_team_result"),
                        resultSet.getInt("a.second_team_result"), resultSet.getDate("a.match_datetime"),
                        resultSet.getInt("a.match_state_id"), resultSet.getInt("a.match_type_id"),
                        resultSet.getInt("a.first_team_id"), resultSet.getInt("a.second_team_id"), tournament);
                Forecast forecast = new Forecast(resultSet.getLong("c.forecast_id"), resultSet.getInt("c.first_team_forecast"),
                        resultSet.getInt("c.second_team_forecast"), match);
                if (forecast.getId() != 0) {
                    match.addForecast(forecast);
                }
                tournament.addFootballMatch(match);
            }
            while (resultSet.next()) {
                Forecast forecast = new Forecast(resultSet.getLong("c.forecast_id"), resultSet.getInt("c.first_team_forecast"),
                        resultSet.getInt("c.second_team_forecast"), match);
                if (match != null && forecast.getId() != 0) {
                    match.addForecast(forecast);
                }
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return match;
    }

    @Override
    public List<Match> getListOfMatches() {
        List<Match> matches = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection()){
            String sql = "SELECT * FROM matches b ";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                matches.add(new Match(resultSet.getLong("b.match_id"),
                        resultSet.getInt("b.first_team_result"), resultSet.getInt("b.second_team_result"),
                        resultSet.getDate("b.match_datetime"), resultSet.getInt("b.match_state_id"),
                        resultSet.getInt("b.match_type_id"), resultSet.getInt("b.first_team_id"),
                        resultSet.getInt("b.second_team_id")));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return matches;
    }
}
