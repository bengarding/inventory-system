package inventory;
/**
 * The Product class creates a Product
 *
 * @author Ben Garding
 */

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product {

    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int idInt;
    private int min;
    private int max;

    private SimpleStringProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty stock;
    private SimpleStringProperty price;

    public Product(int idInt, String name, double price, int stock, int min, int max) {
        this.idInt = idInt;
        this.min = min;
        this.max = max;

        this.id = new SimpleStringProperty(String.valueOf(idInt));
        this.name = new SimpleStringProperty(name);
        this.stock = new SimpleStringProperty(String.valueOf(stock));
        this.price = new SimpleStringProperty(Inventory.currencyFormatter(price));
    }

    /**
     * @return the id
     */
    public int getIdInt() {
        return idInt;
    }

    /**
     * @return the SimpleStringProperty productId
     */
    public SimpleStringProperty idProperty() {
        return id;
    }

    /**
     * @return the SimpleStringProperty productName
     */
    public SimpleStringProperty nameProperty() {
        return name;
    }

    /**
     * @return the SimpleStringProperty productStock
     */
    public SimpleStringProperty stockProperty() {
        return stock;
    }

    /**
     * @return the SimpleStringProperty productPrice
     */
    public SimpleStringProperty priceProperty() {
        return price;
    }

    /**
     * @return the min
     */
    public int getMin() {
        return min;
    }

    /**
     * @param min the min to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * @return the max
     */
    public int getMax() {
        return max;
    }

    /**
     * @param max the max to set
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * @return the associatedParts
     */
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }

    /**
     * @param associatedParts the associatedParts to set
     */
    public void setAssociatedParts(ObservableList<Part> associatedParts) {
        this.associatedParts = associatedParts;
    }

    /**
     * @return the String productId
     */
    public String getId() {
        return id.get();
    }

    /**
     * @return the String productName
     */
    public String getName() {
        return name.get();
    }

    /**
     * @param name the productName to set
     */
    public void setName(String name) {
        this.name.set(name);
    }

    /**
     * @return the String productStock
     */
    public String getStock() {
        return stock.get();
    }

    /**
     * @param stock the productStock to set
     */
    public void setStock(String stock) {
        this.stock.set(stock);
    }

    /**
     * @return the String productPrice
     */
    public String getPrice() {
        return price.get();
    }

    /**
     * @param price the productPrice to set
     */
    public void setPrice(String price) {
        this.price.set(price);
    }

    /**
     * Adds part to associatedParts
     */
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    /**
     * Deletes associated part if it exists
     */
    public boolean deleteAssociatedPart(Part selectedPart) {
        if (associatedParts.contains(selectedPart)) {
            associatedParts.remove(selectedPart);
            return true;
        }
        return false;
    }
}