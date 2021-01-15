package dao.ResultDb;

import java.sql.ResultSet;

public interface ResultDb<T> {
    T mapRow(ResultSet rs);
}
