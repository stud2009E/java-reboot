package ru.sberbank.edu;

public class GDC implements CommonDivisor{

    /**
     * calculate the greatest common divisor of two numbers by Euclid method
     * @param firstNumber first number
     * @param secondNumber second number
     * @return GDC of two numbers
     */
    @Override
    public int getDivisor(int firstNumber, int secondNumber) {

        if (firstNumber < 0 || secondNumber < 0){
            throw new IllegalArgumentException("number must be not negative");
        }

        while (firstNumber != 0 && secondNumber != 0){
            if (firstNumber > secondNumber){
                firstNumber = firstNumber % secondNumber;
            }else {
                secondNumber = secondNumber % firstNumber;
            }
        }

        return firstNumber + secondNumber;
    }
}
