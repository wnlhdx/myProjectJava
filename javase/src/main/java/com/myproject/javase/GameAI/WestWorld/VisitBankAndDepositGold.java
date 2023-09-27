package com.myproject.javase.GameAI.WestWorld;

import java.util.Objects;

/**
 * @author lkxl
 */
public class VisitBankAndDepositGold extends State{
    private static  final VisitBankAndDepositGold visitBankAndDepositGold= new VisitBankAndDepositGold();
    private VisitBankAndDepositGold(){

    }
    public static VisitBankAndDepositGold getInstance(){
        return visitBankAndDepositGold;
    }

    @Override
    public  void Enter(Miner miner){
        if(!Objects.equals(miner.Location.Name, "Bank")){
            System.out.println("Walk to Bank");
        }
        miner.ChangeLocation(Location.Bank);
    }

    @Override
    public void Execute(Miner miner){
        miner.SaveMoney();
        System.out.println("miner"+miner.ID+"Save All his money,now he have "+miner.MoneyInBank);
        if(miner.Rich()){
            miner.ChangeState(GoHomeAndSleepTillRested.getInstance());
        }
        else{
            miner.ChangeState(EnterMineAndDigForNugget.getInstance());
        }
    }

    @Override
    public void Exit(Miner miner){
        System.out.println("miner"+miner.ID+"leave the bank");
    }
}
