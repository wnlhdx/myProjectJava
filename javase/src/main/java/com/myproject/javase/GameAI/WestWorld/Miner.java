package com.myproject.javase.GameAI.WestWorld;

/**
 * @author lkxl
 */
public class Miner extends BaseEntity{
    State CurrentState=EnterMineAndDigForNugget.getInstance();
    Location Location= com.myproject.javase.GameAI.WestWorld.Location.Home;
    int GoldCarried = 0;
    int MoneyInBank=0;
    int Thirst = 0;
    int Fatigue =0;

    public Miner(int ID) {
        super(ID);
    }

    public void Update(){
        CurrentState.Execute(this);
    }

    public void ChangeState(State NewState){
        CurrentState.Exit(this);
        this.CurrentState=NewState;
        CurrentState.Enter(this);
    }

    public void ChangeLocation(Location location){
        this.Location=location;
    }

    public void addGoldCarried(){
        GoldCarried+= 1;
    }

    public void IncreaseFatigue(){
        Fatigue+=1;
    }
    public boolean PocketsFull(){
        return GoldCarried>10;
    }

    public boolean Thirsty() {
        return (Math.random()*10)>5;
    }

    public  void SaveMoney(){
        MoneyInBank+=GoldCarried;
        GoldCarried=0;
    }

    public boolean Rich(){
        return MoneyInBank>30;
    }

    public void BuyWine() {
        GoldCarried-=1;
        Thirst=0;
    }

    public void Sleep() {
        Fatigue=0;
        MoneyInBank=0;
    }
}

