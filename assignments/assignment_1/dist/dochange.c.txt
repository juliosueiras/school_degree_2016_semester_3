#include <stdio.h>
#include <math.h>

void dochange(int money, int* changes) {
    int i, ref[5] = { 100 ,25 ,10 ,5 ,1};

    for (i = 0; i < 5; ++i) {
        changes[i] = money/ref[i];
        money -= (ref[i] * changes[i]);
    }
}

int main(void)
{
    int changes[5];

    int user_input;
    int isValid = 0;

    do {
        printf("Please enter the amount of money you would like changed: ");
        scanf("%d[^\n]", &user_input);
        if(user_input < 0) {
            printf("Wrong input!\n");
            isValid = 0;
        } else {
            isValid = 1;
        };
    } while (isValid != 1);

    dochange(user_input, changes);

    printf("%d Dollar(s)\n", changes[0]);
    printf("%d Quarter(s)\n", changes[1]);
    printf("%d Dimes\n", changes[2]);
    printf("%d Nickel(s)\n", changes[3]);
    printf("%d Penny(ies)\n", changes[4]);
    return 0;
}
