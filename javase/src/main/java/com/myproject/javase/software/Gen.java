package com.myproject.javase.software;

import java.io.*;
import java.util.*;
import java.util.logging.Logger;

/**
 * @author lkxl
 */
public class Gen {
        public static final Logger logger = Logger.getLogger("Caculate");

        public   static  final Random RANDOM_TOOL = new Random();

        public static final ArrayList<String> OPERATION_LIST = new ArrayList<>(Arrays.asList("+","-","×","÷"));

        public static  String InputExerciseFile="./Exercises.txt";

        public static void setInputExerciseFile(String inputExerciseFile) {
                InputExerciseFile = inputExerciseFile;
        }

        public static void setInputAnswerFile(String inputAnswerFile) {
                InputAnswerFile = inputAnswerFile;
        }

        public static  String InputAnswerFile="./Answers.txt";

        public static  String OutputExerciseFile="./Exercises.txt";
        public static  String OutputAnswerFile="./Answers.txt";
;
        public static  String OutputGradeFile="./Grade.txt";
        /**
         * 运算符个数的上下限
         **/
        public static  final int OPERATION_MAX =3;
        public static final int OPERATION_MIN =1;

        /**
         * 生成数字的上下限
         **/
        public static  int NUM_MAX =100;
        public static final int NUM_MIN =0;

        public void setMax(int number){
                NUM_MAX=number;
        }

        /**
         * 符号列表
         **/


        public void CaculateRes(Integer input) throws Exception {
                HashSet<Equation> euoations =new HashSet<>();
                int OPERATION_USE=0;
                for (int j = 1; j <= input; j++) {
                    //随机生成操作符总数
                    boolean noMulti =true;
                    boolean isRequired=false;
                    Question question=new Question();
                    question.genQuestion();
                    String questionstr=question.toString();
                    String answer=question.Answer;
                    output(OutputExerciseFile,j,questionstr);
                    output(OutputAnswerFile,j,answer);
                }
        }

        public void output(String filename,int number,String data) throws Exception {
                String str=number+"."+data+"\n";
                try(PrintWriter writer=new PrintWriter(filename)){
                        writer.write(str);
                }

        }

        public void check() throws FileNotFoundException {
                Scanner questionSc=new Scanner(InputExerciseFile);
                Scanner answerSc=new Scanner(InputAnswerFile);
                int i=0,w=0,l=0;
                ArrayList<Integer> Correct = new ArrayList<>();
                ArrayList<Integer> Wrong=new ArrayList<>();
                while (questionSc.hasNextLine()){
                        while (answerSc.hasNextLine()){
                                String question=questionSc.nextLine().substring(2).trim();
                                String answer=answerSc.nextLine().substring(2).trim();
                                i+=1;
                                Caculate caculate=new Caculate(question);
                                if(caculate.output.equals(answer)){
                                        Correct.add(i);
                                }else{
                                        Wrong.add(i);
                                }
                        }
                }
                String output="Correct:"+Correct.size()+Correct.toString()+"/nWrong:"+Wrong.size()+Wrong.toString();
                try(PrintWriter writer=new PrintWriter(OutputGradeFile)){
                        writer.write(output);
                }

        }


}
