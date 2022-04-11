#include "color.h"

Color::Color() 
    : _red{},_green{},_blue{},_reset{true} {}
Color::Color(int red, int green, int blue) 
    : _red{red},_green{green},_blue{blue},_reset{false}{}

std::string Color::colorize(std::string text) {    
    std::ostringstream temp;
    temp << *this << text << "\x1b[0m";
    return temp.str();
}
std::string Color::to_string() {
    if (_reset) return "(reset)";

    std::ostringstream temp;
    temp << "(" << _red << ","
             <<  _green << ","
             <<   _blue << ")";
    return temp.str();
}
std::ostream& operator<<(std::ostream& ost, Color color) {
    if (color._reset) return ost << "\x1b[0m";
    else {
        return ost << "\x1b[38;2;"  << color._red   << ";"
            << color._green << ";"  << color._blue  << "m";
    }
}

int main(int argc, char const **argv) {
    if (argc == 1) {
        std::cout << "usage: " << argv[0] << " <filename>\n";
        return -1;
    }

    std::ifstream file{argv[1]};

    if (!file) {
        std::cout << "Unable to open file \"" << argv[1] << "\"\n";
        return 2;
    }

    int r{20},g{110},b{50};
    for(std::string line; getline(file, line); r %= 256, g %= 256, b %= 256) {
        Color color{r,g,b};
        std::cout << color.colorize(line) << std::endl;
        r += 14; g += 5; b += 12;
    }
    
    return 0;
}