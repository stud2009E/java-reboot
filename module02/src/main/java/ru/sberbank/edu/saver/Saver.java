package ru.sberbank.edu.saver;

import ru.sberbank.edu.statistics.Statistic;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface Saver {

    void save(Statistic statistic) throws IOException;

}
