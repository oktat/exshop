package views;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Product;

public class ProductTable extends TableView<Product> {
    
    public ProductTable() {
        this.initTable();
    }

    private void initTable() {
        ProductColumn<Product, Integer> idCol = new ProductColumn<>("Az", "id");
        ProductColumn<Product, String> nameCol = new ProductColumn<>("Név", "name");
        ProductColumn<Product, String> itemNumberCol = new ProductColumn<>("Cikkszám", "itemNumber");
        ProductColumn<Product, Integer> countCol = new ProductColumn<>("Darab", "count");
        ProductColumn<Product, Double> priceCol = new ProductColumn<>("Ár", "price");

        // tableView.setItems(this.getProducts());

        this.getColumns().add(idCol);
        this.getColumns().add(nameCol);
        this.getColumns().add(itemNumberCol);
        this.getColumns().add(countCol);
        this.getColumns().add(priceCol);
    }
    
    
}
