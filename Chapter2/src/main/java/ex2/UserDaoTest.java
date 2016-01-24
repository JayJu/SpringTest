package ex2;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by 1015331 on 2016-01-16.
 */
public class UserDaoTest {

    @Test //JUnit에게 테스트메서드임을 알려준다 테스트메서드는 public 이어야 한다
    public void addAndGet() throws ClassNotFoundException,SQLException {
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao dao = context.getBean("userDao", UserDao.class);

        User user = new User();
        user.setId("jiny");
        user.setName("주희진");
        user.setPassword("12345");

        dao.add(user);

        System.out.println(user.getId() + "등록성공");

        User user2 = dao.get(user.getId());

        assertThat(user2.getName(), is(user.getName()));
        assertThat(user2.getPassword(), is(user.getPassword()));
    }


    public static void main(String args[]) {
        JUnitCore.main("ex2.UserDaoTest"); //JUnit을 이용해 테스트를 실행해주는 main()메서드
    }

}
