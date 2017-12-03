package services;

import DAO.TeamDao;
import DAO.TournamentDao;
import DAO.UserDao;
import DTO.TournamentCreateUpdateDto;
import DTO.TournamentViewDto;
import entities.Team;
import entities.Tournament;
import entities.User;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static utils.StaticContent.*;

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
        try {
            Team team = TeamDao.getInstance().getTeamById(addDto.getOrganizerId());
            Tournament addTournament = new Tournament(
                    addDto.getName(), team, LocalDate.parse(addDto.getStartDate(), dateFormatter), 1);
            Tournament savedTournament = TournamentDao.getInstance().addTournament(addTournament);
            return new TournamentViewDto(false, savedTournament.getId(), savedTournament.getName(), savedTournament.getOrganizer().getTeamName(),
                    savedTournament.getStartDate(), getTournamentStateName(savedTournament.getStateId()));
        } catch (Exception e) {
            return new TournamentViewDto(true,"Есть ошибки при сохранении турнира: " + e.toString());
        }
    }

    public TournamentViewDto getTournamentById(Long id, Long userId) {
        Tournament foundTournament = TournamentDao.getInstance().getTournamentById(id);
        if (foundTournament == null) {
            return null;
        }
        String tournamentState = TournamentDao.getInstance().getTournamentStateName(foundTournament.getStateId());
        User currentUser = foundTournament.getUsers().stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst().orElse(null);
        return new TournamentViewDto(foundTournament.getId(), foundTournament.getName(), foundTournament.getOrganizer().getTeamName(),
                foundTournament.getStartDate(), tournamentState, currentUser == null ? null : currentUser.getId());
    }

    public TournamentViewDto closeTournament(Long id) {
        Tournament foundTournament = TournamentDao.getInstance().getTournamentById(id);
        foundTournament.setStateId(2);
        Tournament updatedTournament = TournamentDao.getInstance().updateTournament(foundTournament);

        return new TournamentViewDto(updatedTournament.getId(), updatedTournament.getName(), updatedTournament.getOrganizer().getTeamName(),
                updatedTournament.getStartDate(), getTournamentStateName(updatedTournament.getStateId()));
    }

    public TournamentViewDto registerUserOnTournament(Long tournamentId, Long userId) {
        Tournament foundTournament = TournamentDao.getInstance().getTournamentById(tournamentId);
        User foundedUser = UserDao.getInstance().getUserById(userId);

        Tournament updatedTournament = TournamentDao.getInstance().registerOnTournament(foundTournament, foundedUser);
        String tournamentState = TournamentDao.getInstance().getTournamentStateName(updatedTournament.getStateId());

        return new TournamentViewDto(updatedTournament.getId(), updatedTournament.getName(), updatedTournament.getOrganizer().getTeamName(),
                updatedTournament.getStartDate(), tournamentState);
    }

    public List<TournamentViewDto> getAlLActiveTournaments() {
        return TournamentDao.getInstance().getTournamentsFilterByState(1).stream()
                .map(tr -> new TournamentViewDto(tr.getId(), tr.getName(), tr.getOrganizer().getTeamName(), tr.getStartDate(), getTournamentStateName(tr.getStateId())))
                .collect(Collectors.toList());
    }

    public List<TournamentViewDto> getTournamentsForForecasts(Long userId) {
        return TournamentDao.getInstance().getTournamentsFilterByUser(userId, 1L).stream()
                .map(tr -> new TournamentViewDto(tr.getId(), tr.getName(), tr.getOrganizer().getTeamName(), tr.getStartDate(), getTournamentStateName(tr.getStateId())))
                .collect(Collectors.toList());
    }

    public List<TournamentViewDto> getAllUserTournaments(Long userId) {
        return TournamentDao.getInstance().getTournamentsFilterByUser(userId, null).stream()
                .map(tr -> new TournamentViewDto(tr.getId(), tr.getName(), tr.getOrganizer().getTeamName(), tr.getStartDate(), getTournamentStateName(tr.getStateId())))
                .collect(Collectors.toList());
    }

    private String getTournamentStateName(int id) {
        return TournamentDao.getInstance().getTournamentStateName(id);
    }

    public int getTournamentState(Long tournamentId) {
        return TournamentDao.getInstance().getTournamentState(tournamentId);
    }
}
