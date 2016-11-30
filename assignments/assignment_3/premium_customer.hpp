#ifndef PREMIUM_CUSTOMER_H
#define PREMIUM_CUSTOMER_H

#include "include/premium_customer.hpp"

#include <iostream>
#include <vector>

class PremiumCustomer : public Customer
{
public:
    PremiumCustomer (string name, double balance): Customer(name, balance) {};

    double computeBill() {
        return MONTHLY_FEE + (CALL_CHARGE * getTotalCall()) + (MIN_CHARGE * getTotalMins());
    }

private:
    const double MONTHLY_FEE = 20;
    const double CALL_CHARGE = 0.05;
    const double MIN_CHARGE = 0.10;
};

string generatePhoneNumber() {
    string result;
    int i;
    for (i = 0; i < 10; ++i) {
        result += to_string(rand() % 9);
    }
    return result;
}

#endif
