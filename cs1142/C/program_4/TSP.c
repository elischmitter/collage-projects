// Replace the body of all the functions in this file.
// *** DO NOT CHANGE THE FUNCTION DECLARATIONS ****
/**************************************************
 * NAME: Eli Schmitter
 * USERNAME: epschmit
 *
 *
 *
 *
 **************************************************/
#include "TSP.h"     // Gets the Node data type and the function prototypes
#include <stdlib.h>  // Needed to get the NULL constant
#include <stdio.h>
#include <math.h>
#include <float.h>
// Print out the point stored at the given node.
// You can assume node is not NULL.
// Round to 4 decimal places and output a line feed (\n), e.g.:
// (1.2345, 6.7890)
void printNode(Node* node)
{
  printf("(%.4f, %.4f)\n",node->x,node->y);
}

// Print out all the points in the tour from first to last.
// Passed a pointer to the first node in the tour.
// If the first is NULL, doesn't print anything.
void printTour(Node* first)
{
  if(first != NULL){
    Node* curr = first;
    printNode(curr);
    while(curr->next!=first){
      curr=curr->next;
      printNode(curr);
    }
  }
}

// Get the number of points in the tour.
// Passed a pointer to the first node in the tour.
// If first is NULL, return a size of 0.
int tourSize(Node* first)
{
  if(first==NULL){
    return 0; 
  }
  Node* curr= first;
  int number =0;
  while(curr->next!=first){
    number =number+1;
    curr=curr->next;
  }
  number++;
  return number;
}

// Calculate the Euclidean distance between two nodes.
// You can assume both a and b are both not NULL.
double distance(Node* a, Node* b)
{
  double deltaX = ((a->x)-(b->x));
  double deltaY = ((a->y)-(b->y));
  return sqrt(pow(deltaX,2)+(pow(deltaY,2)));
}

// Calculate the total distance between all points in the tour.
// Since the tour is circular, this includes the distance from the last point back to the start.
// Passed a pointer to the first node in the tour.
// If first is NULL, return a tour length of 0.0.
double tourDistance(Node* first)
{
  if(first== NULL){
    return 0.0;
  }
  Node* curr = first;
  double dist = 0;
  do{
    dist = dist + distance(curr, curr -> next);
    curr = curr -> next;
  }while(curr -> next != first);
  dist = dist + distance(curr, curr -> next);
  return dist;
}

// Add a new point after the point that it is closest to.
// If there is a tie, insert it after the first such point you find.
// Passed a pointer to the first node in the tour, NULL if creating a new tour.
// Returns pointer to the first node in the linked list after the addition.
Node* addNearestNeighbor(Node* first, double x, double y)
{
  if(first == NULL){
    first = malloc(sizeof(Node));
    first -> x = x;
    first -> y = y;
    first -> next = first;
    return first;
  }
  Node* nearcurr = first;
  Node* curr = first -> next;
  Node* new = malloc(sizeof(Node));
  new -> x = x;
  new -> y = y;
  while(curr != first){
    if(distance(nearcurr,new) > distance(curr,new)){
      nearcurr = curr;
    }      
    curr = curr -> next;
  }
  new -> next = nearcurr -> next;
  nearcurr -> next = new;
  return first;
}

// Add a new point after the point where it results in the least increase in tour length.
// If there is a tie, insert it after the first such point you find.
// Passed a pointer to the first node in the tour, NULL if creating a new tour.
// Returns pointer to the first node in the linked list after the addition.
Node* addSmallestIncrease(Node* first, double x, double y)
{
  if(first==NULL){
    first=malloc(sizeof(Node));
    first -> x = x;
    first -> y = y;
    first -> next = first;
    return first;
  }
  Node* smallcurr=first;
  Node* curr=first;
  Node* new= malloc(sizeof(Node));
  new -> x = x;
  new -> y = y;
  double Dnew=0;
  double D=0;
  double smallD=DBL_MAX;
  do{
    D    = distance(curr, curr->next);
    Dnew = distance(curr, new) + distance(curr->next,new);
    if(Dnew-D<=smallD){
      smallcurr = curr;
      smallD=Dnew-D;
    }
    curr = curr -> next;
  }while(curr != first);
    D    = distance(curr, curr->next);
    Dnew = distance(curr, new) + distance(curr->next,new);
    if(Dnew-D<=smallD){
      smallcurr = curr;
      smallD=Dnew-D;
    }
 (new->next)=(smallcurr->next);
  (smallcurr->next)=new;
  return first;
}

// Deallocate all the memory of the Node structures in the linked list.
// Passed a pointer to the first node in the tour.
// If first is NULL, don't do anything.
void freeTour(Node* first)
{
  Node* curr=first;
  if(first!=NULL){ 
    while(curr->next != first){
      Node* temp=curr;
      curr=curr->next;
      free(temp);
    }
    free(curr);
 }
}

