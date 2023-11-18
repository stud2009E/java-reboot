package ru.sberbank.edu.statistics;

/**
 * statistics of text: space count, lines count, the longest line
 */
public interface Statistic {

    /**
     * count lines in file
     * @return count of lines
     */
    int getLineCount();

    /**
     * calculate space count in file
     * @return number of spaces
     */
    int getSpaceCount();

    /**
     * find the longest line from file
     * @return longest line
     */
    String getLongestLine();
}
