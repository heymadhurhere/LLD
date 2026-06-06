package ATM.chains;

import ATM.entity.AtmData;

public class OneHundredDispenser extends CashDispenser{
    @Override
    public boolean canDispense(int amount, AtmData atmData) {
        int notesRequired = amount / 100;
        int notesAvailable = atmData.getNoOfOneHundredNotes();

        int notesToDispense = Math.min(notesRequired, notesAvailable);
        int remainingAmount = amount - (notesToDispense * 100);

        if (remainingAmount == 0) return true;

        if (nextDispenser != null) {
            return nextDispenser.canDispense(remainingAmount, atmData);
        }

        return false;
    }

    @Override
    public void dispense(int amount, AtmData atmData) {
        int notesRequired = amount / 100;
        int notesAvailable = atmData.getNoOfOneHundredNotes();

        int notesToDispense = Math.min(notesRequired, notesAvailable);
        int remainingAmount = amount - (notesToDispense * 100);

        if (notesToDispense > 0) {
            atmData.deductOneHundredNotes(notesToDispense);
            System.out.println("Dispensing " + notesToDispense + " notes of Rs. 100");
        }

        if (remainingAmount > 0 && nextDispenser != null) {
            nextDispenser.dispense(remainingAmount, atmData);
        }
    }
}
