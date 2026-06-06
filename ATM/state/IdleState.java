package ATM.state;

import ATM.context.AtmMachine;
import ATM.entity.Card;

public class IdleState implements AtmState {
    public IdleState() {
        System.out.println("ATM is in Idle state, please insert card to continue");
    }

    @Override
    public void insertCard(AtmMachine machine, Card card) {
        System.out.println("Card is inserted successfully.!!");
        machine.setCurrentCard(card);
        machine.setCurrentState(new CardInsertedState());
    }

    @Override
    public void enterPin(AtmMachine machine, String pin) {
        System.out.println("Error: Please insert your card first");
    }

    @Override
    public void selectOption(AtmMachine machine, String option) {
        System.out.println("Error: Please insert your card first");
    }

    @Override
    public void withdrawCash(AtmMachine machine, int amount) {
        System.out.println("Error: Please insert your card first");
    }

    @Override
    public void ejectCard(AtmMachine machine) {
        System.out.println("Error: No card is currently inserted");
    }
}
