package sample;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Date;

import static java.awt.SystemColor.window;

public class Controller implements EventHandler<ActionEvent> {



    public void handle(ActionEvent event)  {
        try {

            dataBase db = new dataBase();
           db.main();







    } catch (Exception e) {
            e.printStackTrace();
        }


    }




}
