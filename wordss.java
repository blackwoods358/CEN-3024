
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;

/***
 * application sorts words collected form a url by the amount of time they appear on the webpage.
 * @author stephenblackwood
 */

public class wordss extends Application {
    Stage window;
    //two scenes
    Scene s1, s2;
    private static HashMap temp;
    private static Map<String, Long> counts;
    private static Map<String, Long> hm1;
    private static Map<String, Long> en;


    /***
     *
     * @param primaryStage the stage for the UI
     */
    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;


        Button button1 = new Button("Count words");
        button1.setOnAction(e -> window.setScene(s2));
//layout 1
        VBox layout1 = new VBox(30);
        layout1.getChildren().addAll(button1);
        s1 = new Scene(layout1, 400, 400);

       // map2

   TextArea l = new TextArea("Words ->   " + counts);

        Button button2 = new Button(" Go back to scene 1");
        button2.setOnAction(e -> window.setScene(s1));

//layout2
        VBox layout2 = new VBox(30);

    layout2.getChildren().addAll(l, button2);
        s2 = new Scene(layout2, 500, 500);

        window.setScene(s1);
        window.setTitle("word counter");
        window.show();

    }

    /***
     * Creates  a linked list from  counts map ands  sorts the list then places sorted list into temp the hashmap.
     * @param counts the first map that saves the words and keys and the values as the amount of occurrences they have .
     * @return  temp is the sorted hashmap
     */
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

    /***
     *Main method to run program/ this is where the url is read and the map is filled then out put is displayed .
     * @param args passed   input to  Java program  stored in  String args array.
     * @throws IOException if the url can't recalled The io exception will be thrown
     */
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


