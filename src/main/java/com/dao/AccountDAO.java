package com.dao;


import com.main.account.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO implements  IAccountDAO{
    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/doAPI";
            String user = "root";
            String pass = "1234";
            return DriverManager.getConnection(url,user, pass);
        } catch (ClassNotFoundException | SQLException e) {
            return null;
        }
    }

    @Override
    public List<Account> fillAll() {
        List<Account> res = new ArrayList<>();
        String sql = "SELECT * FROM doapi.myaccount";
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        if (connection != null) {
            try {
                statement = connection.prepareStatement(sql);
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Account account = new Account();
                    System.out.println(resultSet.getString("username"));
                    System.out.println(resultSet.getString("password"));
                    account.setUsername(resultSet.getString("username"));
                    account.setPassword(resultSet.getString("password"));
                    res.add(account);
                }
                return res;
            } catch (SQLException e) {
                return null;
            } finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                    if (statement != null) {
                        statement.close();
                    }
                    if (resultSet != null) {
                        resultSet.close();
                    }
                } catch (SQLException e) {
                    return null;
                }
            }
        }
        return null;
    }

    @Override
    public boolean save(Account account) {
        Connection connection = null;
        PreparedStatement statement = null;
        Long id = null;
        ResultSet resultSet = null;
        try {
            String sql = "Insert into doapi.myaccount (username, password) values (?, ?)";
            connection = getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, account.getUsername());
            statement.setString(2, account.getPassword());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getLong(1);
            }
            connection.commit();
            return true;
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            return false;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                return false;
            }
        }
    }

    @Override
    public boolean findOne(Account account) {
        String sql = "SELECT * FROM doapi.myaccount WHERE username = ? and password = ?";
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        if (connection != null) {
            try {
                statement = connection.prepareStatement(sql);
                statement.setString(1, account.getUsername());
                statement.setString(2, account.getPassword());
                resultSet = statement.executeQuery();
                if (!resultSet.next()) {
                    System.out.println("error");
                    return false;
                } else {
                    return true;
                }
            } catch (SQLException e) {
                return false;
            } finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                    if (statement != null) {
                        statement.close();
                    }
                    if (resultSet != null) {
                        resultSet.close();
                    }
                } catch (SQLException e) {
                    return false;
                }
            }
        }
        return false;
    }
}
