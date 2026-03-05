#include <iostream>
#include <mutex>
using namespace std;

class Singleton {
    private:
        static Singleton* instance;
        static mutex mtx;
        Singleton() {
            cout << "Singleton constructor called and new object created" << "\n";
        }
    public:
        static Singleton* getInstance() { // only one thread enters the critical section
            lock_guard<mutex> lock(mtx); // Lock the mutex to ensure thread safety
            if (instance == nullptr) {
                instance = new Singleton();
            }
            return instance;
        }
};

Singleton* Singleton::instance = nullptr;
mutex Singleton::mtx; // Mutex to protect access to the instance

int main() {
    Singleton* a = Singleton::getInstance();
    Singleton* b = Singleton::getInstance(); // this is the way to access the static member of the class.

    cout << (a == b) << "\n";
    return 0;
}