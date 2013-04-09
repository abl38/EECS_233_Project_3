import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.*;


public class ProjectThree {
	private static Scanner inputFile;
	public static Entry[] wordCount(String input_file) throws FileNotFoundException{
		File input = new File(input_file);
		inputFile = new Scanner(new FileInputStream(input));
		HashTable ht = new HashTable(9999);
		int averageCollisions = 0;
		while(inputFile.hasNextLine()){
			StringTokenizer st = new StringTokenizer(inputFile.nextLine(), " \t\n\r\f,.:;?![]()-—'");
			while(st.hasMoreElements()){
				// the current word being checked
				String s = st.nextToken().toLowerCase();
				averageCollisions = (averageCollisions + ht.collisions)/2;
				// initializing a hash code for the word
				// int hash = 0;
				// using Horner's method
				/*
				for (int i = 0; i < s.length(); i++){
					hash = hash * 2540 + s.charAt(i);
				}*/
				ht.insert(s);
			}
			
		}
		System.out.println("Average Collisions: " + averageCollisions);
		return ht.table;
	}
	public static void main(String[] args) throws FileNotFoundException{
		Entry[] table = wordCount(args[0]);
		PrintStream output = new PrintStream(new File (args[1]));
		
		for(int i = 0; i < table.length; i++){
			if(table[i] != null){
				output.println(table[i].key + ": " + table[i].occurrences);	
			}
		}
	}
}
