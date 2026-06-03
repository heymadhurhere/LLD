package VendingMachine.States;

import VendingMachine.core.Products;
import VendingMachine.enums.Coin;


public interface State {
    void insertCoin(VendingMachine machine, Coin coin) throws Exception;
    void pressProductSelectionButton(VendingMachine machine) throws Exception;
    void chooseProduct(VendingMachine machine, int code) throws Exception;
    void cancelAndRefund(VendingMachine machine) throws Exception;
    Products dispenseProduct(VendingMachine machine, int code) throws Exception;
}
