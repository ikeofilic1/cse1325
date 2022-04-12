#ifndef _COLOR_H
#define _COLOR_H

#include <iostream>
#include <string>
#include <sstream>
#include <fstream>

class Color {
  private:
    int  _red;
    int  _green;
    int  _blue;
    bool _reset;

  public:
    Color();
    Color(int, int, int);
    std::string to_string(void);
    std::string colorize(std::string);
    Color operator+(const Color&);
    Color operator-(const Color&);
    Color operator+(const int&);
    Color operator-(const int&);
    Color operator++(); //pre
    Color operator++(int); //post    
    Color operator--(); //pre
    Color operator--(int); //post
    friend std::ostream& operator<<(std::ostream&, Color); 
    static Color BLACK, BLUE, CYAN, GREEN, GREY, MAGENTA, RED, RESET, WHITE, YELLOW;       
};

#endif