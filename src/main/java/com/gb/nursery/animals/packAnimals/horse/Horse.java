package com.gb.nursery.animals.packAnimals.horse;

import com.gb.nursery.animals.packAnimals.PackAnimal;

public class Horse extends PackAnimal {
    public Horse(int id, String name, String birthdate) {
        super(id, name, birthdate);
    }

    public String getType() {
        return "Лошадь";
    }

    public void horseSay() {
        System.out.println("Horse say");
    }
}
