#ifndef UPIPAYMENT_STRATEGY_H
#define UPIPAYMENT_STRATEGY_H

#include "Payment_Strategy.h"
#include <iostream>
#include <string>
#include <iomanip>

using namespace std;

class UPIpaymentStrategy : public PaymentStrategy {
    private:
        string upiId;
    public:
        UPIpaymentStrategy(const string& upi) : upiId(upi) {}

        void pay(double amount) override {
            cout << "Processing UPI payment of Rs " << amount << " using UPI ID: " << upiId << endl;
        }
};

#endif // UPIPAYMENT_STRATEGY_H