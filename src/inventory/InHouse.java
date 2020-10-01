package inventory;

import javafx.beans.property.SimpleStringProperty;

public class InHouse extends Part {

    private int machineId;

    private SimpleStringProperty partId;
    private SimpleStringProperty partName;
    private SimpleStringProperty partPrice;
    private SimpleStringProperty partStock;

    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;

        this.partId = new SimpleStringProperty(Integer.toString(id));
        this.partName = new SimpleStringProperty(name);
        this.partPrice = new SimpleStringProperty(Inventory.currencyFormatter(price));
        this.partStock = new SimpleStringProperty(Integer.toString(stock));
    }

    public String getPartId() {
        return partId.get();
    }

    public SimpleStringProperty partIdProperty() {
        return partId;
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

    public void setPartId(String partId) {
        this.partId.set(partId);
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

    public int getMachineId() {
        return machineId;
    }

    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}
