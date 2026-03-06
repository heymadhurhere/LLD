#ifndef CREDITCARD_H
#define CREDITCARD_H

#include "Payment_Strategy.h"
#include <iostream>
#include <string>
#include <iomanip>

using namespace std;

class CreditCardStrategy : public PaymentStrategy {
    private:
        string cardNumber;
    public:
        CreditCardStrategy(const string& card) : cardNumber(card) {}

        void pay(double amount) override {
            cout << "Processing Credit Card payment of Rs " << amount << " using Card Number: " << cardNumber << endl;
        }
};

#endif // CREDITCARD_H