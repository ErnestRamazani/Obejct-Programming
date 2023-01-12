Ernest Ramazani
CSCI24000
IUPUI Spring 2022
Algorithm "Bank on it"

We are creating a program that simulates an ATM. 
The user can withdraw, deposit and see the balance of both his checking account and saving account. 
There will be an admin who can add user, delete, list all users and apply the interest to all saving account. 
To make this program, we will need to create 4 different classes, checking, saving, user, and the main class. 
the saving class will inherit from the checking class since it will only be different when it comes to interest. 
The first class, checking, will have methods that control the deposit and withdraw. The saving class will inherit these two
method from the checking.  The saving class will also have a method that control the interest. 
the third class will be user. this class will control the user account number and user password. to get the user password and 
account number, wewill need a text file thta will store all this value. The class user will have a function that 
can slipt, using ";" as delimiter, the txt file to get the account number and password. The last class, main, will help 
to put everythong together and run the program. IN the class main, if the account number matches with the password, 
the user can access the menu asking wheter he want to deposit, withdraw or view his current balance. In fact, we are caaling the first 
two classes. If the password is 12345 and the account number is 00000, the user will get directed to the admin menu, where he can apply 
interest rate, add and delete user, and lsit all users. 


