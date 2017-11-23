package DAO;

import entities.Match;

import java.util.List;

public interface MatchDao {
    Match addMatch(Match match);
    Match updateMatch(Match match);
    Match getMatchById(Long matchId);
    List<Match> getMatchesForForecast(Long tournamentId, Long userId);
    List<Match> getListOfMatches();
}
