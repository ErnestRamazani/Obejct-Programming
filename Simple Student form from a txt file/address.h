#ifndef ADDRESS_H_EXISTS
#define ADDRESS_H_EXISTS

#include<iostream>
#include<string>

class Address {

private:
    std::string* address1;
    std::string* city;
    std::string* state;
    std::string* zipCode;




public:
    Address();
    Address(std::string address1, std::string city, std::string state, std::string zipCode);
    ~Address();
    void setaddress1(std::string address1);
    void setcity(std::string city);
    void setstate(std::string state);
    void setzipCode(std::string zipCode);

    std::string getaddress1();
    std::string getcity();
    std::string getstate();
    std::string getzipCode();

};
#endif

