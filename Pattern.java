import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.*;

class Pattern {
    Map<String, Integer> frequency;
    
    public Pattern() {
	frequency = new HashMap<>();
    }

    public void updatePattern(String s) {
	frequency.put(s, frequency.getOrDefault(s, 0) + 1);
    }

    public List<String> sortFrequency() {
	Comparator<String> c = (String s1, String s2) -> (frequency.get(s2) - frequency.get(s1));
	Set<String> s = frequency.keySet();
	List<String> l = s.stream().collect(Collectors.toList());
	Collections.sort(l, c);
	return l;
    }

    public String getNextWord() {
	List<String> l = sortFrequency();
	return l.get(0);
    }
}
