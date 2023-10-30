package ru.sberbank.edu.storage;

import java.io.IOException;

public interface Storage {

    void save(String data) throws IOException;

}
