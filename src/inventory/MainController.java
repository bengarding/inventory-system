package inventory;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Optional;

public class MainController {
    @FXML
    private Button addPart;

    @FXML
    private TableView<Part> partTable;

    @FXML
    private BorderPane mainPanel;

    @FXML
    public void initialize() {
        if(Inventory.getAllParts() != null) {
//            System.out.println(Inventory.getAllParts().get(0).getPartID());
            partTable.setItems(Inventory.getAllParts());
        }
    }

    @FXML
    public void handleAddClick() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainPanel.getScene().getWindow());
        dialog.setTitle("Add New Part");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("partDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("There was an error");  // FIXME: 9/27/2020
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        Optional<ButtonType> result = dialog.showAndWait();

    }
}
