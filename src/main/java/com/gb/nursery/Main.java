package com.gb.nursery;

import com.gb.nursery.animals.pets.cat.Cat;

public class Main {
    public static void main(String[] args) {
        AppToSQL appToSQL = new AppToSQL();
        appToSQL.connect();
//        Cat cat = new Cat(2, "qwe", "qwe");
//        System.out.println(cat.getType());
    }
}