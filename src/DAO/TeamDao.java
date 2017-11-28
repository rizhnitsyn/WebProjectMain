package DAO;

import connection.ConnectionManager;
import entities.Team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeamDao {
    private static TeamDao INSTANCE;

    private TeamDao() {}

    public static TeamDao getInstance() {
        if (INSTANCE == null) {
            synchronized (TeamDao.class) {
                if (INSTANCE ==null) {
                    INSTANCE = new TeamDao();
                }
            }
        }
        return INSTANCE;    }


    public Team getTeamById(Long teamId) {
        Team team = null;
        try (Connection connection = ConnectionManager.getConnection()){
            String sql = "SELECT * FROM teams where team_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1,teamId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                team = new Team(resultSet.getLong("team_id"),
                        resultSet.getString("team_name"));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return team;
    }

    public List<Team> getListOfTeams() {
        List<Team> teams = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection()){
            String sql = "SELECT * FROM teams";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                teams.add(new Team(resultSet.getLong("team_id"),
                        resultSet.getString("team_name")));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return teams;
    }
}
