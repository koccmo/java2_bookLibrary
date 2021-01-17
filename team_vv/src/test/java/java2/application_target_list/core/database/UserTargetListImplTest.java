package java2.application_target_list.core.database;

import java2.application_target_list.core.database.user.UserDatabase;
import java2.application_target_list.core.database.user.UserListImpl;
import java2.application_target_list.core.domain.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.List;


public class UserTargetListImplTest {

    private UserDatabase userDatabase;
    private User firstUser;
    private User secondUser;
    private User thirdUser;

    @Before
    public void setup() {
        userDatabase = new UserListImpl();
        firstUser = new User("name1", "surname1");
        secondUser = new User("name2", "surname2");
        thirdUser = new User("name3", "surname3");
    }


    @Test
    public void testAddUser_v1() {
        userDatabase.addUser(firstUser);
        Assert.assertEquals(userDatabase.getUsersList().size(), 1);
        Long actual = firstUser.getId();
        Long expected = 1L;
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testAddUser_v2() {
        userDatabase.addUser(firstUser);
        userDatabase.addUser(secondUser);
        Assert.assertEquals(userDatabase.getUsersList().size(), 2);
        Long actual = secondUser.getId();
        Long expected = 2L;
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testAddUser_v3() {
        userDatabase.addUser(firstUser);
        userDatabase.addUser(secondUser);
        userDatabase.addUser(thirdUser);
        Assert.assertEquals(userDatabase.getUsersList().size(), 3);
        Long actual = thirdUser.getId();
        Long expected = 3L;
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testDeleteUser_v1() {
        userDatabase.addUser(firstUser);
        userDatabase.addUser(secondUser);
        userDatabase.addUser(thirdUser);

        userDatabase.deleteUser(2L);
        Assert.assertEquals(userDatabase.getUsersList().size(), 2);
        Assert.assertEquals(userDatabase.getUsersList().get(0).getFirstName(), "name1");
        Assert.assertEquals(userDatabase.getUsersList().get(1).getFirstName(), "name3");
    }

    @Test
    public void testDeleteUser_v2() {
        userDatabase.addUser(firstUser);
        userDatabase.addUser(secondUser);
        userDatabase.addUser(thirdUser);
        userDatabase.deleteUser(1L);
        Assert.assertEquals(userDatabase.getUsersList().size(), 2);
        Assert.assertEquals(userDatabase.getUsersList().get(0).getFirstName(), "name2");
        Assert.assertEquals(userDatabase.getUsersList().get(1).getFirstName(), "name3");
    }

    @Test
    public void testChangeUserFirstName_v1() {
        userDatabase.addUser(firstUser);
        userDatabase.changeUserFirstName(1L, "New Name");
        Assert.assertEquals(userDatabase.getUsersList().get(0).getFirstName(), "New Name");
        Assert.assertEquals(userDatabase.getUsersList().get(0).getLastName(), "surname1");
    }

    @Test
    public void testChangeUserFirstName_v2() {
        userDatabase.addUser(firstUser);
        userDatabase.addUser(secondUser);
        userDatabase.changeUserFirstName(2L, "New Name");
        Assert.assertEquals(userDatabase.getUsersList().get(1).getFirstName(), "New Name");
        Assert.assertEquals(userDatabase.getUsersList().get(1).getLastName(), "surname2");
    }

    @Test
    public void testChangeUserLastName_v1() {
        userDatabase.addUser(firstUser);
        userDatabase.changeUserLastName(1L, "New Surname");
        Assert.assertEquals(userDatabase.getUsersList().get(0).getFirstName(), "name1");
        Assert.assertEquals(userDatabase.getUsersList().get(0).getLastName(), "New Surname");
    }

    @Test
    public void testChangeUserLastName_v2() {
        userDatabase.addUser(firstUser);
        userDatabase.addUser(secondUser);
        userDatabase.changeUserLastName(2L, "New Surname");
        Assert.assertEquals(userDatabase.getUsersList().get(1).getFirstName(), "name2");
        Assert.assertEquals(userDatabase.getUsersList().get(1).getLastName(), "New Surname");
    }

    @Test
    public void testGetUsersList_v1() {
        userDatabase.addUser(firstUser);
        List<User> users = userDatabase.getUsersList();
        Assert.assertEquals(users.size(), 1);
        Assert.assertEquals(users.get(0).getFirstName(), "name1");
        Assert.assertEquals(users.get(0).getLastName(), "surname1");
    }

    @Test
    public void testGetUsersList_v2() {
        userDatabase.addUser(firstUser);
        userDatabase.addUser(secondUser);
        List<User> users = userDatabase.getUsersList();
        Assert.assertEquals(users.size(), 2);
        Assert.assertEquals(users.get(0).getFirstName(), "name1");
        Assert.assertEquals(users.get(0).getLastName(), "surname1");
        Assert.assertEquals(users.get(1).getFirstName(), "name2");
        Assert.assertEquals(users.get(1).getLastName(), "surname2");
    }

    @Test
    public void testGetUsersList_v3() {
        userDatabase.addUser(firstUser);
        userDatabase.addUser(secondUser);
        userDatabase.deleteUser(1L);
        List<User> users = userDatabase.getUsersList();
        Assert.assertEquals(users.size(), 1);
        Assert.assertEquals(users.get(0).getFirstName(), "name2");
        Assert.assertEquals(users.get(0).getLastName(), "surname2");
    }

    @Test
    public void testFindUserByFirstName_v1() {
        userDatabase.addUser(firstUser);
        userDatabase.addUser(secondUser);
        userDatabase.addUser(thirdUser);
        List<User> users = userDatabase.findUserByFirstName("name1");
        Assert.assertEquals(users.size(), 1);
        Assert.assertEquals(users.get(0).getFirstName(), "name1");
        Assert.assertEquals(users.get(0).getLastName(), "surname1");
    }

    @Test
    public void testFindUserByFirstName_v2() {
        userDatabase.addUser(firstUser);
        userDatabase.addUser(secondUser);
        userDatabase.addUser(thirdUser);
        List<User> users = userDatabase.findUserByFirstName("name");
        Assert.assertEquals(users.size(), 3);
        Assert.assertEquals(users.get(0).getFirstName(), "name1");
        Assert.assertEquals(users.get(0).getLastName(), "surname1");
        Assert.assertEquals(users.get(1).getFirstName(), "name2");
        Assert.assertEquals(users.get(1).getLastName(), "surname2");
        Assert.assertEquals(users.get(2).getFirstName(), "name3");
        Assert.assertEquals(users.get(2).getLastName(), "surname3");
    }

    @Test
    public void testFindUserByLastName_v1() {
        userDatabase.addUser(firstUser);
        userDatabase.addUser(secondUser);
        userDatabase.addUser(thirdUser);
        List<User> users = userDatabase.findUserByLastName("surname2");
        Assert.assertEquals(users.size(), 1);
        Assert.assertEquals(users.get(0).getFirstName(), "name2");
        Assert.assertEquals(users.get(0).getLastName(), "surname2");
    }

    @Test
    public void testFindUserByLastName_v2() {
        userDatabase.addUser(firstUser);
        userDatabase.addUser(secondUser);
        userDatabase.addUser(thirdUser);
        List<User> users = userDatabase.findUserByLastName("surname");
        Assert.assertEquals(users.size(), 3);
        Assert.assertEquals(users.get(0).getFirstName(), "name1");
        Assert.assertEquals(users.get(0).getLastName(), "surname1");
        Assert.assertEquals(users.get(1).getFirstName(), "name2");
        Assert.assertEquals(users.get(1).getLastName(), "surname2");
        Assert.assertEquals(users.get(2).getFirstName(), "name3");
        Assert.assertEquals(users.get(2).getLastName(), "surname3");
    }


}