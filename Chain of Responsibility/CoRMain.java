import java.util.*;

abstract class MoneyHandler {
    protected MoneyHandler nextHandler;

    public MoneyHandler() {
        this.nextHandler = null;
    }

    public void setNextHandler(MoneyHandler next) {
        nextHandler = next;
    }

    void dispense(int amount) {
    };
}

class ThousandHandler extends MoneyHandler {
    private int numNotes;

    public ThousandHandler(int numNotes) {
        this.numNotes = numNotes;
    }

    @Override
    public void dispense(int amount) {
        int notesNeeded = amount / 1000;

        if (notesNeeded > numNotes) {
            notesNeeded = numNotes;
            numNotes = 0;
        } else {
            numNotes -= notesNeeded;
        }

        if (notesNeeded > 0) {
            System.out.println("Dispensing " + notesNeeded + " 1000 note(s)");
        }

        int remainigAmount = amount - (notesNeeded * 1000);
        if (remainigAmount > 0) {
            if (nextHandler != null) {
                nextHandler.dispense(remainigAmount);
            } else {
                System.out.println("Remaining amount of " + remainigAmount + "cannot be dispensed");
            }
        }
    }
}

class FiveHundredHandler extends MoneyHandler {
    private int numNotes;

    public FiveHundredHandler(int numNotes) {
        this.numNotes = numNotes;
    }

    @Override
    public void dispense(int amount) {
        int notesNeeded = amount / 500;

        if (notesNeeded > numNotes) {
            notesNeeded = numNotes;
            numNotes = 0;
        } else {
            numNotes -= notesNeeded;
        }

        if (notesNeeded > 0) {
            System.out.println("Dispensing " + notesNeeded + " 500 note(s)");
        }

        int remainigAmount = amount - (notesNeeded * 500);
        if (remainigAmount > 0) {
            if (nextHandler != null) {
                nextHandler.dispense(remainigAmount);
            } else {
                System.out.println("Remaining amount of " + remainigAmount + "cannot be dispensed");
            }
        }
    }
}

class TwoHundredHandler extends MoneyHandler {
    private int numNotes;

    public TwoHundredHandler(int numNotes) {
        this.numNotes = numNotes;
    }

    @Override
    public void dispense(int amount) {
        int notesNeeded = amount / 200;

        if (notesNeeded > numNotes) {
            notesNeeded = numNotes;
            numNotes = 0;
        } else {
            numNotes -= notesNeeded;
        }

        if (notesNeeded > 0) {
            System.out.println("Dispensing " + notesNeeded + " 200 note(s)");
        }

        int remainigAmount = amount - (notesNeeded * 200);
        if (remainigAmount > 0) {
            if (nextHandler != null) {
                nextHandler.dispense(remainigAmount);
            } else {
                System.out.println("Remaining amount of " + remainigAmount + "cannot be dispensed");
            }
        }
    }
}

class OneHundredHandler extends MoneyHandler {
    private int numNotes;

    public OneHundredHandler(int numNotes) {
        this.numNotes = numNotes;
    }

    @Override
    public void dispense(int amount) {
        int notesNeeded = amount / 100;

        if (notesNeeded > numNotes) {
            notesNeeded = numNotes;
            numNotes = 0;
        } else {
            numNotes -= notesNeeded;
        }

        if (notesNeeded > 0) {
            System.out.println("Dispensing " + notesNeeded + " 100 note(s)");
        }

        int remainigAmount = amount - (notesNeeded * 100);
        if (remainigAmount > 0) {
            if (nextHandler != null) {
                nextHandler.dispense(remainigAmount);
            } else {
                System.out.println("Remaining amount of " + remainigAmount + " cannot be dispensed");
            }
        }
    }
}

public class CoRMain {
    public static void main(String[] args) {
        MoneyHandler thousandHandler = new ThousandHandler(10);
        MoneyHandler fiveHundredHandler = new FiveHundredHandler(10);
        MoneyHandler twoHundredHandler = new TwoHundredHandler(10);
        MoneyHandler oneHundredHandler = new OneHundredHandler(10);

        thousandHandler.setNextHandler(fiveHundredHandler);
        fiveHundredHandler.setNextHandler(twoHundredHandler);
        twoHundredHandler.setNextHandler(oneHundredHandler);

        int amountWithdraw = 350;

        System.out.println("\nDispensing amount to withdraw: " + amountWithdraw + "\n");
        thousandHandler.dispense(amountWithdraw);
    }
}
