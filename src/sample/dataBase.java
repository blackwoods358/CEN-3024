package sample;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.*;
import java.sql.Connection;
import java.util.*;

public class dataBase {
    public  static void main() throws Exception{
        getConnection();
        createTable ();
        fill();
        insert();
        get();

    }

    /***
     *fill method to run program/ this is where the url is read and put into a database .
     * @throws Exception if the url can't recalled The io exception will be thrown
     */
    //Fill hash map  w values & place into database
    public static void fill()throws Exception{
        //path for data
        URL url = new URL("https://www.gutenberg.org/files/1065/1065-h/1065-h.htm");
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        String line;
        Map<String, Long> counts = new HashMap<>();
//Fill hash map  w values
    while ((line = br.readLine()) != null) {
// how to pick where you start to fill the map with values
            if (line.contentEquals("<H1 ALIGN=\"center\">"))
                continue;
            if (line.contentEquals("<BR><BR><BR><BR>"))
                break;
//Code to get rid of html tags from each line
            line = line.trim().replaceAll(   "\\<.*?\\>", "");
//split lines into single  words
            String[] words = line.split("[\\s.;,?:!()\"]+");
// Adds each word into the map
            for (String word : words) {

                if ( word.length() > 1) {
                    if (counts.containsKey(word)) {
                        counts.put(word, counts.get(word) + 1);
                    } else {
                        counts.put(word, 1L);
                    }
                }
            }
        }

        for (Map.Entry<String, Long> en : counts.entrySet()) {
            final String var1 = en.getKey();
            final Long var2 = en.getValue();

            try {
                Connection con = getConnection();
                PreparedStatement create = con.prepareStatement("INSERT INTO new_table1 (words,occurrences) VALUES('" + var1 + "','" + var2 + "' )  ");
                create.executeUpdate();
            } catch (Exception e) {
                System.out.println(e);
            } finally {

            }

        }
    }
    /***
     * Creates  a Array list from  database ands  prints words to screen
     * @return  Array
     */
// displays words from the database
    public static  ArrayList<String> get()throws Exception {
        try {
            Connection con = getConnection();
            PreparedStatement Display = con.prepareStatement("SELECT  words,occurrences FROM new_table1 ORDER BY occurrences DESC");
            ResultSet result = Display.executeQuery();
            ArrayList<String> array = new ArrayList<String>();
            while (result.next()) {
                System.out.print(result.getString("words"));
                System.out.print("  ");
                System.out.println(result.getString("occurrences"));
                array.add(result.getString("words"));
            }

            return array;

        } catch(Exception e){System.out.println(e);}
        return null;
    }


    // insert into databasse
    public static void insert()throws Exception{
        final String var1 = "run";
        final int var2 = 9;
        try {
            Connection con = getConnection();
            PreparedStatement create = con.prepareStatement("INSERT INTO word (words,occurrences) VALUES ('" + var1 + "','" + var2 + "' )  ");
            create.executeUpdate();
        } catch(Exception e){System.out.println(e);}
        finally {
       //     System.out.println("We have inserted new words");
        }
    }



  //creation of the table in the database
    public static void createTable() throws Exception{
        try{
            Connection con = getConnection();
            PreparedStatement create = con.prepareStatement("CREATE TABLE IF NOT EXISTS new_table1(words varchar(20), occurrences int)");

        } catch(Exception e){System.out.println(e);}
        finally {
          //  System.out.println("We have created the database or it is already been created");
        }
    }


//forming connection to the database
    public static Connection getConnection()throws Exception{
        try {
            //  String driver = "com.mysql.jdbc.Driver";
            // idk why by my time one was not correct an do this to the url..
            String url = "jdbc:mysql://localhost:3306/Final?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String username = "stephen";
            String password = "blackwood";
            //   Class.forName(driver);
            Connection con = DriverManager.getConnection(url,username, password);
          //  System.out.println("We have entered an Entanglement");
            return con;
        } catch(Exception e){System.out.println(e);}
        return null;
    }



}