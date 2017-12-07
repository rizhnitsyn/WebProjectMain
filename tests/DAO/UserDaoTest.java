package DAO;

import DTO.UserLoggedDto;
import entities.User;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserDaoTest {

    @Test
    public void testAddUser() {
        UserDao userDao = UserDao.getInstance();
        Random random = new Random();
        User savedUser = null;
        try {
            savedUser = userDao.addUser(new User("Ivan", String.valueOf(random.nextInt(1000000)), "test" + String.valueOf(random.nextInt(100000)) + "@gmail.com"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        List<User> users = userDao.getListOfUsers();
        Assert.assertNotNull(users);
        Assert.assertTrue(users.size() > 0);
    }

    @Test
    public void testRegExp() {
        String text = "ra@bsb.by1";
        Pattern pattern = Pattern.compile("\\w+@[a-zA-Z]+\\.[a-zA-Z]+");
        Matcher matcher = pattern.matcher(text);
        if (matcher.matches()) {
            System.out.println("good");
        } else {
            System.out.println("bad");
        }


    }

    @Test
    public void md5() {
        String in = "11111";
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
        System.out.println(result);
    }
}
