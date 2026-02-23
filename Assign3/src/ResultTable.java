import java.util.HashMap;

/**
 * ResultTable is a thread-safe class that manages a hash map to store the computed digits of Pi. It provides methods to add results to the table and retrieve the entire result set. The synchronized methods ensure that multiple worker threads can safely access and modify the result table without conflicts.
 * @author Gabriel Nielsen
 */
public class ResultTable {
    private final HashMap<Integer, Integer> resultTable = new HashMap<>();

    /** Adds a computed digit to the result table. This method is synchronized to ensure thread safety when multiple worker threads are adding results to the table.
     * @param digitPosition the position of the digit of Pi that was computed
     * @param digitValue the value of the computed digit of Pi to be stored in the result table
     */
    public synchronized void addResult(int digitPosition, int digitValue) {
        resultTable.put(digitPosition, digitValue);
    }

    /** Retrieves the entire result set from the result table. This method is synchronized to ensure thread safety when multiple worker threads are accessing the result table.
     * @return a hash map containing the digit positions as keys and their corresponding computed digit values as values
     */
    public synchronized HashMap<Integer, Integer> getResults() {
        return resultTable;
    }
}