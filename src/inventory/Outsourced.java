package inventory;

import javafx.beans.property.SimpleStringProperty;

public class Outsourced extends Part {

    private String companyName;

    private SimpleStringProperty partId;
    private SimpleStringProperty partName;
    private SimpleStringProperty partPrice;
    private SimpleStringProperty partStock;

    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;

        this.partId = new SimpleStringProperty(Integer.toString(id));
        this.partName = new SimpleStringProperty(name);
        this.partPrice = new SimpleStringProperty(Inventory.currencyFormatter(price));
        this.partStock = new SimpleStringProperty(Integer.toString(stock));
    }

    /**
     * Getters and Setters
     */

    public String getPartId() {
        return partId.get();
    }

    public SimpleStringProperty partIdProperty() {
        return partId;
    }

    public SimpleStringProperty partNameProperty() {
        return partName;
    }

    public SimpleStringProperty partPriceProperty() {
        return partPrice;
    }

    public SimpleStringProperty partStockProperty() {
        return partStock;
    }

    public String getPartName() {
        return partName.get();
    }

    public String getPartPrice() {
        return partPrice.get();
    }

    public String getPartStock() {
        return partStock.get();
    }

    public void setPartName(String partName) {
        this.partName.set(partName);
    }

    public void setPartPrice(String partPrice) {
        this.partPrice.set(partPrice);
    }

    public void setPartStock(String partStock) {
        this.partStock.set(partStock);
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
