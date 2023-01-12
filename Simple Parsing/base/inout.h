#ifndef INOUT_H_EXISTS
#define INOUT_H_EXISTS
#include <fstream>
#include <sstream>

std::ofstream outFile;
std::ifstream inFile;
std::stringstream ss;
std::string sentence;
std::string word, word1, word2;
int counter, icounter1, icounter2, icounter3;
std::string text;
void in_out() {

	inFile.open("input.txt");
	outFile.open("output.txt");


}


#endif
