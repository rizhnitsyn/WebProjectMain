package DAO.daoImplementation;

import DAO.TournamentDao;
import entities.Match;
import entities.Tournament;
import connection.ConnectionManager;
import entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TournamentDaoImpl implements TournamentDao {
    private static TournamentDaoImpl INSTANCE;

    private TournamentDaoImpl() {}

    public static TournamentDaoImpl getInstance() {
        if (INSTANCE == null) {
            synchronized (TournamentDaoImpl.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TournamentDaoImpl();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public Tournament addTournament(Tournament tournament) {
        try (Connection connection = ConnectionManager.getConnection()){
            String sql = "INSERT INTO tournaments (tournament_name, team_organizer_id, tournament_start_date, tournament_state_id) " +
                    "VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, tournament.getName());
            statement.setLong(2, tournament.getOrganizerId());
            statement.setDate(3, tournament.getStartDate());
            statement.setLong(4,tournament.getStateId());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                tournament.setId(generatedKeys.getLong(1));
            }
            statement.close();
            return tournament;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Tournament getTournamentById(Long tournamentId) {
        try (Connection connection = ConnectionManager.getConnection()){
            String sql = "SELECT * FROM tournaments a " +
                    "LEFT JOIN matches b on a.tournament_id = b.tournament_id " +
                    "LEFT JOIN registration_desc c ON a.tournament_id = c.tournament_id " +
                    "LEFT JOIN users d ON c.user_id = d.user_id " +
                    "WHERE a.tournament_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, tournamentId);
            ResultSet resultSet = statement.executeQuery();
            Tournament tournament = null;
            User user = null;
            if (resultSet.next()) {
                tournament = new Tournament(resultSet.getLong("a.tournament_id"), resultSet.getString("a.tournament_name"),
                        resultSet.getInt("a.team_organizer_id"), resultSet.getDate("a.tournament_start_date"),
                        resultSet.getInt("a.tournament_state_id"));
                tournament.addFootballMatch(new Match(resultSet.getLong("b.match_id"), resultSet.getInt("b.first_team_result"),
                        resultSet.getInt("b.second_team_result"), resultSet.getDate("b.match_datetime"),
                        resultSet.getInt("b.match_state_id"), resultSet.getInt("b.match_type_id"),
                        resultSet.getInt("b.first_team_id"), resultSet.getInt("b.second_team_id"),
                        tournament));
                user = new User(resultSet.getLong("d.user_id"), resultSet.getString("d.first_name"),
                        resultSet.getString("d.second_name"), resultSet.getString("d.email"));
                user.addTournament(tournament);
                tournament.addUser(user);
                while (resultSet.next()) {
                    tournament.addFootballMatch(new Match(resultSet.getLong("b.match_id"),
                            resultSet.getInt("b.first_team_result"), resultSet.getInt("b.second_team_result"),
                            resultSet.getDate("b.match_datetime"), resultSet.getInt("b.match_state_id"),
                            resultSet.getInt("b.match_type_id"), resultSet.getInt("b.first_team_id"),
                            resultSet.getInt("b.second_team_id"), tournament));
                    user = new User(resultSet.getLong("d.user_id"), resultSet.getString("d.first_name"),
                            resultSet.getString("d.second_name"), resultSet.getString("d.email"));
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

    @Override
    public Tournament updateTournament(Tournament tournament) {
        try (Connection connection = ConnectionManager.getConnection()){
            String sql = "UPDATE tournaments SET tournament_name =?, team_organizer_id =? , tournament_start_date =?, tournament_state_id =? WHERE tournament_id =?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, tournament.getName());
            statement.setLong(2, tournament.getOrganizerId());
            statement.setDate(3, tournament.getStartDate());
            statement.setLong(4,tournament.getStateId());
            statement.setLong(5,tournament.getId());
            statement.executeUpdate();
            statement.close();
            return tournament;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Tournament> getListOfTournaments() {
        List<Tournament> tournaments = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection()){
            String sql = "SELECT * FROM tournaments";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tournaments.add(new Tournament(resultSet.getLong("tournament_id"),
                        resultSet.getString("tournament_name"),
                        resultSet.getInt("team_organizer_id"),
                        resultSet.getDate("tournament_start_date"),
                        resultSet.getInt("tournament_state_id")));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return tournaments;
    }

    public boolean registerOnTournament(Tournament tournament, User user) {
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
            return false;
        }
        return true;
    }
}
