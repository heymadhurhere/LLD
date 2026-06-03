package VendingMachine.States;

import VendingMachine.core.Products;
import VendingMachine.enums.Coin;

public class DispenseState implements State {
    private final int codeToDispense;

    public DispenseState(VendingMachine machine, int code) throws Exception {
        System.out.println("Machine is dispensing....");
        this.codeToDispense = code;
        dispenseProduct(machine, code);
    }

    @Override
    public void insertCoin(VendingMachine machine, Coin coin) throws Exception {
        throw new IllegalStateException("Currently dispensing a product. Please wait.");
    }

    @Override
    public void pressProductSelectionButton(VendingMachine machine) throws Exception {
        throw new IllegalStateException("Currently dispensing a product. Please wait.");
    }

    @Override
    public void chooseProduct(VendingMachine machine, int code) throws Exception {
        throw new IllegalStateException("Currently dispensing a product. Please wait.");
    }

    @Override
    public void cancelAndRefund(VendingMachine machine) throws Exception {
        throw new IllegalStateException("Currently dispensing a product. Cannot cancel now.");
    }

    @Override
    public Products dispenseProduct(VendingMachine machine, int code) throws Exception {
        Products product = machine.getInventory().getItem(codeToDispense);
        machine.getInventory().removeDispensedItem(code);

        int totalBalance = 0;
        for (Coin coin : machine.getCoinList()) {
            totalBalance += coin.value;
        }

        int change = totalBalance - product.getPrice();
        System.out.println("Returning change: " + change + " Rs");

        machine.getCoinList().clear();
        if (machine.getInventory().hasAnyItem()) {
            machine.setState(new IdleState());
        } else {
            machine.setState(new OutOfStockState());
        }
        return product;
    }
}
