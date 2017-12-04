package services;

import DAO.MatchDao;
import DAO.TeamDao;
import DTO.MatchCreateDto;
import DTO.MatchShortViewDto;
import DTO.MatchViewDto;
import entities.Forecast;
import entities.Match;
import entities.Team;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static utils.StaticContent.*;

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

    public MatchShortViewDto addMatch(MatchCreateDto dto) {
        try {
            Team firstTeam = TeamDao.getInstance().getTeamById(dto.getFirstTeamId());
            Team secondTeam = TeamDao.getInstance().getTeamById(dto.getSecondTeamId());
            Match match = new Match(LocalDateTime.parse(dto.getMatchDateTime(), dateTimeFormatterInput ), 1, dto.getMatchType(), firstTeam, secondTeam);
            Match savedMatch = MatchDao.getInstance().addMatch(match, dto.getTournamentId());
            return new MatchShortViewDto(false, savedMatch.getId());
        } catch (Exception e) {
            return new MatchShortViewDto(true,"Есть ошибки при сохранении матча: " + e.toString());
        }
    }

    public Map<Integer, String> getMatchTypes() {
        return MatchDao.getInstance().getListOfMatchTypes();
    }

    public MatchViewDto getMatchById(Long id, Long userId) {
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
        matchViewDto.setCurrentUserPoints(calculateUserPoints(foundMatch, userId));

        foundMatch.getForecasts().stream()
                .filter(forecast -> forecast.getUserId().equals(userId))
                .findFirst()
                .ifPresent(matchViewDto::setCurrentUserForecast);
        return matchViewDto;
    }

    public MatchViewDto updateMatch(Long matchId, int firstTeamResult, int secondTeamResult) {
        Match foundMatch = MatchDao.getInstance().getMatchById(matchId);
        foundMatch.setFirstTeamResult(firstTeamResult);
        foundMatch.setSecondTeamResult(secondTeamResult);
        Match updatedMatch = MatchDao.getInstance().updateMatch(foundMatch);
        return new MatchViewDto(updatedMatch);
    }

    public int matchesForForecastCount(Long tournamentId, Long userId) {
        return MatchDao.getInstance().getMatchesForForecastCount(tournamentId, userId);
    }

    public List<MatchShortViewDto> matchesForForecast(Long tournamentId, Long userId) {
        List<Match> matches = MatchDao.getInstance().getMatchesForForecast(tournamentId, userId);
        if (matches == null) {
            return null;
        }
        return matches.stream()
                .map(match -> new MatchShortViewDto(match.getId(), match.getMatchDateTime(), match.getFirstTeam().getTeamName(),
                        match.getSecondTeam().getTeamName(),tournamentId))
                .sorted(Comparator.comparing(MatchShortViewDto::getMatchDateTime).reversed())
                .collect(Collectors.toList());
    }

    public List<MatchShortViewDto> getAllMatchesOfSelectedTournament(Long tournamentId) {
        List<Match> matches = MatchDao.getInstance().getMatchesOfSelectedTournament(tournamentId);
        if (matches == null) {
            return null;
        }
        return matches.stream()
                .map(match -> new MatchShortViewDto(match.getId(), match.getMatchDateTime(), match.getFirstTeam().getTeamName(),
                        match.getSecondTeam().getTeamName(), tournamentId, match.getFirstTeamResult(), match.getSecondTeamResult()))
                .sorted(Comparator.comparing(MatchShortViewDto::getMatchDateTime).reversed())
                .collect(Collectors.toList());
    }

    private String getState(int id) {
        return MatchDao.getInstance().getMatchState(id);
    }

    private String getType(int id) {
        return MatchDao.getInstance().getMatchType(id);
    }

    private int guessedDiffInResultsCount(Match foundMatch) {
        if (foundMatch.getFirstTeamResult() == null || foundMatch.getSecondTeamResult() == null) {
            return 0;
        }
        return (int) foundMatch.getForecasts().stream()
                .filter(forecast -> (((forecast.getFirstTeamForecast() - forecast.getSecondTeamForecast()) ==
                                     (foundMatch.getFirstTeamResult() - foundMatch.getSecondTeamResult())) &&
                                     (foundMatch.getFirstTeamResult() - foundMatch.getSecondTeamResult()) != 0) &&
                                     (forecast.getFirstTeamForecast() != foundMatch.getFirstTeamResult() ||
                                      forecast.getSecondTeamForecast() != foundMatch.getSecondTeamResult()))
                .count();
    }

    private int guessedWinnersCount(Match foundMatch) {
        if (foundMatch.getFirstTeamResult() == null || foundMatch.getSecondTeamResult() == null) {
            return 0;
        }
        return  (int)foundMatch.getForecasts().stream()
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
        if (foundMatch.getFirstTeamResult() == null || foundMatch.getSecondTeamResult() == null) {
            return 0;
        }
        return  (int)foundMatch.getForecasts().stream()
                .filter(forecast -> forecast.getFirstTeamForecast() == foundMatch.getFirstTeamResult() &&
                        forecast.getSecondTeamForecast() == foundMatch.getSecondTeamResult())
                .count();
    }

    private int calculateUserPoints(Match foundMatch, Long userId) {
        if (foundMatch.getFirstTeamResult() == null || foundMatch.getSecondTeamResult() == null) {
            return 0;
        }
        Forecast userForecast = foundMatch.getForecasts().stream()
                .filter(forecast -> forecast.getUserId().equals(userId))
                .findFirst().orElse(null);
        if (userForecast == null) {
            return 0;
        }
        return UserService.getInstance().calculateUserPointsPerMatch(foundMatch, userForecast);
    }


}
