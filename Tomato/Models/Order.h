#ifndef ORDER_H
#define ORDER_H

#include <iostream>
#include <string>
#include <vector>
#include "User.h"
#include "Restaurant.h"
#include "MenuItem.h"
#include "../Strategies/Payment_Strategy.h"
#include "../Time Utils/TimeUtils.h"  

using namespace std;

class Order {
    private:
        vector<MenuItem> items;
        User* user;
        Restaurant* restaurant;
        PaymentStrategy* paymentMethod;
        int orderId;
        static int nextOrderId;
        double total;
        string scheduled; // tells us for when is the order scheduled

    public:
        Order() {
            orderId = nextOrderId++;
            user = NULL;
            restaurant = NULL;
            paymentMethod = NULL;
            total = 0.0;
            scheduled = "";
        }

        virtual ~Order() {
            delete paymentMethod;
        }

        // process payment
        bool processPayment() {
            if (!paymentMethod) {
                cout << "No payment method selected." << endl;
                return false;
            }
            paymentMethod->pay(total);
            return true;
        }

        // getters and setters
        virtual string getType() const = 0;

        int getOrderId() const {
            return orderId;
        }

        User* getUser() const {
            return user;
        }

        Restaurant* getRestaurant() const {
            return restaurant;
        }

        string getScheduled() const {
            return scheduled;
        }

        double getTotal() const {
            return total;
        }

        void setItems(const vector<MenuItem>& itms) {
            items = itms;
            total = 0.0; // calculate total price
            for (const auto& it : items) {
                total += it.getPrice();
            }
        }

        const vector<MenuItem>& getItems() const {
            return items;
        }

        void setPaymentMethod(PaymentStrategy* method) {
            paymentMethod = method;
        }

        void setScheduled(const string& time) {
            scheduled = time;
        }

        void setTotal(double t) {
            total = t;
        }

        void setUser(User* u) {
            user = u;
        }

        void setRestaurant(Restaurant* r) {
            restaurant = r;
        }
};

int Order::nextOrderId = 0;

#endif // ORDER_H