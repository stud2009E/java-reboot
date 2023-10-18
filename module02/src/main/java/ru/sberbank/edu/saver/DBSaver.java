package ru.sberbank.edu.saver;

import ru.sberbank.edu.statistics.Statistic;

public class DBSaver implements Saver {

    private final String dbname;

    public DBSaver(String dbname) {
        this.dbname = dbname;
    }

    @Override
    public void save(Statistic statistic) {
        System.out.println("open connection to " + dbname);
        System.out.println("start recording statistics...");

        System.out.println("recording...");
        System.out.println(statistic);

        System.out.println("end of recording statistics");
        System.out.println("close connection to " + dbname);
    }
}
