package com.gb.nursery.animals.count;

import com.gb.nursery.animals.Animal;
import com.gb.nursery.exeption.AnimalException;

public class Counter implements AutoCloseable{
    private boolean resources = true;
    private static int count = 0;

//    public boolean add(Animal animals) {
//        if (!animals.getName().equals("") && !animals.getBirthdate().equals("")) {
//            count++;
//            resources = false;
//            return true;
//        }
//        return false;
//    }

    public void add() {
        count++;
    }

    public static int getCount() {
        return count;
    }

    @Override
    public void close() throws AnimalException {
        if (resources) throw new AnimalException("Ресурс открыт либо не все поля заполнены!");
    }
}
