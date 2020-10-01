package inventory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("Inventory System");
        primaryStage.setScene(new Scene(root, 1000, 450));
        primaryStage.show();
    }


    public static void main(String[] args) {
        InHouse dummy = new InHouse(12, "test", 1.25, 251, 1, 100, 5);
        Inventory.addPart(dummy);
        Product dummy2 = new Product(5, "Chicken nuggets", 5.55, 5, 2, 57);
        Inventory.addProduct(dummy2);
        launch(args);
    }
}
