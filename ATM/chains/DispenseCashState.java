package ATM.chains;

import ATM.context.AtmMachine;
import ATM.entity.Account;
import ATM.entity.AtmData;
import ATM.entity.Card;
import ATM.state.AtmState;
import ATM.state.IdleState;

public class DispenseCashState implements AtmState {
    private CashDispenser dispenserChain;

    public DispenseCashState() {
        System.out.println("Enter the amount");
        buildDispenserChain();
    }

    public void buildDispenserChain() {
        CashDispenser d2000 = new TwoThousandDispenser();
        CashDispenser d500 = new FiveHundredDispenser();
        CashDispenser d100 = new OneHundredDispenser();

        d2000.setNextDispenser(d500);
        d500.setNextDispenser(d100);

        this.dispenserChain = d2000;
    }

    @Override
    public void insertCard(AtmMachine machine, Card card) {
        System.out.println("Error: Enter the amount you want to withdraw");
    }

    @Override
    public void enterPin(AtmMachine machine, String pin) {
        System.out.println("Enter the amount you want to withdraw");
    }

    @Override
    public void selectOption(AtmMachine machine, String option) {
        System.out.println("Enter the amount you want to withdraw");
    }

    @Override
    public void withdrawCash(AtmMachine machine, int requestAmount) {
        AtmData atmData = machine.getAtmData();
        Account userAccount = machine.getCurrentCard().getBankAccount();

        if (atmData.getTotalCashAvailable() < requestAmount) {
            System.out.println("Insufficient Cash in the Machine");
            ejectCard(machine);
            return;
        }

        if (userAccount.getBalance() < requestAmount) {
            System.out.println("Insufficient balance in the account");
            ejectCard(machine);
            return;
        }

        if (!dispenserChain.canDispense(requestAmount, atmData)) {
            System.out.println("Cannot dispense the requested amount with the current notes available");
            ejectCard(machine);
            return;
        }

        // All checks passed — dispense the cash
        dispenserChain.dispense(requestAmount, atmData);
        userAccount.deductBalance(requestAmount);
        System.out.println("Transaction successful! Please collect your cash.");

        // Save updated ATM data
        machine.getAtmRepository().save(atmData);
        ejectCard(machine);
    }

    @Override
    public void ejectCard(AtmMachine machine) {
        System.out.println("Ejecting card... Thanks for using our ATM.");
        machine.setCurrentCard(null);
        machine.setCurrentState(new IdleState());
    }
}
