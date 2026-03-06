#ifndef DELIVERY_ORDER_H
#define DELIVERY_ORDER_H

#include <iostream>
#include <string>
#include "../Models/Order.h"

using namespace std;

class DeliveryOrder : public Order {
    private:
        string userAddress; // delivery address

    public:
        DeliveryOrder() {
            userAddress = "";
        }

        string getType() const override {
            return "Delivery";
        }

        void setUserAddress(const string& addr) {
            userAddress = addr;
        }

        string getUserAddress() {
            return userAddress;
        }
};


#endif // DELIVERY_ORDER_H