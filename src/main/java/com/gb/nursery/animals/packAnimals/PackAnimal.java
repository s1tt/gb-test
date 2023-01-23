package com.gb.nursery.animals.packAnimals;

import com.gb.nursery.animals.Animal;

public class PackAnimal extends Animal {
    public PackAnimal(int id, String name, String birthdate) {
        super(id, name, birthdate);
    }

    public void toBeWild() {
        System.out.println("To be wild");
    }
}
