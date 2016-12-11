#include <iostream>

int modulus(int val, int divisor)
{
    if ( divisor==0 ) return 0;

    bool neg = val < 0;
    val = abs(val);
    divisor = abs(divisor);

    if (val < divisor) {
        return neg ? -val : val;
    } else
        return neg ? -modulus(val-divisor, divisor): modulus(val-divisor, divisor);
}

int main(void)
{
    std::cout << "Result of 10 % 3: " << modulus(10, 3) << std::endl;
    std::cout << "Result of 68 % 4: " << modulus(68, 4) << std::endl;
    return 0;
}
