//Importing all library required for the class
import java.util.*;
import java.io.*;

// Creating abstract class Pet

public abstract class Pet {
		
		//Declaring protected variable so we can use them in inheritance 
	
        protected String name;
        protected  String age;
        protected  String sex;
        protected  String charac;
        
        // Constructor taking 4 variables	
        
        public Pet(String name, String age, String sex, String charac)
        {
        	
        		// assigning values to our declared variables
                this.name=name;
                this.age=age;
                this.sex=sex;
                this.charac=charac;
        }
        
        // Creating two abstract class that will be used in the main class Pegasus 
        
        public abstract void color();
        public abstract void weight();


       

        // Creating a class that would help to look for a pet in the database and print his main characteristics  

public static boolean log(String path, String petName) {
                
				Scanner obj=new Scanner(System.in);
                String [] textFile;
                String inte;
                
                //Reading the file. If the input is equals to the name, we print all file. 
                
                try {
                        Scanner scanner = new Scanner(new File(path));
                        while (scanner.hasNextLine()) {
                                textFile=scanner.nextLine().split(",");
                                if (textFile[0].equals(petName)) {
                                	
                                	// Printing pet characteristics 
                                         System.out.println("Pet Name: "+textFile[0]);
                                         System.out.println("Pet Age: "+textFile[1]);
                                         System.out.println("Pet Sex: "+textFile[2]);
                                         System.out.println("Pet Color: "+textFile[3]);
                                         System.out.println("Pet Weight: "+textFile[4]);



                                        return true;
                                }
       // Returning errors and try catch errors. 
                        }
                        System.out.println("Pet does not exists");
                        return false;
                } catch (FileNotFoundException e) {
                        System.out.println("Cannot open "+ path);
                }
                return false;

        }

// Class for reading the file 

public static String readFile(String path) {


         String textFile = "";
try {

                 Scanner scanner = new Scanner(new File(path));


                 while (scanner.hasNextLine()) {
                         textFile +=scanner.nextLine()+"\n";

                 }

 } catch (FileNotFoundException e) {

         System.out.println("cannot open " + path);
 }

return textFile;
}

//Class for adding a pet. 

public static void addPet(String path, String newPetName,String newPetAge, String newPetSex, String newPetColor, String newPetWeight) {



                         File f = new File(path);
                         try {
                             BufferedWriter bw = new BufferedWriter(new FileWriter(f, true));
                             
                             //Appending user inputs to the file
                             
                             
                             bw.append("\n"+newPetName+","+newPetAge+","+newPetSex+","+newPetColor+","+newPetWeight+"\n");
                             bw.close();

                         } catch (IOException e) {

                             System.out.println("\nCouldn't Open the file with path "+path+ ", Please cheach Path again!!!");
                }
}

//Class for deleting a pet 

public static void deletePet(String path, String petname) {
Scanner scanner = null;
        Scanner scannerFile = null;;
         Scanner obj = new Scanner(System.in);
         try {
                 File f = new File(path);
                f.createNewFile();
                scannerFile = new Scanner(f);
                
                //Using ArrayList to make a list of all pets making then deleting one.
                
                List<String> loginInfoList = new ArrayList<String>();


                while(scannerFile.hasNextLine()) {
                        String line = scannerFile.nextLine();
                        if(line != null && !line.trim().equals("")) {
                                loginInfoList.add(line);
                        }
                }




                                boolean userFound = false;


                                List<String> updateLoginInfoList = new ArrayList<String>();
                                for(int index=0; index<loginInfoList.size(); index++) {
                                        String[] loginInfo = loginInfoList.get(index).split(",");

                                        if(!petname.equalsIgnoreCase(loginInfo[0])) {
                                                updateLoginInfoList.add(loginInfoList.get(index));
                                        } else {

                                                userFound = true;
                                        }
                                }
 if(userFound){

                                        PrintWriter writer = new PrintWriter(f);


                                        for(int index=0; index<updateLoginInfoList.size(); index++) {
                                                writer.append(updateLoginInfoList.get(index));
                                                if(index != updateLoginInfoList.size()-1) {
                                                        writer.println();
                                                }
                                        }
                                        writer.close();


                                } else {
                                        System.out.println("User not found. No user has been deleted");
                                }



         }catch(FileNotFoundException e) {
         } catch (IOException e) {

             e.printStackTrace();
     }
}
}

     