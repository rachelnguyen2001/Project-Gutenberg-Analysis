import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class TextAnalysis {

    String novelContent;
    Map<String, Integer> uniqueWords;

    public TextAnalysis(String fileName) {
	novelContent = "";
	getTotalNumberOfWords(fileName);
	uniqueWords = new HashMap<>();
    }

    public int getTotalNumberOfWords(String fileName) {
	int numsOfWords = 0;
	StringBuilder sb = new StringBuilder();
	
	try {
	    File file = new File(fileName);
	    Scanner sc = new Scanner(file);

	    while (sc.hasNext()) {
		sb.append(sc.next());
		sb.append(" ");
		numsOfWords++;
	    }

	    sc.close();
	    novelContent = sb.toString();
	} catch (FileNotFoundException e) {
	    System.out.println("File not found!");
	}

	return numsOfWords;
    }

    public int getTotalUniqueWords() {

	while (novelContent.length() > 0) {
	    String current = novelContent.substring(0, novelContent.indexOf(" "));
	    this.uniqueWords.put(current, uniqueWords.getOrDefault(current, 0) + 1);

	    if (novelContent.contains(" ")) {
		novelContent = novelContent.substring(novelContent.indexOf(" ") + 1);
	    } else {
		novelContent = "";
	    }
	}

	return uniqueWords.size();
    }

    public static void main(String[] args) {
	TextAnalysis ta = new TextAnalysis("62441-0.txt");
	System.out.println(ta.getTotalUniqueWords());
    }
}
