package inventory;

import javafx.beans.property.SimpleStringProperty;

public class Outsourced extends Part {

    private String companyName;

    private SimpleStringProperty partID;
    private SimpleStringProperty partName;
    private SimpleStringProperty partPrice;
    private SimpleStringProperty partStock;

    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;

        this.partID = new SimpleStringProperty(Integer.toString(id));
        this.partName = new SimpleStringProperty(name);
        this.partPrice = new SimpleStringProperty(Double.toString(price));
        this.partStock = new SimpleStringProperty(Integer.toString(stock));
    }

    public String getPartID() {
        return partID.get();
    }

    public SimpleStringProperty partIDProperty() {
        return partID;
    }

    public String getPartName() {
        return partName.get();
    }

    public SimpleStringProperty partNameProperty() {
        return partName;
    }

    public String getPartPrice() {
        return partPrice.get();
    }

    public SimpleStringProperty partPriceProperty() {
        return partPrice;
    }

    public String getPartStock() {
        return partStock.get();
    }

    public SimpleStringProperty partStockProperty() {
        return partStock;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
