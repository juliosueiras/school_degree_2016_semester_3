#ifndef CUSTOMER_H
#define CUSTOMER_H

#include <iostream>
#include <vector>

using namespace std;

class Customer
{

private:
    class PhoneCall
    {
    public:
        string getCalledNumber(){ return called_number; }
        string getDate(){ return date; }
        int getMins(){ return mins; }

        operator int() {
            return this->getMins();
        }

        operator string() {
            return this->getCalledNumber() + " | " + this->getDate() + " | " + to_string(getMins());
        }

        PhoneCall(int mins, string called_number, string date)
            : mins(mins), called_number(called_number), date(date) {};

    private:
        string called_number, date;
        int mins;
    };

    const double MONTHLY_FEE = 10;
    const double CALL_CHARGE = 0.50;
    const double MIN_CHARGE = 0;

public:
    Customer(string name, double balance) : name(name), balance(balance) {};

    operator int() {
        return this->computeBill();
    }

    string getName(){ return this->name; }
    double getMonthlyFee(){ return this->MONTHLY_FEE; }
    double getCallCharge(){ return this->CALL_CHARGE; }
    double getMinCharge(){ return this->MIN_CHARGE; }

    int getTotalMins() {
        vector<PhoneCall> curr_phone_logs = getPhoneLogs();
        return accumulate(curr_phone_logs.begin(), curr_phone_logs.end(), 0, plus<int>());
    }

    void increaseTotalCall() { this->total_call++; }

    void addCall(double call, string phone_num, string date) {
        this->phone_logs.push_back(PhoneCall(call, phone_num, date));
        this->increaseTotalCall();
    }

    void printBill() {
        cout << "Name: " << this->getName() << endl;
        cout << "Phone Number |   Date     | Minutes" << endl;
        cout << "------------------------------------" << endl;

        for (auto phone_log : getPhoneLogs()) {
            cout << (string)phone_log << endl;
        }

        cout << "-----------------------------------" << endl;
        cout << "Total Bill: $" << computeBill() << endl;
        cout << "-----------------------------------" << endl;
    }

    virtual double computeBill() {
        return MONTHLY_FEE + (CALL_CHARGE * getTotalCall());
    }

    double getTotalCall() { return total_call; }

    vector<PhoneCall> getPhoneLogs(){ return phone_logs; }

protected:
    string name = "";
    double total_call = 0;
    double total_mins = 0;
    double balance = 0;
    vector<PhoneCall> phone_logs;
};

#endif
