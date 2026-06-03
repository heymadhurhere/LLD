package VendingMachine.States;

import VendingMachine.core.Products;
import VendingMachine.enums.Coin;
import VendingMachine.States.State;
import VendingMachine.States.VendingMachine;

public class SelectionState implements State {

    public SelectionState() {
        System.out.println("Vending Machine is in Selection State. Please choose a product.");
    }

    @Override
    public void insertCoin(VendingMachine machine, Coin coin) throws Exception {
        throw new IllegalStateException("Cannot insert coins while in Selection State. Please choose a product first.");
    }

    @Override
    public void pressProductSelectionButton(VendingMachine machine) throws Exception {
        throw new IllegalStateException("Already in Selection State. Please choose a product.");
    }

    @Override
    public void chooseProduct(VendingMachine machine, int code) throws Exception {
        Products product = machine.getInventory().getItem(code);
        int totalBalance = 0;
        for (Coin coin : machine.getCoinList()) {
            totalBalance += coin.value;
        }

        if (totalBalance < product.getPrice()) {
            System.out.println("Insufficient balance. Please insert more coins.");
            cancelAndRefund(machine);
            return;
        }

        System.out.println("Product selected: " + product.getName() + " (" + product.getPrice() + " coins)");
        machine.setState(new DispenseState(machine, code));
    }

    @Override
    public void cancelAndRefund(VendingMachine machine) throws Exception {
        System.out.println("Transaction cancelled. Refunding coins...");
        machine.getCoinList().clear();
        machine.setState(new IdleState());
    }

    @Override
    public Products dispenseProduct(VendingMachine machine, int code) throws Exception {
        throw new IllegalStateException("Please choose a product before dispensing.");
    }
}
