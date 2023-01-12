Ernest Ramazani 
CSCI24000 
IUPUI Spring 2022

ALGORITHM for Homework8 

We are trying to create a program called Perfect Pet. This program is about creating a virtual Pet program that allows the user te see
a predefined pet, create, delete and play with these pets. 
To make this program, we will need 4 classes and 1 database file. We will need a main class, an abstract class adn 2 interfaces classes.
The main class will be the class of the generic pet, the abstract class will be the Pet class and the two intreface will have function forthe main class
Stating with the interface classes, we will need 2 of them called Emotion and an Unactive. Both will have 2 declared fucntions representing the 
state of the generic pet. Since there are interfaces classes, we will need to overide them in the class that will implement them. The After 
overiding them, these functions will only return string value such as <<generic pet is sleeping,...>>
The second class will be the Pet class. This class will be abstract. We will need to create protected variable so we will use them in the class 
that will inherit our abstract Pet class. We create a constructor and our abstract functions thta we will use in the main class. This class Pet will be in
charge of seing, adding, and deleting all pets. All the pets informations will be stored in a datafile called Pet.txt. we will need some classes
for adding, deleting, seing pets. The last class, main class, will be inheriting from the class Pet and implementing the interfaces Emotion and Uanctive.
We will need a constructor and since it inherits, we will need a super constructor. In our main function, we will create an object <generic pet>
After creating our object, we will print all value of the generic pet from the abstract Pet class. We will then, create a menu that shows user choices 
of what he wants to do. Depending of his choice, we will be calling the object created with the required function. 

here is what the program shouldlook like. 


What you want to do?
1.see generic pet
2. add a pet
3. delete a pet
4. see other pets
5. modify pets
6. Quit 
 if he chooses 1, we shows the generic pet by calling the objet generic pet with the appropriate fucntion // 2. We call the fuction add pet // 3 call the fucntion delete pet // 4 we call the function seeing pet //
5 // we call the function seing a pet, then delete a pet and the add a pet. 
