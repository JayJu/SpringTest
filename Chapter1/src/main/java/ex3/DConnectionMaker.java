package ex3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by 1015331 on 2016-01-16.
 */
public class DConnectionMaker implements ConnectionMaker {
    public Connection makeConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        Connection c = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
        return c;
    }
}
