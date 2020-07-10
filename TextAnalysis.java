import java.io.File;
import java.io.FileNotFoundException;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.lang.Object;
import java.util.*;

class Pair {
    String s;
    int occurrences;

    Pair(String s, int occurrences) {
	this.s = s;
	this.occurrences = occurrences;
    }
}

class TextAnalysis {

    public static ArrayList<String> readFile(String fileName) {
	ArrayList<String> content = new ArrayList<>();

	try {
	    File file = new File(fileName);
	    Scanner sc = new Scanner(file);

	    while (sc.hasNext()) {
		content.add(sc.next());
	    }

	    sc.close();
	} catch (FileNotFoundException e) {
	    System.out.println("File not found!");
	}

	return content;
    }

    public static int getTotalNumberOfWords(String fileName) {
	ArrayList<String> content = readFile(fileName);

	return content.size();
    }

    public static HashMap<String, Integer> getUniqueWords(String fileName) {
	ArrayList<String> content = readFile(fileName);
	HashMap<String, Integer> uniqueWords = new HashMap<>();

	for (int i = 0; i < content.size(); i++) {
	    String current = content.get(i);
	    uniqueWords.put(current, uniqueWords.getOrDefault(current, 0) + 1);
	}

	return uniqueWords;
    }

    public static int getTotalUniqueWords(String fileName) {
	HashMap<String, Integer> uniqueWords = getUniqueWords(fileName);

    	return uniqueWords.size();
    }

    public static List<String> sortByFrequency(HashMap<String, Integer> uniqueWords) {
	Comparator<String> byFrequency = (String s1, String s2) -> (uniqueWords.get(s2) - uniqueWords.get(s1));
	Set<String> s = uniqueWords.keySet();
	List<String> uniqueWord = s.stream().collect(Collectors.toList());
	Collections.sort(uniqueWord, byFrequency);
	return uniqueWord;
    }

    public static Pair[] get20MostFrequentWords(String fileName) {
	HashMap<String, Integer> uniqueWords = getUniqueWords(fileName);
	List<String> uniqueWord;
	uniqueWord = sortByFrequency(uniqueWords);

        Pair[] result = new Pair[20];

	for (int i = 0; i < result.length; i++) {
	    String current = uniqueWord.get(i);
	    result[i] = new Pair(current, uniqueWords.get(current));
	}

    	return result;
    }

    public static Pair[] get20MostInterestingFrequentWords(String fileName) {
	ArrayList<String> mostCommon = readFile("1-1000.txt");
	HashMap<String, Integer> uniqueWords = getUniqueWords(fileName);
	List<String> uniqueWord;
	uniqueWord = sortByFrequency(uniqueWords);

	Pair[] result = new Pair[20];
	int i = 0;
	int j = 0;
	
	while (i < result.length) {
	    String current = uniqueWord.get(j);
	    
	    if (!mostCommon.contains(current)) {
		result[i] = new Pair(current, uniqueWords.get(current));
		i++;
	    }

	    j++;   
	}

	return result;
    }

    public static void pairArrayToString(Pair[] result) {
	for (int i = 0; i < result.length; i++) {
	    Pair current = result[i];
	    System.out.println(current.s + ": " + current.occurrences); 
	}
    }

    public static void main(String[] args) {
	String fileName = "62441-0.txt";

	// 23778
	System.out.println("Total number of words: ");
	System.out.println(getTotalNumberOfWords(fileName));

	// 3257
	System.out.println("Total number of unique words: ");
	System.out.println(getTotalUniqueWords(fileName));

	System.out.println("20 most frequent words: ");
	pairArrayToString(get20MostFrequentWords(fileName));

	System.out.println("20 most interesting frequent words: ");
	pairArrayToString(get20MostInterestingFrequentWords(fileName));

       
    }
}
