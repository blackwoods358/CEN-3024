package Database;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.*;
import java.sql.Connection;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception{
       getConnection();
        createTable ();
        fill();
       insert();

        get();


    }

    // was not sure how to create values with database without a hash map.
    public static void fill()throws Exception{
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


        for (Map.Entry<String, Long> en : counts.entrySet()) {
            String var1 = en.getKey();
            Long var2 = en.getValue();


            try {
                Connection con = getConnection();
                PreparedStatement create = con.prepareStatement("INSERT INTO word (words,occurrences) VALUES('" + var1 + "','" + var2 + "' )  ");
               // while ((line = br.readLine()) != null) {
                    // not sure if this will work
                    create.executeUpdate();
              //  }
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                System.out.println("We have Poem  words & values ");
            }
//}


        }
    }







    public static  ArrayList<String> get()throws Exception {
        try {
            Connection con = getConnection();
            PreparedStatement Display = con.prepareStatement("SELECT  words,occurrences FROM word ");
            ResultSet result = Display.executeQuery();
            ArrayList<String> array = new ArrayList<String>();
            while (result.next()) {
                System.out.print(result.getString("words"));
                System.out.print("  ");
                System.out.println(result.getString("occurrences"));
                array.add(result.getString("words"));
            }
            System.out.println("Found words");
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
                System.out.println("We have inserted new words");
            }


    }



//creation of the table in the database
public static void createTable() throws Exception{
        try{
            Connection con = getConnection();
            PreparedStatement create = con.prepareStatement("CREATE TABLE IF NOT EXISTS word(words varchar(20), occurrences int)");

        } catch(Exception e){System.out.println(e);}
        finally {
            System.out.println("We have created the database or it is already been created");
        }
}



//forming connection to the database
    public static Connection getConnection()throws Exception{
        try {
     //  String driver = "com.mysql.jdbc.Driver";
            // idk why by my time one was not correct an do this to the url..
            String url = "jdbc:mysql://localhost:3306/word occurrences?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
           String username = "stephen";
           String password = "blackwood";
         //   Class.forName(driver);
            Connection con = DriverManager.getConnection(url,username, password);
            System.out.println("We have entered an Entanglement");
            return con;
        } catch(Exception e){System.out.println(e);}
        return null;
    }
}
