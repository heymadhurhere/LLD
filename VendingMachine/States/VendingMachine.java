package VendingMachine.States;

import java.util.ArrayList;
import java.util.List;

import VendingMachine.core.Inventory;
import VendingMachine.enums.Coin;
import VendingMachine.core.Products;

public class VendingMachine {
    private State state;
    private final Inventory inventory;
    private final List<Coin> coinList;

    public VendingMachine(int slotCount) {
        this.inventory = new Inventory(slotCount);
        this.coinList = new ArrayList<>();
    }

    public void insertCoin(Coin coin) throws Exception {
        this.state.insertCoin(this, coin);
    }

    public void pressProductSelectionButton() throws Exception {
        this.state.pressProductSelectionButton(this);
    }

    public void chooseProduct(int code) throws Exception {
        this.state.chooseProduct(this, code);
    }

    public void cancelAndRefund() throws Exception {
        this.state.cancelAndRefund(this);
    }

    public Products dispenseProduct(int code) throws Exception {
        return this.state.dispenseProduct(this, code);
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public List<Coin> getCoinList() {
        return coinList;
    }
}
