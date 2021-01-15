package dao.CategoryDao;

import dao.GenericDao.GenericDao;
import model.Category;
import model.Product;

import java.util.List;

public interface ICategoryDao extends GenericDao<Category> {
    List<Category> findAllCategory();

    List<Category> findCategoryInfoByName(String name);

    void addNewCategory(Product product);

    void deleteCategory(int id);

    void updateCategory(Product product, int id);
}
