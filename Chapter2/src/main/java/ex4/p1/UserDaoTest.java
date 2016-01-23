package ex4.p1;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by 1015331 on 2016-01-16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DaoFactory.class})
public class UserDaoTest {
    @Autowired
    private ApplicationContext context;
    private UserDao dao;

    @Before
    public void setup() {
        System.out.println(this.context); //테스트 3번 모두 동일한 주소값
        System.out.println(this); //테스트마다 다른 주소값
        this.dao = context.getBean("userDao", UserDao.class);
    }

    @Test
    public void addAndGet() throws ClassNotFoundException,SQLException {

        User user1 = new User("jiny", "주희진", "12345");
        User user2 = new User("yuchi", "지혜", "456");

        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        dao.add(user1);
        dao.add(user2);
        assertThat(dao.getCount(), is(2));

        User userget1 = dao.get(user1.getId());
        assertThat(userget1.getName(), is(user1.getName()));
        assertThat(userget1.getPassword(), is(user1.getPassword()));

        User userget2 = dao.get(user2.getId());
        assertThat(userget2.getName(), is(user2.getName()));
        assertThat(userget2.getPassword(), is(user2.getPassword()));
    }


    @Test
    public void count() throws ClassNotFoundException, SQLException {
        User user1 = new User("jin", "희진", "123");
        User user2 = new User("yuchi", "지혜", "456");
        User user3 = new User("ywoo", "연우", "789");

        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        dao.add(user1);
        assertThat(dao.getCount(), is(1));

        dao.add(user2);
        assertThat(dao.getCount(), is(2));

        dao.add(user3);
        assertThat(dao.getCount(), is(3));
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void getUserFailure() throws ClassNotFoundException, SQLException {
        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        dao.get("unknows_id");

    }
    public static void main(String args[]) {
        JUnitCore.main("ex4.p1.UserDaoTest");
    }

}