package inventory;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
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
        partTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    @FXML
    public void handlePartAddClick() {
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
    public void handlePartModifyClick() {
        Part selectedPart = partTable.getSelectionModel().getSelectedItem();
        if (selectedPart == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Part Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a part to modify");
            alert.showAndWait();
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader();
            try {
                fxmlLoader.setLocation(getClass().getResource("partDialog.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setTitle("Add New Part");
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
            PartDialog partDialog = fxmlLoader.getController();
            partDialog.modifyPart(selectedPart);
        }
//        System.out.println(((InHouse) selectedPart).getMachineId());

    }

    @FXML
    public void handleExit() {
        Platform.exit();
    }
}
