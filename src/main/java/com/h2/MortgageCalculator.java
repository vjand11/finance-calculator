package com.h2;

import java.text.DecimalFormat;

public class MortgageCalculator {

    private long loanAmount;
    private int termInYears;
    private float annualRate;
    private double monthlyPayment;

    public MortgageCalculator(long loanAmount, int termInYears, float annualRate) {
        this.loanAmount = loanAmount;
        this.termInYears = termInYears;
        this.annualRate = annualRate;
    }

    private int getNumberOfPayments() {
        return (this.termInYears * 12);
    }

    private float getMonthlyInterestRate() {
        float interestRate = (annualRate / 100);
        float monthlyRate = (interestRate / 12);
        return monthlyRate;
    }

    public void calculateMonthlyPayment() {
        long P = loanAmount;
        float r = getMonthlyInterestRate();
        int n = getNumberOfPayments();
        double m = P * (((r * Math.pow(1 + r, n))) / ((Math.pow((1 + r), n)) - 1));
        this.monthlyPayment = m;
    }

    public String toString() {
        DecimalFormat df = new DecimalFormat("####0.00");
        return "monthlyPayment: " + df.format(monthlyPayment);
    }

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("usage: mortgageCalculator <loanAmount> <termInYears> <annualRate>");
            System.exit(-1);
        }

        long loanAmount = Utilities.getLongValue(args[0]);
        int termInYears = Utilities.getIntValue(args[1]);
        float annualRate = Utilities.getFloatValue(args[2]);

        MortgageCalculator calculator = new MortgageCalculator(loanAmount, termInYears, annualRate);

        calculator.calculateMonthlyPayment();

        System.out.println(calculator.toString());
    }
}
