package DAO;

import entities.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Random;

public class UserDaoTest {

    @Test
    public void testAddUser() {
        UserDao userDao = UserDao.getInstance();
        Random random = new Random();
        User savedUser = userDao.addUser(new User("Ivan", String.valueOf(random.nextInt(1000000)), "test" + String.valueOf(random.nextInt(100000)) + "@gmail.com"));
        Assert.assertNotNull(savedUser);
        Assert.assertTrue(savedUser.getFirstName().equals("Ivan"));
        Assert.assertTrue(savedUser.getId() != 0);
    }

    @Test
    public void testGetUserById() {
        UserDao userDao = UserDao.getInstance();
        User user = userDao.getUserById(1L);
        Assert.assertNotNull(user);
        Assert.assertEquals("Рижницын", user.getSecondName());
//        user.getForecasts().forEach(forecast -> System.out.println(forecast.getFirstTeamForecast() + " " + forecast.getSecondTeamForecast()));
//        user.getTournaments().forEach(tournament -> System.out.println(tournament.getName()));
    }

    @Test
    public void testUpdateUser()  {
        UserDao userDao = UserDao.getInstance();
        Random random = new Random();
        User user = userDao.getUserById(11L);
        String firstName = "updated" + String.valueOf(random.nextInt(1000000));
        user.setFirstName(firstName);
        User updatedUser = userDao.updateUser(user);
        Assert.assertNotNull(updatedUser);
        Assert.assertTrue(updatedUser.getFirstName().equals(firstName));
    }

    @Test
    public void getListOfUsers() {
        UserDao userDao = UserDao.getInstance();
        List<User> users = userDao.getListOfUsers(2);
        Assert.assertNotNull(users);
        Assert.assertTrue(users.size() > 0);
    }
}
