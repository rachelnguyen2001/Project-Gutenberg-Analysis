import java.util.*;

/**
 * A Trie that stores all the strings in a text.
 */
class Trie {

    protected TrieNode root;

    public Trie() {
	root = new TrieNode(" ");
    }

    /**
     * This method adds a sentence to the Trie.
     * @param l a list of strings that represent the sentence
     */
    public void addSentence(List<String> l) {
	TrieNode current = root;
	int i = 0;

	while (i < l.size()) {

	    String now = l.get(i);
	    
	    if (current.getChild(now) != null) {
		current = current.getChild(now);
	    } else {
		TrieNode newNode = new TrieNode(now);
		current.addChild(newNode);
		current = newNode;
	    }

	    i++;
	}
	
    }

    /**
     * This method checks if a sentence is in the Trie.
     * @param l a list of strings that represent the sentence
     * @return true if the sentence is in the Trie
     * @return false if the sentence is not in the Trie
     */
    public boolean containsSentence(List<String> l) {
	TrieNode current = root;
	
	for (int i = 0; i < l.size(); i++) {
	    String now = l.get(i);

	    if (current.getChild(now) != null) {
		current = current.getChild(now);
	    } else {
		return false;
	    }
	}

	return true;
    }

    /**
     * This method gets a sentence and returns an array of all strings that starts with the sentence.
     * @param l a list of strings that represent the sentence
     * @return ArrayList<String> a list of all strings that start with the sentence
     */
    public ArrayList<String> getRecommendation(List<String> l) {
	ArrayList<String> result = new ArrayList<>();

	if (!containsSentence(l)) { return result; }

	TrieNode current = root;
	
        for (int i = 0; i < l.size(); i++) {
	    String now = l.get(i);
	    current = current.getChild(now);
	}

	if (current == null) { return result; }

	String soFar = listToString(l);
	
	for (int i = 0; i < current.children.size(); i++) {
	    getRecommendationHelper(soFar, current.children.get(i), result);
	}

	return result;
    }

    /**
     * This method formats a sentence
     * @param l a list of string that represents a sentence
     * @return String a formatted sentence
     */
    public String listToString(List<String> l) {
	String soFar = "";

	for (int i = 0; i < l.size(); i++) {
	    soFar += l.get(i);
	    soFar += " ";
	}

	return soFar;
    }

    /**
     * This method gets all the sentences that start with a given string.
     * @param soFar the sentence we have so far
     * @param tn the current TrieNode
     * @param result the list of all the possible sentences
     */
    public void getRecommendationHelper(String soFar, TrieNode tn, ArrayList<String> result) {
	soFar += tn.word;
	soFar += " ";
	result.add(soFar);
	
	if (tn.children.size() > 0) {

	    for (int i = 0; i < tn.children.size(); i++) {
		getRecommendationHelper(soFar, tn.children.get(i), result);
	    }
	}

    }
    
    public static void main(String[] args) {
	Trie t = new Trie();
	List<String> l = new ArrayList<>();
	l.add("please");
	l.add("give");
	l.add("me");
	l.add("a");
	l.add("job");

	t.addSentence(l);
	List<String> l1 = new ArrayList<>();
	l1.add("please");
	l1.add("give");
	System.out.println(t.getRecommendation(l1));
	
    }
}
