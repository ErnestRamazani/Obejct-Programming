/*
OPERATING SYSTEM 
Project Number 2: Matrices Multiplications
Group 2 

Instructor: Dongsoo Kim
Author: Joshua Castagna 
Reviewers: Shane Butler, Thomas Chandler, Ernest Ramazani
Filename: main.cpp 
Date: Mars 2nd 2023 

Purpose: This C++ program performs matrix multiplication using multiple 
threads to increase the computation speed. It reads two input matrices 
from files, creates multiple threads to compute the product of the matrices, 
and writes the output matrix to a file. The program also computes basic 
statistics of the output matrix and prints them to the console. 
The main() function is responsible for coordinating the execution of the 
program and freeing the allocated memory at the end. The purpose of the 
program is to demonstrate the use of threads to parallelize the computation 
of a computationally expensive task.
*/


#include <iostream>
#include <chrono>
#include <thread>
#include <cmath>
#include <vector>
#include <numeric>
#include <algorithm>
#include "matrix.h"

using namespace std;

// Define a struct to store the arguments to each thread
struct ThreadArgs {
    double** A;
    double** B;
    double** C;
    int startRow;
    int endRow;
    int n;
    int m;
};

// Define a function to compute the product of two matrices in a given range of rows
void multiplyRange(ThreadArgs& args) {
    for (int i = args.startRow; i < args.endRow; i++) {
        for (int j = 0; j < args.n; j++) {
            args.C[i][j] = 0.0;
            for (int k = 0; k < args.m; k++) {
                args.C[i][j] += args.A[i][k] * args.B[k][j];
            }
        }
    }
}
int main() {
    int n, m;
    double** A = read2d("a.mat", n, m);
    double** B = read2d("b.mat", n, m);
    double** C = allocate2d(n, n);

    // Start the timer
    auto start = chrono::high_resolution_clock::now();

    int numThreads = thread::hardware_concurrency();
    cout << "Number of available threads: " << numThreads << endl;

    // Spawn the threads
    vector<thread> threads(numThreads);
    int rowsPerThread = ceil(double(n) / numThreads);
    vector<ThreadArgs> args(numThreads);
    for (int i = 0; i < numThreads; i++) {
        args[i] = { A, B, C, i * rowsPerThread, min((i + 1) * rowsPerThread, n), n, m };
        threads[i] = thread(multiplyRange, ref(args[i]));
    }

    // Join the threads
    for (int i = 0; i < numThreads; i++) {
        threads[i].join();
    }

    // Compute the elapsed time
    auto end = chrono::high_resolution_clock::now();
    auto duration = chrono::duration_cast<chrono::microseconds>(end - start);
    cout << "Elapsed time: " << duration.count() << " microseconds" << endl;

    // Compute basic statistics
    double sum = 0.0;
    double sumSq = 0.0;
    double numElements = double(n) * n;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            sum += C[i][j];
            sumSq += C[i][j] * C[i][j];
        }
    }
    double average = sum / numElements;
    double variance = (sumSq / numElements) - (average * average);
    double stdev = sqrt(variance);
    cout << "Number of elements: " << numElements << endl;
    cout << "Sum: " << sum << endl;
    cout << "Average: " << average << endl;
    cout << "Standard deviation: " << stdev << endl;

    // Write the result matrix to file
    write2d("c.mat", C, n, n);

    // Print the result matrix to console
    cout << "\nPrinting a sample of c.mat on the Console\n" << endl;
    double** result = read2d("c.mat", n, n);
    print2d("", result, n, n);
    free2d(result);

    // Free the matrices
    free2d(A);
    free2d(B);
    free2d(C);

    return 0;
}
