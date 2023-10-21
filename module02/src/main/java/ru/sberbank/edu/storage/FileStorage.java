package ru.sberbank.edu.storage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class FileStorage implements Storage {
    private final String path;
    public FileStorage(String path){
        this.path = path;
    }

    @Override
    public void save(String data) throws IOException {
        File file = new File(path);

        System.out.println("start recording statistics to file...");

        System.out.println("recording...");
        try(OutputStreamWriter streamWriter = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8)){
            streamWriter.write(data);
        }

        System.out.println(data);
        System.out.println("end of recording statistics to file");
    }

}