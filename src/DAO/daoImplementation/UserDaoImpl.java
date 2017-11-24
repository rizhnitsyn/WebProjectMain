package DAO.daoImplementation;

import connection.ConnectionManager;
import DAO.UserDao;
import entities.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoImpl implements UserDao {

    private static UserDaoImpl INSTANCE;

    private UserDaoImpl() {}

    public static UserDaoImpl getInstance() {
        if (INSTANCE == null) {
            synchronized (UserDaoImpl.class) {
                if (INSTANCE == null) {
                    INSTANCE = new UserDaoImpl();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public User addUser(User user) {
        try (Connection connection = ConnectionManager.getConnection()){
            String sql = "INSERT INTO users (first_name, second_name, email, user_state_id) " +
                    "VALUES (?, ?, ?, 1)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getSecondName());
            statement.setString(3, user.getEmail());

            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getLong(1));
            }

            statement.close();
            return user;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User getUserById(Long userId) {
        User user = null;
        try (Connection connection = ConnectionManager.getConnection()){
            String sql = "SELECT * FROM users a " +
                    "LEFT JOIN registration_desc b ON a.user_id = b.user_id " +
                    "LEFT JOIN tournaments c ON b.tournament_id = c.tournament_id " +
                    "LEFT JOIN forecasts d ON a.user_id = d.user_id " +
                    "LEFT JOIN teams e ON e.team_id = c.team_organizer_id " +
                    "WHERE a.user_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            Tournament tournament;
            if (resultSet.next()) {
                user = new User(resultSet.getLong("a.user_id"),
                        resultSet.getString("a.first_name"),
                        resultSet.getString("a.second_name"),
                        resultSet.getString("a.email"),
                        resultSet.getInt("a.user_state_id"));
                tournament = new Tournament(resultSet.getLong("c.tournament_id"), resultSet.getString("c.tournament_name"),
                        new Team(resultSet.getLong("c.team_organizer_id"), resultSet.getString("e.team_name")),
                        resultSet.getDate("c.tournament_start_date"), resultSet.getInt("c.tournament_state_id"));
                tournament.addUser(user);
                user.addTournament(tournament);
                //ДОСТАТЬ MATCH_ID
                user.addForecast(new Forecast(resultSet.getLong("d.forecast_id"), resultSet.getInt("d.first_team_forecast"),
                        resultSet.getInt("d.second_team_forecast"), user.getId(), resultSet.getLong("d.match_id")));

                while (resultSet.next()) {
                    tournament = new Tournament(resultSet.getLong("c.tournament_id"), resultSet.getString("c.tournament_name"),
                            new Team(resultSet.getLong("c.team_organizer_id"), resultSet.getString("e.team_name")),
                            resultSet.getDate("c.tournament_start_date"), resultSet.getInt("c.tournament_state_id"));
                    tournament.addUser(user);
                    user.addTournament(tournament);
                    user.addForecast(new Forecast(resultSet.getLong("d.forecast_id"), resultSet.getInt("d.first_team_forecast"),
                            resultSet.getInt("d.second_team_forecast"), user.getId(), resultSet.getLong("d.match_id")));
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

    @Override
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

    @Override
    public List<User> getListOfUsers(int state) {
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection()){
            String sql = "SELECT * FROM users WHERE user_state_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, state);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(new User(resultSet.getLong("user_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("second_name"),
                        resultSet.getString("email"),
                        resultSet.getInt("user_state_id")));
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
