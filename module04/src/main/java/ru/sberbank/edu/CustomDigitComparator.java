package ru.sberbank.edu;

import java.util.Comparator;

public class CustomDigitComparator implements Comparator<Integer> {

    @Override
    public int compare(Integer o1, Integer o2) {
        boolean o1IsOdd = isOdd(o1);
        boolean o2IsOdd = isOdd(o2);

        if (!o1IsOdd && o2IsOdd){
            return -1;
        }

        if (o1IsOdd && !o2IsOdd){
            return 1;
        }

        return o1 - o2;
    }

    /**
     * check number is odd
     * @param num {Integer} number
     * @return {boolean} result
     */
    private boolean isOdd(Integer num){
        return num % 2 == 1;
    }
}
