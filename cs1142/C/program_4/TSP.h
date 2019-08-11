#ifndef __TSP_H__
#define __TSP_H__
// **** DO NOT MODIFY THIS FILE ****
// Defines the Node data type used for the circular linked list keeping track of a TSP tour.
// Also has function prototypes for the functions your should implement.

// Node of a linked list that stores (x, y) location of points.
typedef struct node
{
   double x;            // x-coordinate of this point in the tour
   double y;            // y-coordinate of this point in the tour
   struct node* next;   // Pointer to the next node in the linked list
} Node;

// Function prototypes, these should be implmented in TSP.c (do NOT implement in this file!)
void printNode(Node* node);
void printTour(Node* first);
int tourSize(Node* first);
double distance(Node* a, Node* b);
double tourDistance(Node* first);
void freeTour(Node* first);
Node* addNearestNeighbor(Node* first, double x, double y);
Node* addSmallestIncrease(Node* first, double x, double y);

#endif
