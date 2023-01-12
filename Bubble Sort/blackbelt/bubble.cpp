#include <stdio.h>
void bubble (int *T, int n){
	int i, j, k, index; 
	for (i=0; i<n-1; i++){
		index=i;
		for(j=i+1;j<n;j++)
			if(T[index] > T[j])
				index= j; 
		if (index !=i){
			k= T[i];
			T[i]=T[index];
			T[index] =k; 
		}
	}
}
main(){
	int T[9] = {7,3,9,4,6,1,2,8,5};
	int i;
	printf("Before \n");
	for (i=0; i<9;i++)
		printf("T[%d] = %d \n", i,T[i]);
	bubble(T,9);
	printf("After \n");
	for (i=0; i<9; i++)
		printf("T[%d] = %d\n",i,T[i]);
}

