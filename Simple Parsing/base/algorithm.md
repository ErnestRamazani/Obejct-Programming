Ernest Ramazani
IUPUI/CSCI24000/Spring22

HW5 Algorithm  

This algorithm is for writting a program that can convert string into integer 
and write other string from these integers. In fact, we will have a txt file, 
named 'input' that will have lines of numbers separated by commas and other lines
of plain strings. Our goal is to add up these numbers and and display the plain 
strings as many time as the sum in a new txt file called output

to do it, we will import all the necesaary library, iostream, fstream, sstream 
we declare all of our variable, 
we create our function that will open and close files (input and output files)
in out main funtion, we call our first function
we will use 4 while loop 
each while loop will help us to use the getline built in function
this function will help us to get the plain string and all 3 integer separated by commas
we will use the function atoi c>str to convert string to interger 
add up all 3 integers 
we use a for loop that will display n times the plain string for n sum 
the new plain strings will be display in a new text file called output.txt 


//////////////////////////////////////


For the makefile version, 
we will use the same algorithm but we will need one header 
the header file will have all of our declarations and our only void function 
our main file, will attach the header file, and will run the main file from the algorithm 

