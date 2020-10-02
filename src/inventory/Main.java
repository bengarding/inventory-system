package inventory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("Inventory System");
        primaryStage.setScene(new Scene(root, 1000, 450));
        primaryStage.show();
    }


    public static void main(String[] args) {
        InHouse inHouse = new InHouse(5, "chicken nuggets", 5.5, 2, 1, 3, 5);
        Inventory.addPart(inHouse);
        Product product = new Product(6, "Happy meal", 6.51, 3, 2, 112);
        Inventory.addProduct(product);
        launch(args);
    }
}
