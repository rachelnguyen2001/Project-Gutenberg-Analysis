import java.io.File;
import java.io.FileNotFoundException;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.*;

class Pair {
    String s;
    int occurrences;

    Pair(String s, int occurrences) {
	this.s = s;
	this.occurrences = occurrences;
    }
}

/**
 *
 * A text analysis program
 */
class TextAnalysis {
   
    /**
     * This method reads the content of a file.
     * @param fileName the name of the file to be read
     * @return ArrayList<String> a list of words in the file
     */ 
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

    /**
     * This method gets the total number of words in a file.
     * @param fileName the name of the file to be read
     * @return int the total number of words in a file
     */
    public static int getTotalNumberOfWords(String fileName) {
	ArrayList<String> content = readFile(fileName);

	return content.size();
    }

    /**
     * This method pairs each word with its frequency in a file.
     * @param fileName the name of the file to be read
     * @return HashMap<String, Integer> a map that maps each word with its' occurrences
     */
    public static HashMap<String, Integer> getUniqueWords(String fileName) {
	ArrayList<String> content = readFile(fileName);
	HashMap<String, Integer> uniqueWords = new HashMap<>();

	for (int i = 0; i < content.size(); i++) {
	    String current = content.get(i);
	    uniqueWords.put(current, uniqueWords.getOrDefault(current, 0) + 1);
	}

	return uniqueWords;
    }

    /**
     * This method gets the total number of unique words in a file.
     * @param fileName the name of the file to be read
     * @return int the total number of unique words in a file
     */
    public static int getTotalUniqueWords(String fileName) {
	HashMap<String, Integer> uniqueWords = getUniqueWords(fileName);

    	return uniqueWords.size();
    }

    /**
     * This method sorts a list of words by its occurrences in decreasing order.
     * @param HashMap<String, Integer> a map that maps each word to its occurrence
     * @return List<String> words are sorted in decreasing order by there frequencies
     */
    public static List<String> sortByFrequency(HashMap<String, Integer> uniqueWords) {
	Comparator<String> byFrequency = (String s1, String s2) -> (uniqueWords.get(s2) - uniqueWords.get(s1));
	Set<String> s = uniqueWords.keySet();
	List<String> uniqueWord = s.stream().collect(Collectors.toList());
	Collections.sort(uniqueWord, byFrequency);
	return uniqueWord;
    }

    /**
     * This method gets the 20 most frequent words in a file.
     * @param fileName the name of the file to be read
     * @return Pair[] an array of Pair, each Pair has the word and its occurrence
     */
    public static Pair[] get20MostFrequentWords(String fileName) {
	HashMap<String, Integer> uniqueWords = getUniqueWords(fileName);
	List<String> uniqueWord = sortByFrequency(uniqueWords);

        Pair[] result = new Pair[20];

	for (int i = 0; i < result.length; i++) {
	    String current = uniqueWord.get(i);
	    result[i] = new Pair(current, uniqueWords.get(current));
	}

    	return result;
    }

    /**
     * This method gets the 20 most interesting frequent words in a file.
     * @param fileName the name of the file to be read
     * @return Pair[] an array of Pair, each Pair has the word and its occurrence
     */
    public static Pair[] get20MostInterestingFrequentWords(String fileName) {
	ArrayList<String> mostCommon = readFile("1-1000.txt");
	HashMap<String, Integer> uniqueWords = getUniqueWords(fileName);
	List<String> uniqueWord = sortByFrequency(uniqueWords);

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

    /**
     * This method gets the 20 least frequent words in a file.
     * @param fileName the name of the file to be read
     * @return Pair[], an array of Pair, each Pair has the word and its occurrence 
     */
    public static Pair[] get20LeastFrequentWords(String fileName) {
	HashMap<String, Integer> uniqueWords = getUniqueWords(fileName);
	List<String> uniqueWord = sortByFrequency(uniqueWords);

	Pair[] result = new Pair[20];
	int i = 0;
	int j = uniqueWord.size() - 1;

	while (i < result.length) {
	    String current = uniqueWord.get(j);
	    result[i] = new Pair(current, uniqueWords.get(current));
	    i++;
	    j--;
	}

	return result;
    }

    /**
     * This method gets the number of chapters in a file.
     * @param fileName the name of the file to be read
     * @return int the number of chapters in a file
     */
    public static int getNumberOfChapters(String fileName) {
	HashMap<String, Integer> uniqueWords = getUniqueWords(fileName);
	return uniqueWords.get("CHAPTER");
    }

    /**
     * This method maps an integer less than 13 to its Roman representation.
     * @param c an integer represents the chapter
     * @return String the Roman representation of the chapter
     */
    public static String intToRoman(int c) {
	if (c == 1) { return "I"; }
	if (c == 2) { return "II"; }
	if (c == 3) { return "III"; }
	if (c == 4) { return "IV"; }
	if (c == 5) { return "V"; }
	if (c == 6) { return "VI"; }
	if (c == 7) { return "VII"; }
	if (c == 8) { return "VIII"; }
	if (c == 9) { return "IX"; }
	if (c == 10) { return "X"; }
	if (c == 11) { return "XI"; }
	return "XII";
    }

    /**
     * This method gets the progressive frequency of a word throughout the file.
     * @param fileName the name of the file to be read
     * @param word the word that we want to know its progressive frequency
     * @return int[] the frequency of the word through each chapter
     */
    public static int[] getFrequencyOfWord(String fileName, String word) {
	int numOfChapters = getNumberOfChapters(fileName);
	int[] result = new int[numOfChapters];
	ArrayList<String> content = readFile(fileName);
	int maxIndex = content.size();

	for (int i = 0; i < result.length; i++) {
	    int count = 0;
	    String chapter = intToRoman(i + 1);
	    int startIndex = content.indexOf(chapter);

	    while (startIndex < maxIndex && !content.get(startIndex).equals("CHAPTER")) {

		if (word.equals(content.get(startIndex))) {
		    count++;
		}
		startIndex++;
	    }

	    result[i] = count;
	}
	
	return result;
    }

    /**
     * This method maps a Roman representation to an integer.
     * @param s the Roman representation
     * @return int integer value for the Roman representation
     */
    public static int romanToInt(String s) {
	if (s.equals("I")) { return 1; }
	if (s.equals("II")) { return 2; }
	if (s.equals("III")) { return 3; }
	if (s.equals("IV")) { return 4; }
	if (s.equals("V")) { return 5; }
	if (s.equals("VI")) { return 6; }
	if (s.equals("VII")) { return 7; }
	if (s.equals("VIII")) { return 8; }
	if (s.equals("IX")) { return 9; }
	if (s.equals("X")) { return 10; }
	if (s.equals("XI")) { return 11; }
	return 12;
    }

    /**
     * This method gets the content of a given chapter.
     * @param c the chapter value in integer
     * @param fileName the name of the file to be read
     * @return String content of the chapter
     */
    public static String getChapterContent(int c, String fileName) {
	String chapter = intToRoman(c);
	ArrayList<String> content = readFile(fileName);
	int startIndex = content.indexOf(chapter);
	int maxIndex = content.size();
	StringBuilder sb = new StringBuilder();
	
	while (startIndex < maxIndex && !content.get(startIndex).equals("CHAPTER")) {
	    sb.append(content.get(startIndex));
	    sb.append(" ");
	    startIndex++;
	}

	return sb.toString();
    }

    /**
     * This method gets the chapter in which a quote appears.
     * @param fileName the name of the file to be read
     * @param quote the quote
     * @return int the chapter in which the quote appears
     * @return -1 if the quote does not appear in the file
     */
    public static int getChapterQuoteAppears(String fileName, String quote) {
     	int numOfChapters = getNumberOfChapters(fileName);
	
	for (int i = 1; i <= numOfChapters; i++) {
	    String content = getChapterContent(i, fileName);

	    if (content.contains(quote)) {
		return i;
	    }
	    
	}

	return -1;
    }

    /**
     * This method intializes the process for sentence generation.
     * @param fileName the name of the file to be read
     * @return t a Table that contains all the words and their following words
     */
    public static Table initializeGeneration(String fileName) {
	ArrayList<String> content = readFile(fileName);
	Table t = new Table();
	
	for (int i = 0; i < content.size() - 1; i++) {
	    String current = content.get(i);
	    String follow = content.get(i + 1);
	    t.updateTable(current, follow);
	}

	return t;
    }

    /**
     * This method generates a sentence that starts with "The ".
     * @param fileName the name of the file to be read
     * @return String the generated sentence
     */
    public static String generateSentence(String fileName) {
	Table t = initializeGeneration(fileName);
	String current = "The";
	String result = "The ";
	int i = 0;

	while (i < 19) {
	    current = t.getNext(current);
	    result += current;
	    result += " ";
	    i++;
	}

	return result;
    }

    /**
     * This method initializes the process of autocomplete.
     * @param fileName the name of the file to be read
     * @return a Trie that represents all the strings in the file
     */
    public static Trie autoCompleteInitialization(String fileName) {
	Trie t = new Trie();
        ArrayList<String> content = readFile(fileName);
	
	for (int i = 0; i < content.size(); i++) {
	    List<String> current = new ArrayList<>();
	    current.add(content.get(i));
	    t.addSentence(current);
	    
	    for (int j = i + 1; j < content.size(); j++) {
		current.add(content.get(j));
		t.addSentence(current);
	    }
	    
	}

	return t;
    }

    /**
     * This method gets autocompleted sentences.
     * @param startOfSentence the start of a sentence 
     * @param fileName the name of the file to be read
     * @return List<String> a list of autocompleted strings
     */
    public static List<String> getAutocompleteSentence(String startOfSentence, String fileName) {
	List<String> content = new ArrayList<>();
	String current = startOfSentence;
	
	while (current.contains(" ")) {
	    content.add(current.substring(0, current.indexOf(" ")));

	    if (current.contains(" ")) {
		current = current.substring(current.indexOf(" ") + 1);
	    } else {
		break;
	    }
	}

	content.add(current);

	Trie t = autoCompleteInitialization(fileName);
	return t.getRecommendation(content);
	
    }

    /**
     * This method prints out the content of an array of Pair.
     * @param Pair[] an array of Pair
     */
    public static void pairArrayToString(Pair[] result) {
	for (int i = 0; i < result.length; i++) {
	    Pair current = result[i];
	    System.out.println(current.s + ": " + current.occurrences); 
	}
    }

    public static void main(String[] args) {
	String fileName = "62441-0.txt";

	System.out.println("Total number of words: ");
	System.out.println(getTotalNumberOfWords(fileName));

	System.out.println("Total number of unique words: ");
	System.out.println(getTotalUniqueWords(fileName));

	System.out.println("20 most frequent words: ");
	pairArrayToString(get20MostFrequentWords(fileName));

	System.out.println("20 most interesting frequent words: ");
	pairArrayToString(get20MostInterestingFrequentWords(fileName));

	System.out.println("20 least frequent words: ");
	pairArrayToString(get20LeastFrequentWords(fileName));

	System.out.println(Arrays.toString(getFrequencyOfWord(fileName, "the")));

	System.out.println(getChapterQuoteAppears(fileName, "How good it is to be free!"));

	System.out.println(generateSentence(fileName));

	System.out.println(getAutocompleteSentence("I", "test1.txt"));
	System.out.println(getAutocompleteSentence("as we have brought these adventures of Sharp Eyes to an end, we will say", fileName));
      
    }
}
