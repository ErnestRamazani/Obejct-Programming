//bubble.c
////famous bubble sort
////implement the swap algorithm with pointers

#include <stdio.h>
#define MAX 9

//function prototypes
void printValues();
void sort();
void swap(int*, int*);

int values[] = {7, 3, 9, 4, 6, 1, 2, 8, 5};

void swap (int *x, int *y){
	int k;
	k = *x;
	*x = *y;
	*y = k;
}
void printValues(){
	printf("\n [");
	for (int i = 0; i<MAX; i++){
		printf(" %d", values[i]);
	}
	printf ("]");
}

void sort (){
        for (int i = 0; i<MAX; i++){
                for (int j= 0; j<MAX-i-1; j++){
                        if (values[j] > values[j+1]){
                            swap(&values[j], &values[j+1]);
			    printValues();   
			                      
                         }
                }
        }
	
}
int main(){
	printf("Before: \n");
	printValues();
	sort();
	printf("\n");
	printf("After: \n");
	printValues();

	return(0);
} // end main`



