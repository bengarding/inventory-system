package inventory;

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
     * Getters and Setters
     */

    public int getId() {
        return id;
    }

    public SimpleStringProperty productIdProperty() {
        return productId;
    }

    public SimpleStringProperty productNameProperty() {
        return productName;
    }

    public SimpleStringProperty productStockProperty() {
        return productStock;
    }

    public SimpleStringProperty productPriceProperty() {
        return productPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }

    public void setAssociatedParts(ObservableList<Part> associatedParts) {
        this.associatedParts = associatedParts;
    }

    public String getProductId() {
        return productId.get();
    }

    public void setProductId(String productId) {
        this.productId.set(productId);
    }

    public String getProductName() {
        return productName.get();
    }

    public void setProductName(String productName) {
        this.productName.set(productName);
    }

    public String getProductStock() {
        return productStock.get();
    }

    public void setProductStock(String productStock) {
        this.productStock.set(productStock);
    }

    public String getProductPrice() {
        return productPrice.get();
    }

    public void setProductPrice(String productPrice) {
        this.productPrice.set(productPrice);
    }

    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    public boolean deleteAssociatedPart(Part selectedPart) {
        if (associatedParts.contains(selectedPart)) {
            associatedParts.remove(selectedPart);
            return true;
        }
        return false;
    }
}