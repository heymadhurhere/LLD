package VendingMachine.States;

import VendingMachine.core.Products;
import VendingMachine.enums.Coin;

public class OutOfStockState implements State {
    public OutOfStockState() {
        System.out.println("Vending Machine is out of stock. Please choose another product.");
    }

    @Override
    public void insertCoin(VendingMachine machine, Coin coin) throws Exception {
        throw new IllegalStateException("Cannot insert coins. Machine is out of stock.");
    }

    @Override
    public void pressProductSelectionButton(VendingMachine machine) throws Exception {
        throw new IllegalStateException("Cannot select product. Machine is out of stock.");
    }

    @Override
    public void chooseProduct(VendingMachine machine, int code) throws Exception {
        throw new IllegalStateException("Cannot choose product. Machine is out of stock.");
    }

    @Override
    public void cancelAndRefund(VendingMachine machine) throws Exception {
        throw new IllegalStateException("Cannot cancel transaction. Machine is out of stock.");
    }

    @Override
    public Products dispenseProduct(VendingMachine machine, int code) throws Exception {
        throw new IllegalStateException("Cannot dispense product. Machine is out of stock.");
    }
}
