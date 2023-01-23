package com.gb.nursery.animals.packAnimals.donkey;

import com.gb.nursery.animals.packAnimals.PackAnimal;

public class Donkey extends PackAnimal {
    public Donkey(int id, String name, String birthdate) {
        super(id, name, birthdate);
    }

    public String getType() {
        return "Осел";
    }

    public void donkeySay() {
        System.out.println("DonkeySay");
    }
}
