package lesson2.chatLogic;

import java.sql.*;


public class AuthService {
    private static Connection connection;
    private static String URL = "jdbc:mysql://127.0.0.1:3306/chatFX";
    private static String USERNAME = "root";
    private static String PASSWORD = "12345";


    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        return connection;
    }


    public void signUpUser(NewUser newUser) {
        String insert = "INSERT INTO " + Const.USER_TABLE + "(" + Const.USER_NAME + "," + Const.LOGIN + ","
                + Const.PASSWORD + ")" + "VALUES(?,?,?)";
        try {
            PreparedStatement preparedSt = getConnection().prepareStatement(insert);
            preparedSt.setString(1, newUser.getUserName());
            preparedSt.setString(2, newUser.getLogin());
            preparedSt.setString(3, newUser.getPassword());
            preparedSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public String getNickByLoginAndPass(String login, String pass) {
        String select = String.format("SELECT user_name FROM " + Const.USER_TABLE +
                " WHERE login = '%s' and password = '%s'", login, pass);
        try {
            PreparedStatement preparedSt = getConnection().prepareStatement(select);
            ResultSet resultSet = preparedSt.executeQuery();
            if (resultSet.next()) {
                String s = resultSet.getString(1);
                return resultSet.getString(1);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void changeUserName(String usName, String newUserName) {
        String update = String.format("UPDATE " + Const.USER_TABLE +
                " SET user_name = '%s' WHERE user_name = '%s'", newUserName, usName);

        try {
            PreparedStatement preparedSt = getConnection().prepareStatement(update);
            preparedSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}