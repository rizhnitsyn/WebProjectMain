package DAO;

import entities.Match;
import entities.Team;
import entities.Tournament;
import connection.ConnectionManager;
import entities.User;
import servlets.TournamentResultTableServlet;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static utils.StaticContent.dateFormatter;
import static utils.StaticContent.dateTimeFormatter;

public class TournamentDao {
    private static TournamentDao INSTANCE;

    private TournamentDao() {}

    public static TournamentDao getInstance() {
        if (INSTANCE == null) {
            synchronized (TournamentDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TournamentDao();
                }
            }
        }
        return INSTANCE;
    }

    public Tournament addTournament(Tournament tournament) throws Exception {
        try (Connection connection = ConnectionManager.getConnection()){
            String sql = "INSERT INTO tournaments (tournament_name, team_organizer_id, tournament_start_date, tournament_state_id) " +
                    "VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, tournament.getName());
            statement.setLong(2, tournament.getOrganizer().getId());
            statement.setDate(3, Date.valueOf(tournament.getStartDate().toString()));
            statement.setLong(4,tournament.getStateId());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                tournament.setId(generatedKeys.getLong(1));
            }
            statement.close();
            return tournament;
        } catch (Exception e) {
            throw e;
        }
    }

    public Tournament getTournamentById(Long tournamentId) {
        try (Connection connection = ConnectionManager.getConnection()){
            String sql = "SELECT * FROM tournaments a " +
                    "LEFT JOIN matches b on a.tournament_id = b.tournament_id " +
                    "LEFT JOIN teams e on a.team_organizer_id = e.team_id " +
                    "LEFT JOIN registration_desc c ON a.tournament_id = c.tournament_id " +
                    "LEFT JOIN users d ON c.user_id = d.user_id " +
                    "WHERE a.tournament_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, tournamentId);
            ResultSet resultSet = statement.executeQuery();
            Tournament tournament = null;
            User user;
            if (resultSet.next()) {
                tournament = new Tournament(
                        resultSet.getLong("a.tournament_id"),
                        resultSet.getString("a.tournament_name"),
                        new Team(resultSet.getLong("a.team_organizer_id"), resultSet.getString("e.team_name")),
                        LocalDate.parse(resultSet.getString("a.tournament_start_date"), dateFormatter),
                        resultSet.getInt("a.tournament_state_id"));
                tournament.addFootballMatch(createMatch(resultSet));
                user = createUser(resultSet);
                user.addTournament(tournament);
                tournament.addUser(user);
                while (resultSet.next()) {
                    tournament.addFootballMatch(createMatch(resultSet));
                    user = createUser(resultSet);
                    user.addTournament(tournament);
                    tournament.addUser(user);
                }
            }
            resultSet.close();
            statement.close();
            return tournament;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Match createMatch(ResultSet resultSet) throws SQLException {
        String stringDate = resultSet.getString("b.match_datetime");
        if (stringDate == null) {
            return null;
        }
        return new Match(
                resultSet.getLong("b.match_id"),
                LocalDateTime.parse(stringDate, dateTimeFormatter),
                resultSet.getInt("b.match_state_id"),
                resultSet.getInt("b.match_type_id"));
    }

    private User createUser(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getLong("d.user_id"),
                resultSet.getString("d.first_name"),
                resultSet.getString("d.second_name"),
                resultSet.getString("d.email"),
                resultSet.getInt("d.user_state_id"));
    }
    private User createUserById(ResultSet resultSet) throws SQLException {
        Long userId = resultSet.getLong("c.user_id");
        if (userId == 0) {
            return new User(0L);
        }
        return new User(userId);
    }

    public Tournament updateTournament(Tournament tournament) {
        try (Connection connection = ConnectionManager.getConnection()){
            String sql = "UPDATE tournaments SET tournament_name =?, tournament_start_date =?, tournament_state_id =? WHERE tournament_id =?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, tournament.getName());
            statement.setDate(2, Date.valueOf(tournament.getStartDate().toString()));
            statement.setLong(3,tournament.getStateId());
            statement.setLong(4,tournament.getId());
            statement.executeUpdate();
            statement.close();
            return tournament;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getTournamentStateName(int stateId) {
        String tournamentState = null;
        try (Connection connection = ConnectionManager.getConnection()){
            String sql = "SELECT * FROM tournament_states where tournament_state_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, stateId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                tournamentState = resultSet.getString("tournament_state");
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return tournamentState;
    }

    public int getTournamentState(Long tournamentId) {
        int tournamentState = 0;
        try (Connection connection = ConnectionManager.getConnection()){
            String sql = "SELECT * FROM tournaments where tournament_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, tournamentId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tournamentState = resultSet.getInt("tournament_state_id");
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
        return tournamentState;
    }

    public List<Tournament> getTournamentsFilterByState(int tournamentState, Long userId) {
        List<Tournament> tournaments = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection()){
            String sql = "SELECT * FROM tournaments a " +
                    "LEFT JOIN teams b on a.team_organizer_id = b.team_id " +
                    "LEFT JOIN (SELECT * FROM registration_desc WHERE user_id = ? ) c ON a.tournament_id = c.tournament_id " +
                    "WHERE tournament_state_id = ? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, userId);
            statement.setInt(2, tournamentState);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Tournament tournament = createTournament(resultSet);
                tournament.addUser(createUserById(resultSet));
                tournaments.add(tournament);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return tournaments;
    }

    public List<Tournament> getTournamentsFilterByUser(Long userId, Long tournamentState) {
        List<Tournament> tournaments = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection()){
            String condition = getStateCondition(tournamentState);
            String sql = "SELECT * FROM tournaments a " +
                    "LEFT JOIN teams b on a.team_organizer_id = b.team_id " +
                    "JOIN registration_desc c on a.tournament_id = c.tournament_id " +
                    "WHERE c.user_id = ? " +
                    condition;
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, userId);
            if (!condition.equals("")) {
                statement.setLong(2, tournamentState);
            }
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tournaments.add(createTournament(resultSet));            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return tournaments;
    }

    private String getStateCondition(Long tournamentState) {
        String stateCondition;
        if (tournamentState != null) {
            stateCondition = "AND a.tournament_state_id = ?";
        } else {
            stateCondition = "";
        }
        return stateCondition;
    }


    public Tournament registerOnTournament(Tournament tournament, User user) {
        try (Connection connection = ConnectionManager.getConnection()){
            String sql = "INSERT INTO registration_desc (tournament_id, user_id) " +
                    "VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, tournament.getId());
            statement.setLong(2, user.getId());
            tournament.addUser(user);
            user.addTournament(tournament);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return tournament;
    }

    private Tournament createTournament(ResultSet resultSet) throws SQLException {
        return new Tournament(resultSet.getLong("a.tournament_id"),
                resultSet.getString("a.tournament_name"),
                new Team(resultSet.getLong("a.team_organizer_id"), resultSet.getString("b.team_name")),
                resultSet.getDate("a.tournament_start_date").toLocalDate(),
                resultSet.getInt("a.tournament_state_id"));
    }
}
