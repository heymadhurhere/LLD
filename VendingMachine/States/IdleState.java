package VendingMachine.States;

import VendingMachine.core.Products;
import VendingMachine.enums.Coin;

public class IdleState implements State {

    public IdleState() {
        System.out.println("Vending Machine is in Idle State. Please insert coins to start.");
    }

    @Override
    public void insertCoin(VendingMachine machine, Coin coin) throws Exception {
        System.out.println("Coin inserted: " + coin.name() + " (" + coin.value + ")");

        machine.getCoinList().add(coin);
        machine.setState(new HasMoneyState());
    }

    @Override
    public void pressProductSelectionButton(VendingMachine machine) throws Exception {
        throw new IllegalArgumentException("Please insert coins before selecting a product.");
    }

    @Override
    public void chooseProduct(VendingMachine machine, int code) throws Exception {
        throw new IllegalArgumentException("Please insert coins before selecting a product.");
    }

    @Override
    public void cancelAndRefund(VendingMachine machine) throws Exception {
        throw new IllegalArgumentException("No coins to refund. Please insert coins first.");
    }

    @Override
    public Products dispenseProduct(VendingMachine machine, int code) throws Exception {
        throw new IllegalArgumentException("Please insert coins and select a product before dispensing.");
    }
}
