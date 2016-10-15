#include <stdio.h>
#include <math.h>

unsigned long fibonacci(int n) {
    return (n == 0 || n == 1) ? n : fibonacci(n - 1) + fibonacci(n - 2);
}


int main(void)
{
    int user_input;
    int isValid = 0;
    do {
        printf("Please enter the integer number n: ");
        scanf("%d", &user_input);
        if(user_input < 0) {
            printf("Wrong input!\n");
            isValid = 0;
        } else {
            isValid = 1;
        };
    } while (isValid != 1);

    printf("Fibonacci number F_%i is %lu.", user_input, fibonacci(user_input));
    return 0;
}
