package ATM.state;

import ATM.context.AtmMachine;
import ATM.entity.Card;

public interface AtmState {
    void insertCard(AtmMachine machine, Card card);
    void enterPin(AtmMachine machine, String pin);
    void selectOption(AtmMachine machine, String option);
    void withdrawCash(AtmMachine machine, int amount);
    void ejectCard(AtmMachine machine);
}
