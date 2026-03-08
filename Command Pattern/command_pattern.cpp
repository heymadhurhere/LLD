#include <iostream>
#include <vector>

using namespace std;

// command interface
class Command {
    public:
        virtual void execute() = 0;
        virtual void undo() = 0;
        virtual ~Command() { }
};

// receiver
class Light {
    public:
        void on() {
            cout << "Light is turned ON" << endl;
        }
        void off() {
            cout << "Light is turned OFF" << endl;
        }
};

class Fan {
    public:
        void on() {
            cout << "Fan is turned ON" << endl;
        }
        void off() {
            cout << "Fan is turned OFF" << endl;
        }
};

class AC {
    public:
        void on() {
            cout << "AC is turned ON" << endl;
        }
        void off() {
            cout << "AC is turned OFF" << endl;
        }
};

// concreet command for light
class LightCommand : public Command {
    private:
        Light* light;
    public:
        LightCommand(Light* light) {
            this->light = light;
        }

        void execute() {
            light->on();
        }

        void undo() {
            light->off();
        }
};

// concreet command for fan
class FanCommand : public Command {
    private:
        Fan* fan;
    public:
        FanCommand(Fan* fan) {
            this->fan = fan;
        }

        void execute() {
            fan->on();
        }

        void undo() {
            fan->off();
        }
};

// concreet command for AC
class ACCommand : public Command {
    private:
        AC* ac;
    public:
        ACCommand(AC* ac) {
            this->ac = ac;
        }

        void execute() {
            ac->on();
        }

        void undo() {
            ac->off();
        }
};

// remote controller
class Remote {
    private:
        static const int numBtns = 6;
        vector<Command*> btns;
        vector<bool> buttonsPressed;
    public:
        Remote() : btns(numBtns, nullptr), buttonsPressed(numBtns, false) { }

        void setCommand(int idx, Command* cmd) {
            if (idx >= 0 && idx < numBtns) {
                if (btns[idx] != NULL) {
                    delete btns[idx];
                }
                btns[idx] = cmd;
                buttonsPressed[idx] = false;
            }
        }

        void pressButton(int idx) {
            if (idx >= 0 && idx < numBtns && btns[idx] != NULL) {
                if (!buttonsPressed[idx]) {
                    btns[idx]->execute();
                    buttonsPressed[idx] = true;
                } else {
                    btns[idx]->undo();
                }
                buttonsPressed[idx] = !buttonsPressed[idx];
            } else {
                cout << "Invalid button index" << endl;
            }
        }

        ~Remote() {
            for (int i = 0; i < numBtns; i++) {
                if (btns[i] != NULL) {
                    delete btns[i];
                }
            }
        }
};

int main() {

    Light* livingRoomLight = new Light();
    Fan* ceilingFan = new Fan();

    Remote* remote = new Remote();

    remote->setCommand(0, new LightCommand(livingRoomLight));
    remote->setCommand(1, new FanCommand(ceilingFan));

    // Simulate button presses (toggle behavior)
    cout << "--- Toggling Light Button 0 ---" << endl;
    remote->pressButton(0);  // ON
    remote->pressButton(0);  // OFF

    cout << "--- Toggling Fan Button 1 ---" << endl;
    remote->pressButton(1);  // ON
    remote->pressButton(1);  // OFF

    // Press unassigned button to show default message
    cout << "--- Pressing Unassigned Button 2 ---" << endl;
    remote->pressButton(2);

    // Clean up 
    delete remote;
    delete livingRoomLight;
    delete ceilingFan;

    return 0;
}