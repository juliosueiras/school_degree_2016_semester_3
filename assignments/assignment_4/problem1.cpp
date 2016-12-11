#include <iostream>

long toBinary(int decimalNo)
{
    static long binaryNo,remainder,factor = 1;
    if(decimalNo != 0) {
        remainder = decimalNo % 2;
        binaryNo= binaryNo + remainder * factor;
        factor = factor * 10;
        toBinary(decimalNo / 2);
    }
    return binaryNo;
}

int main(void)
{
    std::cout << "Result of 10: " << toBinary(10) << std::endl;
    std::cout << "Result of 68: " << toBinary(68) << std::endl;
    return 0;
}
