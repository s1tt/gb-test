package com.gb.nursery.animals.pets.dog;

import com.gb.nursery.animals.pets.Pet;

public class Dog extends Pet {
    public Dog(int id, String name, String birthdate) {
        super(id, name, birthdate);
    }

    public String getType() {
        return "Собака";
    }

    public void gaf() {
        System.out.println("Gaf");
    }
}
