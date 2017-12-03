package DAO;

import DTO.LoginDto;
import connection.ConnectionManager;
import entities.*;
import utils.StaticContent;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static utils.StaticContent.*;
import static utils.StaticContent.dateTimeFormatter;

public class UserDao {

    private static UserDao INSTANCE;

    private UserDao() {}

    public static UserDao getInstance() {
        if (INSTANCE == null) {
            synchronized (UserDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new UserDao();
                }
            }
        }
        return INSTANCE;
    }

    public User addUser(User user) throws SQLException {
        try (Connection connection = ConnectionManager.getConnection()){
            String sql = "INSERT INTO users (first_name, second_name, email, user_state_id, login, password) " +
                    "VALUES (?, ?, ?, 1, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getSecondName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getLogin());
            statement.setString(5, user.getPassword());

            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getLong(1));
            }

            statement.close();
            return user;

        } catch (SQLException ex) {
            throw ex;
        }
    }

    public User getUserById(Long userId) {
        User user = null;
        try (Connection connection = ConnectionManager.getConnection()){
            String sql = "SELECT * FROM users a " +
                    "LEFT JOIN registration_desc b ON a.user_id = b.user_id " +
                    "LEFT JOIN tournaments c ON b.tournament_id = c.tournament_id " +
                    "WHERE a.user_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            Tournament tournament;
            if (resultSet.next()) {
                user = createUser(resultSet);
                tournament = createTournament(resultSet);
//                tournament.addUser(user);
                user.addTournament(tournament);
                while (resultSet.next()) {
                    tournament = createTournament(resultSet);
//                    tournament.addUser(user);
                    user.addTournament(tournament);
                }
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return user;
    }

    public User checkUser(String login, String password) {
        User user = null;
        try (Connection connection = ConnectionManager.getConnection()){
            String sql = "SELECT * FROM users WHERE login = ? and password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = createUser(resultSet);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return user;

    }

    public User getUserWithStatistics(Long tournamentId, Long userId) {
        User user = null;
        try (Connection connection = ConnectionManager.getConnection()){
            String sql = "SELECT * FROM users a " +
                    "LEFT JOIN registration_desc b ON a.user_id = b.user_id " +
                    "LEFT JOIN tournaments c ON b.tournament_id = c.tournament_id " +
                    "LEFT JOIN matches e ON e.tournament_id = c.tournament_id  " +
                    "LEFT JOIN forecasts d ON a.user_id = d.user_id and d.match_id = e.match_id " +
                    "WHERE c.tournament_id = ? " +
                    "AND a.user_id = ? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, tournamentId);
            statement.setLong(2, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = createUser(resultSet);
                user.addTournament(createTournament(resultSet));
                fillingUserWithData(user, resultSet);
                while (resultSet.next()) {
                    fillingUserWithData(user, resultSet);
                }
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return user;
    }

    public List<User> getUsersOfTournament(Long tournamentId) {
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection()){
            String sql = "SELECT * FROM users a " +
                    "LEFT JOIN registration_desc b ON a.user_id = b.user_id " +
                    "WHERE b.tournament_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, tournamentId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(new User(resultSet.getLong("user_id")));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return users;
    }

    private void fillingUserWithData(User user, ResultSet rs) throws SQLException {
        Long forecastId = rs.getLong("d.forecast_id");
        if (forecastId == 0) {
            return;
        }
        Match match = new Match(
                rs.getLong("e.match_id"),
                rs.getInt("e.first_team_result"),
                rs.getInt("e.second_team_result"));
        Forecast forecast = new Forecast(
                forecastId,
                rs.getInt("d.first_team_forecast"),
                rs.getInt("d.second_team_forecast"),
                user.getId(),
                match);
        user.addForecast(forecast);
    }

    private Tournament createTournament(ResultSet resultSet) throws SQLException {
        Long tournamentId = resultSet.getLong("c.tournament_id");
        if (tournamentId == 0) {
            return null;
        }
        return new Tournament(
                tournamentId,
                resultSet.getString("c.tournament_name"),
                resultSet.getDate("c.tournament_start_date").toLocalDate(),
                resultSet.getInt("c.tournament_state_id"));
    }

    private User createUser(ResultSet resultSet) throws SQLException {
        return new User(resultSet.getLong("user_id"),
                resultSet.getString("first_name"),
                resultSet.getString("second_name"),
                resultSet.getString("email"),
                resultSet.getInt("user_state_id"));
    }

    public User updateUser(User user) {
        try (Connection connection = ConnectionManager.getConnection()){
            String sql = "UPDATE users SET first_name = ?, second_name = ? , email = ?, user_state_id = ? WHERE user_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getSecondName());
            statement.setString(3, user.getEmail());
            statement.setInt(4, user.getUserState());
            statement.setLong(5,user.getId());
            statement.executeUpdate();
            statement.close();
            return user;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<User> getListOfUsers(int state) {
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection()){
            String sql = "SELECT * FROM users WHERE user_state_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, state);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(createUser(resultSet));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return users;
    }

    public String getUserState(int stateId) {
        String matchState = null;
        try (Connection connection = ConnectionManager.getConnection()){
            String sql = "SELECT * FROM user_states WHERE user_state_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, stateId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                matchState = resultSet.getString("user_state");
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return matchState;
    }

    public Map<Integer, String> getUserStates() {
        Map<Integer, String> userRoles = new HashMap<>();
        try (Connection connection = ConnectionManager.getConnection()){
            String sql = "SELECT * FROM user_states";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                userRoles.put(resultSet.getInt("user_state_id"), resultSet.getString("user_state"));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return userRoles;
    }
}
