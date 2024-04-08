package hexlet;

import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {

    public static void main(String[] args) throws SQLException {

        var conn = DriverManager.getConnection("jdbc:h2:mem:hexlet_test");

        var sql = "CREATE TABLE users (id BIGINT PRIMARY KEY AUTO_INCREMENT, username VARCHAR(255), phone VARCHAR(255))";

        var statement = conn.createStatement();
        statement.execute(sql);
        statement.close();

        var sql2 = "INSERT INTO users (username, phone) VALUES ('tommy_1', '123456789')";
        var sql3 = "INSERT INTO users (username, phone) VALUES ('tommy_2', '123456789')";
        var statement2 = conn.createStatement();
        statement2.executeUpdate(sql2);
        statement2.executeUpdate(sql3);
        statement2.close();

        var sql4 = "SELECT * FROM users";
        var statement3 = conn.createStatement();

        var resultSet = statement3.executeQuery(sql4);

        while (resultSet.next()) {
            System.out.println(resultSet.getString("username"));
            System.out.println(resultSet.getString("phone"));
        }
        statement3.close();

        conn.close();
    }
}
