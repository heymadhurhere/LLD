package ATM.state;

import ATM.chains.DispenseCashState;
import ATM.context.AtmMachine;
import ATM.entity.Card;

public class AuthenticatedState implements AtmState {
    public AuthenticatedState() {
        System.out.println("Authentication successful. Please select an option");
    }

    @Override
    public void insertCard(AtmMachine machine, Card card) {
        System.out.println("Error: Card is already inserted");
    }

    @Override
    public void enterPin(AtmMachine machine, String pin) {
        System.out.println("Error: Card already inserted");
    }

    @Override
    public void selectOption(AtmMachine machine, String option) {
        if (option.equalsIgnoreCase("withdraw")) {
            System.out.println("Withdraw option selected");
            machine.setCurrentState(new DispenseCashState());
        } else {
            System.out.println("Currently only withdraw facility is supported.");
        }
    }

    @Override
    public void withdrawCash(AtmMachine machine, int amount) {
        System.out.println("Error: Please select the 'WITHDRAW' option first");
    }

    @Override
    public void ejectCard(AtmMachine machine) {
        System.out.println("Transaction is cancelled");
        machine.setCurrentCard(null);
        machine.setCurrentState(new IdleState());
    }
}
