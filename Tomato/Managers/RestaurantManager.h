#ifndef RESTAURANTMANAGER_H
#define RESTAURANTMANAGER_H

#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include "../Models/Restaurant.h"

using namespace std;

class RestaurantManager {
    private:
        vector<Restaurant*> restaurants; // list of restaurants
        static RestaurantManager* instance; // singleton instance

        RestaurantManager() {} // private constructor for singleton
    public:
        static RestaurantManager* getInstance() {
            if (!instance) {
                instance = new RestaurantManager();
            }
            return instance;
        
        }

        void addRestaurant(Restaurant* restaurant) {
            restaurants.push_back(restaurant);
        }

        vector<Restaurant*> searchByloc(string loc) {
            vector<Restaurant*> result;
            //transform(loc.begin(), loc.end(), loc.begin(), ::tolower);
            for (auto& it : restaurants) {
                if (it->getLocation() == loc) {
                    result.push_back(it);
                }
            }
            return result;
        }
};

RestaurantManager* RestaurantManager::instance = nullptr; // initialize singleton instance to nullptr

#endif // RESTAURANTMANAGER_H