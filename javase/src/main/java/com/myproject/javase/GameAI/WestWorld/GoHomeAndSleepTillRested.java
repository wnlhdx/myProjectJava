package com.myproject.javase.GameAI.WestWorld;

import java.util.Objects;

/**
 * @author lkxl
 */
public class GoHomeAndSleepTillRested extends State{
    public static final GoHomeAndSleepTillRested gohomeandsleeptillrested=new GoHomeAndSleepTillRested();
    private GoHomeAndSleepTillRested(){

    }
    public  static GoHomeAndSleepTillRested getInstance(){
        return gohomeandsleeptillrested;
    }

    @Override
    public  void Enter(Miner miner){
        if(!Objects.equals(miner.Location.Name, "Home")){
            System.out.println("Walk to Home");
        }
        miner.ChangeLocation(Location.Home);
    }

    @Override
    public void Execute(Miner miner){
        miner.Sleep();
        System.out.println("miner"+miner.ID+"Sleep");
        miner.ChangeState(EnterMineAndDigForNugget.getInstance());
    }

    @Override
    public void Exit(Miner miner){
        System.out.println("new day start,miner"+miner.ID+"leave the home");
    }
}
