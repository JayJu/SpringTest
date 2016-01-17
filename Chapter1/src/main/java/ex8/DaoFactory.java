package ex8;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

/**
 * Created by 1015331 on 2016-01-16.
 */

@Configuration
public class DaoFactory {
    @Bean
    public UserDao userDao() {
        UserDao userDao = new ex8.UserDao();
        userDao.setDataSource(dataSource());
        return userDao;
    }

    /*
    public UserDao accountDao() {
        return new UserDao(new DConnectionMaker());
    }

    public UserDao messageDao() {
        return new UserDao(new DConnectionMaker());
    }
    */

    @Bean
    public ConnectionMaker connectionMaker() {
        return new DConnectionMaker();
    }

    @Bean
    public DataSource dataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(org.h2.Driver.class);
        dataSource.setUrl("jdbc:h2:tcp://localhost/~/test");
        dataSource.setUsername("sa");
        dataSource.setPassword("");

        return dataSource;
    }
}
