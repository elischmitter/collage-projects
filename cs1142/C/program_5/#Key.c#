#include "Key.h"
#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include "Checksum.h"

bool initKey(Key* key, const char* filename){
  FILE* fp = fopen(filename,"r");
  if(fp == NULL){
    
    return false;
  }
  int number = 10;
  unsigned char* text = malloc(sizeof(unsigned char)*number);
  int size = 0;
  int ch;
  unsigned char* textr;
  while((ch = fgetc(fp)) != EOF){
    //printf("here");
    if(size>=number){
      number=number*2;
      textr=realloc(text,(size_t) number);
      if(textr==NULL){
	free(text);
	return false;
      }
    text=textr;
    }
    text[size]=ch;
    size=size+1;
  }
  key->data=text;
  key->size=size;
  key->pos=0;
  if(size==0){
    return false;
  }
  else{
    return true;
  }
  return true;
}

void uninitKey(Key* key){
  free(key->data);
}

unsigned char transformUsingKey(Key* key, unsigned char inByte, Checksum* inChecksum, Checksum* outChecksum, bool debug){
  unsigned char keyByte=(key->data)[key->pos];
  unsigned char outByte = inByte ^ keyByte;
  if(debug!=0){
    printf("%3u ^%4u ->%4u\n", inByte,keyByte, outByte);
  }
  updateChecksum(inChecksum,inByte);
  updateChecksum(outChecksum,outByte);
  key->pos=key->pos+1;
  if(key->pos==key->size){
    key->pos=0;
  }
  return outByte;
}

