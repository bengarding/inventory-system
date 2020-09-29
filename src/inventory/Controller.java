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
//        Dialog<ButtonType> dialog = new Dialog<>();
//        dialog.initOwner(mainPanel.getScene().getWindow());
//        dialog.setTitle("Add New Part");
//        FXMLLoader fxmlLoader = new FXMLLoader();
//        fxmlLoader.setLocation(getClass().getResource("partDialog.fxml"));
//        try {
//            dialog.getDialogPane().setContent(fxmlLoader.load());
//        } catch (IOException e) {
//            System.out.println("There was an error");  // FIXME: 9/27/2020
//        }
//        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
//        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
//        Optional<ButtonType> result = dialog.showAndWait();
//
//        PartDialog partController = fxmlLoader.getController();
//        boolean isValid = partController.validate(1);
//        if (result.isPresent() && result.get() == ButtonType.OK && isValid) {
//            System.out.println("saved");
//        } else {
//
//        }
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
