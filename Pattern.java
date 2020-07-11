import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.*;

/**
 * A Pattern that stores that frequencies of following words.
 */
class Pattern {
    Map<String, Integer> frequency;
    
    public Pattern() {
	frequency = new HashMap<>();
    }

    /**
     * This method updates Pattern.
     * @param s the string that we want to update its frequency
     */
    public void updatePattern(String s) {
	frequency.put(s, frequency.getOrDefault(s, 0) + 1);
    }

    /**
     * This method sorts all the words in Pattern by their frequencies in decreasing order.
     * @return List<String> all the words in Pattern sorted by their frequencies in decreasing order
     */
    public List<String> sortFrequency() {
	Comparator<String> c = (String s1, String s2) -> (frequency.get(s2) - frequency.get(s1));
	Set<String> s = frequency.keySet();
	List<String> l = s.stream().collect(Collectors.toList());
	Collections.sort(l, c);
	return l;
    }

    /**
     * This method gets the most likely word to follow the word associated with this Pattern.
     * @return String the most likely word to follow the word associated with this Pattern 
     */
    public String getNextWord() {
	List<String> l = sortFrequency();
	return l.get(0);
    }
}
