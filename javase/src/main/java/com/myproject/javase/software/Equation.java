package com.myproject.javase.software;

import java.util.*;

/**
 * @author lkxl
 */
public class Equation {
    /**
     *本层数字，子表达式直接用运算结果表示
     **/
    List<Fraction> numList;
    /**
     *本层运算符，加减为奇数层，乘除为偶数层，括号直接加两层（因为隔一层或者同类型符号没必要加括号，所以一定是偶数层乘除配奇数层加减带括号）
     **/
    List<Integer> operatorList;
    /**
     *整个表达式，包括子表达式在内的字符串形式，子表达式通过这个字段向上传递表达式内容
     **/
    StringBuilder equationString;
    /**
     *计算结果，子表达式直接将此值当做数字存在父表达式数字列表中简化计算
     **/
    StringBuilder equationRes;
    Fraction res;
    /**
     *运算符统计，包括该层和各子表达式层的运算符数量和。用于统计是否达到运算符数量的上下限。
     **/
    /**
     *层级，仅在生成完毕后运算符数量依然未达到下限时判断是否是最高层来生成低保表达式。
     **/
    List<Equation> equationList;



    boolean isNumber;

    Equation(Fraction fraction){
        isNumber=true;
        numList=null;
        operatorList=null;
        res=fraction;
        equationList = null;
    }


    Equation(){
        isNumber=false;
        numList=new ArrayList<>();
        operatorList= new LinkedList<>();
        equationString=new StringBuilder();
        res=new Fraction();
        equationList = new ArrayList<>();
    }

    /**
     * 计算表达式结果的方法
     * 注意：当除以0或者除不尽时返回-1
     **/
    public void calculate(){
        res=numList.get(0);
        for(int i=0;i<operatorList.size();i++){
            if(operatorList.get(i)==0){
                res=res.add(numList.get(i+1));
            }else if(operatorList.get(i)==1){
                res=res.subtract(numList.get(i+1));
            }else if(operatorList.get(i)==2){
                res=res.multiply(numList.get(i+1));
            }else if(operatorList.get(i)==3){
                if((numList.get(i+1).getRealNumerator()==0)){
                    res=null;
                }else {
                    res = res.divide(numList.get(i + 1));
                }
            }
        }
        if(res!=null){
            addRes();
        }
    }


    /**
     * 补充等号和计算结果到最终生成的表达式中
     **/
    void addRes(){
        chekcRes();
        equationRes=new StringBuilder(equationString).append("=").append(res);
    }

    void chekcRes(){
        Caculate caculate =new Caculate(equationString.toString());
        if(!res.equals(caculate.res)){
            System.out.println(String.format("计算结果不同 res是%s ，但实际结果是 %s ,计算式是%s",res, caculate.res,equationString.toString()));
            for(int i=0;i<numList.size();i++){
                System.out.println("第"+i+"个数字"+numList.get(i)+"整数"+numList.get(i).getRound()+"分子"+numList.get(i).getNumerator()+"分母"+numList.get(i).getDenominator()+"真分子"+numList.get(i).getRealNumerator());
            }
            System.out.println(String.format("运算符%s ,计算数是%s",operatorList,numList));
            System.exit(1);
        }

    }


    public  List<Equation> splitEquation() {
            List<Equation> equations=new ArrayList<>();
            int top=0;
            Equation equation=new Equation();
            for(int i=0;i<operatorList.size();i++){
                if(operatorList.get(i)==1||operatorList.get(i)==3){
                    equation.numList.add(numList.get(i));
                    equation.equationList.add(equationList.get(i));
                    equation.operatorList.add(operatorList.get(i));
                }else{
                    equation.numList.add(numList.get(i));
                    equation.equationList.add(equationList.get(i));
                    equations.add(equation);
                    equation.calculate();
                    equation=new Equation();
                    continue;
                }
            }
            equation.numList.add(numList.get(operatorList.size()));
            equation.equationList.add(equationList.get(operatorList.size()));
            equation.calculate();
            equations.add(equation);
            return equations;
    }
    @Override
    public int hashCode(){
        return this.toString().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        Equation equation2=(Equation) obj;
        if (operatorList.contains(1)||operatorList.contains(3)) {
            List<Equation> equations=splitEquation();
            List<Equation> equations2=equation2.splitEquation();
            if(equations.size()!=equations2.size()){
                return false;
            }
            return  equations.equals(equations2);
        }else{
            List<Integer> olist=equation2.operatorList;
            if(olist.contains(1)||olist.contains(3)){
                return false;
            }
            if(numList.size()!=equation2.numList.size()){
                return false;
            }

            return CheckChange(this,equation2) ;
        }

    }

    private boolean CheckChange(Equation equation,Equation equation2){
            List<Fraction> list1=equation.numList;
            List<Fraction> list2=equation2.numList;
            Collections.sort(list1);
            Collections.sort(list2);
            for(int i=0;i<list1.size();i++){
                if(list1.get(i)!=list2.get(i)){
                    return false;
                }
            }
            return true;
    }

    @Override
    public String toString() {
        if(equationString==null){
            return res.toString();
        }else {
            StringBuilder x=new StringBuilder();
            for(int i=0;i<operatorList.size();i++) {
                x.append(numList.get(i)).append(operatorList.get(i));
            }
            x.append(numList.get(operatorList.size()));
            return  x.toString();
        }
    }
}

