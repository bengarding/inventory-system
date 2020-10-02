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
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    private SimpleStringProperty productId;
    private SimpleStringProperty productName;
    private SimpleStringProperty productStock;
    private SimpleStringProperty productPrice;

    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;

        this.productId = new SimpleStringProperty(String.valueOf(id));
        this.productName = new SimpleStringProperty(name);
        this.productStock = new SimpleStringProperty(String.valueOf(stock));
        this.productPrice = new SimpleStringProperty(Inventory.currencyFormatter(price));
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the SimpleStringProperty productId
     */
    public SimpleStringProperty productIdProperty() {
        return productId;
    }

    /**
     * @return the SimpleStringProperty productName
     */
    public SimpleStringProperty productNameProperty() {
        return productName;
    }

    /**
     * @return the SimpleStringProperty productStock
     */
    public SimpleStringProperty productStockProperty() {
        return productStock;
    }

    /**
     * @return the SimpleStringProperty productPrice
     */
    public SimpleStringProperty productPriceProperty() {
        return productPrice;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
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
    public String getProductId() {
        return productId.get();
    }

    /**
     * @return the String productName
     */
    public String getProductName() {
        return productName.get();
    }

    /**
     * @param productName the productName to set
     */
    public void setProductName(String productName) {
        this.productName.set(productName);
    }

    /**
     * @return the String productStock
     */
    public String getProductStock() {
        return productStock.get();
    }

    /**
     * @param productStock the productStock to set
     */
    public void setProductStock(String productStock) {
        this.productStock.set(productStock);
    }

    /**
     * @return the String productPrice
     */
    public String getProductPrice() {
        return productPrice.get();
    }

    /**
     * @param productPrice the productPrice to set
     */
    public void setProductPrice(String productPrice) {
        this.productPrice.set(productPrice);
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