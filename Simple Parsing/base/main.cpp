#include<iostream>
#include<sstream>
#include<fstream>
#include<stdlib.h>
#include"inout.h"
int main() {
	in_out();
	while (std::getline(inFile, sentence)) {
		ss.clear();
		ss.str("");
		ss.str(sentence);
		(std::getline(inFile, text));
		while (std::getline(ss, word, ',')) {
			while (std::getline(ss, word1, ',')) {
				while (std::getline(ss, word2, ',')) {
					icounter1 = atoi(word.c_str());
					icounter2 = atoi(word1.c_str());
					icounter3 = atoi(word2.c_str());
					counter = icounter1 + icounter2 + icounter3;
					for (int i = 0; i < counter; i++) {
						outFile << text << ' ';
					}
				outFile << std::endl;					}
			}
		}
	}
}

