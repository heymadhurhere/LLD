#include <iostream>
#include <string>

using namespace std;

/*
Abstract class --> 
1. Act as an interface for the outside world to operate the car. 
2. This abstract class tells 'WHAT' all it can do rather then 'HOW' it does that.
3. Since this is an abstract class we cannot directly create Objects of this class. 
4. We need to Inherit it first and then that child class will have the responsibility to 
provide implementation details of all the abstract (virtual) methods in the class.

5. In our real world example of Car, imagine you sitting in the car and able to operate
the car (startEngine, accelerate, brake, turn) just by pressing or moving some
pedals/buttons/ steer the wheel etc. You dont need to know how these things work, and
also they are hidden under the hood.
6. This Class 'Car' denotes that (pedals/buttons/steering wheel etc). 
*/

class Car {
    public:
        virtual void startEngine() = 0;
        virtual void shiftgear(int gear) = 0;
        virtual void accelerate() = 0;
        virtual void brake() = 0;
        virtual void stopEngine() = 0;
        virtual ~Car() {}
};

class SportCar : public Car {
    public:
        string brand;
        string model;
        bool isEngineOn;
        int currentgear;
        int currentSpeed;

        SportCar(string b, string m) {
            brand = b;
            model = m;
            isEngineOn = false;
            currentgear = 0;
            currentSpeed = 0;
        }

        void startEngine() {
            isEngineOn = true;
            cout << "Engine started for : " << brand << " " << model << endl;
        }

        void shiftgear(int gear) {
            if (isEngineOn) {
                currentgear = gear;
                cout << "Gear shifted to : " << currentgear << endl;
            } else {
                cout << "Start the engine first to shift gears." << endl;
            }
        }

        void accelerate() {
            if (isEngineOn) {
                currentSpeed += 10;
                cout << "Accelerating... Current speed: " << currentSpeed << " km/h" << endl;
            } else {
                cout << "Start the engine first to accelerate." << endl;
            }
        }

        void brake() {
            if (isEngineOn) {
                currentSpeed -= 10;
                if (currentSpeed < 0) {
                    currentSpeed = 0;
                }
                cout << "Braking... Current speed: " << currentSpeed << " km/h" << endl;
            } else {
                cout << "Start the engine first to brake." << endl;
            
            }
        }

        void stopEngine() {
            isEngineOn = false;
            currentgear = 0;
            currentSpeed = 0;
            cout << "Engine stopped for : " << brand << " " << model << endl;
        }
};

int main() {
    Car* myCar = new SportCar("Ferrari", "488 Spider");

    myCar->startEngine();
    myCar->shiftgear(1);
    myCar->accelerate();
    myCar->accelerate();
    myCar->shiftgear(2);
    myCar->accelerate();
    myCar->brake();
    myCar->stopEngine();

    delete myCar;

    return 0;

}