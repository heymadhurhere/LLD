#ifndef ORDERMANAGER_H
#define ORDERMANAGER_H

#include <iostream>
#include <string>
#include <vector>
#include "../Models/Order.h"

using namespace std;

class OrderManager {
    private:
        vector<Order*> orders; // list of orders
        static OrderManager* instance; // singleton instance
        OrderManager() {} // private constructor for singleton


    public:
        static OrderManager* getInstance() {
            if (!instance) {
                instance = new OrderManager();
            }
            return instance;
        }

        void addOrder(Order* order) {
            orders.push_back(order);
        }

        void listOrders() {
            cout << "------------ Orders List ------------" << endl;
            for (auto& it : orders) {
                cout << it->getType() << "Order for " << it->getUser()->getName() << "Total: " << it->getTotal() << " At: " << it->getScheduled() << endl;
            }
        }
};

OrderManager* OrderManager::instance = nullptr; // initialize singleton instance to nullptr

#endif // ORDERMANAGER_H