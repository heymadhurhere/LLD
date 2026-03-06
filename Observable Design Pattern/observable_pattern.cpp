#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

class ISubscriber {
    public:
        virtual void update() = 0;
        virtual ~ISubscriber() {}
};

class IChannel {
    public:
        virtual void subscribe(ISubscriber* subscriber) = 0;
        virtual void unsubscribe(ISubscriber* subscriber) = 0;
        virtual void notify(string message) = 0;
        virtual ~IChannel() {}
};

class Channel : public IChannel {
    private:
        vector<ISubscriber*> subscribers;
        string name;
        string latestVideo;
    public:
        Channel(const string& name) {
            this->name = name;
        }
        void subscribe(ISubscriber* subscribe) override {
            if (find(subscribers.begin(), subscribers.end(), subscribe) != subscribers.end()) {
                cout << "Subscriber already subscribed" << endl;
                return;
            }
            subscribers.push_back(subscribe);
        }

        void unsubscribe(ISubscriber* subscriber) override {
            auto it = find(subscribers.begin(), subscribers.end(), subscriber);
            if (it != subscribers.end()) {
                subscribers.erase(it);
            }
        }

        void notify(string message) override {
            for (auto subscriber : subscribers) {
                subscriber->update();
            }
        }

        void uploadVideo(string video) {
            latestVideo = video;
            notify("New video uploaded: " + video);
        }

        string getLatestVideo() {
            return "Our new video is: " + latestVideo;
        }
};

class Subscriber : public ISubscriber {
    private:
        string name;
        Channel* channel;
    public:
        Subscriber(const string& name, Channel* channel) {
            this->name = name;
            this->channel = channel;
        }

        void update() override {
            cout << "Hello " << name << ", " << channel->getLatestVideo() << endl;
        }
};

int main() {
    Channel* channel = new Channel("Mr Beast");
    Subscriber* subscriber1 = new Subscriber("Aditya", channel);
    Subscriber* subscriber2 = new Subscriber("Shivam", channel);
    channel->subscribe(subscriber1);
    channel->subscribe(subscriber2);
    channel->uploadVideo("Video 1");
    channel->unsubscribe(subscriber1);
    channel->uploadVideo("Video 2");
    delete channel;
    delete subscriber1;
    delete subscriber2;
    return 0;
}