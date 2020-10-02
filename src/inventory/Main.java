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
        launch(args);
    }
}

/**
 * If I were to update this application, I would add a feature that would allow for a company to have
 * several locations, where each location would be allowed to have it's own Parts and Products.
 * You would then be able to select the location you wish to use, and then add/modify/delete Parts and Products
 * from that location without affecting any of the others.
 */
