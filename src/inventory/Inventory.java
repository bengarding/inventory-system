package inventory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
        for (int i =0; i < allParts.size(); i++) {
            if (allParts.get(i).getId() == partId) {
                return allParts.get(i);
            }
        }
        return null;
    }

    public static int lookupPartIndex(int partID) {
        for (int i =0; i < allParts.size(); i++) {
            if (allParts.get(i).getId() == partID) {
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
            if(allParts.get(i).getId() == partId) {
                return true;
            }
        }
        return false;
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

    public static void updatePart(int index, Part selectedPart) {
        allParts.get(index).setName(selectedPart.getName());
        allParts.get(index).setPrice(selectedPart.getPrice());
        allParts.get(index).setMin(selectedPart.getMin());
        allParts.get(index).setMax(selectedPart.getMax());
        allParts.get(index).setStock(selectedPart.getStock());

        if(selectedPart.getClass() == InHouse.class) {
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