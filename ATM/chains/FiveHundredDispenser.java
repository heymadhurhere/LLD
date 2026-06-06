package ATM.chains;

import ATM.entity.AtmData;

public class FiveHundredDispenser extends CashDispenser {
    @Override
    public boolean canDispense(int amount, AtmData atmData) {
        int notesRequired = amount / 500;
        int notesAvailable = atmData.getNoOfFiveHundredNotes();

        int notesToDispense = Math.min(notesRequired, notesAvailable);
        int remainingAmount = amount - (notesToDispense * 500);

        if (remainingAmount == 0) return true;

        if (nextDispenser != null) {
            return nextDispenser.canDispense(remainingAmount, atmData);
        }

        return false;
    }

    @Override
    public void dispense(int amount, AtmData atmData) {
        int notesRequired = amount / 500;
        int notesAvailable = atmData.getNoOfFiveHundredNotes();

        int notesToDispense = Math.min(notesRequired, notesAvailable);
        int remainingAmount = amount - (notesToDispense * 500);

        if (notesToDispense > 0) {
            atmData.deductFiveHundredNotes(notesToDispense);
            System.out.println("Dispensing " + notesToDispense + " notes of Rs. 500");
        }

        if (remainingAmount > 0 && nextDispenser != null) {
            nextDispenser.dispense(remainingAmount, atmData);
        }
    }
}
