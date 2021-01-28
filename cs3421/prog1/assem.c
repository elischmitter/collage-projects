
/*
*  CS3421 Assignment 2
*  Name: ELI SCHMITTER
*
*/
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "assem.h"


int numberOfI;
int numberOfD;
int dataAdd;
//array of used labeles
label address[MAXINST];


int main() {
    instruction instructions[MAXINST];
    char line[MAXLINE];
    char lines[MAXINST][MAXLINE];
    int i=0;
    //move stdin to a Char **
    while (fgets(line, MAXLINE, stdin)) {
        strcpy(lines[i],line);
        i++;
    }
    lines[i][0]='\000';
    //first pass of the input
    firstPass(lines);
    //second pass of the input
    int l = secondPass( instructions,lines);
    printf("%d %d\n", numberOfI, numberOfD);
    for (int z = 0; z <= l; z++) {
        printf("%08x\n", instructions[z].data);
    }
}

int firstPass(char lines[MAXINST][MAXLINE]) {
    int currAddress = -4;
    int inData = 0;
    char dir[4];
    char lab[10];
    int count=0;
    while (lines[count][0] !='\000') {
        if (sscanf(lines[count], " .%s", dir) == 1) {
            if (strcmp(dir, "text") == 0) {
                inData = 0;
            } else if (strcmp(dir, "data") == 0) {
                dataAdd=currAddress+4;
                inData = 1;
            }

        } else {
            if (!inData) {
                numberOfI++;
                currAddress = currAddress + 4;
               // printf("%d: %s",currAddress,line);
            } else {
                numberOfD++;
                currAddress = currAddress + 4;
              //  printf("%d: %s",currAddress,line);
            }
        }
        if (lines[count][0] != ' ' && lines[count][0] != '\t') {
            sscanf(lines[count], "%11s ", lab);
            int len = strlen(lab);
            int sum= makeHash(lab,len);
            sum = sum % MAXLINE;
            strcpy(address[sum].name, lab);
            address[sum].add = currAddress;

//            int index = currAddress;
//            printf("addy:%d, lab:%s, sum:%d\n", index, lab,sum);

        }
    count++;
    }
    return 0;
}
//a "hash" function for looking up labels may do
int makeHash(char *str, size_t len) {
    int sum = 0;
    for (int i = 0; i < len; i++) {
        sum = sum + str[i];
    }
    sum = sum % MAXLINE;
    return sum;
}
// second pass of code
int secondPass( instruction* instructions,char lines[MAXINST][MAXLINE]) {

    char dir[4];
    int inst = 0;
    int i;
    int count=0;
    int b;
    char line[MAXLINE], oper[MAXNAME + 1], rs[MAXREG + 1], rt[MAXREG + 1], rd[MAXREG + 1], currlabel[11], *S;
    char inData = 0;
    while (lines[count][0] !='\000') {
        if (lines[count][0] != ' ' && lines[count][0] != '\t') {
            sscanf(line, "%11s: ", currlabel);
            S = lines[count] + strlen(currlabel) + 2;
        } else {
            S = lines[count];
        }

        if (sscanf(S, " .%s", dir) == 1) {
            if (strcmp(dir, "text") == 0) {
                inData = 0;
            } else if (strcmp(dir, "data") == 0) {
                inData = 1;
            }
        }
        if (inData == 1) {
            if (sscanf(line, "%10s", oper) == 1) {
                if (strcmp(oper, ".word") == 0) {
                    char str[10];
                    while (sscanf(line, "%s ", str) == 1) {
                        int i = atoi(str);
                        printf("%d", i);
                        instructions[inst].data = i;
                        inst = inst + 1;
                        printf("inc");
                    }
                }
                int b;
                if (sscanf(line, "%10s %d", oper, &b) == 2) {
                    for (int j = 0; j < i; j++) {
                        instructions[inst].data = 0;
                        inst = inst + 1;
                        printf("inc");
                    }
                }
            }
        } else {
            if (sscanf(S, "%10s $%5[^,], $%5[^,], $%5s", oper, rd, rs, rt) == 4) {
                //R type
                //printf("R\n");
                int out[2] = {0, 0};
                strToInt(oper, out);
                int RD = regToInt(rd);
                int RS = regToInt(rs);
                int RT = regToInt(rt);
                instructions[inst].data = (RD << 11) + (out[0] << 26) + (RS << 21) + (RT << 16) + out[1];
                inst = inst + 1;

            } else if (sscanf(S, "%10s $%5[^,],$%5[^,],%d ", oper, rt, rs, &i) == 4) {
                //I type
                //printf("I\n");
                int out[2] = {0, 0};
                strToInt(oper, out);
                int RS = regToInt(rs);
                int RT = regToInt(rt);
                instructions[inst].data = (out[0] << 26) + (RS << 21) + (RT << 16) + i;
                inst = inst + 1;
            } else if (sscanf(S, "%10[^:]: %10s $%5[^,], $%5[^,], $%5s ", currlabel, oper, rd, rt, rs) == 5) {
                //R type
                int out[2] = {0, 0};
                strToInt(oper, out);
                int RD = regToInt(rd);
                int RS = regToInt(rs);
                int RT = regToInt(rt);
                instructions[inst].data = (out[0] << 26) + (RS << 21) + (RT << 16) + (RD << 11) + out[1];
                inst = inst + 1;

            } else if (sscanf(S, "%10s  $%10[^,],%5[^(]($%5[^)])", oper, rt, currlabel, rs) == 4) {
                //I type with offset lab
                int out[2] = {0, 0};
                strToInt(oper, out);
                int RS = regToInt(rs);
                int RT = regToInt(rt);
                strcat(currlabel, ":");
                b = makeHash(currlabel, strlen(currlabel));
                instructions[inst].data = (out[0] << 26) + (RS << 21) + (RT << 16) + (address[b].add-dataAdd);
                inst = inst + 1;
            } else if (sscanf(S, "%10s  $%10[^,],$%10[^,],%5s)", oper, rs, rt, currlabel) == 4) {
                //branches
                int out[2] = {0, 0};
                strToInt(oper, out);
                int RS = regToInt(rs);
                int RT = regToInt(rt);
                strcat(currlabel, ":");
                int b = makeHash(currlabel, strlen(currlabel));
                instructions[inst].data = (out[0] << 26) + (RS << 21) + (RT << 16) + (address[b].add/4 - (i+2) );
                inst = inst + 1;
            } else if (sscanf(S, "%10s %10s", oper, currlabel) == 2) {
                //j type
                //printf("J\n");
                //some time syscalls end up here
                if (strcmp(oper, "syscall") == 0) {
                    //                  printf("syscall\n");
                    instructions[inst].data = 0xC;
                    inst = inst + 1;
                } else {
                    int out[2] = {0, 0};
                    strToInt(oper, out);
                    // printf("%5s ",currlabel);
                    strcat(currlabel, ":");
                    int b = makeHash(currlabel, strlen(currlabel));
                    instructions[inst].data = (out[0] << 26) + (address[b].add/4);
                    inst = inst + 1;
                }
                //syscall
            } else if (sscanf(S, "%7s n", oper) == 1) {
                if (strcmp(oper, "syscall") == 0) {
                    instructions[inst].data = 0xC;
                    inst++;
                }
            }
        }
        count++;
    }

    return inst;
}

//turn regs into their values
int regToInt(char *in) {
    if (strcmp("zero", in) == 0)
        return 0;
    else if (strcmp("at", in) == 0)
        return 1;
    else if (strcmp("v0", in) == 0)
        return 2;
    else if (strcmp("v1", in) == 0)
        return 3;
    else if (strcmp("a0", in) == 0)
        return 4;
    else if (strcmp("a1", in) == 0)
        return 5;
    else if (strcmp("a2", in) == 0)
        return 6;
    else if (strcmp("a3", in) == 0)
        return 7;
    else if (strcmp("t0", in) == 0)
        return 8;
    else if (strcmp("t1", in) == 0)
        return 9;
    else if (strcmp("t2", in) == 0)
        return 10;
    else if (strcmp("t3", in) == 0)
        return 11;
    else if (strcmp("t4", in) == 0)
        return 12;
    else if (strcmp("t5", in) == 0)
        return 13;
    else if (strcmp("t6", in) == 0)
        return 14;
    else if (strcmp("t7$", in) == 0)
        return 15;
    else if (strcmp("s0", in) == 0)
        return 16;
    else if (strcmp("s1", in) == 0)
        return 17;
    else if (strcmp("s2", in) == 0)
        return 18;
    else if (strcmp("s3", in) == 0)
        return 19;
    else if (strcmp("s4", in) == 0)
        return 20;
    else if (strcmp("s5", in) == 0)
        return 21;
    else if (strcmp("s6", in) == 0)
        return 22;
    else if (strcmp("s7$", in) == 0)
        return 23;
    else if (strcmp("t8", in) == 0)
        return 24;
    else if (strcmp("t9", in) == 0)
        return 25;
    else if (strcmp("k0", in) == 0)
        return 26;
    else if (strcmp("k1", in) == 0)
        return 27;
    else if (strcmp("gp", in) == 0)
        return 28;
    else if (strcmp("sp", in) == 0)
        return 29;
    else if (strcmp("fp", in) == 0)
        return 30;
    else if (strcmp("ra", in) == 0)
        return 31;
    else
        return -1;
}
// operations into opcodes and function numbers
void strToInt(char *in, int *out) {
    if (strcmp("addu", in) == 0) {
        out[0] = 0;
        out[1] = 33;
        return;
    }
    if (strcmp("addiu", in) == 0) {
        out[0] = 9;
        out[1] = 0;
        return;
    }
    if (strcmp("and", in) == 0) {
        out[0] = 0;
        out[1] = 36;
        return;
    }
    if (strcmp("beq", in) == 0) {
        out[0] = 4;
        out[1] = 0;
        return;
    }
    if (strcmp("bne", in) == 0) {
        out[0] = 5;
        out[1] = 0;
        return;
    }
    if (strcmp("div", in) == 0) {
        out[0] = 0;
        out[1] = 26;
        return;
    }
    if (strcmp("j", in) == 0) {
        out[0] = 2;
        out[1] = 0;
        return;
    }
    if (strcmp("lw", in) == 0) {
        out[0] = 35;
        out[1] = 0;
        return;
    }
    if (strcmp("mfhi", in) == 0) {
        out[0] = 0;
        out[1] = 16;
        return;
    }
    if (strcmp("mflo", in) == 0) {
        out[0] = 0;
        out[1] = 18;
        return;
    }
    if (strcmp("or", in) == 0) {
        out[0] = 0;
        out[1] = 37;
        return;
    }
    if (strcmp("slt", in) == 0) {
        out[0] = 0;
        out[1] = 42;
        return;
    }
    if (strcmp("subu", in) == 0) {
        out[0] = 0;
        out[1] = 35;
        return;
    }
    if (strcmp("sw", in) == 0) {
        out[0] = 43;
        out[1] = 0;
        return;
    }
    if (strcmp("syscall", in) == 0) {
        out[0] = 0;
        out[1] = 12;
        return;
    } else {
        out[0] = -1;
        out[1] = -1;
        return;
    }
}
