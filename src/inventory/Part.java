package inventory;
/**
 * The Part class is an abstract class extended by InHouse and Outsourced
 *
 * @author Ben Garding
 */

import javafx.beans.property.SimpleStringProperty;

public abstract class Part {

    private int idInt;
    private SimpleStringProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty price;
    private SimpleStringProperty stock;
    private int min;
    private int max;


    public Part(int id, String name, double price, int stock, int min, int max) {
        this.idInt = id;
        this.id = new SimpleStringProperty(String.valueOf(id));
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleStringProperty(Inventory.currencyFormatter(price));
        this.stock = new SimpleStringProperty(String.valueOf(stock));
        this.min = min;
        this.max = max;
    }

    /**
     * @return the id
     */
    public int getIdInt() {
        return idInt;
    }

    /**
     * @param idInt the id to set
     */
    public void setIdInt(int idInt) {
        this.idInt = idInt;
    }

    /**
     * @return the String ID
     */
    public String getId() {
        return id.get();
    }

    /**
     * @return the SimpleStringProperty ID
     */
    public SimpleStringProperty idProperty() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id.set(id);
    }

    /**
     * @return the String name
     */
    public String getName() {
        return name.get();
    }

    /**
     * @return the SimpleStringProperty name
     */
    public SimpleStringProperty nameProperty() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name.set(name);
    }

    /**
     * @return the String price
     */
    public String getPrice() {
        return price.get();
    }

    /**
     * @return the SimpleStringProperty price
     */
    public SimpleStringProperty priceProperty() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(String price) {
        this.price.set(price);
    }

    /**
     * @return the String stock
     */
    public String getStock() {
        return stock.get();
    }

    /**
     * @return the SimpleStringProperty stock
     */
    public SimpleStringProperty stockProperty() {
        return stock;
    }

    /**
     * @param stock the stock to set
     */
    public void setStock(String stock) {
        this.stock.set(stock);
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


}