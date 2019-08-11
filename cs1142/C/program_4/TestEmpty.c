/********************************************************************
DO NOT MODIFY THIS FILE

This program call functions with a circular linked list with no nodes.
Example compilation and usage:
% gcc  -std=c99 -pedantic -Wall -Wextra -O1 TestEmpty.c TSP.c -o TestEmpty
% ./TestEmpty
printTour
tourSize
0
tourDistance
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
   Node* first = NULL;

   printf("printTour\n");
   printTour(first);
   
   printf("tourSize\n");
   printf("%d\n", tourSize(first));
   
   printf("tourDistance\n");
   printf("%.4f\n", tourDistance(first));
      
   printf("freeTour\n");
   freeTour(first);

   printf("done\n");
   
   return 0;
}
