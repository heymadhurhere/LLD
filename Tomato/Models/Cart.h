#ifndef CART_H
#define CART_H

#include <iostream>
#include <vector>
#include <algorithm>
#include "MenuItem.h"
#include "Restaurant.h"

using namespace std;

class Cart {
    private:
        Restaurant* restaurant;
        vector<MenuItem> items;
    
    public:
        Cart() : restaurant(NULL) {}

        void addItem(const MenuItem& item) {
            if (!restaurant) {
                cout << "Please select a restaurant first." << endl;
                return;
            }
            items.push_back(item);
        }

        double getTotal() const {
            double total = 0.0;
            for (const auto& item : items) {
                total += item.getPrice();
            }
            return total;
        }

        bool isEmpty() const {
            return (!restaurant || items.empty());
        }

        // clear the cart
        void clear() {
            restaurant = NULL;
            items.clear();
        }

        // set restaurant for the cart
        void setRestaurant(Restaurant* res) {
            restaurant = res;
        }

        // get restaurant for the cart
        Restaurant* getRestaurant() const {
            return restaurant;
        }

        // get items in the cart
        vector<MenuItem> getItems() const {
            return items;
        }
};

#endif // CART_H