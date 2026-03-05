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

class GarlicBread {
public:
    virtual void prepare() = 0;
};

class basicGarlicBread : public GarlicBread {
public:
    void prepare() override {
        cout << "Preparing basic garlic bread..." << endl;
    }
};

class StandardGarlicBread : public GarlicBread {
public:
    void prepare() override {
        cout << "Preparing standard garlic bread..." << endl;
    }
};

class PremiumGarlicBread : public GarlicBread {
public:
    void prepare() override {
        cout << "Preparing premium garlic bread..." << endl;
    }
};

class BasicWheatGarlicBread : public GarlicBread {
public:
    void prepare() override {
        cout << "Preparing basic wheat garlic bread..." << endl;
    }
};

class StandardWheatGarlicBread : public GarlicBread {
public:
    void prepare() override {
        cout << "Preparing standard wheat garlic bread..." << endl;
    }
};

class PremiumWheatGarlicBread : public GarlicBread {
public:
    void prepare() override {
        cout << "Preparing premium wheat garlic bread..." << endl;
    }
};

class MealFactory {
    public:
        virtual Burger* createBurger(string& type) = 0;
        virtual GarlicBread* createGarlicBread(string& type) = 0;

};

class SinghBurger : public MealFactory {
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

        GarlicBread* createGarlicBread(string& type) override {
            if (type == "basic") {
                return new basicGarlicBread();
            } else if (type == "standard") {
                return new StandardGarlicBread();
            } else if (type == "premium") {
                return new PremiumGarlicBread();
            } else {
                cout << "Invalid selection" << "\n";
                return nullptr;
            }
        }
};

class KingBurger : public MealFactory {
    public:
        Burger* createBurger(string& type) override {
            if (type == "basic") {
                return new basicBurger();
            } else if (type == "standard") {
                return new StandardWheatBurger();
            } else if (type == "premium") {
                return new PremiumWheatBurger();
            } else {
                cout << "Invalid selection" << "\n";
                return nullptr;
            }
        }

        GarlicBread* createGarlicBread(string& type) override {
            if (type == "basic") {
                return new BasicWheatGarlicBread();
            } else if (type == "standard") {
                return new StandardWheatGarlicBread();
            } else if (type == "premium") {
                return new PremiumWheatGarlicBread();
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

    MealFactory* factory = new KingBurger();
    Burger* burger = factory->createBurger(type);

    if (burger != nullptr) {
        burger->prepare();
        delete burger;
    }

    cout << "Enter garlic bread type (basic, standard, premium): ";
    cin >> type;

    GarlicBread* garlicBread = factory->createGarlicBread(type);

    if (garlicBread != nullptr) {
        garlicBread->prepare();
        delete garlicBread;
    }

    delete factory;

    return 0;
}