package inventory;
/**
 * The InHouse class creates an Outsourced part
 *
 * @author Ben Garding
 */

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
     * @return the String partId
     */
    public String getPartId() {
        return partId.get();
    }

    /**
     * @return the SimpleStringProperty partId
     */
    public SimpleStringProperty partIdProperty() {
        return partId;
    }

    /**
     * @return the SimpleStringProperty partName
     */
    public SimpleStringProperty partNameProperty() {
        return partName;
    }

    /**
     * @return the SimpleStringProperty partPrice
     */
    public SimpleStringProperty partPriceProperty() {
        return partPrice;
    }

    /**
     * @return the SimpleStringProperty
     */
    public SimpleStringProperty partStockProperty() {
        return partStock;
    }

    /**
     * @return the String partName
     */
    public String getPartName() {
        return partName.get();
    }

    /**
     * @return the String partPrice
     */
    public String getPartPrice() {
        return partPrice.get();
    }

    /**
     * @return the String partStock
     */
    public String getPartStock() {
        return partStock.get();
    }

    /**
     * @param partName the partName to set
     */
    public void setPartName(String partName) {
        this.partName.set(partName);
    }

    /**
     * @param partPrice the partPrice to set
     */
    public void setPartPrice(String partPrice) {
        this.partPrice.set(partPrice);
    }

    /**
     * @param partStock the partStock to set
     */
    public void setPartStock(String partStock) {
        this.partStock.set(partStock);
    }

    /**
     * @return the String companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName the companyName to set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
