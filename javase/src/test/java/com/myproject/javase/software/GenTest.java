package com.myproject.javase.software;

import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

/**
 * @author lkxl
 */
public class GenTest {
    public static final Logger logger = Logger.getLogger("CaculateTest");
    private static final String NAME_REG ="^[0-9]*$";


    @Test
    public void Check() throws Exception {
        Gen gen =new Gen();
        gen.check();
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
    public void caculate() throws Exception {
        int number=Integer.parseInt(System.getProperty("QuestionNumber"));
        int max=Integer.parseInt(System.getProperty("MaxNumber"));
        String exfile=System.getProperty("ExerciseFile");
        String answer=System.getProperty("AnswerFile");
        Gen.setInputAnswerFile(answer);
        Gen.setInputExerciseFile(exfile);
        Gen gen =new Gen();
        gen.setMax(max);
        gen.CaculateRes(number);
    }
}
