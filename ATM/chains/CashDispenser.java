package ATM.chains;

import ATM.entity.AtmData;

public abstract class CashDispenser {
    protected CashDispenser nextDispenser;

    public void setNextDispenser(CashDispenser nextDispenser) {
        this.nextDispenser = nextDispenser;
    }

    public abstract boolean canDispense(int amount, AtmData atmData);

    public abstract void dispense(int amount, AtmData atmData);
}
