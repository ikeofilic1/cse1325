CXXFLAGS = --std=c++17

all: color

color: color.o *.h
	$(CXX) $(CXXFLAGS) color.o -o color

color.o: color.cpp *.h
	$(CXX) $(CXXFLAGS) -c color.cpp -o color.o

color.gch: color.h
	$(CXX) $(CXXFLAGS) -c color.h -o color.gch

clean:
	rm -f *.o *.gch ~* a.out test color example
