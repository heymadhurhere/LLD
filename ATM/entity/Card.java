package ATM.entity;

public class Card {
    private String cardNumber;
    private String pin;
    private Account bankAccount;

    public Card(String cardNumber, String pin, Account account) {
        this.bankAccount = account;
        this.cardNumber = cardNumber;
        this.pin = pin;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getPin() {
        return pin;
    }

    public Account getBankAccount() {
        return bankAccount;
    }
}