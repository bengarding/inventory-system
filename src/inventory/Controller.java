package inventory;

import javafx.application.Platform;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class Controller {

    @FXML
    private TableView<Part> partTable;
    @FXML
    private TableView<Product> productTable;
    @FXML
    private TextField partSearch;
    @FXML
    private TextField productSearch;

    @FXML
    public void initialize() {
        /** Creates sortedLists for search bar functionality */
        FilteredList<Part> filteredPartList = new FilteredList<>(Inventory.getAllParts());
        FilteredList<Product> filteredProductList = new FilteredList<>(Inventory.getAllProducts());

        partSearch.textProperty().addListener((observableValue, s, t1) -> {
            filteredPartList.setPredicate(part -> {
                if (t1 == null || t1.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = t1.toLowerCase();
                String id = String.valueOf(part.getId());

                if (part.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (id.indexOf(t1) != -1) {
                    return true;
                }
                return false;
            });
        });

        productSearch.textProperty().addListener((observableValue, s, t1) -> {
            filteredProductList.setPredicate(product -> {
                if (t1 == null || t1.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = t1.toLowerCase();
                String id = String.valueOf(product.getId());

                if (product.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (id.indexOf(t1) != -1) {
                    return true;
                }
                return false;
            });
        });

        SortedList<Part> sortedPartList = new SortedList<>(filteredPartList);
        sortedPartList.comparatorProperty().bind(partTable.comparatorProperty());
        SortedList<Product> sortedProductList = new SortedList<>(filteredProductList);
        sortedProductList.comparatorProperty().bind(productTable.comparatorProperty());

        partTable.setItems(sortedPartList);
        partTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        productTable.setItems(sortedProductList);
        productTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    /**
     * Opens the add part window
     */
    @FXML
    public void handlePartAddButton() {
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

    /**
     * Opens the modify part window
     */
    @FXML
    public void handlePartModifyButton() {
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
                stage.setTitle("Modify Part");
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
            PartDialog partDialog = fxmlLoader.getController();
            partDialog.modifyPart(selectedPart);
        }
    }

    /**
     * Opens a confirmation alert before deleting a part
     */
    @FXML
    public void handlePartDeleteButton() {
        Part selectedPart = partTable.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Part Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a part to delete");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Part");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete " + selectedPart.getName() + "?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                Alert confirm = new Alert(Alert.AlertType.INFORMATION);
                confirm.setTitle("Confirmation");
                confirm.setHeaderText(null);
                if (Inventory.deletePart(selectedPart)) {
                    confirm.setContentText(selectedPart.getName() + " successfully deleted");
                } else {
                    confirm.setContentText(selectedPart.getName() + " was not deleted");
                }
                confirm.showAndWait();
            }
        }
    }

    /**
     * Opens add product window
     */
    @FXML
    public void handleProductAddButton() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("productDialog.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Add New Product");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens modify product window
     */
    @FXML
    public void handleProductModifyButton() {
        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Product Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a product to modify");
            alert.showAndWait();
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader();

            try {
                fxmlLoader.setLocation(getClass().getResource("productDialog.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setTitle("Modify Product");
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ProductDialog productDialog = fxmlLoader.getController();
            productDialog.modifyProduct(selectedProduct);
        }
    }

    /**
     * Opens a confirmation alert before deleting product
     */
    @FXML
    public void handleProductDeleteButton() {
        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
        boolean confirmDelete;

        if (selectedProduct == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Product Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a product to delete");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Product");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete " + selectedProduct.getName() + "?");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                Alert confirm = new Alert(Alert.AlertType.INFORMATION);
                confirm.setTitle("Confirmation");
                confirm.setHeaderText(null);
                if (Inventory.deleteProduct(selectedProduct)) {
                    confirm.setContentText(selectedProduct.getName() + " successfully deleted");
                } else {
                    confirm.setContentText(selectedProduct.getName() + " was not deleted. Please make sure there are no parts associated with it");
                }
                confirm.showAndWait();
            }
        }
    }

    /**
     * Exits application
     */
    @FXML
    public void handleExit() {
        Platform.exit();
    }
}