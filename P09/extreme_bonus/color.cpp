#include "color.h"

Color Color::BLACK{0,0,0};
Color Color::BLUE{0,0,255};
Color Color::CYAN{0,255,255};
Color Color::GREEN{0,255,0};
Color Color::GREY{128,128,128};
Color Color::YELLOW{255,255,0};
Color Color::MAGENTA{255,0,255};
Color Color::RED{255,0,0};
Color Color::WHITE{255,255,255};
Color Color::RESET{};

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
Color Color::operator+(const Color& add) {
    if (add._reset || this->_reset) 
        return this->_reset ? add : *this;

    int r = add._red   + this->_red, 
        g = add._green + this->_green, 
        b = add._blue  + this->_blue;

    if (r < 0) r = 0;
    else if (r > 255) r = 255;
    if (g < 0) g = 0;
    else if (g > 255) g = 255;
    if (b < 0) b = 0;
    else if (b > 255) b = 255;

    return Color{r,g,b};
}
Color Color::operator-(const Color& sub) {
    return *this + Color{sub._red*-1, sub._green*-1, sub._blue*-1};
}

Color Color::operator-(const int& num) {
    return *this + (-1*num);
}
Color Color::operator+(const int& num) {
    return *this + Color{num,num,num};
}
Color Color::operator++() { //pre
    if (_reset) return *this;
    if (_red != 255) ++_red;
    if (_green != 255) ++_green;
    if (_blue != 255) ++_blue;
    return *this;
}
Color Color::operator++(int) {
    Color temp{_red,_green,_blue};
    ++(*this);
    return temp;
}

Color Color::operator--() { //pre
    if (_reset) return *this;
    if (_red != 0) --_red;
    if (_green != 0) --_green;
    if (_blue != 0) --_blue;
    return *this;
}
Color Color::operator--(int) {
    Color temp{_red,_green,_blue};
    --(*this);
    return temp;
}

int main(int argc, char const **argv) {
    Color start = Color::GREY;
    std::cout << "Testing color mixing...\n";
    std::cout << Color::BLACK << "Muhaha!! " << Color::RESET << "Yup, reset still works\n" ;
    std::cout << Color::CYAN + Color::BLACK  << "Should be cyan\n";
    std::cout << Color::RED + Color::BLUE << "Magenta!!\n";
    std::cout << Color::WHITE + Color::RESET << "White as snow\n";
    std::cout << Color::MAGENTA - Color::BLUE << "This in red?\n" << Color::RESET;

    std::cout << "\nTesting darkening by 10's...\n";
    for (int i = 0; i < 150; i += 10) {
        Color use = start - i;
        std::cout << use.colorize(use.to_string()) << " ";
    }
    std::cout << "\n\nTesting lightening by 10's...\n";
    for (int i = 0; i <= 130; i += 10) {
        Color use = start + i;
        std::cout << use.colorize(use.to_string()) << " ";
    }

    std::cout << "\n\nTesting prefix decremental lightening and darkening\n";
    start = Color::MAGENTA;
    for (int i = 0; i <= 20; ++i) {
        if ((i % 20) < 10) std::cout << --start;
        else std::cout << ++start;
        std::cout << start.colorize(start.to_string()) << " ";
    }
    std::cout << "\n\nTesting postfix decremental lightening and darkening\n";
    start = Color::RED;
    for (int i = 0; i < 20; ++i) {
        if ((i % 20) < 10) std::cout << start++;
        else std::cout << start--;
        std::cout << start.colorize(start.to_string()) << " ";
    }

    std::cout << "\n\nEnd scene\n";

    return 0;
}