package ex1;


import org.springframework.dao.EmptyResultDataAccessException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by 1015331 on 2016-01-12.
 */
public class UserDao {
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void deleteAll() throws SQLException {

        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = dataSource.getConnection();
            ps = c.prepareStatement("delete from users");
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                }catch (SQLException e) {
                    
                }
            }
            if (c != null) {
                try {
                    c.close();
                }catch (SQLException e) {

                }
            }
        }

        ps.close();
        c.close();
    }

    public int getCount() throws SQLException {
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            c = dataSource.getConnection();
            ps = c.prepareStatement("select count(*) from users");
            rs = ps.executeQuery();
            rs.next();
            count = rs.getInt(1);
        } catch (SQLException e) {
            throw e;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                }catch (SQLException e) {

                }
            }
            if (ps != null) {
                try {
                    ps.close();
                }catch (SQLException e) {

                }
            }
            if (c != null) {
                try {
                    c.close();
                }catch (SQLException e) {

                }
            }

        }

        rs.close();
        ps.close();
        c.close();

        return count;
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection c = dataSource.getConnection();
        PreparedStatement ps = c.prepareStatement("insert into users (id, name, password) values (?,?,?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        c.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection c = dataSource.getConnection();
        PreparedStatement ps = c.prepareStatement("select * from users where id = ?");
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();

        User user = null;

        if(rs.next()) {
            user = new User();
            user.setId(rs.getString("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
        }

        rs.close();
        ps.close();
        c.close();

        if(user == null) throw new EmptyResultDataAccessException(1);

        return user;
    }

}
