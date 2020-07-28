package sample;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.logging.Handler;
/***
 * application sorts words collected form a url by the amount of time they appear on the webpage.
 * @author stephenblackwood
 */

public class Main extends Application  {


    /***
     *
     * @param primaryStage the stage for the UI
     */
    @Override
    public void start(Stage primaryStage) throws Exception {


        // Panel p to hold the label and text field
        BorderPane paneForTextField = new BorderPane();
        paneForTextField.setPadding(new Insets(5, 5, 5, 5));
        paneForTextField.setStyle("-fx-border-color: Yellow");
        paneForTextField.setCenter(new Label("Find words: "));

        Button bf = new Button();
        bf.setAlignment(Pos.BOTTOM_RIGHT);
        bf.setText("click me");
        paneForTextField.setCenter(bf);


    bf.setOnAction(new Controller());

        BorderPane mainPane = new BorderPane();
   // Text area to display contents
        TextArea ta = new TextArea();


        mainPane.setCenter(new ScrollPane(ta));
        mainPane.setTop(paneForTextField);


        // Create a scene and place it in the stage
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(mainPane, 450, 300);
        primaryStage.setTitle("Wound Counter");
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage


    }

    public static void main(String[] args) {
        launch(args);
    }


}
