Ernest Ramazani 
Homework 6 
Algorithm

This is an algorithm to help me to write a program that takes data from 
a csv files, and print to 2 new files. We are going to use the heap memory 
to help us to save file temporarily and then delete them. 
first, we need to know how our csv data look like. the data is about records of 50 students.
we wiil need to create 3 classes for different types of records. the first class(Student) will 
be in charge of the record of names and gpa, the second one, Address, will be in charge of students addresses, 
and the last one, Date, is for dates. The entire program will be in the class Student, so it will 
takes two instances, Date and Address. All the data will have pointers from the file will be pointers, so 
that it can be stored in the heap and get the value of these data later. After getting the value of these 
data from the heap, we will need to delete them with the destructor in order to free up the memory. 
We will use the set and get logic to get our data and their value from the heap. we will have two constructor
for each classes; one of the must be a null constructor. For each classes, the second constructor will have 
as parameters the data from the file. Classes Date and Address will be used in Student class by using pointers. 
We will use the getline in built_in function to get data from from the file. Since the set and get methods
are used individually for each type of data in eah classes, we wiil need tocall them in the 
Student class. After having the entire type of data with getline, we can print the out in our main function
The main function will manage the output. The output will be a 2d table array. we will have to create 
an student object on the heap that will execute all the Student functions. The main function must print 
2 files, Full and Short report. The full report will be a table with all students records and the 
short report will be a table just for first and last name. We need to delete the object created from the heap. 
  
