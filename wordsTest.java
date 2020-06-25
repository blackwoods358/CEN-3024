package sample;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class wordsTest {

/*    HashMap<Integer, String> map = new HashMap<>();

        map.put(1,  "A");
        map.put(2,  "B");
        map.put(3,  "C");
*/

    @Test
    void main() {
        words test = new words();



        Map<String, Long> output = test.counts;

assertEquals(102, output);



    }
}