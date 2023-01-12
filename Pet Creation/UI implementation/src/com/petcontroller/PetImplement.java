package com.example.oop.petcontroller;

// importing pacakge and libraries 

import com.example.oop.model.Pet;

import java.io.File;
import java.util.Formatter;

import javax.swing.JOptionPane;

// Creating class Pet that implement the interface class

public class PetImplement implements PetDAO {

   
    
    String petFile = File.separator;
    String enr = System.getProperty("user.dir") + petFile + "Bd" + petFile;

    // overriding create method from petdao interface 
    
    @Override
    
    public void create(Pet pet) {
        
        // Creating new file when Pets are added 
        String file = pet.getId() + ".txt";
        File creer = new File(enr);
        File create_file = new File(enr + file);
            
        // try and catch 
            try {
                if (create_file.exists()) {
                    JOptionPane.showMessageDialog(null, "this Pet already exists in the database");
                } else {

                    creer.mkdirs();
                    
                    // Wrtting to the file created 
                    
                    Formatter crea = new Formatter(enr + file);
                    crea.format("%s\r\n%s\r\n%s\r\n%s\r\n%s\r\n%s\r\n%s", 
                            "Id     : " + pet.getId(),
                            "Name   : " + pet.getName() + " ",
                            "Age    : " + pet.getAge() + " Years Old",
                            "Sex    : " + pet.getSex() + " ",
                            "Color  : " + pet.getColor1() + " ",
                            "Weight : " + pet.getWeight() + " Lb",
                            "Charac : " + pet.getCharac() + " ");
                    crea.close();
                    
                }
            } catch (Exception e) {
            }
        
    }
    
      // overriding delete method from petdao interface 

    @Override
    public void delete(Pet pet) {
        
        // method to delete pet 
        
        int id = pet.getId();
        String fiche = pet.getId() + ".txt";
        File creer = new File(enr);
        File read_file = new File(enr + fiche);
        if (id==0) {
            JOptionPane.showMessageDialog(null, "You must fill in the text field");
        } else {
            if (read_file.delete()) {
                JOptionPane.showMessageDialog(null, "Deleting Pet");
            }else{
                JOptionPane.showMessageDialog(null, "this id does not exist");
            }
        }
    }

    
      // overriding delete method from petdao interface 
    
    @Override
    public void interact(Pet pet) {
        
        // method for interacting with pet 
        
        int id = pet.getId();
        String fiche = pet.getId() + ".txt";
        File creer = new File(enr);
        File read_file = new File(enr + fiche);
        if (id==0) {
            JOptionPane.showMessageDialog(null, "You must fill in the text field");
        } else {
            if (read_file.delete()) {
                
            }else{
                JOptionPane.showMessageDialog(null, "this id does not exist");
            }
        }
    }

   

}
