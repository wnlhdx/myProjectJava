package com.myproject.javase.GameAI.WestWorld;

import java.util.Objects;

/**
 * @author lkxl
 */
public class QuenchThirst  extends State{
    private static final QuenchThirst quenchThirst=new QuenchThirst();
    private QuenchThirst(){

    }
    public static QuenchThirst getInstance(){
        return quenchThirst;
    }

    @Override
    public  void Enter(Miner miner){
        if(!Objects.equals(miner.Location.Name, "Bar")){
            System.out.println("Walk to Bar");
        }
        miner.ChangeLocation(Location.Bar);
    }

    @Override
    public void Execute(Miner miner){
        miner.BuyWine();
        System.out.println("miner"+miner.ID+"Buy Wine");
        miner.ChangeState(EnterMineAndDigForNugget.getInstance());
    }

    @Override
    public void Exit(Miner miner){
        System.out.println("miner"+miner.ID+"leave the bar");
    }
}
