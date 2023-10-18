package ru.sberbank.edu.saver;

import ru.sberbank.edu.statistics.Statistic;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileSaver implements Saver{
    private final String path;
    public FileSaver(String path){
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

        System.out.println("end of recording statistics to file");
    }

}