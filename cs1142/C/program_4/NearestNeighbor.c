// **** DO NOT MODIFY THIS FILE ****
// This program creates a tour using the nearest neighbor method.
// Input is from points read in from standard input.
// NOTE: this program must be compiled with your TSP.c implementation file.
//
// Example compilation and usage:
//  % gcc  -std=c99 -pedantic -Wall -Wextra -O1 NearestNeighbor.c TSP.c -o NearestNeighbor
//  % ./NearestNeighbor < tsp8.txt
//  (0.0000, 0.0000)
//  (500.0000, 0.0000)
//  (700.0000, 0.0000)
//  (800.0000, 0.0000)
//  (900.0000, 0.0000)
//  (500.0000, 500.0000)
//  (800.0000, 500.0000)
//  (1000.0000, 0.0000)
//  Tour distance = 3378.8289
//  Number of points = 8

#include "TSP.h"

#include <stdio.h>

int main(void)
{
   // Start with an empty linked list
   Node* first = NULL;

   // Variables for reading in the next point from standard input
   double x = 0.0;
   double y = 0.0;

   // Read in points until we run out of data
   while (scanf("%lf %lf", &x, &y) == 2)
   {
      first = addNearestNeighbor(first, x, y);
   }

   printTour(first);
   printf("Tour distance = %.4f\n", tourDistance(first));
   printf("Number of points = %d\n", tourSize(first));

   freeTour(first);
   first = NULL;

   return 0;
}
