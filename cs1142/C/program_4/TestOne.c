/********************************************************************
DO NOT MODIFY THIS FILE

This program call functions with a circular linked list with one node.
Example compilation and usage:
% gcc  -std=c99 -pedantic -Wall -Wextra -O1 TestOne.c TSP.c -o TestOne
% ./TestOne
printNode
(1.1000, 3.3333)
printTour
(1.1000, 3.3333)
tourSize
1
tourDistance
0.0000
distance(first, first)
0.0000
freeTour
done
*********************************************************************/

#include "TSP.h"

#include <stdio.h>
#include <stdlib.h>

int main(void)
{
   // Create the circular linked list we are going to test
   Node* first = malloc(sizeof(Node));
   first->x = 1.1;
   first->y = 3.33333333;
   first->next = first;
   
   printf("printNode\n");
   printNode(first);
   
   printf("printTour\n");
   printTour(first);
   
   printf("tourSize\n");
   printf("%d\n", tourSize(first));
   
   printf("tourDistance\n");
   printf("%.4f\n", tourDistance(first));
   
   printf("distance(first, first)\n");
   printf("%.4f\n", distance(first, first));
   
   printf("freeTour\n");
   freeTour(first);

   printf("done\n");
   
   return 0;
}
