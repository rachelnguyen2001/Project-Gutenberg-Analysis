import java.util.*;

/**
 * A Table that contains words that are mapped with a list of their following words.
 */
class Table {
    Map<String, Pattern> occurrences;
    
    public Table() {
	occurrences = new HashMap<>();
    }

    /**
     * This method updates the Table.
     * @param current the key that we want to update
     * @param follow the following word that we want to update
     */
    public void updateTable(String current, String follow) {

	if (!occurrences.containsKey(current)) {
	    occurrences.put(current, new Pattern());
	}

	occurrences.get(current).updatePattern(follow);
    }

    /**
     * This method gets the most likely word to follow a given word.
     * @param current the word that we want to find another word to follow
     * @return String the most likely string to follow the given word
     */
    public String getNext(String current) {
	return occurrences.get(current).getNextWord();
    }

}
