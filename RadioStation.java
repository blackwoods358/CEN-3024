package objectorentedp;

public class RadioStation {
    // instance variables
    double frequency;
    String callSigh;
    int maxLength = 4;

    // constructor
    public RadioStation(double frequency, String callSigh) {
        try {
            setCallSigh(callSigh);
        } catch (RadioStationException e) {
            e.printStackTrace();
        }
        try {
            setFrequency(frequency);
        } catch (RadioStationException e) {
            e.printStackTrace();
        }

    }

    // getters and setters
    public String getCallSigh() {
        return callSigh;
    }

    public void setCallSigh(String callSigh) throws RadioStationException {
        if (callSigh.length() == maxLength) {
            this.callSigh = callSigh;
        } else {
            throw new RadioStationException(callSigh, frequency, "Please enter a four letter call sigh ");
        }
    }


    public double getFrequency() {
        return frequency;
    }

    public void setFrequency(double frequency) throws RadioStationException {
        if (frequency > 88 && frequency < 108) {
            this.frequency = frequency;
        } else {
            throw new RadioStationException(callSigh,frequency, "Plsease enter a frequency between 88 and 108");
        }
    }

    public String toString() {

            return  "Call sigh:  " + getCallSigh() + "   Frequency:   " + getFrequency();
    }
}
