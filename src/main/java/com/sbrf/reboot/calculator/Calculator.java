package com.sbrf.reboot.calculator;

public class Calculator {
    public static int getAddition(int firstDigit, int secondDigit) {
        return firstDigit + secondDigit;
    }

    public static int getSubtraction(int firstDigit, int secondDigit) {
        return firstDigit - secondDigit;
    }

    public static int getMultiplication(int firstDigit, int secondDigit) {
        return firstDigit * secondDigit;
    }

    public static int getDivision(int firstDigit, int secondDigit) {
        return firstDigit / secondDigit;
    }

    public static int getPow(int digit, int powValue) {
        if (powValue == 1) {
            return digit;
        } else {
            return digit * getPow(digit, powValue - 1);
        }
    }

    public static long getFactorial(int digit) {
        if (digit <= 2) {
            return digit;
        }
        return digit * getFactorial(digit - 1);
    }

    public static double getPercent(int digit) {
        return (double) digit / 100;
    }

}
