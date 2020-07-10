import java.util.*;

class Table {
    Map<String, Pattern> occurrences;
    
    public Table() {
	occurrences = new HashMap<>();
    }

    public void updateTable(String current, String follow) {

	if (!occurrences.containsKey(current)) {
	    occurrences.put(current, new Pattern());
	}

	occurrences.get(current).updatePattern(follow);
    }

    public String getNext(String current) {
	return occurrences.get(current).getNextWord();
    }

}
