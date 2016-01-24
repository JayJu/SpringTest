package ex3;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by 1015331 on 2016-01-16.
 */
public class UserDaoTest {
    private UserDao dao;
    private User user1;
    private User user2;
    private User user3;

    @Before //@Test 메서드가 실행되기 전에 먼저 실행되어야 하는 메서드를 정의한다.
    public void setup() {
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        dao = context.getBean("userDao", UserDao.class);

        this.user1 = new User("jiny", "주희진", "12345");
        this.user2 = new User("yuchi", "지혜", "456");
        this.user3 = new User("ywoo", "연우", "789");
    }

    @Test //JUnit에게 테스트메서드임을 알려준다 테스트메서드는 public 이어야 한다
    public void addAndGet() throws ClassNotFoundException,SQLException {

        user1 = new User("jiny", "주희진", "12345");
        user2 = new User("yuchi", "지혜", "456");

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
        user1 = new User("jin", "희진", "123");
        user2 = new User("yuchi", "지혜", "456");
        user3 = new User("ywoo", "연우", "789");

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
        JUnitCore.main("ex3.UserDaoTest"); //JUnit을 이용해 테스트를 실행해주는 main()메서드
    }

}