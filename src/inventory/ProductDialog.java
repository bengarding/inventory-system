package inventory;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class ProductDialog {

    @FXML
    private Label label;
    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField stockField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField minField;
    @FXML
    private TextField maxField;
    @FXML
    private Label errorName;
    @FXML
    private Label errorStock;
    @FXML
    private Label errorPrice;
    @FXML
    private Label errorMin;
    @FXML
    private Label errorMax;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;
    @FXML
    private TableView<Part> partTable;
    @FXML
    private TableView<Part> associatedPartTable;
    @FXML
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    @FXML
    private TextField partSearch;

    @FXML
    public void initialize() {
        /** Creates sortedList for search bar functionality */
        FilteredList<Part> filteredPartList = new FilteredList<>(Inventory.getAllParts());

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

        SortedList<Part> sortedPartList = new SortedList<>(filteredPartList);
        sortedPartList.comparatorProperty().bind(partTable.comparatorProperty());

        partTable.setItems(sortedPartList);
        partTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        associatedPartTable.setItems(associatedParts);
        associatedPartTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        if (!Inventory.getAllProducts().isEmpty()) {
            int newId = Inventory.getAllProducts().get(Inventory.getAllProducts().size() - 1).getId();
            newId++;
            idField.setText(String.valueOf(newId));
        } else {
            idField.setText("1");
        }

        /** The following code adds listeners to each text field for input validation.
         * Listeners are also added to text fields that require numerical inputs so only numbers can be entered. */

        nameField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if (aBoolean) {
                    validate(1);
                }
            }
        });

        priceField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (!t1.matches("\\d{0,7}([\\.]\\d{0,2})?")) {
                    priceField.setText(s);
                }
            }
        });

        priceField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if (aBoolean) {
                    validate(2);
                }
            }
        });

        minField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (!t1.matches("\\d{0,7}?")) {
                    minField.setText(s);
                }
            }
        });

        minField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if (aBoolean) {
                    validate(3);
                }
            }
        });

        maxField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (!t1.matches("\\d{0,7}?")) {
                    maxField.setText(s);
                }
            }
        });

        maxField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if (aBoolean) {
                    validate(4);
                }
            }
        });

        stockField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (!t1.matches("\\d{0,7}?")) {
                    stockField.setText(s);
                }
            }
        });

        stockField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if (aBoolean) {
                    validate(5);
                }
            }
        });
    }

    /**
     * Validates each field and saves the product to Inventory.allProducts
     */
    @FXML
    public void handleSaveButton() {
        if (!validateAll()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Part");
            alert.setHeaderText(null);
            alert.setContentText("Please make sure all of the fields are filled out correctly");
            alert.showAndWait();
            return;
        }

        int id = Integer.parseInt(idField.getText());
        String name = nameField.getText();
        int stock = Integer.parseInt(stockField.getText());
        float price = Float.parseFloat(priceField.getText());
        int min = Integer.parseInt(minField.getText());
        int max = Integer.parseInt(maxField.getText());

        Product newProduct = new Product(id, name, price, stock, min, max);
        newProduct.setAssociatedParts(associatedParts);

        if (Inventory.productExists(id)) {
            Inventory.updateProduct(Inventory.lookupProductIndex(id), newProduct);
        } else {
            Inventory.addProduct(newProduct);
        }

        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Closes the window without saving
     */
    @FXML
    public void handleCancelButton() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Takes the selected part from the ViewTable and adds it to associatedParts
     */
    @FXML
    public void handleAddPartsButton() {
        Part selectedPart = partTable.getSelectionModel().getSelectedItem();
        if (associatedParts.contains(selectedPart)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Part Already Associated");
            alert.setHeaderText(null);
            alert.setContentText("This part is already associated with this product");
            alert.showAndWait();
            return;
        }
        associatedParts.add(selectedPart);
    }

    /**
     * Removes the selected part from associatedParts
     */
    @FXML
    public void handleRemovePartsButton() {
        Part selectedPart = associatedPartTable.getSelectionModel().getSelectedItem();
        associatedParts.remove(selectedPart);
    }

    /**
     * Runs when 'Modify' button is clicked in the main window. Loads the product data into the appropriate fields
     */
    public void modifyProduct(Product product) {
        idField.setText(String.valueOf(product.getId()));
        nameField.setText(product.getName());
        priceField.setText(String.valueOf(product.getPrice()));
        minField.setText(String.valueOf(product.getMin()));
        maxField.setText(String.valueOf(product.getMax()));
        stockField.setText(String.valueOf(product.getStock()));
        if (!product.getAllAssociatedParts().isEmpty()) {
            associatedParts.setAll(product.getAllAssociatedParts());
        }
        label.setText("Modify Product");
    }

    /**
     * Runs validation for all fields
     */
    private boolean validateAll() {
        boolean isValid = true;
        for (int i = 1; i <= 5; i++) {
            if (!validate(i)) {
                isValid = false;
            }
        }
        return isValid;
    }

    /**
     * Validates each field
     */
    private boolean validate(int field) {
        boolean isValid = false;

        if (field == 1) {
            if (nameField.getText().isEmpty()) {
                errorName.setVisible(true);
                isValid = false;
            } else {
                errorName.setVisible(false);
                isValid = true;
            }
        } else if (field == 2) {
            if (priceField.getText().isEmpty()) {
                errorPrice.setVisible(true);
                isValid = false;
            } else {
                errorPrice.setVisible(false);
                isValid = true;
            }
        } else if (field == 3) {
            if (minField.getText().isEmpty() ||
                    (!maxField.getText().isEmpty() && Integer.parseInt(minField.getText()) > Integer.parseInt(maxField.getText()))) {
                errorMin.setVisible(true);
                isValid = false;
            } else {
                errorMin.setVisible(false);
                isValid = true;
            }
        } else if (field == 4) {
            if (maxField.getText().isEmpty() ||
                    (!minField.getText().isEmpty() && Integer.parseInt(minField.getText()) > Integer.parseInt(maxField.getText()))) {
                errorMax.setVisible(true);
                isValid = false;
            } else {
                errorMax.setVisible(false);
                isValid = true;
            }
        } else if (field == 5) {
            if (stockField.getText().isEmpty() ||
                    ((!minField.getText().isEmpty() || !maxField.getText().isEmpty()) && (Integer.parseInt(minField.getText()) > Integer.parseInt(stockField.getText()) ||
                            Integer.parseInt(maxField.getText()) < Integer.parseInt(stockField.getText())))) {
                errorStock.setVisible(true);
                isValid = false;
            } else {
                errorStock.setVisible(false);
                isValid = true;
            }
        }
        return isValid;
    }
}
