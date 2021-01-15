package dao.GenericDao;

import dao.ResultDb.ResultDb;

import java.util.List;

public interface GenericDao<T> {
    <T> List<T> query(String sql, ResultDb<T> resultDb, Object... parameters);
    void update(String sql, Object... parameters);
    int insert(String sql, Object... parameters);
}