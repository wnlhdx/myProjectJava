package com.myproject.javase.GameAI.WestWorld;

/**
 * @author lkxl
 */
public abstract class BaseEntity {
    int ID = 0;
    static  int ValidID = 0;

    void setID(int val) {
        this.ID=val;
    }

    public BaseEntity(int id){setID(id);}
}
