package com.gb.nursery.animals.packAnimals.camel;

import com.gb.nursery.animals.packAnimals.PackAnimal;

public class Camel extends PackAnimal {
    public Camel(int id, String name, String birthdate) {
        super(id, name, birthdate);
    }

    public String getType() {
        return "Верблюд";
    }

    public void camelSay() {
        System.out.println("Camel Say");
    }
}
