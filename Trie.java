import java.util.*;

class Trie {

    protected TrieNode root;

    public Trie() {
	root = new TrieNode(" ");
    }

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
    
    public String listToString(List<String> l) {
	String soFar = "";

	for (int i = 0; i < l.size(); i++) {
	    soFar += l.get(i);
	    soFar += " ";
	}

	return soFar;
    }
    
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
