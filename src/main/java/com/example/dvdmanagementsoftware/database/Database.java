package com.example.dvdmanagementsoftware.database;

import com.example.dvdmanagementsoftware.dvd.DVD;
import com.example.dvdmanagementsoftware.order.Order;
import com.example.dvdmanagementsoftware.shoppingcard.ShoppingCard;
import com.example.dvdmanagementsoftware.users.Token;
import com.example.dvdmanagementsoftware.users.User;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

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
                        rs.getString(5), rs.getString(6),
                        rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12));
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
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12)
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public User signIn(String username, String password) throws NoSuchAlgorithmException {
        User user = new User();
        String sql = "SELECT * FROM users WHERE username = '" + username + "' and password = '" + password + "'";
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                 user = new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                         rs.getString(12)
                );
            }

            if (user.getId()>0){
                Token tokenGiven = new Token(username, password);
                user.setToken(tokenGiven.getToken());
                this.updateToken(user.getId(), user.getToken());
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserFromToken(String token) {
        User user = new User();
        String sql = "SELECT * FROM users WHERE token = '" + token + "'";
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                user = new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12)
                );
            }

            if (user.getId()>0){
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateToken(int id, String token) {
        String sql = "update users set token = '" + token + "' where id = " + id;
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean newUser(User user) {
        if (user.getUsername() == null) return false;
        if (user.getPassword() == null) return false;
        boolean exists = checkUsername(user.getUsername());
        if (exists) return false;

        String sql = "insert into users values (?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, null);
            st.setString(2, user.getUsername());
            st.setString(3, user.getPassword());
            st.setString(4, user.getFirstName());
            st.setString(5, user.getLastName());
            st.setString(6, user.getRole());
            st.setString(7, user.getAddress());
            st.setString(8, user.getCardType());
            st.setString(9, user.getCardNumber());
            st.setString(10, user.getCardExpirationDate());
            st.setString(11, user.getCardCVV());
            st.setString(12, "");
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
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
        return false;
    }

    public boolean deleteUser(int id) {
        String sql = "delete from users where id = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            int rows = preparedStatement.executeUpdate();
            if (rows > 0) return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public ArrayList<DVD> getDVDS() {
        ArrayList<DVD> dvds = new ArrayList<>();
        String sql = "select * from dvd";
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                DVD d = new DVD(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getInt(6),
                        rs.getString(7), rs.getString(8), rs.getString(9), rs.getDouble(10), rs.getInt(11));
                dvds.add(d);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dvds;
    }

    public DVD getDVD(int id) {
        String sql = "SELECT * FROM dvd WHERE id = " + id;
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                return new DVD(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getDouble(10),
                        rs.getInt(11)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public DVD getDVDByTitle(String title) {
        String sql = "SELECT * FROM dvd WHERE title = '" + title + "'";
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                return new DVD(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getDouble(10),
                        rs.getInt(11)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean newDVD(DVD dvd) {
        String sql = "insert into dvd values (?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, null);
            st.setString(2, dvd.getTitle());
            st.setString(3, dvd.getActors());
            st.setString(4, dvd.getDirector());
            st.setString(5, dvd.getProduceDate());
            st.setInt(6, dvd.getDuration());
            st.setString(7, dvd.getLanguages());
            st.setString(8, dvd.getSubtitles());
            st.setString(9, dvd.getCategory());
            st.setDouble(10, dvd.getPrice());
            st.setInt(11, dvd.getUnits());
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteDVD(int id) {
        String sql = "delete from dvd where id = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            int row = preparedStatement.executeUpdate();
            if(row > 0) return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public ArrayList<ShoppingCard> getShoppingCards() {
        ArrayList<ShoppingCard> shoppingCards = new ArrayList<>();
        String sql = "select * from shopping_card";
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                ShoppingCard s = new ShoppingCard(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6)
                );
                shoppingCards.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shoppingCards;
    }

    public ShoppingCard getShoppingCard(int id) {
        String sql = "SELECT * FROM shopping_card WHERE id = " + id;
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                return new ShoppingCard(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean deleteShoppingCard(int id) {
        String sql = "delete from shopping_card where id = ?";
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

    public boolean newShoppingCard(ShoppingCard shoppingCard) {
        String sql = "insert into shopping_card values (?,?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, shoppingCard.getId());
            st.setInt(2, shoppingCard.getUserId());
            st.setInt(3, shoppingCard.getDvd_id());
            st.setInt(4, shoppingCard.getAmount());
            st.setString(5, shoppingCard.getCreationDate());
            st.setString(6, shoppingCard.getState());
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean changeStateShoppingCard(int id, String state) {
        String sql = "UPDATE shopping_card SET state = ? WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, state);
            statement.setInt(2, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Order> getOrders() {
        ArrayList<Order> orders = new ArrayList<>();
        String sql = "select * from orders";
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Order o = new Order(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8)
                );
                orders.add(o);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public Order getOrder(int id) {
        String sql = "SELECT * FROM orders WHERE id = " + id;
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                return new Order(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean newOrder(Order order) {
        String sql = "insert into orders values (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, order.getId());
            st.setString(2, order.getAddress());
            st.setInt(3, order.getShoppingCardId());
            st.setInt(4, order.getUserId());
            st.setString(5, order.getState());
            st.setString(6, order.getCreationDate());
            st.setString(7, order.getCompletedDate());
            st.setString(8, order.getDvdIds());
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean changeStateOrder(int id, String state) {
        String sql = "UPDATE orders SET state = ? WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, state);
            statement.setInt(2, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean authenticate(String givenToken, String canBePerformedBy, String userId) {
        if (givenToken == null || givenToken.equals("")) return false;
        User user = this.getUserFromToken(givenToken);
        if (user == null) return false;
        String[] roles = canBePerformedBy.split("/");
        String userRole = user.getRole();
        if(userRole.equals("ADMIN")) return true;

        if(userRole.equals("EMPLOYEE") && this.findRole(roles, "EMPLOYEE")) return true;

        return userRole.equals("CUSTOMER") && this.findRole(roles, "CUSTOMER") && user.getId() == Integer.parseInt(userId);
    }

    private boolean findRole(String[] roles, String needle) {
        for (String role : roles) {
            if (role.equals(needle)) return true;
        }
        return false;
    }
}
