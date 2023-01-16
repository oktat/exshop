package models.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.DataService;
import models.Database;
import models.Product;

public class DataServiceDb implements DataService {
    Database database;
    Connection con;
    public DataServiceDb(Database database) {
        this.database = database;
        con = this.database.connect();
    }
    public ArrayList<Product> getProducts() {
        ArrayList<Product> productList = null;
        try {
            productList = tryGetProducts();
        } catch (SQLException e) {
            System.err.println("Hiba! A termékek lekérdezése sikertelen!");
        }
        return productList;
    }
    public ArrayList<Product> tryGetProducts() throws SQLException {
        String sql = "select * from products";
        Statement statement = this.con.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        ArrayList<Product> productList = new ArrayList<>();
        while(resultSet.next()) {
            Product product = new Product();
            product.setId(resultSet.getInt("id"));
            product.setName(resultSet.getString("name"));
            product.setItemNumber(resultSet.getString("itemNumber"));
            product.setCount(resultSet.getInt("count"));
            product.setPrice(resultSet.getDouble("price"));
            productList.add(product);
        }
        return productList;        
    }
}
