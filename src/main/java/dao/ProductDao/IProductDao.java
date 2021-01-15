package dao.ProductDao;

import dao.GenericDao.GenericDao;
import model.Product;

import java.util.List;

public interface IProductDao extends GenericDao<Product> {
    List<Product> findAllProduct();

    List<Product> findProductByName(String name);

    void addNewProduct(Product product);

    void deleteProduct(int id);

    void updateProduct(Product product, int id);
}
