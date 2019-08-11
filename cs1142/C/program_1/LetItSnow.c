/***********************
 * Name: Eli Schmitter 
 * Username: epschmit
 * makes an mad lib type of thing 
 *  
 */
#include <stdio.h>
int main(int argc, char* argv []){
  if (argc < 7){
    printf("LetItSnow <noun> <adjective> <noun> <adjective> <verb> <verb> \n");
    return 1;
  }
  else {
    printf("Oh, the %s outside is %s,\n",argv[1],argv[2]);
    printf("But the %s is so %s,\n",argv[3],argv[4]);
    printf("And since we've no place to %s,\n",argv[5]);
    printf("Let it %s, let it %s, let it %s!\n",argv[6],argv[6],argv[6]);
    return 0;
       
	

}

}
