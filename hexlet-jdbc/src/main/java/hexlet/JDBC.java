package hexlet;

import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {

    public static void main(String[] args) throws SQLException {

        try(var connection = DriverManager.getConnection("jdbc:h2:mem:dao-test")) {
            var sql = "CREATE TABLE users (id bigint PRIMARY KEY AUTO_INCREMENT, username varchar(255), phone varchar(255))";
            try (var statement = connection.createStatement()) {
                statement.execute(sql);
            }
            var dao = new UserDAO(connection);

            var user1 = new User("Tom", "1111");

            System.out.println(user1.getId());
            dao.save(user1);
            System.out.println(user1.getId());

            var user2 = dao.find(user1.getId()).get();
            System.out.println(user1.getId() == user2.getId());

            System.out.println(dao.find(user1.getId()).get());
            user1.setName("Joe");
            dao.save(user1);
            System.out.println(dao.find(user1.getId()).get());

            System.out.println("-----");
            var user3 = new User("Sam", "3333");
            dao.save(user3);
            var sql2 = "SELECT * FROM users";
            try (var statement = connection.createStatement()) {
                var users_Table = statement.executeQuery(sql2);
                while (users_Table.next()) {
                    System.out.print(users_Table.getString("username") + " ");
                    System.out.println(users_Table.getString("phone"));
                }
            }
            System.out.println("-----");
            dao.delete(user1);
            var sql3 = "SELECT * FROM users";
            try (var statement = connection.createStatement()) {
                var users_Table = statement.executeQuery(sql3);
                while (users_Table.next()) {
                    System.out.print(users_Table.getString("username") + " ");
                    System.out.println(users_Table.getString("phone"));
                }
            }
        }
    }
}
