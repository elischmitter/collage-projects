/***************************************************
 *Name: Eli Schmitter 
 *Username: epschmit
 *Discrtion:Shows snow fall from a given rain and temp
 *
 *
 *
 ***************************************************/
#include <stdlib.h>
#include<stdio.h>
int main(int argc,char* argv[]){

  if (argc<3){
    printf("RainToSnow <inches of rain> <Fahrenheit temperature>\n");
  }
  else{
    double rain = atof(argv[1]);
    double temp = atof(argv[2]);    
    if (rain < 0){
      printf("Amount of rain must be non-negative!\n");  
    }  
    
    double snow;
    if(temp>=32)
      snow=rain*0;
    else if (temp<32&&temp>=27)
      snow=rain*10;
    else if (temp<27&&temp>=20)
      snow=rain*15;
    else if (temp<27&&temp>=15)
      snow=rain*20;
    else if (temp<15&&temp>=10)
      snow=rain*30;
    else if (temp<10&&temp>=0)
      snow=rain*40;
    else if (temp<0&&temp>=-20)
      snow=rain*50;
    else
      snow=rain*50;

    printf("%.1f inches of rain at %.1fF would result in %.1f inches of snow.\n",rain,temp,snow);
    float snowp=(snow/108.0)*100.0;
    if(snowp<100)
      printf("This would cover about %.0f%% of the Husky statue!\n",snowp);
    else
      printf("This would cover all of the Husky statue!\n");
    return 1;
  }
}
