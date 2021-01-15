package dao.ResultDb;

import model.Category;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryRs implements ResultDb<Category> {
    @Override
    public Category mapRow(ResultSet rs) {
        Category category;
        try {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            return category = new Category(id, name);
        } catch (SQLException throwables) {
            return null;
        }

    }
}
