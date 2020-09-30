package inventory;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    @FXML
    private Button addPart;

    @FXML
    private TableView<Part> partTable;

    @FXML
    private BorderPane mainPanel;
    @FXML
    private Button exit;

    @FXML
    public void initialize() {
            partTable.setItems(Inventory.getAllParts());
    }

    @FXML
    public void handleAddClick() {
        try {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("partDialog.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Add New Part");
        stage.setScene(scene);
        stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleExit() {
        Platform.exit();
    }
}
