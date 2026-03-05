#include <iostream>
using namespace std;

class Burger {
    public:
        virtual void prepare() = 0;
        virtual ~Burger() {}
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

class basicWheatBurger : public basicBurger {
    public:
        void prepare() override {
            cout << "Preparing basic wheat burger..." << endl;
        }
};

class StandardWheatBurger : public StandardBurger {
    public:
        void prepare() override {
            cout << "Preparing standard wheat burger..." << endl;
        }
};

class PremiumWheatBurger : public PremiumBurger {
    public:
        void prepare() override {
            cout << "Preparing premium wheat burger..." << endl;
        }
};

class BurgerFactory {
    public:
        virtual Burger* createBurger(string& type) = 0;
};

class SinghBurger : public BurgerFactory {
    public:
        Burger* createBurger(string& type) override {
            if (type == "basic") {
                return new basicBurger();
            } else if (type == "standard") {
                return new StandardBurger();
            } else if (type == "premium") {
                return new PremiumBurger();
            } else {
                cout << "Invalid selection" << "\n";
                return nullptr;
            }
        }
};

class KingBurger : public BurgerFactory {
    public:
        Burger* createBurger(string& type) override {
            if (type == "basic") {
                return new basicWheatBurger();
            } else if (type == "standard") {
                return new StandardWheatBurger();
            } else if (type == "premium") {
                return new PremiumWheatBurger();
            } else {
                cout << "Invalid selection" << "\n";
                return nullptr;
            }
        }
};

int main() {
    string type;
    cout << "Enter burger type (basic, standard, premium): ";
    cin >> type;

    BurgerFactory* factory = new KingBurger();
    Burger* burger = factory->createBurger(type);

    if (burger != nullptr) {
        burger->prepare();
        delete burger;
    }

    return 0;
}