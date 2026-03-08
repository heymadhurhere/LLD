#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
#include <ctime>

using namespace std;

/*--------- NOTIFICATION AND DECORATORS ---------*/

class INotification {
    public:
        virtual string getContent() const = 0;
        virtual ~INotification() {}
};

// concrete class
class SimpleNotification : public INotification {
    private:
        string text;
    public:
        SimpleNotification(const string& text) {
            this->text = text;
        }
        string getContent() const override {
            return text;
        }
};

// abstract decorator class
class INotificationDecorator : public INotification {
    protected:
        INotification* notification; // only the derived classes can access this
    public:
        INotificationDecorator (INotification* n) {
            notification = n;
        }
        
        virtual ~INotificationDecorator() {
            delete notification;
        }
};

// decorator to add timiestamp to the notification
class TimeStampDecorator : public INotificationDecorator {
    public:
        TimeStampDecorator(INotification* n) : INotificationDecorator(n) { }

        string getContent() const override {
            std::time_t now = std::time(0); 

            // Convert 'now' to a string format (e.g., "Day Month Date hh:mm:ss Year\n")

            char* dt = std::ctime(&now);
            string dtStr(dt);
            dtStr.pop_back(); // remove the newline character
            return "[" + dtStr + "] " + notification->getContent();
        }
};

class SignatureDecorator : public INotificationDecorator {
    private:
        string signature;
    public:
        SignatureDecorator(INotification* n, const string& signature) : INotificationDecorator(n) { }

        string getContent() const override {
            return notification->getContent() + " - " + signature;
        
        }
};

/*--------- OBSERVER DESIGN PATTERN ---------*/

// observer interface
class IObserver {
    public:
        virtual void update() = 0;
    
        virtual ~IObserver() { };
};

// abstract observable class
class IObservable {
    public:
        virtual void addObserver(IObserver* observer) = 0;
        virtual void removeObserver(IObserver* observer) = 0;
        virtual void notifyObservers() = 0;
};

// concrete observable class;
class NotificationObservable : public IObservable {
    private:
        vector<IObserver*> observers;
        INotification* notification;
    public:
        NotificationObservable() {
            notification = NULL;
        }

        void addObserver(IObserver* observer) override {
            observers.push_back(observer);
        }

        void removeObserver(IObserver* observer) override {
            auto it = find(observers.begin(), observers.end(), observer);
            if (it != observers.end()) {
                observers.erase(it);
            }
        }

        void notifyObservers() override {
            for (unsigned int i = 0; i < observers.size(); i++) {
                observers[i]->update();
            }
        }

        void setNotification(INotification* currNotification) {
            if (notification != NULL) {
                delete notification;
            }
            notification = currNotification;
            notifyObservers();
        }

        INotification* getNotification() {
            return notification;
        }

        string getNotificationContent() {
            return notification->getContent(); 
        }

        ~NotificationObservable() {
            if (notification != NULL)
                delete notification;
        }
};

// concrete observer class logger
class Logger : public IObserver {
    private:
        NotificationObservable* obs;
    public:
        Logger(NotificationObservable* observable) {
            this->obs = observable;
        }

        void update() override {
            cout << "Logger: new notification:" << obs->getNotificationContent() << endl;
        }
};

/*--------- STRATEGY DESING PATTERN ---------*/
class INotificationStrategy {
    public:
        virtual void sendNotification(string content) = 0;
};

// Email strategy
class EmailStrategy : public INotificationStrategy {
    private:
        string emailId;
    public:
        EmailStrategy(string emailId) {
            this->emailId = emailId;
        }

        void sendNotification(string content) override {
            cout << "Sending email to " << emailId << ": " << content << endl;
        }
};

// SMS strategy
class SMSStrategy : public INotificationStrategy {
    private:
        string phoneNumber;
    public:
        SMSStrategy(string phoneNumber) {
            this->phoneNumber = phoneNumber;
        }

        void sendNotification(string content) {
            cout << "Sending SMS to " << phoneNumber << ": " << content << endl;
        }
};

// Pop-Up strategy
class PopUpStrategy : public INotificationStrategy {
    public:
        void sendNotification(string content) {
            cout << "Sending pop-up notification: " << content << endl;
        }
};

/*--------- NOTIFICATION ENGINE ---------*/
class NotificationEngine : public IObserver {
    private:
        NotificationObservable* notificationObservable;
        vector<INotificationStrategy*> notificationStrategies;
    public:
        NotificationEngine(NotificationObservable* observable) {
            this->notificationObservable = observable;
        }

        void addNotificationStrategy(INotificationStrategy* strategy) {
            this->notificationStrategies.push_back(strategy);
        }

        void update() {
            string message = notificationObservable->getNotificationContent();
            for (const auto notificationStrategy : notificationStrategies) {
                notificationStrategy->sendNotification(message);
            }
        }
};

/*--------- NOTIFICATION SERVICE ---------*/
// singleton class
// client interacts with this class only
class NotificationService {
    private:
        NotificationObservable* observable;
        static NotificationService* instance;
        vector<INotification*> notifications;

        NotificationService() {
            // private constructor
            observable = new NotificationObservable();
        }
    public:
        static NotificationService* getInstance() {
            if (instance == NULL) {
                instance = new NotificationService();
            }
            return instance;
        }

        NotificationObservable* getObservable() {
            return observable;
        }

        // creates new notification and notifies observers
        void sendNotification(INotification* notification) {
            notifications.push_back(notification);
            observable->setNotification(notification);
        }

        ~NotificationService() {
            delete observable;
        }
};

NotificationService* NotificationService::instance = NULL;

int main() {
    // create notification service
    NotificationService* notificationService = NotificationService::getInstance();

    // get observable
    NotificationObservable* observable = notificationService->getObservable();

    // create observers
    Logger* logger = new Logger(observable);

    // Create NotificationEngine observers.
    NotificationEngine* notificationEngine = new NotificationEngine(observable);

    notificationEngine->addNotificationStrategy(new EmailStrategy("random.person@gmail.com"));
    notificationEngine->addNotificationStrategy(new SMSStrategy("+91 9876543210"));
    notificationEngine->addNotificationStrategy(new PopUpStrategy());

    // Attach these observers.
    observable->addObserver(logger);
    observable->addObserver(notificationEngine);

    // Create a notification with decorators.
    INotification* notification = new SimpleNotification("Your order has been shipped!");
    notification = new TimeStampDecorator(notification);
    notification = new SignatureDecorator(notification, "Customer Care");
    
    notificationService->sendNotification(notification);

    delete logger;
    delete notificationEngine;
    return 0;
}