package ru.skillbench.tasks.basics.control;

public class ControlFlowStatements1Impl implements ControlFlowStatements1 {
    public float getFunctionValue(float x){
        if (x>0)
            return (float)(2*Math.sin(x));
        else
            return 6-x;
    }

    public String decodeWeekday(int weekday){
        switch (weekday){
            case (1):
                return "Monday";
            case (2):
                return "Tuesday";
            case (3):
                return "Wednesday";
            case (4):
                return "Thursday";
            case (5):
                return "Friday";
            case (6):
                return "Saturday";
            case (7):
                return "Sunday";
        }
        return "Not a day";
    }

    public int[][] initArray(){
        int[][] myArray = new int[8][5];
        for (int i = 0; i < 8; ++i)
            for (int j = 0; j < 5; ++j){
                myArray[i][j] = i*j;
            }
        return myArray;
    }

    public int getMinValue(int[][] array){
        int minimum = array[0][0];
        for (int[] array1 : array)
            for (int num : array1){
                if (num < minimum){
                    minimum = num;
                }
            }
        return minimum;
    }

    public ControlFlowStatements1.BankDeposit calculateBankDeposit(double P){
        ControlFlowStatements1.BankDeposit deposit = new ControlFlowStatements1.BankDeposit();
        deposit.amount = 1000;
        do{
            deposit.years ++;
            deposit.amount *= (1+P/100);
        } while(deposit.amount < 5000);
        return deposit;
    }

    public static void main(String[] args) {
        ControlFlowStatements1 object = new ControlFlowStatements1Impl();
        System.out.println(object.getFunctionValue((float)0.5));
        System.out.println(object.calculateBankDeposit(12.5));
    }
}
