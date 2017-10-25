Chenxiao Guan
cguan3@u.rochester.edu
CSC-172 Project #2, University of Rochester
I did not collaborate with anyone in this project.

Run "URCalculator.java".

This is a calculator that takes users inputs of a equation and return the result, or a specific command to store or clear the values of variables.

WARNING: PLEASE DON'T TYPE ANY SPACE IN BETWEEN YOUR INPUT OR IT WILL CAUSE ERROR

The calculator supports six kinds of operations:
1. +
2. -
3. *
4. /
5. ()
6. []
7. {}
6. =

By using "+", "-", "*", "/", "()", "[]" or "{}" to numbers, you will get the result of the equation if your input is well formed in mathematical expression.

You can also use variables to do the operation. Use "=" to assign the value of a specific variable. For example, if the user types "a=4" and the value of "a" will then be 4 so that "a+3" will return 7. You can store any numbers of variables you want.

You can type "exit" to quit the program at anytime.


store variables:
1. Don't use pure number string for the variable name. If that's the case, the program won't read the number string as the name of variable. Instead, the program will just use the value of the given string to do the calculation. However, you can use names such as "a3" or "1234B" as names of variables.
2. There is a difference between "a" and "A" - i.e., the capitalization of the variable name makes a difference. They can store two different values. Please be careful when you are using variables to do the calculation.


clear memory:
1. use the command "clearall" to clear all the variables that have been stored before.
2. use the command "cleara" to clear the memory of "a", and same for other variable names. Keep in mind that please don't include any space in between your command input or the program won't work properly.
