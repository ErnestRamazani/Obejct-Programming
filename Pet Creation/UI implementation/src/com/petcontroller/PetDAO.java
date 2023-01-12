
package com.example.oop.petcontroller;

// importing package 

import com.example.oop.model.Pet;

// Creating the interface class 

public interface PetDAO {
    public void create(Pet pet);
    public void delete(Pet pet);
    public void interact(Pet pet);
}
