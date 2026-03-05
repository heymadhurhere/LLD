#include <iostream>
using namespace std;

class Burger {
public:
    virtual void prepare() = 0;
    virtual ~Burger() {};
};

class basicBurger : public Burger {
public:
    void prepare() override {
        cout << "Preparing basic burger..." << endl;
    }
};

class StandardBurger : public Burger {
public:
    void prepare() override {
        cout << "Preparing standard burger..." << endl;
    }
};

class PremiumBurger : public Burger {
public:
    void prepare() override {
        cout << "Preparing premium burger..." << endl;
    }
};

class BurgerFactory {
public:
    Burger* createBurger(string type) {
        if (type == "basic") {
            return new basicBurger();
        } else if (type == "standard") {
            return new StandardBurger();
        } else if (type == "premium") {
            return new PremiumBurger();
        } else {
            return nullptr;
        }
    }
};

int main() {
    BurgerFactory* factory = new BurgerFactory();
    string type;
    cout << "Enter burger type (basic, standard, premium): ";
    cin >> type;
    Burger* burger = factory->createBurger(type);
    if (burger != nullptr) {
        burger->prepare();
    }
    delete burger;
    return 0;
}