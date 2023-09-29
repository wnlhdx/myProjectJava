package com.myproject.javase.software;

import java.util.Stack;

/**
 * @author lkxl
 */
public class Caculate {

        private final String input;
        private    StringBuilder calcuStr = new StringBuilder();
        public  Fraction res;
        Stack<Character> stack = new Stack<Character>();
        public String output;
        public Caculate(String in) {
            input = in;
            output =suffixToArithmetic(Trans()).toString();
        }




        public String Trans() {
            StringBuilder builder=new StringBuilder();
            for (int j = 0; j < input.length(); j++) {
                char ch = input.charAt(j);
                switch (ch) {
                    case '+', '-' -> gotOper(ch, 1);
                    case '×', '÷' -> gotOper(ch, 2);
                    case '(' -> stack.push(ch);
                    case ')' -> gotParen(ch);
                    default -> {
                        calcuStr.append(input.charAt(j));
                        int x=j+1;
                        while (j<input.length()-1&&((input.charAt(x)>=48&&input.charAt(x)<=57)||input.charAt(x)==39||input.charAt(x)==47)){
                            calcuStr.append(input.charAt(x));
                            j=x;
                            x+=1;
                        }
                        calcuStr.append(" ");
                    }
                }
            }
            while (!stack.isEmpty()) {
                calcuStr.append(stack.pop()).append(" ");
            }
            return calcuStr.toString().trim();
        }
        public void gotOper(char opThis, int prec1) {
            while (!stack.isEmpty()) {
                char opTop = stack.peek();
                if (opTop == '(') {
                    break;
                }
                else {
                    int prec2;
                    if (opTop == '+' || opTop == '-')
                        prec2 = 1;
                    else
                        prec2 = 2;
                    if (prec2 < prec1) {
                        break;
                    }
                    else {
                        stack.pop();
                        calcuStr.append(opTop).append(" ");
                    }
                }
            }
            stack.push(opThis);
        }
        public void gotParen(char ch){
            while (!stack.isEmpty()) {
                char chx = stack.pop();
                if (chx == '(')
                    break;
                else {
                    calcuStr.append(chx).append(" ");
                }
            }
        }


    public Fraction suffixToArithmetic(String qua) {
        //使用正则表达式 匹配数字
        String[] strings = qua.toString().split(" ");  //将字符串转化为字符串数组
        Stack<Fraction> stack = new Stack<Fraction>();
        for (String str : strings) {
            //如果是数字，则进栈
            if (!Gen.OPERATION_LIST.contains(str)) {
                if(CheckIsInt(str)){
                    if(str==""){
                        System.out.println("??");
                    }
                    stack.push(new Fraction(Integer.parseInt(str),1));
                }else {
                    stack.push(new Fraction(str,true));
                }
            } else {
                //如果是运算符，弹出运算数，计算结果。
                Fraction y = stack.pop();
                Fraction x = stack.pop();
                stack.push( caculate(x, y, str)); //将运算结果重新压入栈。
            }
        }
        res= stack.pop(); //弹出栈顶元素就是运算最终结果。
        return res;
    }

    public boolean CheckIsInt(String str){
        if(str.contains("/")&&str.contains("'")){
            return  false;
        }
        return true;
    }
    private static Fraction caculate(Fraction x, Fraction y, String simble) {
               if (simble.trim().equals("+"))
                   return x.add(y);
               if (simble.trim().equals("-"))
                   return x.subtract(y);
               if (simble.trim().equals("×"))
                   return x.multiply(y);
               if (simble.trim().equals("÷"))
                   return x.divide(y);
               return new Fraction(0,1);
    }
}
