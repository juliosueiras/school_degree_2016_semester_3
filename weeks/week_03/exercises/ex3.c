#include <math.h>
#include <stdlib.h>
#include <stdio.h>

void calculatePossibleSolution(double currentInterval) {
    if(currentInterval <= 14) {
        double resultOfEquation = (9/2)*pow(cos(currentInterval),2)-(currentInterval/4);

        if(fabs(resultOfEquation) < 0.00001) {
            printf("Possible Solution: %f\n", resultOfEquation);
        }
        calculatePossibleSolution(currentInterval+0.00001);
    }
}

int main(void) {
    calculatePossibleSolution(-1);
}

