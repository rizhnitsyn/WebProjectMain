package DAO.daoImplementation;

import DAO.MatchDao;
import entities.Match;
import connection.ConnectionManager;
import entities.Forecast;
import entities.Team;
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
            statement.setLong(4, match.getFirstTeam().getId());
            statement.setLong(5, match.getSecondTeam().getId());
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
            statement.setLong(6, match.getFirstTeam().getId());
            statement.setLong(7, match.getSecondTeam().getId());
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
                    "LEFT JOIN teams e ON b.team_organizer_id = e.team_id " +
                    "LEFT JOIN teams f ON a.first_team_id = f.team_id " +
                    "LEFT JOIN teams m ON a.second_team_id = m.team_id " +
                    "LEFT JOIN forecasts c ON a.match_id = c.match_id " +
                    "WHERE a.match_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, matchId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Tournament tournament = new Tournament(
                        resultSet.getLong("b.tournament_id"),
                        resultSet.getString("b.tournament_name"),
                        new Team(resultSet.getLong("b.team_organizer_id"), resultSet.getString("e.team_name")),
                        resultSet.getDate("b.tournament_start_date"),
                        resultSet.getInt("b.tournament_state_id"));
                match = createMatch(resultSet);
                match.setTournament(tournament);
                Forecast forecast = createForecast(resultSet, match.getId());
                if (forecast.getId() != 0) {
                    match.addForecast(forecast);
                }
                tournament.addFootballMatch(match);
                while (resultSet.next()) {
                    forecast = createForecast(resultSet, match.getId());
                    if (forecast.getId() != 0) {
                        match.addForecast(forecast);
                    }
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
            String sql = "SELECT * FROM matches a " +
                    "LEFT JOIN teams f ON a.first_team_id = f.team_id " +
                    "LEFT JOIN teams m ON a.second_team_id = m.team_id ";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                matches.add(createMatch(resultSet));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return matches;
    }

    private Forecast createForecast(ResultSet resultSet, Long matchId) throws SQLException {
        return new Forecast(
                resultSet.getLong("c.forecast_id"),
                resultSet.getInt("c.first_team_forecast"),
                resultSet.getInt("c.second_team_forecast"),
                resultSet.getLong("c.user_id"),
                matchId);
    }

    private Match createMatch(ResultSet resultSet) throws SQLException {
        return new Match(
                resultSet.getLong("a.match_id"),
                resultSet.getInt("a.first_team_result"),
                resultSet.getInt("a.second_team_result"),
                resultSet.getDate("a.match_datetime"),
                resultSet.getInt("a.match_state_id"),
                resultSet.getInt("a.match_type_id"),
                new Team(resultSet.getLong("a.first_team_id"), resultSet.getString("f.team_name")),
                new Team(resultSet.getLong("a.second_team_id"), resultSet.getString("m.team_name")));
    }

    public String getMatchState(int stateId) {
        String matchState = null;
        try (Connection connection = ConnectionManager.getConnection()){
            String sql = "SELECT * FROM match_states WHERE match_state_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, stateId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                matchState = resultSet.getString("match_state");
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return matchState;
    }

    public String getMatchType(int stateId) {
        String matchType = null;
        try (Connection connection = ConnectionManager.getConnection()){
            String sql = "SELECT * FROM match_types WHERE match_type_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, stateId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                matchType = resultSet.getString("match_type");
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return matchType;
    }
}
