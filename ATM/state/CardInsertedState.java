package ATM.state;

import ATM.context.AtmMachine;
import ATM.entity.Card;

public class CardInsertedState implements AtmState {
    public CardInsertedState() {
        System.out.println("Card is inserted. Please enter your PIN");
    }

    @Override
    public void insertCard(AtmMachine machine, Card card) {
        System.out.println("Error: A card is already inserted");
    }

    @Override
    public void enterPin(AtmMachine machine, String pin) {
        String expectedPin = machine.getCurrentCard().getPin();

        if (expectedPin.equals(pin)) {
            System.out.println("PIN accepted");
            machine.setCurrentState(new AuthenticatedState());
        } else {
            System.out.println("Incorrect PIN. Ejecting card...");
            ejectCard(machine);
        }
    }

    @Override
    public void selectOption(AtmMachine machine, String option) {
        System.out.println("Error: Please enter your PIN first.");
    }

    @Override
    public void withdrawCash(AtmMachine machine, int amount) {
        System.out.println("Error: Please enter your PIN first.");
    }

    @Override
    public void ejectCard(AtmMachine machine) {
        System.out.println("Ejecting card... Please collect it");
        machine.setCurrentCard(null);
        machine.setCurrentState(new IdleState());
    }
}
