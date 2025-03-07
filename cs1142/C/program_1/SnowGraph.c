/***********************
 * Name: Eli Schmitter 
 * Username: epschmit
 * plots Snow fall data 
 *  
 */

#include <stdio.h>
#include <stdlib.h>
#include <math.h>
int main(int argc, char* argv[]){
  double total=0;
  /* printf("%i\n",argc); */
  double avg;  
  int i;
  int max=2;
  int min=2;

  if(argc==1){
    printf("SnowGraph <month1> <amount1> [<month2> <amount2>] ...\n");
    return 0;
  }
  for(i=2;i<argc;i=i+2){
    double snow=atof(argv[i]);
    /* printf("%s\n",argv[i]); */
    /* printf("%i\n",i); */
    if(snow<0){
      printf("All amounts must be non-negative!\n");
      return 0;
    }
    if(atof(argv[max])<atof(argv[i]))
      max=i;
    if(atof(argv[min])>atof(argv[i]))
      min=i;
    total=total+snow;
   }
  if(argc % 2==0){
    avg= total/((i-2)/2);
  }
  else{
    avg=total/((i-1)/2);  
  }
  printf("total snow %.2f, average snow %.2f\n",total,avg);
  if(argc%2==1){
    for(int i=1;i<argc;i=i+2){
      printf("     %s: ",argv[i]);
      for(int x=0; x<(int)round(atof(argv[i+1])); x++)
	printf("*");
      printf(" %.1f",atof(argv[i+1]));
      if(min-1==i)
	printf(" min");
      if(max-1==i)
	printf(" max");
      printf("\n");
    }
  }
  else{
    for(int i=1;i<argc-1;i=i+2){
      printf("     %s: ",argv[i]);
      for(int x=0; x<(int)round(atof(argv[i+1])); x++)
	printf("*");
      printf(" %.1f",atof(argv[i+1]));
      if(min-1==i)
	printf(" min");
      if(max-1==i)
	printf(" max");
      printf("\n");
    }

  }
}
