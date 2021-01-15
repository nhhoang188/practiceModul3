package dao.CategoryDao;

import dao.GenericDao.AbstractDao;
import dao.ResultDb.CategoryRs;
import model.Category;
import model.Product;

import java.util.List;

public class CategoryDao extends AbstractDao<Category> implements ICategoryDao {
    static String SHOW_ALL_CATEGORY = "SELECT * FROM category;";
    static String SHOW_CATEGORY_BY_NAME = "SELECT * FROM category WHERE LIKE NAME = ?;";
    static String ADD_NEW_CATEGORY = "INSERT INTO CATEGORY (NAME) VALUES (?) ";
    static String DELETE_CATEGORY = "delete from CATEGORY where id = ?;";
    static String UPDATE_CATEGORY = "update user set name = ? where id = ?;";

    @Override
    public List<Category> findAllCategory() {
        return query(SHOW_ALL_CATEGORY, new CategoryRs());
    }

    @Override
    public List<Category> findCategoryInfoByName(String name) {
        return query(SHOW_CATEGORY_BY_NAME, new CategoryRs(), name);
    }

    @Override
    public void addNewCategory(Product product) {
        update(ADD_NEW_CATEGORY, product.getName());
    }

    @Override
    public void deleteCategory(int id) {
        update(DELETE_CATEGORY, id);
    }

    @Override
    public void updateCategory(Product product, int id) {
        update(UPDATE_CATEGORY, id);
    }
}
