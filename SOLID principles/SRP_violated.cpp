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

        double calculateTotal() {
            double total = 0.0;
            for (auto p : products) {
                total += p->price;
            }
            return total;
        }

        // SRP violated as the method is in same class
        void printInvoice() {
            cout << "Invoice:" << endl;
            for (auto p : products) {
                cout << p->name << " - Rs" << p->price << endl;
            }
            cout << "Total: Rs" << calculateTotal() << endl;
        }

        // SRP violated as the method is in same class
        void saveToDataBase() {
            cout << "Saving to database..." << endl;
        }
};

int main() {
    Product* p1 = new Product("Laptop", 50000);
    Product* p2 = new Product("Smartphone", 20000);

    ShoppingCart cart;
    cart.addProduct(p1);
    cart.addProduct(p2);

    cart.printInvoice();
    cart.saveToDataBase();

    delete p1;
    delete p2;

    return 0;
}