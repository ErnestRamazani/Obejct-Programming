

#include<iostream>
#include<ctime>
#include <cstdlib>

//global variables which represents position of a horse in a race

char horse0[15] = { '0','.','.','.','.','.','.','.','.','.','.','.','.','.','.' };
char horse1[15] = { '1','.','.','.','.','.','.','.','.','.','.','.','.','.','.' };
char horse2[15] = { '2','.','.','.','.','.','.','.','.','.','.','.','.','.','.' };
char horse3[15] = { '3','.','.','.','.','.','.','.','.','.','.','.','.','.','.' };
char horse4[15] = { '4','.','.','.','.','.','.','.','.','.','.','.','.','.','.' };

void flip0() {
   srand(time(NULL));
    for (int i = 0, j = 0, k = 0, l = 0, m = 0; horse0[14] != 0 || horse1[14] != 1 || horse2[14] != 2 || horse3[14] != 3 || horse4[14] != 4; ) {
	std::cout<< "Press enter to continue"<<std::endl;
	if (std::cin.get()=='\n'){
	}

        int x0 = rand() % 2;
        int x1 = rand() % 2;
        int x2 = rand() % 2;
        int x3 = rand() % 2;
        int x4 = rand() % 2;
        if (x0 == 1) {

            std::cout << horse0 << std::endl;
            int temp = horse0[i];
            horse0[i] = horse0[i + 1];
            horse0[i + 1] = temp;
            i++;
        }
        else {
            std::cout << horse0 << std::endl;
        }
        if ((i + 1) == 16) {
            std::cout << "Horse 0 win" << std::endl;
            break;
        }
        if (x1 == 1) {

            std::cout << horse1 << std::endl;
            int temp = horse1[j];
            horse1[j] = horse1[j + 1];
            horse1[j + 1] = temp;
            j++;
        }
        else {
            std::cout << horse1 << std::endl;
        }
        if ((j + 1) == 16) {
            std::cout << "Horse 1 win" << std::endl;
            break;
        }
        if (x2 == 1) {
            std::cout << horse2 << std::endl;
            int temp = horse2[k];
            horse2[k] = horse2[k + 1];
            horse2[k + 1] = temp;
            k++;
        }
        if ((k + 1) == 16) {
            std::cout << "Horse 2 win" << std::endl;
            break;
        }
        else {
            std::cout << horse2 << std::endl;
        }
        if (x3 == 1) {
            std::cout << horse3 << std::endl;
            int temp = horse3[l];
            horse3[l] = horse3[l + 1];
            horse3[l + 1] = temp;

            l++;
        }
        else {
            std::cout << horse3 << std::endl;
        }
        if ((l + 1) == 16) {
            std::cout << "Horse 3 win" << std::endl;
            break;
        }
        if (x4 == 1) {

            std::cout << horse4 << std::endl;
            int temp = horse4[m];
            horse4[m] = horse4[m + 1];
            horse4[m + 1] = temp;
            m++;

        }

        else {
            std::cout << horse4 << std::endl;
        }
        if ((m + 1) == 16) {
            std::cout << "Horse 4 win" << std::endl;
            break;
        }
        std::cout << std::endl;
    }
}


int main() {
    int random_seed;
    std::cout << "Please enter a random seed: " << std::endl;
    std::cin >> random_seed;

    flip0();
}
