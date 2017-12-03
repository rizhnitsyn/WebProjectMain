package DAO;

import entities.Match;
import entities.Team;
import entities.Tournament;
import org.junit.Assert;
import org.junit.Test;
import utils.StaticContent;

import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static utils.StaticContent.*;
import static utils.StaticContent.dateTimeFormatter;

public class MatchDaoTest {

    @Test
    public void testAddFootballMatch() throws Exception {
        MatchDao footballMatchDao = MatchDao.getInstance();
        Tournament tournament = new Tournament();
        tournament.setId(2L);
        LocalDateTime dd = LocalDateTime.now();
        Match match2 = new Match(dd, 1, 1, new Team(1L), new Team(3L));
        Match match = footballMatchDao.addMatch(match2, 6L);
        Assert.assertNotNull(match);
        Assert.assertTrue(match.getId() != 0);
    }

    @Test
    public void testGetFootballMatchById() {
        Match match = MatchDao.getInstance().getMatchById(41L);
        System.out.println(match);
        Assert.assertNotNull(match);
        Assert.assertEquals(4L, (long)match.getSecondTeam().getId());
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
