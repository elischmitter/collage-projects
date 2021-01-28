CC=gcc
CFLAGS=-g
SUBMIT=sim
SUBMITC=$(SUBMIT:=.c)
SUBMITH=$(SUBMIT:=.h)

all: $(SUBMIT) 

%: %.c
	$(CC) $(CFLAGS) -o $@ $@.c -g

.PHONY: clean package

package: $(SUBMITC)
	tar -cvzf hw4.tgz $(SUBMITH) $(SUBMITC) readme.txt Makefile 

clean:
	rm -f $(SUBMIT) hw4.tgz
