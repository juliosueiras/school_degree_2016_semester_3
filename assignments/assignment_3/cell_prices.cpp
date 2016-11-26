#include <iostream>

class Customer
{
public:
    Customer (std::string name, int calls);
    virtual ~Customer ();

private:
    std::string name;
    int calls;
};


int main () {
    Customer *list[6] ;
    list[0] = new Customer("John Smith", 20); // 20 is the initial balance
    list[1] = new Customer("Bob Smith", 50);
    // list goes on...
    list[6] = new PremiumCustomer("Jane Doe", 100);
    list[7] = new PremiumCustomer("Mary Doe", 60);

    // Add number of calls:
    list[0].addCall(51); // call was for 50 minutes
    list[0].addCall(23); // call was for 23 mins
    list[6].addCall(71);
    // more calls added…
    for(int i=0; i<6; i++) {
        cout << "Customer " << list[i] << " owes "
             << list[i].computeBill() << " dollars." << endl;
    }
    return 0;
}
