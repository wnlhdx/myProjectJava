package com.myproject.javase.software;

import org.junit.jupiter.api.Test;

import java.util.Enumeration;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * @author lkxl
 */
public class CaculateTest {
    public static final Logger logger = Logger.getLogger("CaculateTest");
    private static final String NAME_REG ="^[0-9]*$";
    @Test
    public void check(){
        Caculate caculate=new Caculate();
        for(int i=1;i<=10;i++){
            int k=(int)(Math.random()*10+1);
            logger.info("第"+i+"组,共"+k+"个");
            caculate.CaculateRes(k);
        }
    }

    @Test
    public void testFrac(){
        System.out.println(new Fraction(5,9,0).subtract(new Fraction(2,3,0)));
    }

    @Test
    public void testEuaCal(){
        Equation equation=new Equation();
        equation.numList.add(new Fraction(4,1,0));
        equation.numList.add(new Fraction(4,3,0));
        equation.operatorList.add(3);
        equation.equationString=new StringBuilder("4÷0'4/3");
        equation.calculate();
    }
    @Test
    public void testCheck(){
        CheckRes checkRes=new CheckRes("(100+48*(30+39))*24");
        System.out.println(checkRes.res);
    }

    public void caculate(){
        String num="";
        int number=0;
        do {
            System.out.println("请输入一个数字");
            Scanner scanner = new Scanner(System.in);
            num = scanner.nextLine();
            if (num.length() != 1 || !(num.matches(NAME_REG))) {
                //命令行输入第一个参数为数字
                logger.info("错误,请输入一个数字");
                continue;
            }else {
                number=Integer.parseInt(num);
            }
        }while (number!=0);
            Caculate caculate=new Caculate();
            caculate.CaculateRes(number);
    }
}
