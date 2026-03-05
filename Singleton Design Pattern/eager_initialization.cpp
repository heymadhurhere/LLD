#include <iostream>
using namespace std;

class Singleton {
    private:
        static Singleton* instance;
        Singleton() {
            cout << "Singleton constructor called and new object created" << "\n";
        }
    public:
        static Singleton* getInstance() {
            return instance;
        }
};

Singleton* Singleton::instance = new Singleton(); // Eager initialization of the singleton instance

int main() {
    Singleton* a = Singleton::getInstance();
    Singleton* b = Singleton::getInstance(); // this is the way to access the static member of the class.

    cout << (a == b) << "\n";
    return 0;
}