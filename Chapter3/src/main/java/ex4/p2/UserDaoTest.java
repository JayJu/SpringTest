package ex4.p2;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by 1015331 on 2016-01-16.
 */
@RunWith(SpringJUnit4ClassRunner.class) //스프링의 테스트 컨텍스트 프레임워크의 JUnit 확장기능 지정
@ContextConfiguration(locations = "applicationContext.xml") //테스트 컨텍스트가 자동으로 만들어 줄 애플리케이션 컨텍스트의 위치 지정
@DirtiesContext //테스트 메서드에서 어플리케이션 컨텍스트의 구성이나 상태를 변경한다는 것을 테스트 컨텍스트에게 알려준다.
                //테스트 컨텍스트는 이 애노테이션이 붙은 테스트 클래스에는 애플리케이션 컨텍스트를 공유하지 않는다. 메서드 레벨도 사용 가능.
public class UserDaoTest {
    @Autowired //테스트 오브젝트가 만들어지고 나면 스프링 테스트 컨텍스트에 의해 자동으로 값이 주입된다.
    private ApplicationContext context;

    @Autowired
    private UserDao dao;
    private User user1;
    private User user2;
    private User user3;

    @Before //@Test 메서드가 실행되기 전에 먼저 실행되어야 하는 메서드를 정의한다.
    public void setup() {
        System.out.println(this.context); //테스트 3번 모두 동일한 주소값
        System.out.println(this); //테스트마다 다른 주소값

        //테스트에서 UserDao가 사용 할 Datasource오브젝트를 직접 생성.
        DataSource dataSource = new SingleConnectionDataSource("jdbc:h2:tcp://localhost/~/test", "sa", "", true);
        dao.setDataSource(dataSource);

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
        JUnitCore.main("ex4.p2.UserDaoTest"); //JUnit을 이용해 테스트를 실행해주는 main()메서드
    }

}