/********************************************************************
DO NOT MODIFY THIS FILE

This program call functions with a circular linked list with two nodes.
Example compilation and usage:
% gcc  -std=c99 -pedantic -Wall -Wextra -O1 TestTwo.c TSP.c -o TestTwo
% ./TestTwo
printNode
(1.1000, 3.3333)
printTour
(1.1000, 3.3333)
(2.2000, 6.6667)
tourSize
2
tourDistance
7.0203
distance(first, second)
3.5101
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

   Node* second = malloc(sizeof(Node));
   second->x = 2.2;
   second->y = 6.66666666;

   first->next = second;
   second->next = first;
   
   printf("printNode\n");
   printNode(first);
   
   printf("printTour\n");
   printTour(first);
   
   printf("tourSize\n");
   printf("%d\n", tourSize(first));
   
   printf("tourDistance\n");
   printf("%.4f\n", tourDistance(first));
   
   printf("distance(first, second)\n");
   printf("%.4f\n", distance(first, second));
   
   printf("freeTour\n");
   freeTour(first);

   printf("done\n");
   
   return 0;
}
