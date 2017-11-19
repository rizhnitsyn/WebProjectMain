package DAO;

import entities.User;

import java.util.List;

public interface UserDao {
    User addUser(User user);
    User updateUser(User user);
    User getUserById(Long userId);
    List<User> getListOfUsers();
}
