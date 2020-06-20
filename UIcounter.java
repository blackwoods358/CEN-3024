package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;

public class UIcounter extends Application {
    private static Button button1;
    private static HashMap temp;
    private static Map<String, Long> counts;
    private static Map<String, Long> hm1;
    private static Map<String, Long> en;
Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception {
window = primaryStage;
window.setTitle("Word counter");

        button1 = new Button("Count Words");

        button1.setOnAction((e) -> closeProgram());

        StackPane layout = new StackPane();


        layout.getChildren().addAll(button1);
        Scene scene = new Scene(layout, 400, 400);
        window.setScene(scene);
        window.show();

    }
private void closeProgram(){
    System.out.println("Lets count lets count");
   window.close();

}
    //function to sort map
    public static HashMap<String, Long> sortByValue(Map<String, Long> counts) {
        launch();
        // Create a list From HashMap
        List<Map.Entry<String, Long>> list =
                new LinkedList<Map.Entry<String, Long>>(counts.entrySet());

// sort list with collections
        Collections.sort(list, new Comparator<Map.Entry<String, Long>>() {

            public int compare(Map.Entry<String, Long> o1,
                               Map.Entry<String, Long> o2) {

                return (o1.getValue()).compareTo(o2.getValue());
            }
        });


        //Reverse the list
        Collections.reverse(list);


        // put data from sorted list to hashmap
        temp = new LinkedHashMap<String, Long>();
        for (Map.Entry<String, Long> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }


    public static void main(String[] args) throws IOException {
        //path for data
        URL url = new URL("https://www.gutenberg.org/files/1065/1065-h/1065-h.htm");
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        String line;
        counts = new HashMap<>();

        //Fill hash map  w values
        while ((line = br.readLine()) != null) {
            String[] words = line.split("[\\s.;,?:!()/<\"]+");

            for (String word : words) {
                word = word.trim();
                String nline = br.readLine();
                if (nline.equalsIgnoreCase("<P CLASS=\"poem\">"))
                    continue;

                if (nline.equalsIgnoreCase("</P>"))
                    break;


                if (word.length() >= 1) {
                    if (counts.containsKey(word)) {
                        counts.put(word, counts.get(word) + 1);
                    } else {
                        counts.put(word, 1L);
                    }
                }

            }
        }
        // Display sorted Hashmap
        hm1 = sortByValue(counts);

        for (Map.Entry<String, Long> en : hm1.entrySet()) {

            System.out.println("Key = " + en.getKey() +
                    ", Value = " + en.getValue());

        }

    }

}