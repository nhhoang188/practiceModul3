package dao.ResultDb;

import model.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRs implements ResultDb<Product> {
    @Override
    public Product mapRow(ResultSet rs) {
        Product product;
        try {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            double price = rs.getDouble("price");
            int quantity = rs.getInt("quantity");
            String color = rs.getString("color");
            int idcategory = rs.getInt("idcategory");
           return product = new Product(id, name,price,quantity,color,idcategory);

        } catch (SQLException throwables) {
            return null;
        }
    }
}
