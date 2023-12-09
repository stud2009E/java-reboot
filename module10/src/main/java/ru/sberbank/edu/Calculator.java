package ru.sberbank.edu;

import org.springframework.stereotype.Component;

@Component
public class Calculator {

    public double complexPercent(String sum, String percentage, String years){
        double sumValue, percentageValue, yearsValue;

        try {
            sumValue = Double.parseDouble(sum);
            percentageValue = Double.parseDouble(percentage);
            yearsValue = Double.parseDouble(years);
        }catch (NumberFormatException | NullPointerException ex){
            throw new IllegalArgumentException("Неверный формат данных", ex);
        }

        if(sumValue <= 0|| percentageValue <= 0 || yearsValue <= 0){
            throw new IllegalArgumentException("Неверный формат данных. Скорректируйте значения");
        }

        if ( sumValue < 50000d ){
            throw new IllegalArgumentException("Минимальная сумма на момент открытия вклада 50_000 рублей");
        }

        return sumValue * Math.pow(1 + percentageValue / 100, yearsValue);
    }

}
