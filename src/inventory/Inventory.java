package inventory;
/**
 * The Inventory class controls the lists of parts and products
 *
 * @author Ben Garding
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.text.NumberFormat;

public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     * Adds a new Part to allParts
     *
     * @param newPart the Part to be added
     */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /**
     * Adds a new Product to allProducts
     *
     * @param newProduct the Product to be added
     */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /**
     * Returns a Part found by part ID
     *
     * @param partId the part ID to be searched with
     * @return the Part, if found
     */
    public static Part lookupPart(int partId) {
        for (int i = 0; i < allParts.size(); i++) {
            if (allParts.get(i).getId() == partId) {
                return allParts.get(i);
            }
        }
        return null;
    }

    /**
     * Returns the index of a Part in allParts
     *
     * @param partId the Part ID to be searched with
     * @return the index of the Part
     */
    public static int lookupPartIndex(int partId) {
        for (int i = 0; i < allParts.size(); i++) {
            if (allParts.get(i).getId() == partId) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns the index of a Product in allProducts
     *
     * @param partId the Product ID to be searched with
     * @return the index of the Product
     */
    public static int lookupProductIndex(int partId) {
        for (int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getId() == partId) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Checks if a Part exists in allParts
     *
     * @param partId the Part ID to be searched with
     * @return boolean if the Part is found or not
     */
    public static boolean partExists(int partId) {
        if (allParts.isEmpty()) {
            return false;
        }
        for (int i = 0; i < allParts.size(); i++) {
            if (allParts.get(i).getId() == partId) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a Product exists in allProducts
     *
     * @param productId the Product ID to be searched with
     * @return boolean if the Product is found or not
     */
    public static boolean productExists(int productId) {
        if (allProducts.isEmpty()) {
            return false;
        }
        for (int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getId() == productId) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a Product if found by Product ID
     *
     * @param productId the Product ID to be searched with
     * @return the Product if found
     */
    public static Product lookupProduct(int productId) {
        for (int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getId() == productId) {
                return allProducts.get(i);
            }
        }
        return null;
    }

    /**
     * Returns allParts if allParts contains a Part name
     *
     * @param partName the Part name to be searched with
     * @return allParts if Part is found
     */
    public static ObservableList<Part> lookupPart(String partName) {
        for (int i = 0; i < allParts.size(); i++) {
            if (allParts.get(i).getName() == partName) {
                return allParts;
            }
        }
        return null;
    }

    /**
     * Returns allProducts if allProducts contains a Product name
     *
     * @param productName the Product name to be searched with
     * @return allProducts if the Product is found
     */
    public static ObservableList<Product> lookupProduct(String productName) {
        for (int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getName() == productName) {
                return allProducts;
            }
        }
        return null;
    }

    /**
     * Updates a Part in allParts
     *
     * @param index        the index of the Part in allParts
     * @param selectedPart the Part to be updated
     */
    public static void updatePart(int index, Part selectedPart) {
        allParts.get(index).setName(selectedPart.getName());
        allParts.get(index).setPrice(selectedPart.getPrice());
        allParts.get(index).setMin(selectedPart.getMin());
        allParts.get(index).setMax(selectedPart.getMax());
        allParts.get(index).setStock(selectedPart.getStock());

        if (selectedPart.getClass() == InHouse.class) {
            ((InHouse) allParts.get(index)).setMachineId(((InHouse) selectedPart).getMachineId());
            ((InHouse) allParts.get(index)).setPartName(((InHouse) selectedPart).getPartName());
            ((InHouse) allParts.get(index)).setPartPrice(((InHouse) selectedPart).getPartPrice());
            ((InHouse) allParts.get(index)).setPartStock(((InHouse) selectedPart).getPartStock());
        } else {
            ((Outsourced) allParts.get(index)).setCompanyName(((Outsourced) selectedPart).getCompanyName());
            ((Outsourced) allParts.get(index)).setPartName(((Outsourced) selectedPart).getPartName());
            ((Outsourced) allParts.get(index)).setPartPrice(((Outsourced) selectedPart).getPartPrice());
            ((Outsourced) allParts.get(index)).setPartStock(((Outsourced) selectedPart).getPartStock());
        }
    }

    /**
     * Updates a Product in allProducts
     *
     * @param index      the index of the Product in allProducts
     * @param newProduct the Product to be updated
     */
    public static void updateProduct(int index, Product newProduct) {
        allProducts.get(index).setName(newProduct.getName());
        allProducts.get(index).setPrice(newProduct.getPrice());
        allProducts.get(index).setMin(newProduct.getMin());
        allProducts.get(index).setMax(newProduct.getMax());
        allProducts.get(index).setStock(newProduct.getStock());
        allProducts.get(index).setAssociatedParts(newProduct.getAllAssociatedParts());

        allProducts.get(index).setProductName(newProduct.getName());
        allProducts.get(index).setProductPrice(currencyFormatter(newProduct.getPrice()));
        allProducts.get(index).setProductStock(String.valueOf(newProduct.getStock()));
    }

    /**
     * Deletes a Part from allParts if it exists
     *
     * @param selectedPart the Part to be deleted
     * @return boolean if the Part was deleted or not
     */
    public static boolean deletePart(Part selectedPart) {
        if (allParts.contains(selectedPart)) {
            allParts.remove(selectedPart);
            return true;
        }
        return false;
    }

    /**
     * Deletes a Product from allProducts if it exits and does not have any associated parts
     *
     * @param selectedProduct the Product to be deleted
     * @return boolean if the Product was deleted or not
     */
    public static boolean deleteProduct(Product selectedProduct) {
        if (selectedProduct.getAllAssociatedParts().isEmpty()) {
            if (allProducts.contains(selectedProduct)) {
                allProducts.remove(selectedProduct);
                return true;
            }
        }
        return false;
    }

    /**
     * @return allParts
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * @return allProducts
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    /**
     * Formats a double into a currency String
     *
     * @param price the price to be formatted
     * @return the formatted price
     */
    public static String currencyFormatter(double price) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return formatter.format(price);
    }
}