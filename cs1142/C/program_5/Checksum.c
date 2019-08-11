#include "Checksum.h"
#include <stdio.h>
void initChecksum(Checksum* checksum){
  checksum->sum1=0;
  checksum->sum2=0;
}

void updateChecksum(Checksum* checksum, unsigned char byte){
  checksum->sum1=(checksum->sum1+byte)%255;
  checksum->sum2=(checksum->sum1+checksum->sum2)%255;
}


void getChecksum(Checksum* checksum, char str[]){
  int sum = (checksum->sum2*256)+checksum->sum1;
  sprintf(str, "%.4X", sum);
}

