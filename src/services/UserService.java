package services;

import DAO.UserDao;
import DTO.*;
import entities.Forecast;
import entities.Match;
import entities.Tournament;
import entities.User;
import utils.StaticContent;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static utils.StaticContent.*;

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

    public void addUser(UserCreateDto userDto) throws SQLException {
        UserDao.getInstance().addUser(new User(userDto));
}

    public UserLoggedDto checkRegistration(UserCreateDto dto) {
        if (dto.getFirstName().isEmpty() || dto.getSecondName().isEmpty() || dto.getEmail().isEmpty() || dto.getLogin().isEmpty() || dto.getPassword().isEmpty()) {
            return new UserLoggedDto(true,"Заполните все поля");
        }
        return new UserLoggedDto(false, "Успешно");
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

    public UserViewDto getUserById(Long userId) {
        User foundedUser = UserDao.getInstance().getUserById(userId);
        if (foundedUser == null) {
            return null;
        }
        String state = getState(foundedUser.getUserState());
        return new UserViewDto(foundedUser, state);
    }

    public UserViewDto getShortUserById(Long userId) {
        User foundedUser = UserDao.getInstance().getShortUserById(userId);
        if (foundedUser == null) {
            return null;
        }
        return new UserViewDto(foundedUser.getId(), foundedUser.getFirstName(), foundedUser.getSecondName());
    }

    public void printResultTable(Long tournamentId) {
        List<UsersResultTableDto> users = UserService.getInstance().getUsersWithStatistic(tournamentId);
        TournamentShortViewDto tournament = TournamentService.getInstance().getTournamentName(tournamentId);

        if (!users.isEmpty()) {
            synchronized (UserService.class) {
                File file = new File(FILE_DIRECTORY, FILE_NAME + LocalTime.now().format(timeFormat) + FILE_SUFFIX);
                try (FileWriter fileWriter = new FileWriter(file)) {
                    fileWriter.write("Результаты турнира: " + tournament.getName() + "\n");
                    fileWriter.write("------------------------------------------------------------");
                    fileWriter.write("ФИО" + "6 очков" + "4 очка" + "3 очка" + "1 очко" + "БАЛЛЫ");
                    for (UsersResultTableDto user : users) {
                        fileWriter.write(user.getFirstName() + user.getSecondName());
                        fileWriter.write(user.getGuessedResultCount());
                        fileWriter.write(user.getGuessedDiffInResultsCount());
                        fileWriter.write(user.getGuessedDrawCount());
                        fileWriter.write(user.getGuessedWinnersCount());
                        fileWriter.write(user.getTotalPoints());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<UsersResultTableDto> getUsersWithStatistic(Long tournamentId) {
        List<User> usersOfTournament = UserDao.getInstance().getUsersOfTournament(tournamentId);

        return usersOfTournament.stream()
                .map(user -> UserDao.getInstance().getUserWithStatistics(tournamentId, user.getId()))
                .map(user -> new UsersResultTableDto(user.getId(), user.getFirstName(), user.getSecondName(),
                        calculateTotalPointsOfUser(user),
                        guessedResultsCount(user),
                        guessedWinnersCount(user),
                        guessedDiffInResultsCount(user),
                        guessedDrawCount(user),
                        tournamentId
                        ))
                .sorted(Comparator.comparing(UsersResultTableDto::getTotalPoints).reversed())
                .collect(Collectors.toList());
    }

    private int calculateTotalPointsOfUser(User user) {
        return user.getForecasts().stream()
                .map(forecast -> calculateUserPointsPerMatch(forecast.getMatch(), forecast))
                .mapToInt(points -> points)
                .sum();
    }

    public int calculateUserPointsPerMatch(Match foundMatch, Forecast userForecast) {
        if (foundMatch.getFirstTeamResult() == null || foundMatch.getSecondTeamResult() == null) {
            return 0;
        }
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

    private int guessedDrawCount(User user) {
        return (int) user.getForecasts().stream()
                .filter(forecast -> forecast.getFirstTeamForecast() == forecast.getSecondTeamForecast() &&
                        forecast.getMatch().getFirstTeamResult().equals(forecast.getMatch().getSecondTeamResult()) &&
                        forecast.getFirstTeamForecast() != forecast.getMatch().getFirstTeamResult())
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

    public void changeUserState(UserViewDto userViewDto, int userState) {
        UserDao.getInstance().updateUser(new User(userViewDto, userState));
    }

    public UserUpdateAnswerDto changeUserPassword(Long userId, String password) {
        User updatedUser = UserDao.getInstance().changePassword(new User(userId, password));
        if (updatedUser != null) {
            return new UserUpdateAnswerDto("Успешно", "/login");
        } else {
            return new UserUpdateAnswerDto("Произошла ошибка при смене пароля", "/login");
        }
    }

    public List<UserViewDto> getUsersForRegistration() {
        List<User> userList = UserDao.getInstance().getListOfUsers();
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
