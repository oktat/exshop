package views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import models.ConfigHandler;
import models.DataService;
import models.Product;
import models.api.DataServiceApi;
import models.database.DataServiceDb;
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
        this.tableView = new ProductTable();
        this.tableView.setItems(getProducts());
    }
    private void initData() {
        ConfigHandler conf = new ConfigHandler("Shop.properties");
        String serviceType = conf.getProperty("service.type");
        if (serviceType.equals("mariadb")) {
            dataService = new DataServiceDb(new MariadbDatabase(
                conf.getProperty("database.name"),
                conf.getProperty("database.username"),
                conf.getProperty("database.password")
            ));
        }else {
            dataService = new DataServiceApi();
        }
    }
    private ObservableList<Product> getProducts() {
        ObservableList<Product> productList = 
        FXCollections.observableArrayList(dataService.getProducts());
        return productList;
    }
}
