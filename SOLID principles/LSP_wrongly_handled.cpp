#include <iostream>
#include <string>
#include <vector>
#include <typeinfo>
using namespace std;

class Account
{
public:
    virtual void deposit(double amount) = 0;
    virtual void withdraw(double amount) = 0;
};

class SavingsAccount : public Account
{
private:
    double balance;

public:
    SavingsAccount()
    {
        balance = 0.0;
    }

    void deposit(double amount) override
    {
        balance += amount;
        cout << "Deposited " << amount << " to Savings Account. Current balance: " << balance << endl;
    }

    void withdraw(double amount) override
    {
        if (balance >= amount)
        {
            balance -= amount;
            cout << "Withdrew " << amount << " from Savings Account. Current balance: " << balance << endl;
        }
        else
        {
            cout << "Insufficient balance in Savings Account." << endl;
        }
    }
};

class CurrentAccount : public Account
{
private:
    double balance;

public:
    CurrentAccount()
    {
        balance = 0.0;
    }

    void deposit(double amount) override
    {
        balance += amount;
        cout << "Deposited " << amount << " to Current Account. Current balance: " << balance << endl;
    }

    void withdraw(double amount) override
    {
        if (balance >= amount)
        {
            balance -= amount;
            cout << "Withdrew " << amount << " from Current Account. Current balance: " << balance << endl;
        }
        else
        {
            cout << "Insufficient balance in Current Account." << endl;
        }
    }
};

class FixedDepositAccount : public Account
{
private:
    double balance;

public:
    FixedDepositAccount()
    {
        balance = 0.0;
    }

    void deposit(double amount) override
    {
        balance += amount;
        cout << "Deposited " << amount << " to Fixed Deposit Account. Current balance: " << balance << endl;
    }

    void withdraw(double amount) override
    {
        throw logic_error("Withdrawals are not allowed from Fixed Deposit Account.");
    }
};

class BankClient
{
private:
    vector<Account *> accounts;

public:
    BankClient(vector<Account *> accounts)
    {
        this->accounts = accounts;
    }

    void processTransactions()
    {
        for (Account *acc : accounts)
        {
            acc->deposit(1000); // All accounts allow deposits

            if (typeid(*acc) == typeid(FixedDepositAccount))
            {
                cout << "Skipping withdrawal for Fixed Deposit Account." << endl;
                
            }
            else
            {
                // Assuming all accounts support withdrawal (LSP Violation)
                try
                {
                    acc->withdraw(500);
                }
                catch (const logic_error &e)
                {
                    cout << "Exception: " << e.what() << endl;
                }
            }
        }
    }
};

int main()
{
    vector<Account *> accounts;
    Account *savingsAccount = new SavingsAccount();
    Account *currentAccount = new CurrentAccount();
    Account *fixedDepositAccount = new FixedDepositAccount();
    accounts.push_back(savingsAccount);
    accounts.push_back(currentAccount);
    accounts.push_back(fixedDepositAccount);

    BankClient client(accounts);
    client.processTransactions();

    return 0;
}