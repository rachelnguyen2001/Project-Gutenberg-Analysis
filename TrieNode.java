import java.util.*;

class TrieNode {

    protected String word;
    protected ArrayList<TrieNode> children;

    TrieNode(String word) {
	this.word = word;
	children = new ArrayList<>();
    }

    public void addChild(TrieNode tn) {
	children.add(tn);
    }

    public TrieNode getChild(String s) {

	for (int i = 0; i < children.size(); i++) {
	    if (children.get(i).word.equals(s)) {
		return children.get(i);
	    }
	}

	return null;
    }

    public static void main(String[] args) {
	TrieNode tn = new TrieNode("please");
	tn.addChild(new TrieNode("give"));
	tn.addChild(new TrieNode("me"));
	tn.addChild(new TrieNode("a"));
	tn.addChild(new TrieNode("job"));
	System.out.println(tn.getChild("me").word);
    }
}
