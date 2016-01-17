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

    @Test
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
/*
        System.out.println(user2.getName());
        System.out.println(user2.getPassword());

        System.out.println(user2.getId() + "조회성공");
*/
        /*if(!user.getName().equals(user2.getName())) {
            System.out.println("테스트 실패 (name)");
        }
        else if(!user.getPassword().equals(user2.getPassword())) {
            System.out.println("테스트 실패 (password)");
        }
        else{
            System.out.println("조회 테스트 성공!");
        }*/
        user2.setName("1");
        assertThat(user2.getName(), is(user.getName()));
        assertThat(user2.getPassword(), is(user.getPassword()));
    }

    public static void main(String args[]) {
        JUnitCore.main("ex2.UserDaoTest");
    }
}
