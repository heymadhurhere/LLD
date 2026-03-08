#include <iostream>
#include <string>
#include <vector>

using namespace std;

// interface for target class which client interacts
class IReports {
    public:
        virtual string getJSONdata(const string& data) = 0;
        virtual ~IReports() {}
};

// adaptee class which is the 3rd  party library
class XMLdataProvider {
    public:
    string getXMLdata(const string& data) {
        size_t pos = data.find(':');
        string name = data.substr(0, pos);
        string id = data.substr(pos + 1);

        return "<user>"
                "<name>" + name + "</name>"
                "<id>" + id + "</id>"
                "</user>";
    }
};

// adapter class which converts xmlData to JSON
class XMLdataProviderAdapter : public IReports {
    private:
        XMLdataProvider* xmlDataProvider;
    public:
        XMLdataProviderAdapter(XMLdataProvider* provider) {
            this->xmlDataProvider = provider;
        }

        string getJSONdata(const string& data) override {
            string xmlData = xmlDataProvider->getXMLdata(data);

            size_t startName = xmlData.find("<name>") + 6;
            size_t endName   = xmlData.find("</name>");
            string name      = xmlData.substr(startName, endName - startName);

            size_t startId = xmlData.find("<id>") + 4;
            size_t endId   = xmlData.find("</id>");
            string id      = xmlData.substr(startId, endId - startId);

            return "{\"name\":\"" + name + "\", \"id\":" + id + "}";
        }
};

// client code
class Client {
    public:
        void getReport(IReports* report, string raw) {
            cout << "JSON data is : " << report->getJSONdata(raw) << endl;
        }
};

int main() {
    // 1. Create the adaptee
    XMLdataProvider* xmlProv = new XMLdataProvider();

    // 2. Make our adapter
    IReports* adapter = new XMLdataProviderAdapter(xmlProv);

    // 3. Give it some raw data
    string rawData = "Alice:42";

    // 4. Client prints the JSON
    Client* client = new Client();
    client->getReport(adapter, rawData);
    // → Processed JSON: {"name":"Alice", "id":42}

    // 5. Cleanup
    delete adapter;
    delete xmlProv;
    return 0;
}