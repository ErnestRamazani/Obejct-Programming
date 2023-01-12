#include "date.h" 
#include <string> 
#include <iostream>


Date::Date() {

    Date::birthYear = new std::string(" ");
    Date::completionYear = new std::string(" ");
}
Date::Date(std::string birthYear, std::string completionYear) {

    Date::birthYear = new std::string(birthYear);
    Date::completionYear = new std::string(completionYear);
}
Date::~Date() {
    delete birthYear;
    delete completionYear;
}

void Date::setbirthYear(std::string birthYear) {
    *Date::birthYear = birthYear;
}
std::string Date::getbirthYear() {
    return *birthYear;
}


void Date::setcompletionYear(std::string completionYear) {
    *Date::completionYear = completionYear;
}
std::string Date::getcompletionYear() {
    return *completionYear;
}

