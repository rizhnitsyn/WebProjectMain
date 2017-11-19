package DAO;

import entities.Tournament;

import java.util.List;

public interface TournamentDao {
    Tournament addTournament(Tournament tournament);
    Tournament updateTournament(Tournament tournament);
    Tournament getTournamentById(Long tournamentId);
    List<Tournament> getListOfTournaments();
}
