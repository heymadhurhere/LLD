package ATM.chains;

import ATM.entity.AtmData;

public class TwoThousandDispenser extends CashDispenser {
    @Override
    public boolean canDispense(int amount, AtmData atmData) {
        int notesRequired = amount / 2000;
        int notesAvailable = atmData.getNoOfTwoThousandNotes();

        int notesToDispense = Math.min(notesRequired, notesAvailable);
        int remainingAmount = amount - (notesToDispense * 2000);

        if (remainingAmount == 0) return true;

        if (nextDispenser != null) {
            return nextDispenser.canDispense(remainingAmount, atmData);
        }

        return false;
    }

    @Override
    public void dispense(int amount, AtmData atmData) {
        int notesRequired = amount / 2000;
        int notesAvailable = atmData.getNoOfTwoThousandNotes();

        int notesToDispense = Math.min(notesRequired, notesAvailable);
        int remainingAmount = amount - (notesToDispense * 2000);

        if (notesToDispense > 0) {
            atmData.deductTwothousandNotes(notesToDispense);
            System.out.println("Dispensing " + notesToDispense + " notes of Rs. 2000");
        }

        if (remainingAmount > 0 && nextDispenser != null) {
            nextDispenser.dispense(remainingAmount, atmData);
        }
    }
}