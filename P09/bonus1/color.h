#ifndef _COLOR_H
#define _COLOR_H

#include <iostream>
#include <string>
#include <sstream>

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
    friend std::ostream& operator<<(std::ostream&, Color);
        
};

#endif