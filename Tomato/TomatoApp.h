#ifndef TOMATO_APP_H
#define TOMATO_APP_H

#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include "Models/User.h"
#include "Models/Restaurant.h"
#include "Models/Cart.h"
#include "Managers/RestaurantManager.h"
#include "Managers/OrderManager.h"
#include "Strategies/Payment_Strategy.h"
#include "Strategies/UPIpayment_strategy.h"
#include "Factories/NowOrder.h"
#include "Factories/ScheduleOrderFactory.h"
#include "Services/Notification.h"
#include "Time Utils/TimeUtils.h"

using namespace std;

class TomatoApp {
    public:
        TomatoApp() {
            initializeRestaurants();
        }

        void initializeRestaurants() {
            // store locations in lowercase to match RestaurantManager::searchByloc,
            // which lowercases the search string before comparing
            Restaurant* restaurant1 = new Restaurant("Bikaner", "delhi");
            restaurant1->addMenuItem(MenuItem("P1", "Chole Bhature", 120));
            restaurant1->addMenuItem(MenuItem("P2", "Samosa", 15));

            Restaurant* restaurant2 = new Restaurant("Haldiram", "kolkata");
            restaurant2->addMenuItem(MenuItem("P1", "Raj Kachori", 80));
            restaurant2->addMenuItem(MenuItem("P2", "Pav Bhaji", 100));
            restaurant2->addMenuItem(MenuItem("P3", "Dhokla", 50));

            Restaurant* restaurant3 = new Restaurant("Saravana Bhavan", "chennai");
            restaurant3->addMenuItem(MenuItem("P1", "Masala Dosa", 90));
            restaurant3->addMenuItem(MenuItem("P2", "Idli Vada", 60));
            restaurant3->addMenuItem(MenuItem("P3", "Filter Coffee", 30));

            RestaurantManager* restaurantManager = RestaurantManager::getInstance();
            restaurantManager->addRestaurant(restaurant1);
            restaurantManager->addRestaurant(restaurant2);
            restaurantManager->addRestaurant(restaurant3);
        }

        vector<Restaurant*> searchRestaurant(const string& location) {
            return RestaurantManager::getInstance()->searchByloc(location);
        }

        void selectRestaurant(User* user, Restaurant* restaurant) {
            Cart* cart = user->getCart(); // cart of user is fetched
            cart->setRestaurant(restaurant);
        }

        void addToCart(User* user, const string& itemCode) {
            Restaurant* restaurant = user->getCart()->getRestaurant();
            if (!restaurant) {
                cout << "Select a restaurant first" << endl;
                return;
            }
            for (const auto& item : restaurant->getMenu()) {
                if (item.getCode() == itemCode) {
                    user->getCart()->addItem(item);
                    break;
                }
            }
        }
        
        Order* CheckOutNow(User* user, const string& orderType, PaymentStrategy* paymentStrategy) {
            return checkout(user, orderType, paymentStrategy, new NowOrderFactory());
        }
        
        Order* CheckOutSchedule(User* user, const string& orderType, PaymentStrategy* paymentStrategy, const string& scheduleTime) {
            return checkout(user, orderType, paymentStrategy, new ScheduleOrderFactory(scheduleTime));
        }

        Order* checkout(User* user, const string& orderType, PaymentStrategy* paymentStrategy, OrderFactory* orderFactory) {
            if (user->getCart()->isEmpty()) return NULL;

            Cart* userCart = user->getCart(); // get the cart of the user
            Restaurant* orderedRestaurant = userCart->getRestaurant(); // get the restaurant from which food is ordered
            vector<MenuItem> itemsOrdered = userCart->getItems(); // get the items ordered
            double totalAmount = userCart->getTotal(); // get the total amount

            Order* order = orderFactory->createOrder(user, userCart, orderedRestaurant, itemsOrdered, paymentStrategy, orderType, totalAmount);
            OrderManager::getInstance()->addOrder(order);
            return order;
        }

        void payForOrder(User* user, Order* order) {
            bool isPaymentSuccess = order->processPayment();

            if (isPaymentSuccess) {
                Notification* notification = new Notification();
                notification->notify(order);
                user->getCart()->clear();
            }
        }

        void printUserCart(User* user) {
        cout << "Items in cart:" << endl;
        cout << "------------------------------------" << endl;
        for (const auto& item : user->getCart()->getItems()) {
            cout << item.getCode() << " : " << item.getName() << " : ₹" << item.getPrice() << endl;
        }
        cout << "------------------------------------" << endl;
        cout << "Grand total : ₹" << user->getCart()->getTotal() << endl;
    }

    
};

#endif // TOMATO_APP_H