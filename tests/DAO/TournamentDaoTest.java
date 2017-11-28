package DAO;

import entities.Team;
import entities.Tournament;
import entities.User;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class TournamentDaoTest {
    @Test
    public void testAddTournament() throws ParseException, SQLException {
        TournamentDao tournamentDao = TournamentDao.getInstance();
        Random random = new Random();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", Locale.UK);
        Date parsedDate = formatter.parse("10.06.2018");
        java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());
        Tournament savedTournament = tournamentDao.addTournament(new Tournament(String.valueOf(random.nextInt(1000000)),
                 new Team(2L), sqlDate, 2));
        Assert.assertNotNull(savedTournament);
        Assert.assertTrue(savedTournament.getId() != 0);
    }

    @Test
    public void testGetTournamentById() {
        TournamentDao tournamentDao = TournamentDao.getInstance();
        Tournament tournament = tournamentDao.getTournamentById(1L);
        Assert.assertNotNull(tournament);
        Assert.assertEquals("Чемпионат Европы 2016", tournament.getName());
//        tournament.getMatches().forEach(match -> System.out.println(match.getId()));
//        tournament.getUsers().forEach(user -> System.out.println(user.getSecondName()));
    }

    @Test
    public void testUpdateTournament()  {
        TournamentDao tournamentDao = TournamentDao.getInstance();
        Random random = new Random();
        Tournament tournament = tournamentDao.getTournamentById(2L);
        String name = String.valueOf(random.nextInt(1000000));
        tournament.setName("updated" + name);
        Tournament updatedTournament = tournamentDao.updateTournament(tournament);
        Tournament newTournament = tournamentDao.getTournamentById(2L);
        Assert.assertNotNull(updatedTournament);
        Assert.assertEquals("updated" + name, newTournament.getName());
    }

    @Test
    public void getListOfTournaments() {
        List<Tournament> tournaments = TournamentDao.getInstance().getTournamentsFilterByState(1);
        Assert.assertNotNull(tournaments);
        Assert.assertTrue(tournaments.size() > 0);
    }

    @Test
    public void registerOnTournament() {
        TournamentDao tournamentDao = TournamentDao.getInstance();
        Tournament tournament = tournamentDao.getTournamentById(4L);
        User user = UserDao.getInstance().getUserById(11L);
        Tournament registered = tournamentDao.registerOnTournament(tournament, user);
        Assert.assertNotNull(registered);
        Assert.assertTrue(registered.getUsers().contains(user));
    }
}
