package ru.sberbank.edu.storage;

import ru.sberbank.edu.statistics.Statistic;
import java.io.IOException;

public interface Storage {

    void save(Statistic statistic) throws IOException;

}
