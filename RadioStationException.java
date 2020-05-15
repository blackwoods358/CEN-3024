package objectorentedp;

public class RadioStationException extends Exception {

    //constructor
    public RadioStationException(String callSigh, double frequency, String message) {
        super(message);
        getFrequency();
        getCallSigh();

    }

    //getters
    private void getCallSigh() {
    }

    private void getFrequency() {

    }


}
