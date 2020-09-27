package inventory;

import javafx.collections.ObservableList;

public class Inventory {

    private static ObservableList<Part> allParts;
    private static ObservableList<Product> allProducts;

    public static void addPart(Part newPart) {
        /** Code here */
    }

    public static void addProduct(Product newProduct) {
        /** Code here */
    }

    public static Part lookupPart(int partId) {
        /** Code here */
        return null;
    }

    public static Product lookupProduct(int productID) {
        /** Code here */
        return null;
    }

    public static ObservableList<Part> lookupPart(String partName) {
        /** Code here */
        return null;
    }

    public static ObservableList<Product> lookupProduct(String productName) {
        /** Code here */
        return null;
    }

    public static void updatePart(int index, Part selectedPart){
        /** Code here */
    }

    public static void updateProduct(int index, Product product) {
        /** Code here */
    }

    public static boolean deletePart(Part selectedPart) {
        /** Code here */
        return true;
    }

    public static boolean deleteProduct(Product selectedProduct) {
        /** Code here */
        return true;
    }

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}