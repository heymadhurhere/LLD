#ifndef ORDER_FACTORY_H
#define ORDER_FACTORY_H

#include "../Models/Order.h"
#include "../Models/Cart.h"
#include "../Models/Restaurant.h"
#include "../Strategies/Payment_Strategy.h"
#include "../Models/User.h"
#include "../Models/MenuItem.h"
#include <iostream>
#include <string>
#include <vector>

using namespace std;

class OrderFactory {
    public:
        virtual Order* createOrder(User* user,
                                   Cart* cart,
                                   Restaurant* restaurant,
                                   const vector<MenuItem>& menuItems,
                                   PaymentStrategy* paymentMethod,
                                   const string& orderType,
                                   double total) = 0;

        virtual ~OrderFactory() {}
};

#endif // ORDER_FACTORY_H