#include <iostream>
#include <algorithm>

int main(int argc, char const *argv[]) {
    std::vector<std::string> v;
    std::string me;
    while(std::cin) {
        getline(std::cin, me);
        v.push_back(me);
    }
    std::sort(v.begin(), v.end());
    for (auto a : v)
        std::cout << a << std::endl;
    return 0;
}