#ifndef USER_H
#define USER_H

#include <iostream>
#include <string>
#include <vector>
#include "Cart.h"

using namespace std;

class User {
    private:
        int userID;
        string name;
        string address;
        Cart* cart;
    
    public:
        User(int userId, const string& name, const string& address)
            : userID(userId), name(name), address(address) {
            cart = new Cart();
        }

        ~User() {
            delete cart;
        }

        // getters and setters
        int getUserId() const {
            return userID;
        }

        string getName() const {
            return name;
        }

        void setName(const string& n) {
            name = n;
        }

        string getAddress() const {
            return address;
        }

        void setAddress(const string& addr) {
            address = addr;
        }

        Cart* getCart() const {
            return cart;
        }
};

#endif // USER_H