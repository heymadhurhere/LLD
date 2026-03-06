#ifndef SCHEDULE_ORDER_FACTORY_H
#define SCHEDULE_ORDER_FACTORY_H

#include "OrderFactory.h"
#include "../Models/DeliveryOrder.h"
#include "../Models/PickUpOrder.h"
#include "../Time Utils/TimeUtils.h"
using namespace std;

class ScheduleOrderFactory : public OrderFactory {
private:
    string scheduleTime;
public:
    ScheduleOrderFactory(const string& scheduleTime) : scheduleTime(scheduleTime) {}

    Order* createOrder(User* user,
                       Cart* cart,
                       Restaurant* restaurant,
                       const vector<MenuItem>& menuItems,
                       PaymentStrategy* paymentStrategy,
                       const string& orderType,
                       double totalCost) override {
        Order* order = nullptr;

        if(orderType == "Delivery") {
            auto deliveryOrder = new DeliveryOrder();
            deliveryOrder->setUserAddress(user->getAddress());
            order = deliveryOrder;
        } 
        else {
            auto pickupOrder = new PickUpOrder();
            pickupOrder->setRestaurantAddress(restaurant->getLocation());
            order = pickupOrder;
        }
        order->setUser(user);
        order->setRestaurant(restaurant);
        order->setItems(menuItems);
        order->setPaymentMethod(paymentStrategy);
        order->setScheduled(scheduleTime);
        order->setTotal(totalCost);
        return order;
    }
};

#endif // SCHEDULED_ORDER_FACTORY_H