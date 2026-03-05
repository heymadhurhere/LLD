#include <iostream>
using namespace std;

class NoSingleton {
    public:
        NoSingleton() {
            cout << "Singleton constructor called and new object created" << "\n";
        }
};

int main() {
    NoSingleton* a = new NoSingleton();
    NoSingleton* b = new NoSingleton();

    cout << (a == b) << "\n";
    return 0;
}