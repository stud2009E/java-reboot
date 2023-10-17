package ru.sberbank.edu;

public class StatisticImpl implements Statistic{

    private String path;

    public StatisticImpl(String path){
        this.path = path;
    }
    @Override
    public int getLineCount() {
        return 0;
    }

    @Override
    public int getSpaceCount() {
        return 0;
    }

    @Override
    public String getLongestLine() {
        return null;
    }
}
