package views;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class ProductColumn<S, T> extends TableColumn<S, T> {

    public ProductColumn(String colTitle, String colName) {
        super(colTitle);
        this.setMinWidth(50);        
        this.setCellValueFactory(new PropertyValueFactory<>(colName));
    }
    
}
