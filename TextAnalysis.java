import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class TextAnalysis {

    public int getTotalNumberOfWords(String fileName) {
	int numsOfWords = 0;
	
	try {
	    File file = new File(fileName);
	    Scanner sc = new Scanner(file);

	    while (sc.hasNext()) {
		sc.next();
		numsOfWords++;
	    }

	    sc.close();
	} catch (FileNotFoundException e) {
	    System.out.println("File not found!");
	}

	return numsOfWords;
    }

    public static void main(String[] args) {
	TextAnalysis ta = new TextAnalysis();
	System.out.println(ta.getTotalNumberOfWords("62441-0.txt"));
    }
}
