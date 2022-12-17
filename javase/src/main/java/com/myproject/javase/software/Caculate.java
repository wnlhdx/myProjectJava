package com.myproject.javase.software;

import java.util.*;
import java.util.logging.Logger;

/**
 * @author lkxl
 */
public class Caculate {
        public static final Logger logger = Logger.getLogger("Caculate");

        private  static  final Random RANDOM_TOOL = new Random();

        public static final ArrayList<String> OPERATION_LIST = new ArrayList<>(Arrays.asList("+","-","×","÷"));

;
        /**
         * 运算符个数的上下限
         **/
        public static  final int OPERATION_MAX =3;
        private static final int OPERATION_MIN =1;

        /**
         * 生成数字的上下限
         **/
        public static  int NUM_MAX =100;
        private static final int NUM_MIN =0;

        /**
         * 符号列表
         **/

        public void CaculateRes(Integer input){
                HashSet<Equation> euoations =new HashSet<>();
                for (int j = 1; j <= input; j++) {
                    //随机生成操作符总数
                    int operationSum = RANDOM_TOOL.nextInt(OPERATION_MAX-OPERATION_MIN) + OPERATION_MIN;
                    boolean noMulti =true;
                    boolean isRequired=false;
                    Equation equation=genEquation(operationSum,noMulti,isRequired,0);
//                    if(equation.equationRes==null){
//                        logger.info(equation.toString());
//                    }
                    if(euoations.add(equation)){
                        String result=String.format("第 %d 个算式：%s",j,equation.equationRes);
                        logger.info(result);
                        if(equation.equationRes==null){
                            System.out.println(equation.toString());
                        }
                        //System.out.println(equation.equationList);
                    }
                }
        }

        public static Integer genRandomOpNumber(int operationsMax,boolean isReq){
            int operationsThisLevel;
            if(isReq&&(operationsMax==1)){
                operationsThisLevel=1;
            }else {
                int operationLeast =isReq?1:0;
                //注意这里可以生成0，即无该层，但需要
                operationsThisLevel=RANDOM_TOOL.nextInt(operationsMax-operationLeast)+operationLeast;
//                if(operationsThisLevel==0){
//                    System.out.println("least:"+operationLeast+"req:"+isReq);
//                }
            }

            return operationsThisLevel;
        }


        public static  void dealSubEquation(Equation equation,Equation subEquation,boolean noMulti,int index,boolean hasThisLevel,boolean isBasicLevel){
            equation.equationList.add(index,subEquation);
            equation.countOperator+=subEquation.countOperator;
            equation.numList.add(index,subEquation.res);
            //乘法上一层为加法，需要加括号，加法上一层为乘法，不需要加括号。由于隔两层是同类型理论上可以去括号
            if(noMulti){
                equation.equationString.append(subEquation.equationString);
            }else{
                equation.equationString.append('(').append(subEquation.equationString).append(')');
            }
            if(!hasThisLevel){
                equation.res=subEquation.res;
                if(isBasicLevel){
                    equation.equationRes=subEquation.equationRes;
                    equation.equationString=subEquation.equationString;
                }
            }
        }
        public static Equation genEquation(int operationsMax,boolean noMulti, boolean isRequired,Integer level){
            Equation equation;
            //该层的操作符总数
            do{
                equation =new Equation();
                equation.level=level;
                int operationsThisLevel=genRandomOpNumber(operationsMax,isRequired);
                //计算并加总操作符
                equation.countOperator=operationsThisLevel;
                equation.operatorList =genOperator(operationsThisLevel,noMulti);
                for(int i=0;i<=operationsThisLevel;i++){
                    boolean isSubEquation=false;
                    //判断当前索引需要生成的是一个数字还是一个子表达式，如果已经用完所有的运算符可用数量，则无法进一步生成子表达式
                    if(equation.countOperator!=operationsMax){
                        //如果最后一个索引符号还没用用完那么这个索引必须是一个子表达式
                        if(i<operationsThisLevel){
                            isSubEquation=RANDOM_TOOL.nextBoolean();
                        }else {
                            isSubEquation=true;
                        }
                    }
                    if(Boolean.TRUE.equals(isSubEquation)){
                        boolean Required;
                        Equation subEquation;
                        int operator=0;
                        //第一个数取后一个操作符其余的子表达式取前一个操作符进行判断，判断子表达式是否强制是上一层的
                        if(operationsThisLevel==0){
                            //第0层可以不要，即直接乘除没有加减，但此后所有的层都需要一层或两层的上，这个逻辑主要处理第0层,当这层不要的时候下一层必须要
                           Required=!isRequired;
//                         System.out.println("第"+level+"层为0了,isrequired是"+isRequired);
                        }else {
                            if(i==0) {
                                operator=equation.operatorList.get(0);
                            }else {
                                operator=equation.operatorList.get(i-1);
                            }
                        }
                        //减法和除法生成的子表达式并不强制要求是上一层，上两层也是可以的即3-(2-1)无法去括号，加法和乘法生成的子表达式强制要求是上一层的，上两层或以上会被去括号即3+(2+1)=3+2+1，3+(2*5)=3+2*5，3*(5*5)=3*5*5
                        //除第0层以外主要参考的是符号
                        Required= operator != 1 && operator != 3;
                        subEquation=genEquation(operationsMax-equation.countOperator,!noMulti,Required,level+1);
                        dealSubEquation(equation,subEquation,noMulti,i,operationsThisLevel!=0,level==0);
//                        if(subEquation.operatorList.size()==0&&equation.operatorList.size()==0){
//                            System.out.println("???");
//                        }
                    }else{
                            //仅为数字时添加到数字列表和字符串中
                            Fraction num = genNum();
                            equation.numList.add(i, num);
                            equation.equationString.append(num);
                            equation.equationList.add(i, new Equation(num));
                    }
                    if(i!=operationsThisLevel){
                        equation.equationString.append(OPERATION_LIST.get(equation.operatorList.get(i)));
                    }
                }
                if(operationsThisLevel>0){
                    equation.calculate();
//                    if(equation.res>0){
//                        System.out.println("第"+level+"层生成符号"+equation.operatorList.toString());
//                        System.out.println("第"+level+"层算式"+equation.equationString);
//                        System.out.println("第"+level+"层结果"+equation.res);
//                    }
                }
            }while (equation.res==null);
            return equation;
        }
        public static Fraction genNum(){
            if(RANDOM_TOOL.nextBoolean()){
                int num=  RANDOM_TOOL.nextInt(NUM_MAX-NUM_MIN)+NUM_MIN;
                return  new Fraction(num,1);
            }else{
                int de=RANDOM_TOOL.nextInt(NUM_MAX-NUM_MIN-1)+NUM_MIN+1;;
                return  new Fraction(RANDOM_TOOL.nextInt(de-NUM_MIN)+NUM_MIN,de);
            }

        }

        public static List<Integer> genOperator(int operationsSum, boolean noMulti){
            List<Integer>  operationList=new ArrayList<>();
            for(int i=0;i<operationsSum;i++){
                if(noMulti){
                    operationList.add(RANDOM_TOOL.nextInt(2));
                }else {
                    operationList.add(RANDOM_TOOL.nextInt(2,4));
                }
            }
            return operationList;
        }

}
