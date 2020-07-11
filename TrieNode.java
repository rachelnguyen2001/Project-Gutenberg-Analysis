import java.util.*;

class TrieNode {

    protected String word;
    protected ArrayList<TrieNode> children;

    TrieNode(String word) {
	this.word = word;
	children = new ArrayList<>();
    }

    /**
     * This method adds a child to the given TrieNode.
     * @param tn the TrieNode to be added as a child
     */
    public void addChild(TrieNode tn) {
	children.add(tn);
    }

    /**
     * This method gets a child of the current TrieNode that represents a given word.
     * @param s the given word
     * @return TrieNode if there is a child that represents the given word
     * @return null if there is no child that represents the given word
     */
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
