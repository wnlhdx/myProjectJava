package com.myproject.javase.GameAI.WestWorld;

import java.lang.reflect.Executable;
import java.util.Objects;

/**
 * @author lkxl
 */
public class EnterMineAndDigForNugget extends State{
    private static final EnterMineAndDigForNugget enterMineAndDigForNugget=new EnterMineAndDigForNugget();
    private EnterMineAndDigForNugget(){
    }
    public static EnterMineAndDigForNugget getInstance(){
        return enterMineAndDigForNugget;
    }

    @Override
    public  void Enter(Miner miner){
        if(!Objects.equals(miner.Location.Name, "GoldMine")){
            System.out.println("Walk to GoldMine");
        }
        miner.ChangeLocation(Location.GoldMine);
    }

    @Override
    public void Execute(Miner miner){
        miner.addGoldCarried();
        miner.IncreaseFatigue();
        System.out.println("miner "+miner.ID+" get a nugget");
        if(miner.PocketsFull()){
            miner.ChangeState(VisitBankAndDepositGold.getInstance());
        }
        if(miner.Thirsty()){
            miner.ChangeState(QuenchThirst.getInstance());
        }
    }

    @Override
    public void Exit(Miner miner){
        System.out.println("miner "+miner.ID+" get full of nugget");
    }
}
