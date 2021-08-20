package ru.skillbench.tasks.basics.control;

public interface ControlFlowStatements1 {
    float getFunctionValue(float x);
    String decodeWeekday(int weekday);
    int[][] initArray();
    int getMinValue(int[][] array);
    BankDeposit calculateBankDeposit(double P);
    public static class BankDeposit{
        public int years = 0;
        public double amount;
        @Override
        public String toString() {
            return years+" years: $"+amount;
        }
    }
}
