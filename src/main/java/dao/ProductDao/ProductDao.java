package dao.ProductDao;

import dao.GenericDao.AbstractDao;
import dao.ResultDb.ProductRs;
import model.Product;

import java.util.List;

public class ProductDao extends AbstractDao<Product> implements IProductDao{
    static String SHOW_ALL_PRODUCT = "SELECT * FROM PRODUCT;";
    static String SHOW_PRODUCT_BY_NAME = "SELECT * FROM PRODUCT WHERE LIKE NAME = ?;";
    static String ADD_NEW_PRODUCT = "INSERT INTO PRODUCT (NAME, PRICE, QUANTITY, COLOR, IDCATEGORY) VALUES (?,?,?,?,?) ";
    static String DELETE_PRODUCT = "delete from PRODUCT where id = ?;";
    static String UPDATE_PRODUCT = "update PRODUCT set name = ?, PRICE = ?, QUANTITY = ?, COLOR = ? where id = ?;";
    @Override
    public List<Product> findAllProduct() {
        return query(SHOW_ALL_PRODUCT, new ProductRs());
    }

    @Override
    public List<Product> findProductByName(String name) {
        return query(SHOW_PRODUCT_BY_NAME,new ProductRs(),name);
    }

    @Override
    public void addNewProduct(Product product) {
        update(ADD_NEW_PRODUCT, product.getName(), product.getPrice(),product.getQuantity(),product.getColor(), product.getCategory());
    }

    @Override
    public void deleteProduct(int id) {
    update(DELETE_PRODUCT, id);
    }

    @Override
    public void updateProduct(Product product, int id) {
        update(UPDATE_PRODUCT, product.getName(), product.getPrice(),product.getQuantity(),product.getColor(), id);
    }
}
