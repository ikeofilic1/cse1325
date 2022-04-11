#include "color.h"

Color::Color(int red, int green, int blue) : 
    _red{red}, _green{green}, _blue{blue}{}

std::string Color::colorize(std::string text) {
    return "\x1b[38;2;" + std::to_string(_red) + ";"
        + std::to_string(_green) + ";" 
        + std::to_string (_blue) + "m"
        + text +  "\x1b[0m";

}
std::string Color::to_string() {
    std::ostringstream temp;
    temp << "(" << _red << ","
             <<  _green << ","
             <<   _blue << ")";
    return temp.str();
}

int main(int argc, char const **argv) {
    Color green   {7,230,7};
    Color yellow{254,221,0};
    Color pink {214,37,152};

    std::cout << green.colorize("Fluorescent green") << std::endl;
    std::cout << yellow.colorize("Banana yellow") << std::endl;
    std::cout << pink.colorize("Deeeeep pink") <<std::endl;

    int r,g,b;
    std::cout << "Enter red, green, and blue ints: ";
    std::cin >> r >> g >> b;

    Color color{r,g,b};
    std::cout << color.colorize(color.to_string()) <<std::endl;

    return 0;
}