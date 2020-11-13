CC=gcc
AR=ar
OBJECTS_MAIN=main.o
OBJECTS_LIB=power.o basicMath.o
FLAGS =-Wall -g

all:mymaths mymathd mains maind

mains:$(OBJECTS_MAIN) libmyMath.a
	$(CC) $(FLAGS) -o mains $(OBJECTS_MAIN) libmyMath.a
	
maind:$(OBJECTS_MAIN)
	$(CC) $(FLAGS) -o maind $(OBJECTS_MAIN) ./libmyMath.so
	
mymaths:$(OBJECTS_LIB)
	$(AR) -rcs libmyMath.a $(OBJECTS_LIB)
	
mymathd:$(OBJECTS_LIB)
	$(CC) -shared -o libmyMath.so $(OBJECTS_LIB)
		
main.o: myMath.h mainmyMath.c
	$(CC) $(FLAGS) -c -o main.o mainmyMath.c
			
basicMath.o: basicMath.c myMath.h
	$(CC) $(FLAGS) -c -o basicMath.o basicMath.c
		
power.o: power.c myMath.h
	$(CC) $(FLAGS) -c -o power.o power.c
	
.PHONY: clean all

clean:
	rm -f *.o *.a *.so mains maind
