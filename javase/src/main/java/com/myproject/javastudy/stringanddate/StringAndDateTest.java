package com.myproject.javastudy.stringanddate;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

/**
 * @author lkxl
 */
public class StringAndDateTest {
    private final Logger logger = Logger.getLogger("test.Test");
    private static final String EMPTY_STRING_EXCEPTION ="Do not use Empty String";
    public void  testString() throws MyExceptions {
        String testString = "  Hello World  ";
        String testTrim="test"+testTrim(testString)+"test";
        logger.info(testTrim);
        testString=testTrim.replace(" ","").replace("test","");
        String testReverse=indexReverse(testString,3,5);
        logger.info(testReverse);
        String testReverseSide=indexReverse(testString,0,9);
        logger.info(testReverseSide);
        String testContain=Integer.toString(containTime("llHelloWorlldll","ll"));
        logger.info(testContain);
        String sameSubString = sameSubString("HellWorld","WorldHell");
        logger.info(sameSubString);
        String sortString = sortString(testString);
        logger.info(sortString);

    }

    public String  testTrim(String test) throws MyExceptions {
        if(test.isEmpty()){
            throw new MyExceptions(EMPTY_STRING_EXCEPTION);
        }
        int startSpace =0;
        int endSpace =0;
        for(int i=0;i<test.length();i++){
            if((test.charAt(i))==32){
                startSpace+=1;
            }else {
                break;
            }
        }
        for(int j=test.length()-1;j>=0;j--){
            if((test.charAt(j))==32){
                endSpace+=1;
            }else {
                break;
            }
        }
        return test.substring(startSpace,test.length()-endSpace);
    }

    public String indexReverse(String test,int startIndex,int endIndex) throws MyExceptions {

        if(endIndex>=test.length()||startIndex<0||test.isEmpty()){
            throw new MyExceptions("Error Input");
        }
        else{
            StringBuilder stringBuilder =new StringBuilder();
            StringBuilder reverstring =new StringBuilder();
            if(startIndex!=0){
                stringBuilder.append(test.substring(0,startIndex));
            }
            reverstring.append(test.substring(startIndex,endIndex+1));
            reverstring.reverse();
            stringBuilder.append(reverstring);
            if(endIndex!=(test.length()-1)){
                stringBuilder.append(test.substring(endIndex+1,test.length()));
            }
            return  stringBuilder.toString();
        }
    }

    public String sameSubString(String string1,String string2) throws MyExceptions {
        if(string1.isEmpty()||string2.isEmpty()){
            throw new MyExceptions(EMPTY_STRING_EXCEPTION);
        }
        if(string1.length()>string2.length()){
            String temp=string1;
            string1=string2;
            string2=temp;
        }
        String result="";
        for(int i=string2.length();i>=0;i--){
            for(int j=0;j<=string2.length()-i;j++){
                if(string1.contains(string2.substring(j,j+i))){
                        result=string2.substring(j,j+i);
                        break;
                }
            }
            if(!(result.isEmpty())){
                break;
            }
        }
        return result;
    }

    public String sortString(String testString) throws MyExceptions {
        if(testString.isEmpty()){
            throw new MyExceptions(EMPTY_STRING_EXCEPTION);
        }
        char[] charArray=new char[testString.length()-1];
        testString.getChars(0,testString.length()-1,charArray,0);
        Arrays.sort(charArray);
        return  new String(charArray);
    }

    public int getTime(byte[] testByte,byte[] containByte){
        int times=0;
        for(int i=0;i<testByte.length;i++){
            if(testByte[i]==containByte[0]){
                for(int j=1;j<containByte.length;j++){
                    if(i+j>=testByte.length||containByte[j]!=testByte[i+j]){
                        break;
                    }
                    if(j==containByte.length-1){
                        times+=1;
                    }
                }
            }
        }
        return times;
    }

    public  int containTime(String testString,String containString) throws MyExceptions {
        if(testString.isEmpty()||containString.isEmpty()){
            throw new MyExceptions(EMPTY_STRING_EXCEPTION);
        }
        byte[] testByte=testString.getBytes();
        byte[] containByte=containString.getBytes();
        return getTime(testByte,containByte);
    }

    public void testDate(){
        //1970.1.1
        long currentTime =System.currentTimeMillis();
        String time = Long.toString(currentTime);
        Date date = new Date(currentTime);
        String dateTime = date.toString();
        Calendar calendar =Calendar.getInstance();
        String calendarTime = calendar.toString();
        java.sql.Date sqlDate = new java.sql.Date(currentTime);
        String sqlDateStr=sqlDate.toString();
        logger.info(time);
        logger.info(dateTime);
        logger.info(calendarTime);
        logger.info(sqlDateStr);
    }
}
