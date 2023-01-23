package com.gb.nursery.animals.pets;

import com.gb.nursery.animals.Animal;

public class Pet extends Animal {
    public Pet(int id, String name, String birthdate) {
        super(id, name, birthdate);
    }

    public void toBeNice() {
        System.out.println("To be nice");
    }
}
