# Assignment 4
## Evaluation:
10 points, 10% of your final grade.
## Due date:
December 15th, 2016 (11:30pm)

## Description
In this assignment you need to write small programs in F#.

### Problem N1 (5 points):
Write print out the binary representation of a given number.
Note that the binary representation of 0 should be 0.
You can use this C++ code as a starting point:
```cpp
long toBinary(int decimalNo) {
	static long binaryNo,remainder,factor = 1;
	if(decimalNo != 0) {
		remainder = decimalNo % 2;
		binaryNo= binaryNo + remainder * factor;
		factor = factor * 10;
		toBinary(decimalNo / 2);
	}
	return binaryNo;
}
```

### Problem N2 (5 points):
Compute recursively n mod m without using %.
You can use this C++ code as a starting point:
```cpp
int modulus(int val, int divisor) {
	if ( divisor==0 ) return 0;
	bool neg = val < 0;
	val = abs(val);
	divisor = abs(divisor);
	if (val < divisor) {
		return neg ? -val : val;
	} else return neg ? -modulus(val-divisor, divisor): modulus(val-divisor, divisor);
}
```

Your F# program must:
- Run in MS Visual Studio or Visual Studio code.
- Match the code given above as close as possible (same variables, function names, etc)
- Note: You can use new variables if needed.
- Use the same algorithm(s) as in the code given above.
- Use proper F# syntax and optimization techniques as shown in the class.

Submission:
- Please make sure your programs run/compile!
- Save each program as a text file with extension .txt.
- You must upload problem1.txt and problem2.txt files to the Dropbox.
- Your submission must be unique.
- Late submissions are not accepted!

Mark Breakdown & Deductions:
Total: 5 points / per program
- Missing Functionality
- The program's algorithm, variables, etc are different from the code given.
- Program doesn't compile/run, or partial functionality is provided.