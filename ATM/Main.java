package ATM;

import ATM.context.*;
import ATM.chains.*;
import ATM.database.*;
import ATM.entity.*;
import ATM.state.*;

public class Main {
    public static void main(String[] args) {

        System.out.println("--- 1. SYSTEM BOOTUP & WIRING ---");

        // 1. Setup the Database (Repository)
        AtmRepository repository = new AtmRepository();

        // 2. Setup the physical ATM data (ID: ATM-1)
        // Let's load it with: 2x ₹2000 (4000), 5x ₹500 (2500), 10x ₹100 (1000) = Total
        // ₹7500
        AtmData atm1Data = new AtmData("ATM-1", 2, 5, 10);
        repository.save(atm1Data);

        // 3. Boot up the ATM Machine
        AtmMachine atmMachine = new AtmMachine("ATM-1", repository);

        // 4. Create a User Account and Card
        Account myAccount = new Account("ACC-98765", 10000.0); // User has 10,000 in bank
        Card myCard = new Card("CARD-1234-5678", "4321", myAccount); // Correct PIN is 4321

        System.out.println("\n--- 2. SCENARIO A: WRONG PIN ---");
        atmMachine.insertCard(myCard);
        atmMachine.enterPin("0000"); // Wrong PIN!
        // The machine will automatically reject this, eject the card, and return to
        // IDLE.

        System.out.println("\n--- 3. SCENARIO B: INVALID AMOUNT (₹1410) ---");
        // User tries again with the correct PIN
        atmMachine.insertCard(myCard);
        atmMachine.enterPin("4321");
        atmMachine.selectOption("WITHDRAW");

        // ATM has enough money, User has enough money, BUT we don't have ₹10 notes.
        atmMachine.withdrawCash(1410);
        // The Chain of Responsibility will catch this and gracefully abort!

        System.out.println("\n--- 4. SCENARIO C: SUCCESSFUL WITHDRAWAL (₹2700) ---");
        // User inserts card again
        atmMachine.insertCard(myCard);
        atmMachine.enterPin("4321");
        atmMachine.selectOption("WITHDRAW");

        // We need 1x ₹2000, 1x ₹500, 2x ₹100
        atmMachine.withdrawCash(2700);

        System.out.println("\n--- 5. FINAL SYSTEM AUDIT ---");
        AtmData updatedAtmData = repository.getAtmDetails("ATM-1");
        System.out.println("User Bank Balance: ₹" + myAccount.getBalance());
        System.out.println("ATM Remaining ₹2000 Notes: " + updatedAtmData.getNoOfTwoThousandNotes());
        System.out.println("ATM Remaining ₹500 Notes: " + updatedAtmData.getNoOfFiveHundredNotes());
        System.out.println("ATM Remaining ₹100 Notes: " + updatedAtmData.getNoOfOneHundredNotes());
        System.out.println("ATM Total Cash Left: ₹" + updatedAtmData.getTotalCashAvailable());
    }
}
