package services;

import DAO.daoImplementation.TeamDaoImpl;
import DAO.daoImplementation.TournamentDaoImpl;
import DAO.daoImplementation.UserDaoImpl;
import DTO.TournamentCreateUpdateDto;
import DTO.TournamentViewDto;
import entities.Team;
import entities.Tournament;
import entities.User;

import java.util.List;
import java.util.stream.Collectors;

public final class TournamentService {
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

    public TournamentViewDto addTournament(TournamentCreateUpdateDto addDto) {
        Team team = TeamDaoImpl.getInstance().getTeamById(addDto.getOrganizerId());
        Tournament savedTournament = TournamentDaoImpl.getInstance()
                .addTournament(new Tournament(addDto, team));
        return new TournamentViewDto(savedTournament.getId(), savedTournament.getName(), savedTournament.getOrganizer().getTeamName(),
                savedTournament.getStartDate(), getState(savedTournament.getStateId()));
    }

    public TournamentViewDto getTournamentById(Long id) {
        Tournament foundTournament = TournamentDaoImpl.getInstance().getTournamentById(id);
        if (foundTournament == null) {
            return null;
        }
        String tournamentState = TournamentDaoImpl.getInstance().getTournamentState(foundTournament.getStateId());
        User currentUser = foundTournament.getUsers().stream()
                .filter(user -> user.getId() == 1)
                .findFirst().orElse(null);
        Long userId = currentUser == null ? null : currentUser.getId();

        return new TournamentViewDto(foundTournament.getId(), foundTournament.getName(), foundTournament.getOrganizer().getTeamName(),
                foundTournament.getStartDate(), tournamentState, userId);
    }

    public TournamentViewDto closeTournament(Long id) {
        Tournament foundTournament = TournamentDaoImpl.getInstance().getTournamentById(id);
        foundTournament.setStateId(2);
        Tournament updatedTournament = TournamentDaoImpl.getInstance().updateTournament(foundTournament);

        return new TournamentViewDto(updatedTournament.getId(), updatedTournament.getName(), updatedTournament.getOrganizer().getTeamName(),
                updatedTournament.getStartDate(), getState(updatedTournament.getStateId()));
    }

    public TournamentViewDto registerUserOnTournament(Long tournamentId, Long userId) {
        Tournament foundTournament = TournamentDaoImpl.getInstance().getTournamentById(tournamentId);
        User foundedUser = UserDaoImpl.getInstance().getUserById(userId);

        Tournament updatedTournament = TournamentDaoImpl.getInstance().registerOnTournament(foundTournament, foundedUser);
        String tournamentState = TournamentDaoImpl.getInstance().getTournamentState(updatedTournament.getStateId());

        return new TournamentViewDto(updatedTournament.getId(), updatedTournament.getName(), updatedTournament.getOrganizer().getTeamName(),
                updatedTournament.getStartDate(), tournamentState);
    }

    public List<TournamentViewDto> getAlLActiveTournaments() {
        return TournamentDaoImpl.getInstance().getTournamentsFilterByState(1).stream()
                .map(tr -> new TournamentViewDto(tr.getId(), tr.getName(), tr.getOrganizer().getTeamName(), tr.getStartDate(), getState(tr.getStateId())))
                .collect(Collectors.toList());
    }

    public List<TournamentViewDto> getTournamentsForForecasts(Long userId) {
        return TournamentDaoImpl.getInstance().getTournamentsFilterByUser(userId).stream()
                .map(tr -> new TournamentViewDto(tr.getId(), tr.getName(), tr.getOrganizer().getTeamName(), tr.getStartDate(), getState(tr.getStateId())))
                .collect(Collectors.toList());
    }

    private String getState(int id) {
        return TournamentDaoImpl.getInstance().getTournamentState(id);
    }
}
