package DAO;

import DAO.daoImplementation.MatchDaoImpl;
import entities.Match;
import entities.Tournament;
import org.junit.Assert;
import org.junit.Test;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class MatchDaoTest {

    @Test
    public void testAddFootballMatch() {
        MatchDaoImpl footballMatchDaoImpl = MatchDaoImpl.getInstance();
        java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());
        Tournament tournament = new Tournament();
        tournament.setId(2L);
        Match match = footballMatchDaoImpl.addMatch(new Match(sqlDate, 1, 1, 1, 2, tournament));
        Assert.assertNotNull(match);
        Assert.assertTrue(match.getId() != 0);
    }

    @Test
    public void testGetFootballMatchById() {
        MatchDaoImpl footballMatchDaoImpl = MatchDaoImpl.getInstance();
        Match match = footballMatchDaoImpl.getMatchById(1L);
        Assert.assertNotNull(match);
        Assert.assertEquals(2, match.getSecondTeamId());
//        System.out.println(match);
        System.out.println(match.getTournament().getName());
        match.getForecasts().forEach(forecast -> System.out.println(forecast.getId()));
    }

    @Test
    public void testUpdateFootballMatch()  {
        MatchDaoImpl footballMatchDaoImpl = MatchDaoImpl.getInstance();
        Random random = new Random();
        Match match = footballMatchDaoImpl.getMatchById(37L);
        int matchResult = random.nextInt(1000000);
        match.setFirstTeamResult(matchResult);
        Match updatedMatch = footballMatchDaoImpl.updateMatch(match);
        Match newMatch = footballMatchDaoImpl.getMatchById(37L);
        Assert.assertNotNull(updatedMatch);
        Assert.assertEquals(matchResult, (int) newMatch.getFirstTeamResult());
    }

    @Test
    public void getListOfFootballMatches() {
        List<Match> matches = MatchDaoImpl.getInstance().getListOfMatches();
        Assert.assertNotNull(matches);
        Assert.assertTrue(matches.size() > 0);
    }
}
