package views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import models.ConfigHandler;
import models.Product;
import models.database.DataService;
import models.database.MariadbDatabase;

public class MainView extends VBox{
    Label productLabel;
    DataService dataService;
    TableView<Product> tableView;
    public MainView() {
        productLabel = new Label("Termékek");
        
        this.initData();
        this.initTable();
        this.getChildren().add(productLabel);
        this.getChildren().add(tableView);
    }
    private void initTable() {
        tableView = new TableView<>();
        
        TableColumn<Product, Integer> idCol = new TableColumn<>("Az");
        idCol.setMinWidth(50);
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        
        TableColumn<Product, String> nameCol = new TableColumn<>("Név");
        nameCol.setMinWidth(100);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        TableColumn<Product, String> itemNumberCol = new TableColumn<>("Cikkszám");
        itemNumberCol.setMinWidth(50);
        itemNumberCol.setCellValueFactory(new PropertyValueFactory<>("itemNumber"));

        TableColumn<Product, Integer> countCol = new TableColumn<>("Darab");
        countCol.setMinWidth(50);
        countCol.setCellValueFactory(new PropertyValueFactory<>("count"));
        
        TableColumn<Product, Double> priceCol = new TableColumn<>("Ár");
        priceCol.setMinWidth(50);
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        tableView.setItems(this.getProducts());

        tableView.getColumns().add(idCol);
        tableView.getColumns().add(nameCol);
        tableView.getColumns().add(itemNumberCol);
        tableView.getColumns().add(countCol);
        tableView.getColumns().add(priceCol);
        
    }
    private ObservableList<Product> getProducts() {
        ObservableList<Product> productList = 
        FXCollections.observableArrayList(dataService.getProducts());
        return productList;
    }
    private void initData() {
        ConfigHandler conf = new ConfigHandler("Shop.config");

        System.out.printf("%s\n%s\n%s\n",
        conf.getProperty("database.name"),
        conf.getProperty("database.username"),
        conf.getProperty("database.password")        
        );

        dataService = new DataService(new MariadbDatabase(
            conf.getProperty("database.name"),
            conf.getProperty("database.username"),
            conf.getProperty("database.password")
        ));
        // ArrayList<Product> productList = dataService.getProducts();
        // System.out.println(productList.get(0).getName());
    }
    
}
