package wordCount;
import java.io.*;
import java.util.*;

import pc2.CellList;
import pc2.CellList.CellNode;

public class main {

	public static void main(String[] args) throws IOException {
		String address = "/Users/mr.a/Desktop/eclipse/wordCount/src/wordCount/jokes.txt";
		FileInputStream file = new FileInputStream(address);
		Scanner fileInput = new Scanner(file);
		
			//Creating the arrayList
		ArrayList<String> words = new ArrayList<String>();
		ArrayList<Integer> count = new ArrayList<Integer>();
		
			//Scanning file and identifying words
		while (fileInput.hasNext()) {
			//Get the next word
			String nextWord = fileInput.next();
			//Determine if the word is an arrayList
			if(words.contains(nextWord)) {
				int index = words.indexOf(nextWord);
				count.set(index, count.get(index) + 1);
			}
			
			else {
				words.add(nextWord);
				count.add(1);
			}
			
		}
		
		fileInput.close();
		file.close();
		
		for(int i = 0; i < words.size(); i++) {
			System.out.println(words.get(i) + " : " + count.get(i));
		}
	}
}








