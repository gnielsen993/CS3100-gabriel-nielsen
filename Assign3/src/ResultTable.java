import java.util.HashMap;

public class ResultTable {
    private final HashMap<Integer, Integer> resultTable = new HashMap<>();

    public synchronized void addResult(int digitPosition, int digitValue) {
        resultTable.put(digitPosition, digitValue);
    }

    public synchronized HashMap<Integer, Integer> getResults() {
        return resultTable;
    }
}