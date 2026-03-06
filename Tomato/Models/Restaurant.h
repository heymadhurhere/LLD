#ifndef RESTAURANT_H
#define RESTAURANT_H

#include <iostream>
#include <string>
#include <vector>
#include "MenuItem.h"

using namespace std;

class Restaurant {
    private:
        static int nextRestaurantID;
        int restaurantID;
        string name;
        string location;
        vector<MenuItem> menu;

    public:
        Restaurant(const string& name, const string& location)
            : restaurantID(nextRestaurantID++), name(name), location(location) {}

        ~Restaurant() {
            cout << "Restaurant " << name << " is being destroyed." << endl;
            menu.clear();
        }

        // getters and setters
        string getName() const {
            return name;
        }

        void setName(const string& n) {
            name = n;
        }

        string getLocation() const {
            return location;
        }

        void setLocation(const string& loc) {
            location = loc;
        }

        void addMenuItem(const MenuItem& item) {
            menu.push_back(item);
        }

        const vector<MenuItem>& getMenu() const {
            return menu;
        }
};

int Restaurant::nextRestaurantID = 0;

#endif // RESTAURANT_H