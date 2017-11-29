package services;

import DAO.MatchDao;
import DAO.TeamDao;
import DAO.TournamentDao;
import DTO.MatchCreateDto;
import DTO.MatchShortViewDto;
import DTO.MatchViewDto;
import entities.Forecast;
import entities.Match;
import entities.Team;
import entities.Tournament;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class MatchService {
    private static MatchService INSTANCE;

    private MatchService() {}

    public static MatchService getInstance() {
        if (INSTANCE == null) {
            synchronized (MatchService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MatchService();
                }
            }
        }
        return INSTANCE;
    }

    public MatchViewDto addMatch(MatchCreateDto dto) {
        Team firstTeam = TeamDao.getInstance().getTeamById(dto.getFirstTeam());
        Team secondTeam = TeamDao.getInstance().getTeamById(dto.getSecondTeam());
        Tournament tournament = TournamentDao.getInstance().getTournamentById(dto.getTournamentId());
        Match match = new Match(dto.getMatchDateTime(), dto.getMatchState(), dto.getMatchType(),
                firstTeam, secondTeam, tournament);

        Match savedMatch = MatchDao.getInstance().addMatch(match);
        return new MatchViewDto(savedMatch);
    }

    public Map<Integer, String> getMatchTypes() {
        return MatchDao.getInstance().getListOfMatchTypes();
    }

    public MatchViewDto getMatchById(Long id) {
        Match foundMatch = MatchDao.getInstance().getMatchById(id);
        if (foundMatch == null) {
            return null;
        }
        MatchViewDto matchViewDto = new MatchViewDto(foundMatch);

        matchViewDto.setMatchState(getState(foundMatch.getMatchState()));
        matchViewDto.setMatchType(getType(foundMatch.getMatchType()));
        matchViewDto.setForecastsCount(foundMatch.getForecasts().size());
        matchViewDto.setFirstTeamWinCount(firstTeamWinCount(foundMatch));
        matchViewDto.setSecondTeamWinCount(secondTeamWinCount(foundMatch));
        matchViewDto.setDrawCount(drawCount(foundMatch));
        matchViewDto.setGuessedResultsCount(guessedResultsCount(foundMatch));
        matchViewDto.setGuessedWinnersCount(guessedWinnersCount(foundMatch));
        matchViewDto.setGuessedDiffInResultsCount(guessedDiffInResultsCount(foundMatch));
        matchViewDto.setCurrentUserPoints(calculateUserPoints(foundMatch));

        foundMatch.getForecasts().stream()
                .filter(forecast -> forecast.getUserId() == 1)
                .findFirst()
                .ifPresent(matchViewDto::setCurrentUserForecast);
        return matchViewDto;
    }

    public MatchViewDto updateMatch(Long id, int firstTeamResult, int secondTeamResult) {
        Match foundMatch = MatchDao.getInstance().getMatchById(id);
        foundMatch.setFirstTeamResult(firstTeamResult);
        foundMatch.setSecondTeamResult(secondTeamResult);
        Match updatedMatch = MatchDao.getInstance().updateMatch(foundMatch);
        return new MatchViewDto(updatedMatch);
    }

    public List<MatchShortViewDto> matchesForForecast(Long tournamentId, Long userId) {
        List<Match> matches = MatchDao.getInstance().getMatchesForForecast(tournamentId,userId);
        if (matches == null) {
            return null;
        }
        return matches.stream()
                .map(match -> new MatchShortViewDto(match.getId(), match.getMatchDateTime(), match.getFirstTeam().getTeamName(), match.getSecondTeam().getTeamName(),tournamentId))
                .collect(Collectors.toList());
    }

    public List<MatchShortViewDto> getAllMatchesOfSelectedTournament(Long tournamentId) {
        List<Match> matches = MatchDao.getInstance().getMatchesOfSelectedTournament(tournamentId);
        if (matches == null) {
            return null;
        }
        return matches.stream()
                .map(match -> new MatchShortViewDto(match.getId(), match.getMatchDateTime(), match.getFirstTeam().getTeamName(), match.getSecondTeam().getTeamName(), tournamentId))
                .collect(Collectors.toList());
    }

    private String getState(int id) {
        return MatchDao.getInstance().getMatchState(id);
    }

    private String getType(int id) {
        return MatchDao.getInstance().getMatchType(id);
    }

    private int guessedDiffInResultsCount(Match foundMatch) {
        return (int) foundMatch.getForecasts().stream()
                .filter(forecast -> (((forecast.getFirstTeamForecast() - forecast.getSecondTeamForecast()) ==
                                     (foundMatch.getFirstTeamResult() - foundMatch.getSecondTeamResult())) &&
                                     (foundMatch.getFirstTeamResult() - foundMatch.getSecondTeamResult()) != 0) &&
                                     (forecast.getFirstTeamForecast() != foundMatch.getFirstTeamResult() ||
                                      forecast.getSecondTeamForecast() != foundMatch.getSecondTeamResult()))
                .count();
    }

    private int guessedWinnersCount(Match foundMatch) {
        return (int) foundMatch.getForecasts().stream()
                .filter(forecast -> (Integer.compare(forecast.getFirstTeamForecast(), forecast.getSecondTeamForecast()) ==
                                    Integer.compare(foundMatch.getFirstTeamResult(), foundMatch.getSecondTeamResult())) &&
                                   (forecast.getFirstTeamForecast() != foundMatch.getFirstTeamResult() ||
                                    forecast.getSecondTeamForecast() != foundMatch.getSecondTeamResult()))
                .count();
    }

    private int firstTeamWinCount(Match foundMatch) {
        return (int) foundMatch.getForecasts().stream()
                .filter(forecast -> (forecast.getFirstTeamForecast() > forecast.getSecondTeamForecast()))
                .count();
    }

    private int secondTeamWinCount(Match foundMatch) {
        return (int) foundMatch.getForecasts().stream()
                .filter(forecast -> (forecast.getFirstTeamForecast() < forecast.getSecondTeamForecast()))
                .count();
    }

    private int drawCount(Match foundMatch) {
        return (int) foundMatch.getForecasts().stream()
                .filter(forecast -> (forecast.getFirstTeamForecast() == forecast.getSecondTeamForecast()))
                .count();
    }

    private int guessedResultsCount(Match foundMatch) {
        return (int) foundMatch.getForecasts().stream()
                .filter(forecast -> forecast.getFirstTeamForecast() == foundMatch.getFirstTeamResult() &&
                        forecast.getSecondTeamForecast() == foundMatch.getSecondTeamResult())
                .count();
    }

    private Integer calculateUserPoints(Match foundMatch) {
        Forecast userForecast = foundMatch.getForecasts().stream()
                .filter(forecast -> forecast.getUserId() == 1)
                .findFirst().orElse(null);
        if (userForecast == null) {
            return null;
        }
        return UserService.getInstance().calculateUserPointsPerMatch(foundMatch, userForecast);
    }


}
