package ru.sberbank.edu;

import ru.sberbank.edu.storage.DBStorage;
import ru.sberbank.edu.storage.FileStorage;
import ru.sberbank.edu.statistics.RowStatistic;
import ru.sberbank.edu.statistics.Statistic;
import ru.sberbank.edu.storage.Storage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


public class App 
{
    public static void main( String[] args ) throws IOException {
        Path dataPath = Paths.get("module02", "src","test","resources" , "data02.txt");
        String absDataPath = dataPath.toFile().getAbsolutePath();

        Path statPath = Paths.get("module02", "src","test","resources" , "statistics.txt");
        String absStatPath = statPath.toFile().getAbsolutePath();

        Statistic statistic = new RowStatistic(absDataPath);

        save(new FileStorage(absStatPath), statistic.toString());
        save(new DBStorage("hana"), statistic.toString());
    }

    /**
     * save statistics data
     * @param storage file- or db-storage
     * @param data row statistics
     * @throws IOException
     */
    public static void save(Storage storage, String data) throws IOException {
        storage.save(data);
    }
}
