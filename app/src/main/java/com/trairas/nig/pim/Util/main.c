#include <stdio.h>
#define T 1000000

int main()
{


int vetor[T];
int tmp=0;
int i;
int numeros=0;

/*
vetor[0] = 8;
vetor[1] = 7;
vetor[2] = 3;
vetor[3] = 5;

vetor[4] = 4;
vetor[5] = 3;
vetor[6] = 3;
vetor[7] = 2;
*/

printf("Prenchendo...");
for(i=0;i<T;i++){
vetor[i] = T-i;
}
printf("pronto.");


/*
for(i=0;i<T;i++){
printf("\n Vetor[%d] = %d ",i, vetor[i]);
}*/


for(numeros=0;numeros<T;numeros++){
for(i=0;i<8;i++){ if(i <  T & vetor[i] > vetor[i+1]){
tmp=-1;
tmp = vetor[i];
vetor[i] = vetor[i+1];
vetor[i+1] = tmp;
} } } printf("\nVendo lista ordenada > \n");

printf("Ordenação terminada> vendo");

/*
for(i=0;i<T;i++){
printf("\n Vetor[%d] = %d ",i, vetor[i]);
}*/


printf("\n\n");
return 0;
}
