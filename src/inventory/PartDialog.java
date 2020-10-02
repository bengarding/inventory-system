package inventory;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class PartDialog {

    @FXML
    private Label label;
    @FXML
    private RadioButton inHouse;
    @FXML
    private RadioButton outsourced;
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
    private Label machineIdLabel;
    @FXML
    private TextField machineIdField;
    @FXML
    private Label companyLabel;
    @FXML
    private TextField companyField;
    @FXML
    private Label errorCompany;
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
    private Label errorMachineId;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;

    @FXML
    public void initialize() {
        if (!Inventory.getAllParts().isEmpty()) {
            int newId = Inventory.getAllParts().get(Inventory.getAllParts().size() - 1).getId();
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

        machineIdField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (!t1.matches("\\d{0,7}?")) {
                    machineIdField.setText(s);
                }
            }
        });

        machineIdField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if (aBoolean) {
                    validate(6);
                }
            }
        });

        companyField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if (aBoolean) {
                    validate(7);
                }
            }
        });
    }

    @FXML
    /** Runs input validation on all of the fields, saves the part to Inventory.allParts, then closes the window */
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

        if (Inventory.partExists(id)) {
            int index = Inventory.lookupPartIndex(id);
            if (inHouse.isSelected()) {
                int machineId = Integer.parseInt(machineIdField.getText());
                InHouse newInHouse = new InHouse(id, name, price, stock, min, max, machineId);
                Inventory.updatePart(index, newInHouse);
            } else {
                String company = companyField.getText();
                Outsourced newOutsourced = new Outsourced(id, name, price, stock, min, max, company);
                Inventory.updatePart(index, newOutsourced);
            }
        } else if (inHouse.isSelected()) {
            int machineId = Integer.parseInt(machineIdField.getText());
            InHouse newInHouse = new InHouse(id, name, price, stock, min, max, machineId);
            Inventory.addPart(newInHouse);
        } else {
            String company = companyField.getText();
            Outsourced newOutsourced = new Outsourced(id, name, price, stock, min, max, company);
            Inventory.addPart(newOutsourced);
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
     * Runs when 'Modify' button is clicked in the main window. Loads the part data into the appropriate fields
     */
    public void modifyPart(Part part) {
        idField.setText(String.valueOf(part.getId()));
        nameField.setText(part.getName());
        priceField.setText(String.valueOf(part.getPrice()));
        minField.setText(String.valueOf(part.getMin()));
        maxField.setText(String.valueOf(part.getMax()));
        stockField.setText(String.valueOf(part.getStock()));
        label.setText("Modify Part");
        if (part.getClass() == InHouse.class) {
            machineIdField.setText(String.valueOf(((InHouse) part).getMachineId()));
        } else {
            machineIdField.setVisible(false);
            machineIdLabel.setVisible(false);
            companyField.setVisible(true);
            companyLabel.setVisible(true);
            outsourced.setSelected(true);
            companyField.setText(((Outsourced) part).getCompanyName());
        }

    }

    /**
     * Shows either the Machine ID or Company text fields based on which radio button is selected
     */
    @FXML
    public void selectRadio() {
        if (outsourced.isSelected()) {
            machineIdLabel.setVisible(false);
            machineIdField.setVisible(false);
            machineIdField.clear();
            companyLabel.setVisible(true);
            companyField.setVisible(true);
            errorCompany.setVisible(false);
            errorMachineId.setVisible(false);
        } else {
            machineIdLabel.setVisible(true);
            machineIdField.setVisible(true);
            companyLabel.setVisible(false);
            companyField.setVisible(false);
            companyField.clear();
            errorMachineId.setVisible(false);
            errorCompany.setVisible(false);
        }
    }

    /**
     * Runs validation for all fields
     */
    private boolean validateAll() {
        boolean isValid = true;
        for (int i = 1; i <= 7; i++) {
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
        } else if (field == 6) {
            if (inHouse.isSelected() && machineIdField.getText().isEmpty()) {
                errorMachineId.setVisible(true);
                isValid = false;
            } else {
                errorMachineId.setVisible(false);
                isValid = true;
            }
        } else if (field == 7) {
            if (outsourced.isSelected() && companyField.getText().isEmpty()) {
                errorCompany.setVisible(true);
                isValid = false;
            } else {
                errorCompany.setVisible(false);
                isValid = true;
            }
        }
        return isValid;
    }
}