#ifndef MENUITEM_H
#define MENUITEM_H

#include <iostream>
#include <string>
#include <vector>

using namespace std;

class MenuItem {
    private:
        string code;
        string name;
        double price;
    public:
        MenuItem(const string& code, const string& name, double price)
            : code(code), name(name), price(price) {}

        // getters and setters
        string getCode() const {
            return code;
        }

        void setCode(const string& c) {
            code = c;
        }

        string getName() const {
            return name;
        }

        void setname(const string& n) {
            name = n;
        }

        double getPrice() const {
            return price;
        }

        void setPrice(double p) {
            price = p;
        }
};

#endif // MENUITEM_H