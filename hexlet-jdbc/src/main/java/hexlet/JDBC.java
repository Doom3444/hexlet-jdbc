package hexlet;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class JDBC {

    public static void main(String[] args) throws SQLException {

        var connection = DriverManager.getConnection("jdbc:h2:mem:hexlet_test");

        var dao = new UserDAO(connection);

        var user1 = new User("Tom", "1111");

        System.out.println(user1.getId());
        dao.save(user1);
        System.out.println(user1.getId());

        var user2 = dao.find(user1.getId()).get();
        System.out.println(user1.getId() == user2.getId());

        connection.close();
    }
}
