package com.myproject.javase.GameAI.WestWorld;

/**
 * @author lkxl
 */
public class Main {
    public static void main(String[] args) {
        Miner miner=new Miner(1);
        for(;;){
            miner.Update();
        }

    }
}
