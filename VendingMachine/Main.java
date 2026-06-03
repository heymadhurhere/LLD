package VendingMachine;

import VendingMachine.States.IdleState;
import VendingMachine.States.VendingMachine;
import VendingMachine.enums.Coin;
import VendingMachine.enums.ItemType;
import VendingMachine.core.Products;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("--- SYSTEM BOOTUP (ADMIN PHASE) ---");

            // 1. Initialize the machine with 10 slots (Codes 101 to 110) [cite: 2668]
            VendingMachine machine = new VendingMachine(10);

            // Turn the machine on (Set initial state) [cite: 2204]
            machine.setState(new IdleState());

            // 2. Admin fills the inventory [cite: 2676, 2720]
            Products coke1 = new Products(ItemType.COKE, 12);
            Products coke2 = new Products(ItemType.COKE, 12);
            Products pepsi = new Products(ItemType.PEPSI, 9);

            machine.getInventory().addItemToShelf(coke1, 101);
            machine.getInventory().addItemToShelf(coke2, 101); // Slot 101 has 2 Cokes
            machine.getInventory().addItemToShelf(pepsi, 102); // Slot 102 has 1 Pepsi

            System.out.println("\n--- SCENARIO 1: IMPATIENT USER ---");
            // User walks up and immediately presses a button without putting money in.
            try {
                machine.pressProductSelectionButton();
            } catch (Exception e) {
                // The IdleState catches this and blocks it!
                System.out.println("MACHINE REJECTED ACTION: " + e.getMessage());
            }

            System.out.println("\n--- SCENARIO 2: SUCCESSFUL PURCHASE ---");
            // User inserts coins [cite: 2699, 2700]
            machine.insertCoin(Coin.TEN_RUPEES);
            machine.insertCoin(Coin.FIVE_RUPEES); // Total: 15 Rs

            // User presses the button to activate the keypad [cite: 2702, 2705]
            machine.pressProductSelectionButton();

            // User types in '101' for a Coke (Costs 12 Rs)
            machine.chooseProduct(101);

            System.out.println("\n--- SCENARIO 3: SOLD OUT SCENARIO ---");
            // Next user walks up and tries to buy the remaining inventory
            machine.insertCoin(Coin.TEN_RUPEES);
            machine.pressProductSelectionButton();

            try {
                machine.chooseProduct(102); // Pepsi costs 9 Rs. They inserted 10 Rs.

                machine.insertCoin(Coin.TEN_RUPEES);
                machine.pressProductSelectionButton();
                machine.chooseProduct(102); // Should throw an error!

            } catch (Exception e) {
                System.out.println("MACHINE REJECTED ACTION: " + e.getMessage());
                // Since they failed to buy, they hit the cancel button to get their money back
                machine.cancelAndRefund();
            }

        } catch (Exception e) {
            System.out.println("CRITICAL SYSTEM ERROR: " + e.getMessage());
        }
    }
}
