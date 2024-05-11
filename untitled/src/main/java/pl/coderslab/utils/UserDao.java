package pl.coderslab.utils;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.Arrays;

public class UserDao {
    private static final String CREATE_USER_QUERY = "INSERT INTO users(userName, email, password) VALUES (?, ?, ?);";
    private static final String SELECT_USER = "SELECT * FROM users WHERE id = ?;";
    private static final String UPTADE_USER = "UPDATE users SET userName = ?, email = ?, password = ? WHERE id = ?;";
    private static final String DELETE_QUERY = "DELETE FROM users where id = ?;";
    private static final String READ_ALL = "SELECT * FROM users;";

    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public User create(User user) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, hashPassword(user.getPassword()));
            statement.executeUpdate();
            //Pobieramy wstawiony do bazy identyfikator, a następnie ustawiamy id obiektu user.
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User read(int userId) {

        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(SELECT_USER);
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("username");
                String email = rs.getString("email");
                String password = rs.getString("password");
                User user = new User(name,email,password);
                user.setId(userId);
//                user.setUserName(name);
//                user.setEmail(email);
//                user.setPassword(password);
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void update(User user) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPTADE_USER);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, hashPassword(user.getPassword()));
            statement.setInt(4, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int userId) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);
            statement.setInt(1, userId);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public User[] findAll() {
        try (Connection connection = DbUtil.getConnection()) {
            User[] users = new User[0];
            PreparedStatement statement = connection.prepareStatement(READ_ALL);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String email = rs.getString("email");
                String userName = rs.getString("username");
                String password = rs.getString("password");
                int id = rs.getInt("id");
                User user = new User();
                user.setId(id);
                user.setUserName(userName);
                user.setEmail(email);
                user.setPassword(password);
                users = addToArray(user,users);

            }

            return users;
        } catch (SQLException e) {
            e.getMessage();
            return null;
        }
    }

    private User[] addToArray(User u, User[] users) {
        User[] tmpUsers = Arrays.copyOf(users, users.length + 1);
        tmpUsers[users.length] = u;
        return tmpUsers;
    }


}
