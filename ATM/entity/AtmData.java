package ATM.entity;

public class AtmData {
    private String atmId;
    private int noOfTwoThousandNotes;
    private int noOfFiveHundredNotes;
    private int noOfOneHundredNotes;

    public AtmData(String atmId, int noOfTwoThousandNotes, int noOfFiveHundredNotes, int noOfOneHundredNotes) {
        this.atmId = atmId;
        this.noOfFiveHundredNotes = noOfFiveHundredNotes;
        this.noOfOneHundredNotes = noOfOneHundredNotes;
        this.noOfTwoThousandNotes = noOfTwoThousandNotes;
    }

    public double getTotalCashAvailable() {
        return (noOfFiveHundredNotes * 500) + (noOfOneHundredNotes * 100) + (noOfTwoThousandNotes * 2000);
    }

    public int getNoOfTwoThousandNotes() {
        return noOfTwoThousandNotes;
    }

    public int getNoOfFiveHundredNotes() {
        return noOfFiveHundredNotes;
    }

    public int getNoOfOneHundredNotes() {
        return noOfOneHundredNotes;
    }

    public void deductTwothousandNotes(int count) {
        this.noOfTwoThousandNotes -= count;
    }

    public void deductFiveHundredNotes(int count) {
        this.noOfFiveHundredNotes -= count;
    }

    public void deductOneHundredNotes(int count) {
        this.noOfOneHundredNotes -= count;
    }

    public String getAtmId() {
        return atmId;
    }
}
