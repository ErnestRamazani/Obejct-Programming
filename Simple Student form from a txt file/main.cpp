#include<string>

#include "student.h"


int main() {

    std::string firstName, lastName, gpa, creditHours;
    Student* studentObject = new Student[51];
    studentObject->printShort();
    studentObject->print();
    studentObject->setfirstName(firstName);
    studentObject->setlastName(lastName);

    studentObject->setcreditHours(creditHours);


    //outFile << studentObject->getfirstName() << " " << studentObject->getlastName() << " " << studentObject->getaddress1() << " " <<studentObject->getcity() << " " << studentObject->getstate() << " " << studentObject->getzipCode()<< " " <<studentObject->getbirthYear() << " " << studentObject->getcompletionYear() << " " << studentObject->getgpa()<< " " <<studentObject->getcreditHours() << std::endl; 
        //outFileShort<< studentObject->getfirstName() << " " << studentObject->getlastName()<<std::endl;
          delete[] studentObject;
              return 0;
                }
