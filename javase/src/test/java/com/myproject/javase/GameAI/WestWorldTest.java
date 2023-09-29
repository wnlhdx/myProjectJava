package com.myproject.javase.GameAI;

import com.myproject.javase.GameAI.WestWorld.Miner;
import org.junit.jupiter.api.Test;

/**
 * @author lkxl
 */
public class WestWorldTest {
    @Test
    public void TestMinder(){
        Miner miner=new Miner(1);
        for(;;){
            miner.Update();
        }
    }


}
