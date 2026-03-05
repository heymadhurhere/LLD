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
            if (instance == nullptr) {
                instance = new Singleton();
            }
            return instance;
        }
};

Singleton* Singleton::instance = nullptr;

int main() {
    Singleton* a = Singleton::getInstance();
    Singleton* b = Singleton::getInstance(); // this is the way to access the static member of the class.

    cout << (a == b) << "\n";
    return 0;
}