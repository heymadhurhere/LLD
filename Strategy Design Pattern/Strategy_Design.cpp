#include <iostream>
using namespace std;

class WalkableRobot {
public:
    virtual void walk() = 0;
    virtual ~WalkableRobot() {};
};

class NormalWalk : public WalkableRobot {
public:
    void walk() override {
        cout << "Walking normally..." << endl;
    }
};

class NoWalk : public WalkableRobot {
public:
    void walk() override {
        cout << "I can't walk!" << endl;
    }
};

class TalkableRobot {
    public:
        virtual void talk() = 0;
        virtual ~TalkableRobot() {};
};

class NormalTalk : public TalkableRobot {
public:
    void talk() override {
        cout << "Talking normally..." << endl;
    }
};

class NoTalk : public TalkableRobot {
public:
    void talk() override {
        cout << "I can't talk!" << endl;
    }
};

class FlyableRobot {
public:
    virtual void fly() = 0;
    virtual ~FlyableRobot() {};
};

class NormalFLy : public FlyableRobot {
public:
    void fly() override {
        cout << "Flying normally..." << endl;
    }
};

class NoFly : public FlyableRobot {
public:
    void fly() override {
        cout << "I can't fly!" << endl;
    }
};

class Robot {
    protected:
        WalkableRobot* walkFeature;
        TalkableRobot* talkFeature;
        FlyableRobot* flyFeature;
    public:
        Robot(WalkableRobot* walk, TalkableRobot* talk, FlyableRobot* fly) {
            walkFeature = walk;
            talkFeature = talk;
            flyFeature = fly;
        }

        void walk() {
            walkFeature->walk();
        }

        void talk() {
            talkFeature->talk();
        }

        void fly() {
            flyFeature->fly();
        }

        virtual void projection() = 0;
};

// robot types
class CompanionRobot : public Robot {
public:
    CompanionRobot(WalkableRobot* walk, TalkableRobot* talk, FlyableRobot* fly) : Robot(walk, talk, fly) {}

    void projection() override {
        cout << "Projecting hologram..." << endl;
    }
};

class WorkerRobot : public Robot {
public:
    WorkerRobot(WalkableRobot* walk, TalkableRobot* talk, FlyableRobot* fly) : Robot(walk, talk, fly) {}

    void projection() override {
        cout << "Projecting image..." << endl;
    }
};

int main() {
    Robot* robot1 = new CompanionRobot(new NormalWalk(), new NormalTalk(), new NoFly());
    robot1->walk();
    robot1->talk();
    robot1->fly();
    robot1->projection();

    cout << "-----------------------------" << endl;

    Robot* robot2 = new WorkerRobot(new NormalWalk(), new NoTalk(), new NormalFLy());
    robot2->walk();
    robot2->talk();
    robot2->fly();
    robot2->projection();

    return 0;

}