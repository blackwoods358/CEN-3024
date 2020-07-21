package sample;
import java.io.*;
import java.net.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class client extends Application {


    // String answer = answer();
    // IO streams

    DataOutputStream toServer = null;
    DataInputStream fromServer = null;
    public boolean prime;


    // BufferedReader toServer = null;
    // BufferedReader fromServer = null;


    String answer() {

        if (prime == false) {
            return "no";
        }

        return "yes";
    }


    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Panel p to hold the label and text field
        BorderPane paneForTextField = new BorderPane();
        paneForTextField.setPadding(new Insets(5, 5, 5, 5));
        paneForTextField.setStyle("-fx-border-color: red");
        paneForTextField.setLeft(new Label("Enter a number to see if it is prime: "));

        TextField tf = new TextField();
        tf.setAlignment(Pos.BOTTOM_RIGHT);
        paneForTextField.setCenter(tf);

        BorderPane mainPane = new BorderPane();
        // Text area to display contents
        TextArea ta = new TextArea();
        mainPane.setCenter(new ScrollPane(ta));
        mainPane.setTop(paneForTextField);

        // Create a scene and place it in the stage
        Scene scene = new Scene(mainPane, 450, 200);
        primaryStage.setTitle("Client"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        tf.setOnAction(e -> {
            try {
                // Get the num from the text field
                double num = Double.parseDouble(tf.getText().trim());

                // Send the radius to the server
                toServer.writeDouble(num);
                toServer.flush();

                // Get answer from the server
                prime = fromServer.readBoolean();


                // Display to the text answer

                ta.appendText("Number is " + num + "\n");
                ta.appendText("Is this number prime? "
                        + answer() + '\n');
            } catch (IOException ex) {
                System.err.println(ex);
            }


        });

        try {
            // Create a socket to connect to the server
            Socket socket = new Socket("localhost", 3306);

            // Create an input stream to receive data from the server
            fromServer = new DataInputStream(socket.getInputStream());

            // Create an output stream to send data to the server
            toServer = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            ta.appendText(ex.toString() + '\n');
        }


    }


    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }
}