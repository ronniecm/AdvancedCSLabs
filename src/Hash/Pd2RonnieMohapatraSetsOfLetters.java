package Hash;

//Name: Ronnie Mohapatra
//Date: 18 March 2019
//What I learned: a) Remove items while iterating through it causes a ConcurrentModificationException
//				  b) Store items to delete first then removeAll(Collection<?>)
//				  c) Use good variable names
//How I feel about this lab: Good thought process and very challenging
//What I wonder: What other applications of Sets are there
//Question:  If Java didn't implement Sets, how would you solve this programming problem?  Be creative!
//
import java.util.*;
import java.io.*;

public class Pd2RonnieMohapatraSetsOfLetters {
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("declarationLast(1).txt");
		FileReader reader = new FileReader(file);
		BufferedReader br = new BufferedReader(reader);
		
		Set<Character> commonUpperCase = new HashSet<Character>();
		Set<Character> commonLowerCase = new HashSet<Character>();
		Set<Character> commonPunctuation = new HashSet<Character>();


		try {
			int i = 0;
			while (br.ready()) {
				String line = br.readLine();
				System.out.println(line);
				char[] arr = line.toCharArray();
				Set<Character> lowerCase = new HashSet<Character>();
				Set<Character> upperCase = new HashSet<Character>();
				Set<Character> punctuation = new HashSet<Character>();
				
				for(char letter : arr) {
					if(Character.isLetter(letter)) {
						if(Character.isUpperCase(letter))
							upperCase.add(letter);
						else
							lowerCase.add(letter);
					} else {
						punctuation.add(letter);
					}
				}
				
				System.out.println("Lower Case: " + lowerCase);
				System.out.println("Upper Case: " + upperCase);
				System.out.println("Other: " + punctuation);
				
				if(i == 0) {
					for(char letter : lowerCase)
						commonLowerCase.add(letter);
					for(char letter : upperCase)
						commonUpperCase.add(letter);
					for(char letter : punctuation)
						commonPunctuation.add(letter);
				} else {
					Iterator<Character> commonLowerCaseIterator = commonLowerCase.iterator();
					ArrayList<Character> lowerCaseToRemove = new ArrayList<Character>();
					while(commonLowerCaseIterator.hasNext()) {
						Character c = commonLowerCaseIterator.next();
						if(!lowerCase.contains(c))
							lowerCaseToRemove.add(c);
					}
					commonLowerCase.removeAll(lowerCaseToRemove);
					
					Iterator<Character> commonUpperCaseIterator = commonUpperCase.iterator();
					ArrayList<Character> upperCaseToRemove = new ArrayList<Character>();
					while(commonUpperCaseIterator.hasNext()) {
						Character c = commonUpperCaseIterator.next();
						if(!upperCase.contains(c))
							upperCaseToRemove.add(c);
					}
					commonUpperCase.removeAll(upperCaseToRemove);
					
					Iterator<Character> commonPunctuationIterator = commonPunctuation.iterator();
					ArrayList<Character> punctuationToRemove = new ArrayList<Character>();
					while(commonPunctuationIterator.hasNext()) {
						Character c = commonPunctuationIterator.next();
						if(!punctuation.contains(c))
							punctuationToRemove.add(c);
					}
					commonPunctuation.removeAll(punctuationToRemove);
				}
				
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println();
		System.out.println("Common Lower Case: " + commonLowerCase);
		System.out.println("Common Upper Case: " + commonUpperCase);
		System.out.println("Common Other: " + commonPunctuation);
		// for each line read, convert the String to a character array by using
		// toCharArrays() of String class
		// need to create 3 sets of Character: lower case, upper case, punctuation marks

		// for each character in each line, check if it is a letter (use
		// Character.isLetter() AND lower case (use Character.isLowerCase()
		// upper case (use Character.isUpperCase()
		// otherwise, it is a punctuation mark put each to its corresponding set.
		//
		// Need to check the common characters in each group: lower, upper, punctuation
		// marks.
		// You need to use an iterator to iterate over each set. You might need to use
		// the contains
		// and remove methods of Set.

	}
}

/* Output 

We, therefore, the Representatives of the united States of 
Lower Case: [a, d, e, f, h, i, n, o, p, r, s, t, u, v]
Upper Case: [R, S, W]
Other: [ , ,]
America, in General Congress, Assembled, appealing to the 
Lower Case: [a, b, c, d, e, g, h, i, l, m, n, o, p, r, s, t]
Upper Case: [A, C, G]
Other: [ , ,]
Supreme Judge of the world for the rectitude of our intentions,
Lower Case: [c, d, e, f, g, h, i, l, m, n, o, p, r, s, t, u, w]
Upper Case: [S, J]
Other: [ , ,]
do, in the Name, and by the Authority of the good People of 
Lower Case: [a, b, d, e, f, g, h, i, l, m, n, o, p, r, t, u, y]
Upper Case: [P, A, N]
Other: [ , ,]
these Colonies, solemnly publish and declare, That these United 
Lower Case: [a, b, c, d, e, h, i, l, m, n, o, p, r, s, t, u, y]
Upper Case: [C, T, U]
Other: [ , ,]
Colonies are, and of Right ought to be Free and Independent 
Lower Case: [a, b, d, e, f, g, h, i, l, n, o, p, r, s, t, u]
Upper Case: [R, C, F, I]
Other: [ , ,]
States; that they are Absolved from all Allegiance to the 
Lower Case: [a, b, c, d, e, f, g, h, i, l, m, n, o, r, s, t, v, y]
Upper Case: [A, S]
Other: [ , ;]
British Crown, and that all political connection between them 
Lower Case: [a, b, c, d, e, h, i, l, m, n, o, p, r, s, t, w]
Upper Case: [B, C]
Other: [ , ,]
and the State of Great Britain, is and ought to be totally 
Lower Case: [a, b, d, e, f, g, h, i, l, n, o, r, s, t, u, y]
Upper Case: [B, S, G]
Other: [ , ,]
dissolved; and that as Free and Independent States, they have 
Lower Case: [a, d, e, h, i, l, n, o, p, r, s, t, v, y]
Upper Case: [S, F, I]
Other: [ , ;, ,]
full Power to levy War, conclude Peace, contract Alliances, 
Lower Case: [a, c, d, e, f, i, l, n, o, r, s, t, u, v, w, y]
Upper Case: [P, A, W]
Other: [ , ,]
establish Commerce, and to do all other Acts and Things which 
Lower Case: [a, b, c, d, e, g, h, i, l, m, n, o, r, s, t, w]
Upper Case: [A, C, T]
Other: [ , ,]
Independent States may of right do. And for the support of this 
Lower Case: [a, d, e, f, g, h, i, m, n, o, p, r, s, t, u, y]
Upper Case: [A, S, I]
Other: [ , .]
Declaration, with a firm reliance on the protection of divine 
Lower Case: [a, c, d, e, f, h, i, l, m, n, o, p, r, t, v, w]
Upper Case: [D]
Other: [ , ,]
Providence, we mutually pledge to each other our Lives, our 
Lower Case: [a, c, d, e, g, h, i, l, m, n, o, p, r, s, t, u, v, w, y]
Upper Case: [P, L]
Other: [ , ,]
Fortunes and our sacred Honor.
Lower Case: [a, r, s, c, t, d, u, e, n, o]
Upper Case: [F, H]
Other: [ , .]

Common Lower Case: [d, e, n, o, r, t]
Common Upper Case: []
Common Other: [ ]
*/