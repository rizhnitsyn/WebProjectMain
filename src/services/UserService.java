package services;

import DAO.UserDao;
import DTO.*;
import entities.Forecast;
import entities.Match;
import entities.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static utils.StaticContent.*;

public final class UserService {
    private static UserService INSTANCE;

    private static final Object LOCK = new Object();

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
        String email = dto.getEmail();

        if (dto.getFirstName().isEmpty() || dto.getSecondName().isEmpty() || email.isEmpty() || dto.getLogin().isEmpty() || dto.getPassword().isEmpty()) {
            return new UserLoggedDto(true,"Заполните все поля");
        } else {
            Pattern pattern = Pattern.compile("\\w+@[a-zA-Z]+\\.[a-zA-Z]+");
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                return new UserLoggedDto(false, "Успешно");
            }
            return new UserLoggedDto(true,"Неверный формат Email");
        }
    }

    private String md5(String in) {
        String result = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.reset();
            digest.update(in.getBytes());
            BigInteger bigInt = new BigInteger(1, digest.digest());
            result = bigInt.toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }

    public UserLoggedDto checkHashPassword(LoginDto dto) {
        User loggedUser  = UserDao.getInstance().getShortUser(dto.getLogin());

        if (loggedUser != null && checkHashPassword(dto, loggedUser.getPassword())) {
            return new UserLoggedDto("Все данные введены верно", "/homepage", true, loggedUser.getId(), loggedUser.getFirstName(),
                    loggedUser.getSecondName(), loggedUser.getUserState());
        } else {
            return new UserLoggedDto("Неверно введены логин или пароль", false);
        }
    }

    private boolean checkHashPassword(LoginDto dto, String password) {
        return md5(password + dto.getRandomNumber()).equals(dto.getPassword());
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

    public UnloadFileDto printResultTable(UnloadFileDto unloadFileDto) {
        Long tournamentId = unloadFileDto.getIdTournament();
        List<UsersResultTableDto> users = UserService.getInstance().getUsersWithStatistic(tournamentId);
        TournamentShortViewDto tournament = TournamentService.getInstance().getTournamentName(tournamentId);

        if (!users.isEmpty()) {
            synchronized (LOCK) {
                File file = new File(FILE_DIRECTORY, FILE_NAME + LocalTime.now().format(timeFormat) + FILE_SUFFIX);
                try (FileWriter fileWriter = new FileWriter(file)) {
                    fileWriter.write(buildField("Результаты турнира: ", 30) + buildField(tournament.getName(),47) + "\n");
                    fileWriter.write("------------------------------------------------------------------------------|" + "\n");
                    fileWriter.write("            ФИО             |" + "6 очков  |" + "4 очка   |" + "3 очка   |" + "1 очко   |" + "БАЛЛЫ    |" + "\n");
                    fileWriter.write("------------------------------------------------------------------------------|" + "\n");
                    for (UsersResultTableDto user : users) {
                        fileWriter.write(buildField(user.getFirstName() + " " + user.getSecondName(), 28));
                        fileWriter.write(buildField(String.valueOf(user.getGuessedResultCount()), 9));
                        fileWriter.write(buildField(String.valueOf(user.getGuessedDiffInResultsCount()), 9));
                        fileWriter.write(buildField(String.valueOf(user.getGuessedDrawCount()), 9));
                        fileWriter.write(buildField(String.valueOf(user.getGuessedWinnersCount()), 9));
                        fileWriter.write(buildField(String.valueOf(user.getTotalPoints()), 9) + "\n");
                    }
                    fileWriter.write("------------------------------------------------------------------------------|" + "\n");
                    unloadFileDto.setMessage("Выгрузка прошла успешно");
                    return unloadFileDto;
                } catch (IOException e) {
                    unloadFileDto.setMessage(e.toString());
                    return unloadFileDto;
                }
            }
        }
        unloadFileDto.setMessage("Нета данных для выгрузки");
        return unloadFileDto;
    }

    private String buildField(String myString, int totalLength) {
        int spaceCount = totalLength - myString.length();
        if (spaceCount <= 0) {
            return myString + "|";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(myString);
        for (int i = 0; i < spaceCount; i++) {
            stringBuilder.append(" ");
        }
        stringBuilder.append("|");
        return stringBuilder.toString();
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
