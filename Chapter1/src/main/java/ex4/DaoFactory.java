package ex4;

/**
 * Created by 1015331 on 2016-01-16.
 */
public class DaoFactory {
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
    public ConnectionMaker connectionMaker() {
        return new DConnectionMaker();
    }
}
