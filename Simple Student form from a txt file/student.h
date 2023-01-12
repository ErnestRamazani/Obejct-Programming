#ifndef STUDENT_H_EXISTS
#define STUDENT_H_EXISTS

#include "address.h"
#include "date.h"
#include <string>

class Address;
class Date;
class Student {
private:
    std::string* arr[51];
    std::string* firstName;
    std::string* lastName;
    std::string* gpa;
    std::string* creditHours;
    Address* address;
    Date* date;

public:
    Student();
    Student(std::string arr[51], std::string firstName, std::string lastName, std::string gpa, std::string creditHours);
    void setfirstName(std::string firstName);
    void setlastName(std::string lastName);
    void setcreditHours(std::string creditHours);
    void setgpa(std::string gpa);
    std::string getfirstName();
    std::string getlastName();
    std::string getgpa();
    std::string getcreditHours();
    void print();
    void printShort();
    void setaddress1(Address*);
    void setstate(Address*);
    void setcity(Address*);
    void setzip(Address*);
    void setbirthYear(Date*);
    void setcompletionYear(Date*);
    Date getbirthYear();
    Date getcompletionYear();
    Address getaddress1();
    Address getcity();
    Address getstate();
    Address getzipCode();

    ~Student();

};
#endif
