#include <iostream>
#include <vector>
#include <algorithm>
#include <numeric>
#include <string>
#include <iomanip>
#include <ctime>

#include "include/customer.hpp"
#include "include/premium_customer.hpp"

using namespace std;

int main () {

    srand (time(NULL));
    int i;
    vector<Customer> basic_customers, premium_customers;

    int total_customer = rand() % 500 + 100;
    int basic_customer_num = total_customer / 2;
    int premium_customer_num = total_customer - basic_customer_num;

    for (i = 0; i < basic_customer_num; ++i) {
        Customer curr_customer = Customer("Customer #" + to_string(i), 60);

        int date = 1;
        for (i = 0; i < rand() % 100 + 10; ++i) {

            if( i % 4 == 0) {
                date ++;
            }

            curr_customer.addCall(rand() % 240 + 1, generatePhoneNumber(), "2016-12-" + to_string(date));
        }

        basic_customers.push_back(curr_customer);
    }


    for (i = 0; i < premium_customer_num; ++i) {
        Customer curr_customer = PremiumCustomer("Premium Customer #" + to_string(i), 60);

        int date = 1;
        for (i = 0; i < rand() % 100 + 10; ++i) {
            if( i % 4 == 0) {
                date ++;
            }
            curr_customer.addCall(rand() % 240 + 1, generatePhoneNumber(), "2016-12-" + to_string(date));
        }
        premium_customers.push_back(curr_customer);
    }

    /* for (auto basic_customer: basic_customers) { */
    /*     basic_customer->printBill(); */
    /* } */

    int basic_total = accumulate(basic_customers.begin(), basic_customers.end(), 0, plus<int>());
    int premium_total = accumulate(premium_customers.begin(), premium_customers.end(), 0, plus<int>());

    cout << "Total Customer Number: " << total_customer << endl;
    cout << "Basic Customer Number: " << basic_customer_num << endl;
    cout << "Premium Customer Number: " << premium_customer_num << endl;
    cout << "Total Basic Customer Bill: $" << basic_total << endl;
    cout << "Total Premium Customer Bill: $" << premium_total << endl;

    cout << "Average Basic Customer Bill: $" << basic_total/50 << endl;
    cout << "Average Premium Customer Bill: $" << premium_total/50 << endl;
    cout << "Different: $" << basic_total/50 - premium_total/50 << endl;

    /* for (auto premium_customer: premium_customers) { */
    /*     premium_customer->printBill(); */
    /* } */
   return 0;
}
