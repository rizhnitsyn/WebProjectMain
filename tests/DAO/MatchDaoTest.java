package DAO;

import entities.Match;
import entities.Team;
import entities.Tournament;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class MatchDaoTest {

    @Test
    public void testAddFootballMatch() {
        MatchDao footballMatchDao = MatchDao.getInstance();
//        java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());
        Tournament tournament = new Tournament();
        tournament.setId(2L);
        Match match = footballMatchDao.addMatch(new Match(LocalDateTime.now(), 1, 1, new Team(1L),
                new Team(3L), tournament));
        Assert.assertNotNull(match);
        Assert.assertTrue(match.getId() != 0);
    }

    @Test
    public void testGetFootballMatchById() {
        MatchDao footballMatchDao = MatchDao.getInstance();
        Match match = footballMatchDao.getMatchById(1L);
        Assert.assertNotNull(match);
        Assert.assertEquals(2L, (long)match.getSecondTeam().getId());
//        System.out.println(match);
        System.out.println(match.getTournament().getName());
        match.getForecasts().forEach(forecast -> System.out.println(forecast.getId()));
    }

    @Test
    public void testUpdateFootballMatch()  {
        MatchDao footballMatchDao = MatchDao.getInstance();
        Random random = new Random();
        Match match = footballMatchDao.getMatchById(37L);
        int matchResult = random.nextInt(1000000);
        match.setFirstTeamResult(matchResult);
        Match updatedMatch = footballMatchDao.updateMatch(match);
        Match newMatch = footballMatchDao.getMatchById(37L);
        Assert.assertNotNull(updatedMatch);
        Assert.assertEquals(matchResult, (int) newMatch.getFirstTeamResult());
    }

    @Test
    public void getListOfFootballMatches() {
        List<Match> matches = MatchDao.getInstance().getMatchesOfSelectedTournament(1L);
        Assert.assertNotNull(matches);
        Assert.assertTrue(matches.size() > 0);
    }
}
