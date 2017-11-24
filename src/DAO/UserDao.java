package DAO;

import entities.Role;
import entities.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
    User addUser(User user);
    User updateUser(User user);
    User getUserById(Long userId);
    List<User> getListOfUsers(int state);
    Map<Integer, String> getUserStates();
}
