package services;

import DAO.daoImplementation.UserDaoImpl;
import DTO.UserCreateDto;
import DTO.UserViewDto;
import entities.Role;
import entities.User;

import java.util.HashMap;
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

    public UserViewDto addUser(UserCreateDto userDto) {
        User newUser = new User(userDto);
        User savedUser = UserDaoImpl.getInstance().addUser(newUser);
        String state = getState(savedUser.getUserState());
        return new UserViewDto(savedUser, state);
    }

    public UserViewDto getUserById(Long id) {
        User foundedUser = UserDaoImpl.getInstance().getUserById(id);
        if (foundedUser == null) {
            return null;
        }
        String state = getState(foundedUser.getUserState());
        return new UserViewDto(foundedUser, state);
    }

    public UserViewDto changeUserState(UserViewDto userViewDto, int userState) {
        User updatedUser = UserDaoImpl.getInstance().updateUser(new User(userViewDto, userState));
        String state = getState(updatedUser.getUserState());
        return new UserViewDto(updatedUser, state);
    }

//    public UserViewDto blockUser(UserViewDto userViewDto) {
//        User updatedUser = UserDaoImpl.getInstance().updateUser(new User(userViewDto, 3));
//        String state = getState(updatedUser.getUserState());
//        return new UserViewDto(updatedUser, state);
//    }

    public List<UserViewDto> getUsersForRegistration() {
        List<User> userList = UserDaoImpl.getInstance().getListOfUsers(1);
        return userList.stream()
                .map(user -> new UserViewDto(user, getState(user.getUserState())))
                .collect(Collectors.toList());
    }

    public Map<Integer, String> getUserStates() {
        return UserDaoImpl.getInstance().getUserStates();
    }

    private String getState(int id) {
        return UserDaoImpl.getInstance().getUserState(id);
    }
}
