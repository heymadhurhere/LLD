#include <iostream>
#include <string>
#include <vector>

using namespace std;

class Product {
    public:
        string name;
        double price;

        Product(string n, double p) {
            name = n;
            price = p;
        }
};

class ShoppingCart {
    private:
        vector<Product*> products;
    public:
        void addProduct(Product* product) {
            products.push_back(product);
        }

        // getter
        vector<Product*> getProduct() {
            return products;
        }

        double calculateTotal() {
            double total = 0.0;
            for (auto p : products) {
                total += p->price;
            }
            return total;
        }

        // SRP violated as the method is in same class
        void saveToDataBase() {
            cout << "Saving to database..." << endl;
        }
};

class ShoppingCartPrinter {
    private:
        ShoppingCart* cart;
    public:
        ShoppingCartPrinter(ShoppingCart* c) {
            cart = c;
        }

        void printInvoice() {
            cout << "Invoice:" << endl;
            for (auto p : cart->getProduct()) {
                cout << p->name << " - Rs" << p->price << endl;
            }
            cout << "Total: Rs" << cart->calculateTotal() << endl;
        }
};

class ShoppingCartStorage {
    private:
        ShoppingCart* cart;
    public:
        ShoppingCartStorage(ShoppingCart* c) {
            cart = c;
        }

        void saveToDataBase() {
            cout << "Saving to database..." << endl;
        }

        void saveToMongoDB() {
            cout << "Saving to MongoDB..." << endl;
        }

        void saveToFile() {
            cout << "Saving to file..." << endl;
        }
};

int main() {
    ShoppingCart* cart = new ShoppingCart();
    Product* p1 = new Product("Laptop", 50000);
    Product* p2 = new Product("Smartphone", 20000);

    cart->addProduct(p1);
    cart->addProduct(p2);

    ShoppingCartPrinter* printer = new ShoppingCartPrinter(cart);

    printer->printInvoice();

    ShoppingCartStorage* storage = new ShoppingCartStorage(cart);
    storage->saveToDataBase();
    storage->saveToMongoDB();
    storage->saveToFile();


    delete p1;
    delete p2;
    delete cart;
    delete printer;
    delete storage;

    return 0;
}