//
// Created by eli on 10/9/2020.
//

#ifndef ASSEM_H
#define ASSEM_H
#include <stdio.h>
#include <stdint.h>
#include <string.h>
#include <stdlib.h>
#define MAXLINE 80
#define MAXNAME 10
#define MAXREG 5
#define MAXINST 32768
//instruction typedef
typedef union {
    struct Rtype {
        uint8_t opcode: 6;
        uint8_t rs: 5;
        uint8_t rt: 5;
        uint8_t rd: 5;
        uint8_t shamt: 5;
        uint8_t funct: 6;
    } R;
    struct Itype {
        uint8_t opcode: 6;
        uint8_t rs: 5;
        uint8_t rt: 5;
        uint16_t immediate;
    } I;
    struct Jtype {
        uint8_t opcode: 6;
        uint32_t immediate: 16;
    } J;
    // was going to use bitfields to make the instructions but padding mad it inposible
    uint32_t data;

} instruction;
//label typedef
typedef struct label {
    int add;
    char name[10];
} label;
int regToInt(char *in);

int makeHash(char *str, size_t len);

void strToInt(char *in, int *out);

int firstPass(char lines[MAXINST][MAXLINE]);

int secondPass(instruction instructions[], char lines[MAXINST][MAXLINE]);
#endif //PROG1_ASSEM_H
