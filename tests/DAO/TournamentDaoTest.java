package DAO;

import entities.Team;
import entities.Tournament;
import entities.User;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import static utils.StaticContent.dateFormatter;
import static utils.StaticContent.dateTimeFormatter;

public class TournamentDaoTest {
    @Test
    public void testAddTournament() throws Exception {
        TournamentDao tournamentDao = TournamentDao.getInstance();
        Random random = new Random();
        Tournament savedTournament = tournamentDao.addTournament(new Tournament(String.valueOf(random.nextInt(1000000)),
                 new Team(2L), LocalDate.now(), 2));
        Assert.assertNotNull(savedTournament);
        Assert.assertTrue(savedTournament.getId() != 0);
    }

    @Test
    public void testGetTournamentById() {
        TournamentDao tournamentDao = TournamentDao.getInstance();
        Tournament tournament = tournamentDao.getTournamentById(1L);
//        System.out.println(LocalDate.parse("2016-12-10", dateFormatter));
//        System.out.println(LocalDateTime.parse("2016-12-10 11:00", dateTimeFormatter));
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
        User user = UserDao.getInstance().getUserById(3L);
        Tournament registered = tournamentDao.registerOnTournament(tournament, user);
        Assert.assertNotNull(registered);
        Assert.assertTrue(registered.getUsers().contains(user));
    }
}
