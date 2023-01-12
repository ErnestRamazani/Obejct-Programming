#ifndef DATE_H_EXISTS
#define DATE_H_EXISTS

#include <iostream>
#include <string>


class Date {

private:
    std::string* birthYear;
    std::string* completionYear;

public:
    Date();
    Date(std::string birthYear, std::string completionYear);
    ~Date();
    void setbirthYear(std::string birthYear);
    void setcompletionYear(std::string completionYear);
    std::string getbirthYear();
    std::string getcompletionYear();
};
#endif
