#ifndef NOW_ORDER_H
#define NOW_ORDER_H

#include "OrderFactory.h"
#include "../Models/DeliveryOrder.h"
#include "../Models/PickUpOrder.h"
#include "../Time Utils/TimeUtils.h"
#include "../Models/Order.h"
#include <iostream>
#include <string>
#include <vector>

using namespace std;

class NowOrderFactory : public OrderFactory {
    public:
        Order* createOrder(User* user,
                           Cart* cart,
                           Restaurant* restaurant,
                           const vector<MenuItem>& menuItems,
                           PaymentStrategy* paymentMethod,
                           const string& orderType,
                           double total) override {
            Order* order = NULL;
            if (orderType == "Delivery") {
                auto deliveryOrder = new DeliveryOrder();
                deliveryOrder->setUserAddress(user->getAddress());
                order = deliveryOrder;
            } else {
                auto pickupOrder = new PickUpOrder();
                pickupOrder->setRestaurantAddress(restaurant->getLocation());
                order = pickupOrder;
            }
            order->setUser(user);
            order->setRestaurant(restaurant);
            order->setItems(menuItems);
            order->setPaymentMethod(paymentMethod);
            const string now = TimeUtils::getCurrentTime();
            order->setScheduled(now); // set the order to be scheduled now
            order->setTotal(cart->getTotal());
            return order;
        }
};

#endif // NOW_ORDER_H