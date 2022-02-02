package com.example.dvdmanagementsoftware.database;

import com.example.dvdmanagementsoftware.users.User;
import java.sql.*;
import java.util.ArrayList;

public class Database {
    Connection connection = null;
    Statement statement;
    public Database() {
        String url = "jdbc:mysql://0.0.0.0:3306/dvdMS?allowPublicKeyRetrieval=true&useSSL=false";
        String username = "root";
        String password = "root";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        String sql = "select * from users";
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                User u = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5),rs.getInt(6),
                        rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11));
                users.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

        public User getUser(int id) {
            String sql = "SELECT * FROM users WHERE id = " + id;
            try {
                statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(sql);
                if (rs.next()) {
                    return new User(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getInt(6),
                            rs.getString(7),
                            rs.getString(8),
                            rs.getString(9),
                            rs.getString(10),
                            rs.getString(11)
                    );
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

        public boolean checkUsername(String username) {
            String sql = "SELECT * FROM users WHERE username = '" + username + "'";
            Statement statement;
            try {
                statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(sql);
                if (rs.next()) return true;
            } catch (SQLException e) {e.printStackTrace();}
            return false;
        }

        public User signIn(String username, String password) {
            User user = null;
            String sql = "SELECT * FROM users WHERE username = " + username + " and password = " + password;
            Statement statement;
            try {
                statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(sql);
                if (rs.next()) {
                    return new User(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getInt(6),
                            rs.getString(7),
                            rs.getString(8),
                            rs.getString(9),
                            rs.getString(10),
                            rs.getString(11)
                    );
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

        public boolean newUser(User user) {
            if (user.getUsername() == null) return false;
            if (user.getPassword() == null) return false;
            boolean exists = checkUsername(user.getUsername());
            if (!exists) {
                String sql = "insert into users values (?,?,?,?,?,?,?,?,?,?,?)";
                try {
                    PreparedStatement st = connection.prepareStatement(sql);
                    st.setInt(1, user.getId());
                    st.setString(2, user.getUsername());
                    st.setString(3, user.getPassword());
                    st.setString(4, user.getFirstName());
                    st.setString(5, user.getLastName());
                    st.setInt(6, user.getRole());
                    st.setString(7, user.getAddress());
                    st.setString(8, user.getCardType());
                    st.setString(9, user.getCardNumber());
                    st.setString(10, user.getCardExpirationDate());
                    st.setString(11, user.getCardCVV());
                    st.executeUpdate();
                    return true;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return false;
        }

        public boolean updatePassword(int id, String password) {
            String sql = "UPDATE users SET password = ? where id = ?";
            try {
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, password);
                statement.setInt(2, id);
                statement.executeUpdate();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return true;
        }

        public boolean deleteUser(int id) {
            String sql = "delete from users where id = ?";
            PreparedStatement preparedStatement;
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
    }
