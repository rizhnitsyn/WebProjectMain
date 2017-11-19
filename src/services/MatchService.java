package services;

import DAO.daoImplementation.MatchDaoImpl;
import DTO.MatchDto;
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
        return new MatchDto(savedMatch.getId(), savedMatch.getFirstTeamResult(), savedMatch.getSecondTeamResult(),
                savedMatch.getMatchDateTime(), savedMatch.getMatchState(), savedMatch.getMatchType(), savedMatch.getFirstTeamId(),
                savedMatch.getSecondTeamId(), savedMatch.getTournament().getId());
    }

    public MatchDto getMatchById(Long id) {
        //в слое ДТО позже будем доставать из справочников текстовые значения и выдавать позьзователю
        Match foundMatch = MatchDaoImpl.getInstance().getMatchById(id);
        if (foundMatch == null) {
            return null;
        }
        return new MatchDto(foundMatch.getId(), foundMatch.getFirstTeamResult(), foundMatch.getSecondTeamResult(),
                foundMatch.getMatchDateTime(), foundMatch.getMatchState(), foundMatch.getMatchType(), foundMatch.getFirstTeamId(),
                foundMatch.getSecondTeamId(), foundMatch.getTournament().getId());
    }

    public MatchDto updateMatch(Long id, int firstTeamResult, int secondTeamResult) {
        Match foundMatch = MatchDaoImpl.getInstance().getMatchById(id);
        foundMatch.setFirstTeamResult(firstTeamResult);
        foundMatch.setSecondTeamResult(secondTeamResult);
        Match updatedMatch = MatchDaoImpl.getInstance().updateMatch(foundMatch);
        return new MatchDto(updatedMatch.getId(), updatedMatch.getFirstTeamResult(), updatedMatch.getSecondTeamResult(),
                updatedMatch.getMatchDateTime(), updatedMatch.getMatchState(), updatedMatch.getMatchType(),
                updatedMatch.getFirstTeamId(), updatedMatch.getSecondTeamId(), updatedMatch.getTournament().getId());
    }
}
