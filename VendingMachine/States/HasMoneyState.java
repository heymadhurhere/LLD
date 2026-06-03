package VendingMachine.States;

import VendingMachine.core.Products;
import VendingMachine.enums.Coin;
import VendingMachine.States.State;
import VendingMachine.States.VendingMachine;

public class HasMoneyState implements State {

    public HasMoneyState() {
        System.out.println("Vending Machine is in Has Money State. Please select a product.");
    }

    @Override
    public void insertCoin(VendingMachine machine, Coin coin) throws Exception {
        System.out.println("Coin inserted: " + coin.name() + " (" + coin.value + ")");
        machine.getCoinList().add(coin);
    }

    @Override
    public void pressProductSelectionButton(VendingMachine machine) throws Exception {
        System.out.println("Product selection button pressed. Please choose a product.");
        machine.setState(new SelectionState());
    }

    @Override
    public void chooseProduct(VendingMachine machine, int code) throws Exception {
        throw new IllegalArgumentException("Please press the product selection button before choosing a product.");
    }

    @Override
    public void cancelAndRefund(VendingMachine machine) throws Exception {
        System.out.println("Transaction cancelled. Refunding coins...");
        int totalRefund = 0;
        for (Coin coin : machine.getCoinList()) {
            totalRefund += coin.value;
        }
        System.out.println("Total refund: " + totalRefund);
        machine.getCoinList().clear();
        machine.setState(new IdleState());
    }

    @Override
    public Products dispenseProduct(VendingMachine machine, int code) throws Exception {
        // Implementation for dispensing a product
        throw new IllegalArgumentException("Please select a product before dispensing.");
    }
}
