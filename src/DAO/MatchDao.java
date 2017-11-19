package DAO;

import entities.Match;

import java.util.List;

public interface MatchDao {
    Match addMatch(Match match);
    Match updateMatch(Match match);
    Match getMatchById(Long matchId);
    List<Match> getListOfMatches();
}
