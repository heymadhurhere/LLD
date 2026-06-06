package ATM.context;

import ATM.database.AtmRepository;
import ATM.entity.Card;
import ATM.state.*;
import ATM.entity.AtmData;

public class AtmMachine {
    private final String atmId;
    private final AtmRepository atmRepository;

    private AtmState currentState;
    private Card currentCard;

    public AtmMachine(String atmid, AtmRepository atmRepository) {
        this.atmId = atmid;
        this.atmRepository = atmRepository;

        this.currentState = new IdleState();
        this.currentCard = null;
    }

    public void insertCard(Card card) {
        currentState.insertCard(this, card);
    }

    public void enterPin(String pin) {
        currentState.enterPin(this, pin);
    }

    // selectOption(String option) delegates to state — see line 67

    public void withdrawCash(int amount) {
        currentState.withdrawCash(this, amount);
    }

    public void ejectCard() {
        currentState.ejectCard(this);
    }

    public String getAtmId() {
        return atmId;
    }

    public AtmRepository getAtmRepository() {
        return atmRepository;
    }

    public AtmData getAtmData() {
        return atmRepository.getAtmDetails(this.atmId);
    }

    public void setCurrentState(AtmState currentState) {
        this.currentState = currentState;
    }

    public Card getCurrentCard() {
        return currentCard;
    }

    public void setCurrentCard(Card currentCard) {
        this.currentCard = currentCard;
    }

    public void selectOption(String option) {
        currentState.selectOption(this, option);
    }
}