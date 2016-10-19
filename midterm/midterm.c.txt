#include <stdio.h>
#include <stdlib.h>

typedef struct {
	int num1, num2;
	int gcd;
} gcd2_t;

void find_gcd(gcd2_t*);
void collect_data(gcd2_t*);
void print_gcd(gcd2_t*);

int main() {
	gcd2_t *gcd2;
	gcd2 = (gcd2_t*) malloc(sizeof(gcd2_t*));

	collect_data(gcd2);
	find_gcd(gcd2);
	print_gcd(gcd2);
	free(gcd2);
	return 0;
}

void collect_data(gcd2_t* gcd2) {
	printf("\n\n Recursion : Find GCD of two numbers :\n");
	printf("------------------------------------------\n");
	printf(" Input 1st number: ");
	scanf("%d",&gcd2->num1);
	printf(" Input 2nd number: ");
	scanf("%d",&gcd2->num2);
}

void print_gcd(gcd2_t* gcd2) {
	printf("\n The GCD of %d and %d is: %d\n\n",gcd2->num1,gcd2->num2, gcd2->gcd);
}

void find_gcd(gcd2_t* gcd2) {
	// Reference Iterative Euclidean Algorithm
	int num1 = gcd2->num1, num2 = gcd2->num2;

	if (num1 < 0) {
		num1 = -num1;
	}

	if (num2 < 0) {
		num2 = -num2;
	}

	if (num2) {
		while ((num1 %= num2) && (num2 %= num1));
	}

	gcd2->gcd = (num1 + num2);
}
