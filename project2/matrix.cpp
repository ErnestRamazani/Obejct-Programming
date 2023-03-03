/*
OPERATING SYSTEM
Project Number 2: Matrices Multiplications
Group 2

Instructor: Dongsoo Kim
Author: Joshua Castagna
Reviewers: Shane Butler, Thomas Chandler, Ernest Ramazani
Filename: matrix.cpp
Date: Mars 2nd 2023

Purpose: The matrix.cpp file contains the implementation of 
functions used to manipulate matrices in the C++ program. 
These functions include allocating and freeing memory for a 
two-dimensional matrix, reading and writing matrices to binary files, 
computing the sum, average, and standard deviation of matrix elements, 
and printing matrices to the console. The file is important for the 
program because it provides the implementation details for these functions.
*/

#include <iostream>
#include <fstream>
#include "matrix.h"
using namespace std;

// Allocate memory for a two-dimensional matrix
double** allocate2d(int rows, int cols)
{
    double** data;
    if (rows == 0 || cols == 0)
        data = nullptr;
    else {
        data = new double* [rows];
        double* temp = new double[rows * cols];
        for (size_t i = 0; i < rows; i++)
            data[i] = temp + i * cols;
    }
    return data;
}

// Free memory allocated for a two-dimensional matrix
void free2d(double** x)
{
    if (x != nullptr) {
        delete[] x[0];
        delete[] x;
    }
}

// Read a two-dimensional matrix from a binary file
double** read2d(string filename, int& nr, int& nc)
{
    ifstream fin;
    fin.open(filename, ios::binary);
    fin.read((char*)&nr, sizeof(int));
    fin.read((char*)&nc, sizeof(int));
    double** x = allocate2d(nr, nc);
    fin.read((char*)x[0], sizeof(double) * nr * nc);
    fin.close();
    return x;
}

// Write a two-dimensional matrix to a binary file
void write2d(string filename, double** x, int nr, int nc)
{
    ofstream fout;
    fout.open(filename, ios::binary);
    fout.write((char*)&nr, sizeof(int));
    fout.write((char*)&nc, sizeof(int));
    fout.write((char*)x[0], sizeof(double) * nr * nc);
    fout.close();
}

// Print a two-dimensional matrix to the console
// We are printing the matrix c.mat just written in the above
//function in order to have a sense of the matrix
void print2d(string msg, double** x, int nr, int nc)
{
    if (msg.length() > 0)
        cout << msg;
    cout << "(" << nr << "*" << nc << ")" << endl;

    for (int i = 0; i < nr; i++) {
        if (nr > 8 && i == 6) {
            cout << "...\n";
            i = nr - 2;
            continue;
        }

        if (nc < 9) {
            for (int j = 0; j < nc; j++)
                cout << x[i][j] << ((j + 1 == nc || (j + 1) % 8 == 0) ? "\n" : " ");
        }
        else {
            for (int j = 0; j < 6; j++)
                cout << x[i][j] << " ";
            cout << "... " << x[i][nc - 1] << "\n";
        }
    }
}