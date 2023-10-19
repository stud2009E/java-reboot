package ru.sberbank.edu.storage;

import ru.sberbank.edu.statistics.Statistic;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileStorage implements Storage {
    private final String path;
    public FileStorage(String path){
        this.path = path;
    }

    @Override
    public void save(Statistic statistic) throws IOException {
        File file = new File(path);

        System.out.println("start recording statistics to file...");

        System.out.println("recording...");
        try(OutputStreamWriter streamWriter = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8)){
            streamWriter.write(statistic.toString());
        }

        System.out.println(statistic);
        System.out.println("end of recording statistics to file");
    }

}