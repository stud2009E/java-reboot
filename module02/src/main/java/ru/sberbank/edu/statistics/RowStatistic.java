package ru.sberbank.edu.statistics;

import java.io.*;
import java.util.ArrayList;

/**
 * This class collects statistics on a text file. Calculates analytics:
 * 1. Number of spaces in the file
 * 2. Number of lines in the file
 * 3. Longest line
 */
public class RowStatistic implements Statistic {
    private final ArrayList<String> rows = new ArrayList<>();
    private int spaceCount = 0;
    private String longestLine = "";

    public RowStatistic(String path) throws IOException {

        try (FileReader fileReader = new FileReader(path);
             BufferedReader bufferedReader = new BufferedReader(fileReader)){

            String row = bufferedReader.readLine();
            while (row != null){
                rows.add(row);
                row = bufferedReader.readLine();
            }
        }
    }

    /**
     * count lines in file
     * @return count of lines
     */
    @Override
    public int getLineCount() {
        return rows.size();
    }

    /**
     * calculate space count in file
     * @return number of spaces
     */
    @Override
    public int getSpaceCount() {
        if (spaceCount > 0){
            return spaceCount;
        }

        for (String row : rows) {
            for (int i = 0; i < row.length(); i++) {
                if (row.charAt(i) == ' ') {
                    spaceCount++;
                }
            }
        }

        return spaceCount;
    }

    /**
     * find the longest line from file
     * @return longest line
     */
    @Override
    public String getLongestLine() {

        if (longestLine.length() > 0){
            return longestLine;
        }

        for (String row : rows){
            if (longestLine.length() < row.length()){
                longestLine = row;
            }
        }

        return longestLine;
    }
}
