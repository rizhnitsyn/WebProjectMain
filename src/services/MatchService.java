package services;

import DAO.daoImplementation.MatchDaoImpl;
import DTO.MatchDto;
import entities.Forecast;
import entities.Match;

public class MatchService {
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

    public MatchDto addMatch(Match match) {
        Match savedMatch = MatchDaoImpl.getInstance().addMatch(match);
        return new MatchDto(savedMatch);
    }

    public MatchDto getMatchById(Long id) {
        //в слое ДТО позже будем доставать из справочников текстовые значения и выдавать позьзователю
        Match foundMatch = MatchDaoImpl.getInstance().getMatchById(id);
        if (foundMatch == null) {
            return null;
        }
        MatchDto matchDto = new MatchDto(foundMatch);

        matchDto.setForecastsCount(foundMatch.getForecasts().size());
        matchDto.setFirstTeamWinCount(firstTeamWinCount(foundMatch));
        matchDto.setSecondTeamWinCount(secondTeamWinCount(foundMatch));
        matchDto.setDrawCount(drawCount(foundMatch));
        matchDto.setGuessedResultsCount(guessedResultsCount(foundMatch));
        matchDto.setGuessedWinnersCount(guessedWinnersCount(foundMatch));
        matchDto.setGuessedDiffInResultsCount(guessedDiffInResultsCount(foundMatch));
        matchDto.setCurrentUserPoints(calculateUserPoints(foundMatch));

        foundMatch.getForecasts().stream()
                .filter(forecast -> forecast.getUserId() == 1)
                .findFirst()
                .ifPresent(matchDto::setCurrentUserForecast);

        return matchDto;
    }

    public MatchDto updateMatch(Long id, int firstTeamResult, int secondTeamResult) {
        Match foundMatch = MatchDaoImpl.getInstance().getMatchById(id);
        foundMatch.setFirstTeamResult(firstTeamResult);
        foundMatch.setSecondTeamResult(secondTeamResult);
        Match updatedMatch = MatchDaoImpl.getInstance().updateMatch(foundMatch);
        return new MatchDto(updatedMatch);
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
