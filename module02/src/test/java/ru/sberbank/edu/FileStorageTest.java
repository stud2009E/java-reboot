package ru.sberbank.edu;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.sberbank.edu.storage.FileStorage;
import ru.sberbank.edu.storage.Storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

public class FileStorageTest {

    private Storage storage;
    private File file;

    @BeforeEach
    public void setUp(){
        file = null;
        try {
            file = File.createTempFile("tempFileName", ".tmp");
            storage = new FileStorage(file.getAbsolutePath());
        } catch (IOException e) {
            fail("can not create temp file for test");
        }
    }

    @ParameterizedTest(name="{0}")
    @CsvSource({
            "Hello world",
            "Test Data",
            "row 1 row 2 row 3"
    })
    public void testContent(String data){
        try {
            storage.save(data);
        } catch (IOException e) {
            fail("can not save data into file", e);
        }

        try (InputStream inputStream = new FileInputStream(file)){
            byte[] inputStreamData = inputStream.readAllBytes();
            byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);

            assertArrayEquals(inputStreamData, dataBytes);
        } catch (IOException e) {
            fail("can not read data from file", e);
        }
    }

    @Test
    @DisplayName("try to save null data and catch IllegalArgumentException")
    public void testNullData(){
        try {
            storage.save(null);
            fail();
        } catch (IllegalArgumentException e){
            assertNotNull(e);
        }
        catch (IOException e) {
            fail("can not save data into file", e);
        }
    }

    @AfterEach
    public void tearDown(){
        if(!file.delete()){
            fail("can't delete file");
        }

        file = null;
        storage = null;
    }
}