package com.myproject.javase.software;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author lkxl
 */
public class Fraction implements Comparable<Fraction>{
        public Fraction(){
            round=0;
            numerator=0;
            denominator=1;
        }
        int round;
        int numerator;
        int denominator;



        public int getRealNumerator() {
            return realNumerator;
        }

        public void setRealNumerator(int realNumerator) {
            this.realNumerator = realNumerator;
        }

        int realNumerator;
        /**
         * Constructor
         *
         * @param numr
         * @param denr
         */
        public Fraction(int numr, int denr,int round) {
            this();
            denominator = denr;
            realNumerator = numr+round*denr;
            reduce();
            calculateRound();
        }

        public Fraction(int numr, int denr) {
            this();
            denominator = denr;
            realNumerator = numr;
            reduce();
            calculateRound();
        }

        public Fraction(String str,boolean hasRound){
            this();
            String[] strings=str.split("/");
            this.denominator=Integer.parseInt(strings[1]);
            if(hasRound){
                String[] strings1=strings[0].split("'");
                this.round=Integer.parseInt(strings1[0]);
                this.numerator=Integer.parseInt(strings1[1]);
                realNumerator=numerator+denominator*round;
            }else{
                this.realNumerator=Integer.parseInt(strings[0]);
                calculateRound();
            }
        }



    @Override
    public boolean equals(Object obj) {
            Fraction fraction2=(Fraction) obj;
            return realNumerator==fraction2.getRealNumerator()&&denominator==fraction2.getDenominator();
    }

    public int getNumerator() {
            return numerator;
        }

        public void setNumerator(int numerator) {
            this.numerator = numerator;
        }

        public int getDenominator() {
            return denominator;
        }

        public void setRound(int round) {
            this.round = round;
        }

        public int getRound() {
            return round;
        }

        public void setDenominator(int denominator) {
            this.denominator = denominator;
        }

        public  void calculateRound() {
            round=realNumerator/denominator;
            numerator=realNumerator-denominator*round;
        }

        /**
         * Calculates gcd of two numbers
         *
         * @param numerator
         * @param denominator
         * @return
         */
        public int calculateGCD(int numerator, int denominator) {
//            if(denominator==0){
//                System.out.println("整数"+round+"真分子"+realNumerator+"分母"+denominator+"分子"+numerator);
//            }
            if (numerator % denominator == 0) {
                return denominator;
            }
            return calculateGCD(denominator, numerator % denominator);
        }

        /**
         * Reduce the fraction to lowest form
         */
        void reduce() {
            int gcd = calculateGCD(realNumerator, denominator);
            realNumerator /= gcd;
            denominator /= gcd;
        }

        /**
         * Adds two fractions
         *
         * @param fractionTwo
         * @return
         */
        public Fraction add(Fraction fractionTwo) {
            int numer = (realNumerator * fractionTwo.getDenominator()) + (fractionTwo.getRealNumerator() * denominator);
            int denr =  denominator * fractionTwo.getDenominator();
            return new Fraction(numer, denr);
        }

        /**
         * Subtracts two fractions
         *
         * @param fractionTwo
         * @return
         */
        public Fraction subtract(Fraction fractionTwo) {
            int newNumerator = (realNumerator * fractionTwo.denominator) - (fractionTwo.realNumerator * denominator);
            if(newNumerator<0){
                return null;
            }
            int newDenominator = denominator * fractionTwo.denominator;
            return new Fraction(newNumerator, newDenominator);
        }

        /**
         * Multiples two functions
         *
         * @param fractionTwo
         * @return
         */
        public Fraction multiply(Fraction fractionTwo) {
            int newNumerator = realNumerator * fractionTwo.realNumerator;
            int newDenominator = denominator * fractionTwo.denominator;
            return new Fraction(newNumerator, newDenominator);
        }

        /**
         * Divides two fractions
         *
         * @param fractionTwo
         * @return
         */
        public Fraction divide(Fraction fractionTwo) {
            int newNumerator = realNumerator * fractionTwo.getDenominator();
            int newDenominator = denominator * fractionTwo.realNumerator;
            return new Fraction(newNumerator, newDenominator);
        }

        /**
         * Returns string representation of the fraction
         */
        @Override
        public String toString() {
            if(numerator!=0){
                return this.round+"'"+this.numerator + "/" + this.denominator;
            }
            else {
               return  String.valueOf(round);
            }
        }

    @Override
    public int compareTo(Fraction o) {
            Fraction res=this.subtract(o);
            return res==null?-1:res.realNumerator;
    }
}
