package ex8;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by 1015331 on 2016-01-16.
 */
public interface ConnectionMaker {
    public Connection makeConnection() throws ClassNotFoundException, SQLException;
}
