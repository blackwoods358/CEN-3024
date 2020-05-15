package objectorentedp;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) throws RadioStationException {

        Scanner keyboard = new Scanner(System.in);
        String check;
        int maxLength = 4;
        String[] RadioStations = new String[6];

        for (int i = 0; i < 6; ++i) {

            System.out.println("Enter Call sigh   ");
            String callSigh = keyboard.next();


            System.out.println("Eneter frequency");
            double frequency = Double.parseDouble(keyboard.next());


            String RS;
            RS = String.valueOf(new RadioStation(frequency, callSigh));
            RadioStations[i] = RS;


           if (callSigh.length() != maxLength) {
                  System.out.println("Radio station unsuccessfully created!");
              }else if(frequency < 88 || frequency > 108) {
                   System.out.println("Radio station unsuccessfully created!");
            }else{
             System.out.println("Station successfully created   " + RadioStations[i]);
         }
         System.out.println(RadioStations[i]);
        }
        }

    }
