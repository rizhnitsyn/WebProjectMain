package services;

import DAO.TeamDao;
import entities.Team;

import java.util.List;

public final class TeamService {
    private static TeamService INSTANCE = null;

    private TeamService() {}

    public static TeamService getInstance() {
        if (INSTANCE == null) {
            synchronized (TeamService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TeamService();
                }
            }
        }
        return INSTANCE;
    }

    public List<Team> getListOfTeams() {
        return TeamDao.getInstance().getListOfTeams();
    }

    public Team getTeamById(Long teamId) {
        return TeamDao.getInstance().getTeamById(teamId);
    }

}
