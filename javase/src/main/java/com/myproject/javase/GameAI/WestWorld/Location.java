package com.myproject.javase.GameAI.WestWorld;

/**
 * @author lkxl
 */
enum Location{

    GoldMine("GoldMine"),
    Home("Home"),
    Bank("Bank"),
    Bar("Bar");

    public final String Name;

    private Location(String name) {
        this.Name=name;
    }

  }
