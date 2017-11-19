package services;

import DAO.daoImplementation.UserDaoImpl;
import DTO.UserDto;
import entities.User;

public class UserService {
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

    public UserDto addUser(User user) {
        User savedUser = UserDaoImpl.getInstance().addUser(user);
        return new UserDto(savedUser.getId(), savedUser.getFirstName(), savedUser.getSecondName(), savedUser.getEmail());
    }

    public UserDto getUserById(Long id) {
        User foundedUser = UserDaoImpl.getInstance().getUserById(id);
        if (foundedUser == null) {
            return null;
        }
        return new UserDto(foundedUser.getId(), foundedUser.getFirstName(), foundedUser.getSecondName(), foundedUser.getEmail());
    }
}
