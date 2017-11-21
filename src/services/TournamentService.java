package services;

import DAO.daoImplementation.TournamentDaoImpl;
import DTO.TournamentDto;
import entities.Tournament;

public class TournamentService {
    private static TournamentService INSTANCE;

    private TournamentService() {}

    public static TournamentService getInstance() {
        if (INSTANCE == null) {
            synchronized (TournamentService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TournamentService();
                }
            }
        }
        return INSTANCE;
    }

    public TournamentDto addTournament(Tournament tournament) {
        Tournament savedTournament = TournamentDaoImpl.getInstance().addTournament(tournament);
        return new TournamentDto(savedTournament.getId(), savedTournament.getName(), savedTournament.getOrganizerId(),
                savedTournament.getStartDate(), savedTournament.getStateId());
    }

    public TournamentDto getTournamentById(Long id) {
        //в слое ДТО позже будем доставать из справочников текстовые значения и выдавать позьзователю
        Tournament foundTournament = TournamentDaoImpl.getInstance().getTournamentById(id);
        if (foundTournament == null) {
            return null;
        }
        return new TournamentDto(foundTournament);
    }

    public TournamentDto closeTournament(TournamentDto tournamentDto) {
        tournamentDto.setStateId(2);
        Tournament updatedTournament = TournamentDaoImpl.getInstance().updateTournament(new Tournament(tournamentDto));
        return new TournamentDto(updatedTournament);
    }

    public TournamentDto registerUserOnTournament(TournamentDto tournamentDto) {
        tournamentDto.setStateId(2);
        Tournament updatedTournament = TournamentDaoImpl.getInstance().updateTournament(new Tournament(tournamentDto));
        return new TournamentDto(updatedTournament);
    }
}
