package dao.GenericDao;

import dao.ResultDb.ResultDb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AbstractDao<T> implements GenericDao<T> {
    public String jdbcURL = "jdbc:mysql://localhost:3306/productmanager";
    public String jdbc_USERNAME = "Hoang188";
    public String jdbc_PASSWORD = "hoang188";

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(jdbcURL, jdbc_USERNAME, jdbc_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            return null;
        }
    }

    @Override
    public <T> List<T> query(String sql, ResultDb<T> resultDb, Object... parameters) {
        List<T> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            setParameter(preparedStatement, parameters);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(resultDb.mapRow(resultSet));
            }
            return list;
        } catch (SQLException throwables) {
            return null;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    @Override
    public void update(String sql, Object... parameters) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql);
            setParameter(preparedStatement, parameters);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException throwables) {

            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public int insert(String sql, Object... parameters) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            int id;
            connection = getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            setParameter(preparedStatement, parameters);
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
            connection.commit();
        } catch (SQLException throwables) {

            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return 0;
    }

    private void setParameter(PreparedStatement preparedStatement, Object... parameters) {
        try {
            for (int i = 0; i < parameters.length; i++) {
                Object parameter = parameters[i];
                int index = i + 1;
                if (parameter instanceof Integer) {
                    preparedStatement.setInt(index, (Integer) parameter);
                } else if (parameter instanceof String) {
                    preparedStatement.setString(index, (String) parameter);
                } else if (parameter instanceof Double) {
                    preparedStatement.setDouble(index, (Double) parameter);
                }

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
