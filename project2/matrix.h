/*
OPERATING SYSTEM
Project Number 2: Matrices Multiplications
Group 2

Instructor: Dongsoo Kim
Author: Joshua Castagna
Reviewers: Shane Butler, Thomas Chandler, Ernest Ramazani
Filename: matrix.h
Date: Mars 2nd 2023

Purpose:The matrix.h header file declares the function prototypes, 
data types, and constants used in the matrix manipulation functions
implemented in matrix.cpp. Its purpose is to provide a consistent and
standardized way to access and use these functions throughout the program.
*/

#ifndef MATRIX_HH
#define MATRIX_HH

#include <string>
using namespace std;

double** allocate2d(int rows, int cols);
void free2d(double** x);

double** read2d(string filename, int& nr, int& nc);
void write2d(string filename, double** x, int nr, int nc);


void print2d(string msg, double** x, int nr, int nc); 

#endif
