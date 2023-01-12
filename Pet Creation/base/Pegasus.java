// Importing necessary library

import java.util.*;
import java.io.*;

//Class Pegasus inherits from Pet and implements Unactive and Emotion

public class Pegasus extends Pet implements Unactive, Emotion{

		// Pegasus constructor takes 4 variables 
	
        public Pegasus(String name, String age, String sex, String charac) {
    
        	// Super Constructor from Pet Class
        	
        		super(name, age,sex,charac);

        }

        //main function 
        
        
        public static void main(String[] args) {

        		// Creating object Pegasus from Pegasus class taking 4 values for the 4 varibles
        	
        	
                Pegasus pegasus = new Pegasus("Pegasus","Infinite", "M", "Holds ThunderBolt for Zeus");

                // Scanner object for inputs and declaring some variables 
                
                Scanner obj = new Scanner(System.in);
                String choice,petname, petLog, petAge, petSex, petColor, petWeight;
                
                // Declaring our database    
             
                String path = "pet.txt";
 
                // Printing the Menu to the User 
                
                while (true) {
                        System.out.print("What de you want to do?: \n1.See Pegasus\n2.See other Pets\n3.Create a Pet\n4.Delete a Pet\n5.Interact with Pets\n6.Quit");
                        
                        // Taking the input from the user 
                        choice = obj.nextLine();
                        
                        // Reading input from user and use it to run different class objects 
                        
                        switch(choice) {
                        
                        //User choose 1 
                        
                        case "1":

                        // Run the object Pegasus with assigned functions 
                               
                                pegasus.intro();
                                pegasus.color();
                                pegasus.weight();
                                pegasus.hungry();
                                pegasus.happy();
                                pegasus.sleeping();
                                pegasus.dead();

                      //break 
                                
              break;
              
            //User choose 2
              
                         case "2":
                        	 
            // taking the user input and run the function log form the class Pet 
                        	 
                                 System.out.print("\nEnter Pet Name: ");
                 petLog = obj.nextLine();


                 if ( log(path, petLog)) {



             }
             
             //break
                 
              break;
              
              //User choose 3
                         case "3":

              //Printing questions, reading user input and running the function addPet from the Pet class 
                 System.out.print("\nEnter Pet Name: ");
                 petLog = obj.nextLine();
                 System.out.print("\nEnter Pet Age: ");
                 petAge = obj.nextLine();
                 System.out.print("\nEnter Pet Sex: ");
                 petSex = obj.nextLine();
                 System.out.print("\nEnter Pet Color: ");
                 petColor = obj.nextLine();
                 System.out.print("\nEnter Pet Weight: ");
                 petWeight = obj.nextLine();



                 addPet(path, petLog,petAge,petSex,petColor,petWeight);
                 System.out.println("\nYou added the pet "+petLog+ " into our system");
                 
              
                 break;
                 
                 //User choose 4
                 
                         case "4":
                        	 
                        	 //Printing questions, reading user input and running the function DeletePet from the Pet class 
                        	 
                                 System.out.print("\nEnter deleting Pet: ");
                                 petname = obj.nextLine();
                                 deletePet(path,petname);
                                 break;
                                 
                                 //User choose 5
                                 
                         case "5":
                        	
                        	 // Printing questions, reading user input and running the function log to see if the pet exists, then the function DeletePet from the Pet class and replace 
                        	 //the deleted pet with a new pet with the addpet function 
                                         System.out.println("Change your pet characteristic");
                                         System.out.print("\nEnter Pet Name: ");
                                         petLog = obj.nextLine();
                                         log(path, petLog);
                                         deletePet(path,petLog);

                                         System.out.println("Tpye the new name");
                                         String new_pet_name = obj.nextLine();

                                         System.out.println("Tpye the new Age");
                                         String new_pet_age = obj.nextLine();


                                         System.out.println("Tpye the new Sex");
                                         String new_pet_sex = obj.nextLine();


                                         System.out.println("Tpye the new Color");
                                         String new_pet_color = obj.nextLine();


                                         System.out.println("Tpye the new Weight");
                                         String new_pet_weight = obj.nextLine();

                                         System.out.println("\nYour Pet has been Updated");

                                        addPet(path,new_pet_name,new_pet_age,new_pet_sex,new_pet_color,new_pet_weight);

                                 break;
                                 
                                 //User chooses option 6 
                                 
                         case "6":
                        	 
                        	 	//Quitting the program
                        	 
                 System.out.println("Exiting the App!\n");

                 System.exit(0) ;

                                 break;
                        }
                }



        }
        
        // Creating function into that print all value of the Pet Pegasus 
        
        public void intro() {
                System.out.println("Name: "+name+"\n");
                System.out.println("Age: "+age+"\n");
                System.out.println("Sex: "+sex+"\n");
                System.out.println("Characteristic: "+charac+"\n");
        }
        
        //Override the function Happy form the interface Emotion
        
        @Override
        public void happy() {
        	
        	// Printing the state of the pet Pegasus
        	
                System.out.println(name+ " is Happy \n");

        }
        
      //Override the function hungry form the interface Emotion
        

        @Override
        public void hungry() {
        	
        	// Printing the state of the pet Pegasus
        	
                System.out.println(name+ " is hungry\n");


        }

      //Override the function sleeping form the interface Unactive
        
        @Override
        public void sleeping() {

        	// Printing the state of the pet Pegasus
        	
                System.out.println(name+ " is spleeping\n");

        }

      //Override the function dead form the interface Unactive

        @Override
        public void dead() {
        	
        	// Printing the state of the pet Pegasus
        	
                System.out.println(name+ " is dead\n");
        }


      //Override the abstract function color form the super class(abstract class) Pet
        
        @Override
        public void color() {

        	// Printing the color of the pet Pegasus
        	
                System.out.println("Color: Pure White\n");

        }

        //Override the abstract function wieght form the super class(abstract class) Pet
        
        @Override
        public void weight() {

        	// Printing the weight of the pet Pegasus
        	
                System.out.println("Weight: 1500 lb\n");



        }



}