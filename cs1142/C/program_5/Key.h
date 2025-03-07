#ifndef KEY_H
#define KEY_H
#include <stdbool.h>
#include "Checksum.h"
typedef struct
{
  unsigned char* data;   // Stores the actual data for this Key
  int size;              // How many bytes long this Key is 
  int pos;               // Current position in the key to use for next encoding/decoding
} Key;

bool initKey(Key* key, const char* filename);
  
void uninitKey(Key* key);
unsigned char transformUsingKey(Key* key, unsigned char inByte, Checksum* inChecksum, Checksum* outChecksum, bool debug);

#endif
