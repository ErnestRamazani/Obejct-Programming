#include <string>
#include<fstream>
#include <sstream>

#include "student.h"
#include "address.h"
#include "date.h"



Student::Student() {
    Student::firstName = new std::string(" ");
    Student::lastName = new std::string(" ");
    Student::gpa = new std::string(" ");
    Student::creditHours = new std::string(" ");
}

Student::Student(std::string arr[51], std::string firstName, std::string lastName, std::string gpa, std::string creditHours) {
    Student::firstName = new std::string(firstName);
    Student::lastName = new std::string(lastName);
    Student::gpa = new std::string(gpa);
    Student::creditHours = new std::string(creditHours);
}

Student::~Student() {
    delete firstName;
    delete lastName;
    delete creditHours;
    delete gpa;
}

void Student::setfirstName(std::string firstName) {
    *Student::lastName = firstName;
}

std::string Student::getfirstName() {
    return *firstName;
}

void Student::setlastName(std::string lastName) {
    *Student::lastName = lastName;
}
std::string Student::getlastName() {
    return *lastName;
}

void Student::setgpa(std::string gpa) {
    *Student::gpa = gpa;
}
std::string Student::getgpa() {
    return *gpa;
}

void Student::setcreditHours(std::string creditHours) {
    *Student::creditHours = creditHours;
}
std::string Student::getcreditHours() {
    return *creditHours;
}

void Student::print() {

    std::ifstream inFile;
    inFile.open("students.dat");
    std::stringstream ss;
    std::ofstream outFile;

    outFile.open("fullReport.txt");
    std::string sentence;
    std::string firstName, lastName, creditHours, gpa, address1, city, state, birthYear, completionYear, zipCode;
    std::string text;



    while (std::getline(inFile, sentence)) {
        ss.clear();
        ss.str("");
        ss.str(sentence);
        while (std::getline(ss, firstName, ',')) {
            while (std::getline(ss, lastName, ',')) {
                while (std::getline(ss, address1, ',')) {
                    while (std::getline(ss, city, ',')) {
                        while (std::getline(ss, state, ',')) {
                            while (std::getline(ss, zipCode, ',')) {
                                while (std::getline(ss, birthYear, ',')) {
                                    while (std::getline(ss, completionYear, ',')) {
                                        while (std::getline(ss, gpa, ',')) {
                                            while (std::getline(ss, creditHours, ',')) {

                                            }
                                            outFile << firstName << " " << lastName << " " << address1 << " " << city << " " << state << " " << zipCode << " " << birthYear << " " << completionYear << " " << gpa << " " << creditHours << std::endl;
                                        }
                                        outFile << std::endl;
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }
    }

}

void Student::printShort() {
    std::ofstream outFileShort;
    std::ifstream inFile;
    inFile.open("students.dat");
    std::stringstream ss;
    outFileShort.open("shortReport.txt");
    std::string sentence;
    std::string firstName, lastName, gpa;
    std::string text;



    while (std::getline(inFile, sentence)) {
        ss.clear();
        ss.str("");
        ss.str(sentence);
        while (std::getline(ss, firstName, ',')) {
            while (std::getline(ss, lastName, ',')) {
                while (std::getline(ss, gpa, ',')) {
                }
                outFileShort << firstName << " " << lastName << std::endl;
            }
            outFileShort << std::endl;
        }
    }
}

