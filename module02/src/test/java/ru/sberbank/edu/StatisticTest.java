package ru.sberbank.edu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.sberbank.edu.statistics.RowStatistic;
import ru.sberbank.edu.statistics.Statistic;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Unit test for StatisticTest.
 */
public class StatisticTest {
    @ParameterizedTest(name = "source file {0}")
    @CsvSource({
            "data01.txt,4,28,Eu tincidunt tortor aliquam nulla facilisi cras fermentum. Ac tortor dignissim convallis aenean.",
            "data02.txt,5,15,5     whitespace",
            "data03.txt,0,0,"
    })
    public void statisticCheck(String filename, int lineCount, int spaceCount, String longestLine){
        Path dataPath = Paths.get("src", "test", "resources", filename);
        String absDataPath = dataPath.toFile().getAbsolutePath();

        try {
            Statistic statistic = new RowStatistic(absDataPath);
            assertThat(statistic.getLineCount()).isEqualTo(lineCount);
            assertThat(statistic.getSpaceCount()).isEqualTo(spaceCount);
            assertThat(statistic.getLongestLine()).isEqualTo(longestLine);
        }catch (IOException e){
            fail("file i/o error", e);
        }
    }

    @Test
    @DisplayName("Check io exception with no file")
    public void failWithIOExceptionTest(){
        try {
            new RowStatistic("");
            fail("Expected exception was not thrown");
        } catch (IOException e) {
            assertNotNull(e);
        }
    }
}
