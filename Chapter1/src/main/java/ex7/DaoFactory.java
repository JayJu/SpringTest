package ex7;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by 1015331 on 2016-01-16.
 */

@Configuration
public class DaoFactory {
    @Bean
    public UserDao userDao() {
        return new UserDao(connectionMaker());
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
}
