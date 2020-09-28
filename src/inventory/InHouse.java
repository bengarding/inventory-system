package inventory;

import javafx.beans.property.SimpleStringProperty;

public class InHouse extends Part {

    private int machineId;

    private SimpleStringProperty partID;
    private SimpleStringProperty partName;
    private SimpleStringProperty partPrice;
    private SimpleStringProperty partStock;

    public InHouse(int id, String name, double price, int stock, int min, int max) {
        super(id, name, price, stock, min, max);
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

    public int getMachineId() {
        return machineId;
    }

    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}
