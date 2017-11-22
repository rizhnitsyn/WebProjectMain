package services;

import DAO.daoImplementation.MatchDaoImpl;
import DAO.daoImplementation.TeamDaoImpl;
import DTO.MatchCreateDto;
import DTO.MatchViewDto;
import entities.Forecast;
import entities.Match;
import entities.Team;

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
        Team firstTeam = TeamDaoImpl.getInstance().getTeamById(dto.getFirstTeam());
        Team secondTeam = TeamDaoImpl.getInstance().getTeamById(dto.getSecondTeam());
        Match match = new Match(dto);

        Match savedMatch = MatchDaoImpl.getInstance().addMatch(match);
        return new MatchViewDto(savedMatch);
    }

    public MatchViewDto getMatchById(Long id) {
        Match foundMatch = MatchDaoImpl.getInstance().getMatchById(id);
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
        Match foundMatch = MatchDaoImpl.getInstance().getMatchById(id);
        foundMatch.setFirstTeamResult(firstTeamResult);
        foundMatch.setSecondTeamResult(secondTeamResult);
        Match updatedMatch = MatchDaoImpl.getInstance().updateMatch(foundMatch);
        return new MatchViewDto(updatedMatch);
    }

    private String getState(int id) {
        return MatchDaoImpl.getInstance().getMatchState(id);
    }

    private String getType(int id) {
        return MatchDaoImpl.getInstance().getMatchType(id);

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

        if (foundMatch.getFirstTeamResult() == userForecast.getFirstTeamForecast() &&
                foundMatch.getSecondTeamResult() == userForecast.getSecondTeamForecast()) {
            return 6;
        } else if (userForecast.getFirstTeamForecast() - userForecast.getSecondTeamForecast() ==
                foundMatch.getFirstTeamResult() - foundMatch.getSecondTeamResult() &&
                foundMatch.getFirstTeamResult() - foundMatch.getSecondTeamResult() != 0) {
            return 4;
        } else if(userForecast.getFirstTeamForecast() - userForecast.getSecondTeamForecast() ==
                foundMatch.getFirstTeamResult() - foundMatch.getSecondTeamResult() &&
                foundMatch.getFirstTeamResult() - foundMatch.getSecondTeamResult() == 0) {
            return 3;
        } else if (Integer.compare(userForecast.getFirstTeamForecast(), userForecast.getSecondTeamForecast()) ==
                Integer.compare(foundMatch.getFirstTeamResult(), foundMatch.getSecondTeamResult())) {
            return 1;
        } else {
            return 0;
        }
    }
}
