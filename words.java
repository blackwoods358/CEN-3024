
package sample;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;

public class words {
    Map<String, Long> counts = new HashMap<>();

    //function to sort map
    public static HashMap<String, Long> sortByValue(Map<String, Long> counts) {
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
        HashMap<String, Long> temp = new LinkedHashMap<String, Long>();
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
        Map<String, Long> counts = new HashMap<>();

        //Fill hash map  w values
        while ((line = br.readLine()) != null) {
            String[] words = line.split("[\\s.;,?:!/<\"]+");

            for (String word : words) {
                word = word.trim();
                String nline = br.readLine();
                if (nline.equalsIgnoreCase("<P CLASS=\"poem\">"))
                    continue;

                if (nline.equalsIgnoreCase("</P>"))
                    break;


                if (word.length() > 0) {
                    if (counts.containsKey(word)) {
                        counts.put(word, counts.get(word) + 1);
                    } else {
                        counts.put(word, 1L);
                    }
                }

            }
        }
        Map<String, Long> hm1 = sortByValue(counts);
        // Display sorted Hashmap


        for (Map.Entry<String, Long> en : hm1.entrySet()) {
            System.out.println("Key = " + en.getKey() +
                    ", Value = " + en.getValue());

        }
    }

}
