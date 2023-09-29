package com.myproject.javase.software;

import static com.myproject.javase.software.Gen.OPERATION_LIST;
import static com.myproject.javase.software.Gen.RANDOM_TOOL;

/**
 * @author lkxl
 */
public class Question extends Equation {
    public int OP_USE = 0;
    public int OP_MAX = Gen.OPERATION_MAX;
    public int NUM_MAX = 100;
    private final int NUM_MIN = 0;

    String Quesions;
    String Answer;

    public void genQuestion() {
        boolean noMulti = true;
        boolean isRequired = false;
        Equation equation = genEquation(OP_MAX, noMulti, isRequired);
    }

    public Integer genRandomOpNumber(int operationsMax, boolean isReq) {
        int operationsThisLevel;
        int operationLeast = isReq ? 1 : 0;
        //注意这里可以生成0，即无该层，但需要
        operationsThisLevel = RANDOM_TOOL.nextInt(operationsMax - operationLeast) + operationLeast;
        return operationsThisLevel;
    }

    public Integer genOperation(boolean noMulti){
        int op=RANDOM_TOOL.nextInt(2)*2+1;
        if(noMulti){
            op+=1;
        }
        return  op;
    }
    public Equation genEquation(int operationMAX, boolean noMulti, boolean isRequired) {
        Equation equation;
        //该层的操作符总数
        do {
            equation = new Equation();
            //计算并加总操作符
            int operationsThisLevel = genRandomOpNumber(Gen.OPERATION_MAX, isRequired);
            OP_USE += operationsThisLevel;
            for (int i = 0; i <= operationsThisLevel; i++) {
                //判断当前索引需要生成的是一个数字还是一个子表达式，如果已经用完所有的运算符可用数量，则无法进一步生成子表达式
                boolean isSubEquation = RANDOM_TOOL.nextBoolean();
                equation.operatorList.add(genOperation(noMulti));
                //如果最后一个索引符号还没用用完那么这个索引必须是一个子表达式

                if (i == operationsThisLevel) {
                    isSubEquation = true;
                }
                if (Boolean.TRUE.equals(isSubEquation)) {
                    Equation subEquation;
                    int operator = 0;
                    //第一个数取后一个操作符其余的子表达式取前一个操作符进行判断，判断子表达式是否强制是上一层的
                    if (operationsThisLevel == 0) {
                        //乘除法算作加减法上一层，但是如果某层没有，比如加减没有，那么至少会有乘除，否则小括号内的加减就毫无意义；即不可能连续两层为空
                        //减法和除法生成的子表达式并不强制要求是上一层，上两层也是可以的即3-(2-1)无法去括号，加法和乘法生成的子表达式强制要求是上一层的，上两层或以上会被去括号即3+(2+1)=3+2+1，3+(2*5)=3+2*5，3*(5*5)=3*5*5
                        if (i == 0) {
                            operator = equation.operatorList.get(0);
                        } else {
                            operator = equation.operatorList.get(i - 1);
                        }
                        isRequired = operator != 1 && operator != 3;
                    }
                    do {
                        subEquation = genEquation(OP_MAX - OP_USE, !noMulti, isRequired);
                    }while (subEquation.res.denominator>NUM_MAX);
                    dealSubEquation(equation, subEquation, noMulti, i, isRequired);
                } else {
                    //仅为数字时添加到数字列表和字符串中
                    Fraction num = genNum();
                    equation.numList.add(i, num);
                    equation.equationString.append(num);
                    equation.equationList.add(i, new Equation(num));
                }
                if (i != operationsThisLevel) {
                    equation.equationString.append(Gen.OPERATION_LIST.get(equation.operatorList.get(i)));
                }
            }
            if (operationsThisLevel > 0) {
                equation.calculate();
            }
        } while (equation.res == null);
        return equation;
    }

    public static void dealSubEquation(Equation equation, Equation subEquation, boolean noMulti, int index, boolean hasThisLevel) {
        equation.equationList.add(index, subEquation);
        equation.numList.add(index, subEquation.res);
        //乘法上一层为加法，需要加括号，加法上一层为乘法，不需要加括号。由于隔两层是同类型理论上可以去括号
        if (noMulti) {
            equation.equationString.append(subEquation.equationString);
        } else {
            equation.equationString.append('(').append(subEquation.equationString).append(')');
        }
        if (!hasThisLevel) {
            equation.res = subEquation.res;
        }
    }

    public Fraction genNum() {
        if (RANDOM_TOOL.nextBoolean()) {
            int num = RANDOM_TOOL.nextInt(NUM_MAX - NUM_MIN) + NUM_MIN;
            return new Fraction(num, 1);
        } else {
            int de = RANDOM_TOOL.nextInt(NUM_MAX - NUM_MIN - 1) + NUM_MIN + 1;
            ;
            return new Fraction(RANDOM_TOOL.nextInt(de - NUM_MIN) + NUM_MIN, de);
        }
    }

    public void getAnswer(){
        Answer=new Caculate(Quesions).output;
    }
}
