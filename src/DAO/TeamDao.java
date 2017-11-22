package DAO;

import entities.Team;

import java.util.List;

public interface TeamDao {
    Team getTeamById(Long teamId);
    List<Team> getListOfTeams();
}
