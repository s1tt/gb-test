package com.gb.nursery.animals.pets.hamster;

import com.gb.nursery.animals.pets.Pet;

public class Hamster extends Pet {

    public Hamster(int id, String name, String birthdate) {
        super(id, name, birthdate);
    }

    public String getType() {
        return "Хомяк";
    }

    public void hamsterSay() {
        System.out.println("hamsterSay");
    }
}
