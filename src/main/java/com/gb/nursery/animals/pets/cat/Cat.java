package com.gb.nursery.animals.pets.cat;

import com.gb.nursery.animals.pets.Pet;

public class Cat extends Pet {
    public Cat(int id, String name, String birthdate) {
        super(id, name, birthdate);
    }

    public String getType() {
        return "Кот";
    }

    public void mew() {
        System.out.println("Mew");
    }
}
