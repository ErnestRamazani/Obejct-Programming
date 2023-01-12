#include"address.h"
#include<iostream>

Address::Address() {
    Address::address1 = new std::string(" ");
    Address::city = new std::string(" ");
    Address::state = new std::string(" ");
    Address::zipCode = new std::string(" ");

}
Address::Address(std::string address1, std::string city, std::string state, std::string zipCode) {
    Address::address1 = new std::string(address1);
    Address::city = new std::string(city);
    Address::state = new std::string(state);
    Address::zipCode = new std::string(zipCode);

}
Address::~Address() {
    delete address1;
    delete city;
    delete state;
    delete zipCode;

}

void Address::setaddress1(std::string address1) {
    *Address::address1 = address1;
}
std::string Address::getaddress1() {
    return *address1;
}

void Address::setcity(std::string city) {
    *Address::city = city;
}
std::string Address::getcity() {
    return *city;
}

void Address::setstate(std::string state) {
    *Address::state = state;
}
std::string Address::getstate() {
    return *state;
}

void Address::setzipCode(std::string zipCode) {
    *Address::zipCode = zipCode;
}
std::string Address::getzipCode() {
    return *zipCode;
}
