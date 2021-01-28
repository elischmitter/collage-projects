oCC=gcc
CFLAGS=-g
SUBMIT=assem
SUBMITC=$(SUBMIT:=.c) $(SUBMIT:=.h)

all: $(SUBMIT) 

%: %.c
	$(CC) $(CFLAGS) -o $@ $@.c

.PHONY: clean package

package: $(SUBMITC)
	tar -cvzf hw2.tgz $(SUBMITC) readme.txt Makefile

clean:
	rm -f $(SUBMIT) hw2.tgz
