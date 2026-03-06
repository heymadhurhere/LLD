#ifndef PICKUPORDER_H
#define PICKUPORDER_H

#include <iostream>
#include <string>
#include "Order.h"

using namespace std;

class PickUpOrder : public Order {
    private:
        string restaurantAddress; // pickup address

    public:
        PickUpOrder() {
            restaurantAddress = "";
        }

        string getType() const override {
            return "PickUp";
        }

        void setRestaurantAddress(const string& addr) {
            restaurantAddress = addr;
        }


        string getRestaurantAddress() const {
            return restaurantAddress ;
        }
};


#endif