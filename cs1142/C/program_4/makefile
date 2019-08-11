# Makefile for the TSP assignment

# These variables control the specifics of the compilation
CC = gcc
CFLAGS = -std=c99 -pedantic -Wall -Wextra -O1
LDFLAGS = -lm 

# When you type "make", it will default to trying to build all the programs
default: NearestNeighbor SmallestIncrease TestEmpty TestOne TestTwo

# Build the object code for just the TSP functions
TSP.o: TSP.c TSP.h
	${CC} ${CFLAGS} -c TSP.c

# To build just the NearestNeighbor executable, type "make NearestNeighbor"
NearestNeighbor: NearestNeighbor.c TSP.o
	${CC} ${CFLAGS} NearestNeighbor.c TSP.o -o NearestNeighbor ${LDFLAGS}

# To build just the SmallestIncrease executable, type "make SmallestIncrease"
SmallestIncrease: SmallestIncrease.c TSP.o
	${CC} ${CFLAGS} SmallestIncrease.c TSP.o -o SmallestIncrease ${LDFLAGS}

# The remaining executables are for testing purposes

# To build just the TestEmpty executable, type "make TestEmpty"
TestEmpty: TestEmpty.c TSP.o
	${CC} ${CFLAGS} TestEmpty.c TSP.o -o TestEmpty ${LDFLAGS}

# To build just the TestOne executable, type "make TestOne"
TestOne: TestOne.c TSP.o
	${CC} ${CFLAGS} TestOne.c TSP.o -o TestOne ${LDFLAGS}

# Rule for building the TestOne executable
# To build just the TestOne executable, type "make TestOne"
TestTwo: TestTwo.c TSP.o
	${CC} ${CFLAGS} TestTwo.c TSP.o -o TestTwo ${LDFLAGS}

# If you type "make clean", it will remove all previously built executable files
clean:
	rm NearestNeighbor SmallestIncrease TestEmpty TestOne TestTwo TSP.o
