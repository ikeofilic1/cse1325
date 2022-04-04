#include <iostream>
#include <algorithm>

int main(int argc, char const **argv){
    for(int i = 1; i < argc; ++i) {
        std::string var(argv[i]);
        std::reverse(var.begin(), var.end());
        std::cout << var << " ";
    }
    std::cout << std::endl;
    
    return 0;
}