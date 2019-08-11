CC = gcc
CFLAGS = -std=c99 -pedantic -Wall -Wextra -O1 -g
LDFLAGS = -lm
default: Crypto

Crypto.o: 
	${CC} ${CFLAGS} -c Crypto.c

Key.o: 
	${CC} ${CFLAGS} -c Key.c

Checksum.o: 
	${CC} ${CFLAGS} -c Checksum.c

Crypto:	Checksum.o Key.o Crypto.o
	${CC} ${CFLAGS} Checksum.o Key.o Crypto.o -o Crypto ${LDFLAGS}
clean:
	rm Crypto Checksum.o Key.o Crypto.o
