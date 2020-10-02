package inventory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.text.NumberFormat;

public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    public static Part lookupPart(int partId) {
        for (int i = 0; i < allParts.size(); i++) {
            if (allParts.get(i).getId() == partId) {
                return allParts.get(i);
            }
        }
        return null;
    }

    public static int lookupPartIndex(int partId) {
        for (int i = 0; i < allParts.size(); i++) {
            if (allParts.get(i).getId() == partId) {
                return i;
            }
        }
        return -1;
    }

    public static int lookupProductIndex(int partId) {
        for (int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getId() == partId) {
                return i;
            }
        }
        return -1;
    }

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

    public static boolean productExists(int partId) {
        if (allProducts.isEmpty()) {
            return false;
        }
        for (int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getId() == partId) {
                return true;
            }
        }
        return false;
    }

    public static Product lookupProduct(int productId) {
        for (int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getId() == productId) {
                return allProducts.get(i);
            }
        }
        return null;
    }

    public static ObservableList<Part> lookupPart(String partName) {
        for (int i = 0; i < allParts.size(); i++) {
            if (allParts.get(i).getName() == partName) {
                return allParts;
            }
        }
        return null;
    }

    public static ObservableList<Product> lookupProduct(String productName) {
        for (int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getName() == productName) {
                return allProducts;
            }
        }
        return null;
    }

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

    public static void updateProduct(int index, Product newProduct) {
        allProducts.get(index).setName(newProduct.getName());
        allProducts.get(index).setPrice(newProduct.getPrice());
        allProducts.get(index).setMin(newProduct.getMin());
        allProducts.get(index).setMax(newProduct.getMax());
        allProducts.get(index).setStock(newProduct.getStock());
        allProducts.get(index).setAssociatedParts(newProduct.getAllAssociatedParts());

        allProducts.get(index).setProductName(newProduct.getName());
        allProducts.get(index).setProductPrice(String.valueOf(newProduct.getPrice()));
        allProducts.get(index).setProductStock(String.valueOf(newProduct.getStock()));
    }

    public static boolean deletePart(Part selectedPart) {
        if (allParts.contains(selectedPart)) {
            allParts.remove(selectedPart);
            return true;
        }
        return false;
    }

    public static boolean deleteProduct(Product selectedProduct) {
        if (selectedProduct.getAllAssociatedParts().isEmpty()) {
            if (allProducts.contains(selectedProduct)) {
                allProducts.remove(selectedProduct);
                return true;
            }
        }
        return false;
    }

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    public static String currencyFormatter(double price) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return formatter.format(price);
    }
}