Heap: address.o date.o student.o main.o 
	g++ address.o date.o student.o main.o -o Heap
address.o: address.cpp address.h 
	g++ -c address.cpp
date.o: date.cpp date.h
	g++ -c date.cpp
student.o: student.cpp student.h
	g++ -c student.cpp
main.o: main.cpp student.h
	g++ -c main.cpp
clean:
	rm -f *.o
	rm Heap
run: Heap
	./Heap
