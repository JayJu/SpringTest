package ex7;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

/**
 * Created by jin on 2016. 1. 17..
 */
public class UserDaoConnecionCountingTest {
    public static void main(String args[]) throws ClassNotFoundException, SQLException{
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CountingDaoFactory.class);
        UserDao dao = context.getBean("userDao", UserDao.class);
        CountingConnectionMaker ccm = context.getBean("connectionMaker", CountingConnectionMaker.class);
        System.out.println("Connection counter: " + ccm.getCounter());

        User user = new User();
        user.setId("jiny");
        user.setName("주희진");
        user.setPassword("12345");

        dao.add(user);

        System.out.println(user.getId() + "등록성공");
        System.out.println("Connection counter: " + ccm.getCounter());

        User user2 = dao.get(user.getId());
        System.out.println(user2.getName());
        System.out.println(user2.getPassword());

        System.out.println(user2.getId() + "조회성공");

        System.out.println("Connection counter: " + ccm.getCounter());
    }
}
