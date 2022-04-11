#include "color.h"

Color::Color() 
    : _red{},_green{},_blue{},_reset{true} {}
Color::Color(int red, int green, int blue) 
    : _red{red},_green{green},_blue{blue},_reset{false}{}

std::string Color::colorize(std::string text) {
    std::ostringstream temp;
    operator<<(temp, *this);
    temp << text << "\x1b[0m";
    return temp.str();
    /*return "\x1b[38;2;" + std::to_string(_red) + ";"
        + std::to_string(_green) + ";" 
        + std::to_string (_blue) + "m"
        + text +  "\x1b[0m";*/
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
    Color reset{};
    Color green   {7,230,7};
    Color yellow{254,221,0};
    Color pink {214,37,152};

    std::cout << green  << "Fluorescent green" << reset << std::endl;
    std::cout << yellow << "Banana yellow"     << reset << std::endl;
    std::cout << pink   << "Deeeeep pink"      << reset << std::endl;

    int r,g,b;
    std::cout << "Enter red, green, and blue ints: ";
    std::cin >> r >> g >> b;

    Color color{r,g,b};
    std::cout << color << color.to_string();
    std::cout << reset.to_string() << reset << std::endl;

    return 0;
}