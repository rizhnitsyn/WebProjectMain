package services;

import DAO.UserDao;
import DTO.*;
import entities.Forecast;
import entities.Match;
import entities.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class UserService {
    private static UserService INSTANCE;

    private UserService() {}

    public static UserService getInstance() {
        if (INSTANCE == null) {
            synchronized (UserService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new UserService();
                }
            }
        }
        return INSTANCE;
    }

    public UserViewDto addUser(UserCreateDto userDto) throws SQLException {
        User newUser = new User(userDto);
        User savedUser = UserDao.getInstance().addUser(newUser);
        String state = getState(savedUser.getUserState());
        return new UserViewDto(savedUser, state);
    }

    public String checkRegistration(UserCreateDto dto) {
        if (dto.getFirstName().isEmpty() || dto.getSecondName().isEmpty() || dto.getEmail().isEmpty() || dto.getLogin().isEmpty() || dto.getPassword().isEmpty()) {
            return "Заполните все поля";
        }
        return null;
    }

    public UserLoggedDto checkPassword(LoginDto dto) {
        User loggedUser = UserDao.getInstance().checkUser(dto.getLogin(), dto.getPassword());
        if (loggedUser != null) {
            return new UserLoggedDto("Все данные введены верно", "/homepage", true, loggedUser.getId(), loggedUser.getFirstName(),
                    loggedUser.getSecondName(), loggedUser.getUserState());
        } else {
            return new UserLoggedDto("Неверно введены логин или пароль", false);
        }
    }

    public UserViewDto getUserById(Long id) {
        User foundedUser = UserDao.getInstance().getUserById(id);
        if (foundedUser == null) {
            return null;
        }
        String state = getState(foundedUser.getUserState());
        return new UserViewDto(foundedUser, state);
    }

    public List<UsersResultTableDto> getUsersWithStatistic(Long tournamentId) {
        List<User> usersOfTournament = UserDao.getInstance().getUsersOfTournament(tournamentId);
        return usersOfTournament.stream()
                .map(user -> UserDao.getInstance().getUserWithStatistics(tournamentId, user.getId()))
                .map(user -> new UsersResultTableDto(user.getId(), user.getFirstName(), user.getSecondName(),
                        calculateTotalPointsOfUser(user),
                        guessedResultsCount(user),
                        guessedWinnersCount(user),
                        guessedDiffInResultsCount(user)))
                .collect(Collectors.toList());
    }

    private int calculateTotalPointsOfUser(User user) {
        return user.getForecasts().stream()
                .map(forecast -> calculateUserPointsPerMatch(forecast.getMatch(), forecast))
                .mapToInt(points -> points)
                .sum();
    }

    public int calculateUserPointsPerMatch(Match foundMatch, Forecast userForecast) {
        Integer secondTeamResult = foundMatch.getSecondTeamResult();
        Integer firstTeamResult = foundMatch.getFirstTeamResult();
        if (firstTeamResult == userForecast.getFirstTeamForecast() &&
                secondTeamResult == userForecast.getSecondTeamForecast()) {
            return 6;
        } else if (userForecast.getFirstTeamForecast() - userForecast.getSecondTeamForecast() ==
                firstTeamResult - secondTeamResult &&
                firstTeamResult - secondTeamResult != 0) {
            return 4;
        } else if(userForecast.getFirstTeamForecast() - userForecast.getSecondTeamForecast() ==
                firstTeamResult - secondTeamResult &&
                firstTeamResult - secondTeamResult == 0) {
            return 3;
        } else if (Integer.compare(userForecast.getFirstTeamForecast(), userForecast.getSecondTeamForecast()) ==
                Integer.compare(firstTeamResult, secondTeamResult)) {
            return 1;
        } else {
            return 0;
        }
    }

    private int guessedResultsCount(User user) {
        return (int) user.getForecasts().stream()
                .filter(forecast -> forecast.getFirstTeamForecast() == forecast.getMatch().getFirstTeamResult() &&
                        forecast.getSecondTeamForecast() == forecast.getMatch().getSecondTeamResult())
                .count();
    }

    private int guessedWinnersCount(User user) {
        return (int) user.getForecasts().stream()
                .filter(forecast -> {
                    Match match = forecast.getMatch();
                    return (Integer.compare(forecast.getFirstTeamForecast(), forecast.getSecondTeamForecast()) ==
                            Integer.compare(match.getFirstTeamResult(), match.getSecondTeamResult())) &&
                            (forecast.getFirstTeamForecast() != match.getFirstTeamResult() ||
                                    forecast.getSecondTeamForecast() != match.getSecondTeamResult()) &&
                            (forecast.getFirstTeamForecast() - forecast.getSecondTeamForecast()) != (match.getFirstTeamResult() - match.getSecondTeamResult());
                })
                .count();
    }

    private int guessedDiffInResultsCount(User user) {
        return (int) user.getForecasts().stream()
                .filter(forecast -> {
                    Match match = forecast.getMatch();
                    return (((forecast.getFirstTeamForecast() - forecast.getSecondTeamForecast()) ==
                            (match.getFirstTeamResult() - match.getSecondTeamResult())) &&
                            (match.getFirstTeamResult() - match.getSecondTeamResult()) != 0) &&
                            (forecast.getFirstTeamForecast() != match.getFirstTeamResult() ||
                                    forecast.getSecondTeamForecast() != match.getSecondTeamResult());
                })
                .count();
    }

    public UserViewDto changeUserState(UserViewDto userViewDto, int userState) {
        User updatedUser = UserDao.getInstance().updateUser(new User(userViewDto, userState));
        String state = getState(updatedUser.getUserState());
        return new UserViewDto(updatedUser, state);
    }

    public List<UserViewDto> getUsersForRegistration() {
        List<User> userList = UserDao.getInstance().getListOfUsers(1);
        return userList.stream()
                .map(user -> new UserViewDto(user, getState(user.getUserState())))
                .collect(Collectors.toList());
    }

    public Map<Integer, String> getUserStates() {
        return UserDao.getInstance().getUserStates();
    }

    private String getState(int id) {
        return UserDao.getInstance().getUserState(id);
    }
}
