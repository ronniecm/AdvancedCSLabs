package Hash;

//*********************************************************************************************************************************
//Name: Ronnie Mohapatra 
//Period: 2                                               
//Date: 26 March 2019
//What I learned: a). Create a reverse map
//				  b). Generating a map
//				  c). Reading input from a file 
//How I feel about this lab: This was a very good insight into the real life application of maps. Reversing a map is good method for
//							 two-way translation. Overall it was pretty easy. 
//What I wonder: What other applications of maps are there?
//***********************************************************************************************************************************

import java.io.*;
import java.util.*;
public class Pd2RonnieMohapatraDictionaryLab
{
  public static void main(String[] args) throws Exception
  {
	  /*
      try
      {
          System.setOut(new PrintStream(new FileOutputStream("dictionaryOutput.txt")));
      }
      catch(Exception e)
      {
          System.out.println(e.getClass());
      }
      */
      Map<String, Set<String>> eng2spn = new TreeMap<String, Set<String>>();
      Scanner infile = new Scanner(new File("spanglish.txt"));
      while(infile.hasNext())
      {
          add(eng2spn, infile.next(), infile.next());
      }
      infile.close();
      System.out.println("ENGLISH TO SPANISH");
      display(eng2spn);
    
      Map<String, Set<String>> spn2eng = reverse(eng2spn);
      System.out.println("SPANISH TO ENGLISH");
      display(spn2eng);
  }
 
 // Note: must explain how your method works
 // Postcondition: display the contents of  a dictionary
  public static void display(Map<String, Set<String>> m)
  {
      Set<String> keySet = m.keySet();
      for(String key : keySet)
          System.out.println(key + " " + m.get(key));
  }
 // Note: must explain how your method works
 // postcondition: insert a new pair to the English to Spanish Dictionary
  public static void add(Map<String, Set<String>> engToSpnDictionary, String word, String translation)
  { 
      if(engToSpnDictionary.get(word) == null)
      {
          engToSpnDictionary.put(word, new TreeSet<String>());
      }
      
      engToSpnDictionary.get(word).add(translation);
  }
  // Note: must explain how your method works
  // postcondition: returns a Spanish to English dictionary
  public static Map<String, Set<String>> reverse(Map<String, Set<String>> engToSpnDictionary)
  {
      Set<String> engToSpanishKeySet = engToSpnDictionary.keySet();
      Map<String, Set<String>> spanishToEngDictionary = new TreeMap<String, Set<String>>();

      for(String key : engToSpanishKeySet) 
      {
          Set<String> spanishWords = engToSpnDictionary.get(key);
          for(String spanishWord : spanishWords)
          {
              if(spanishToEngDictionary.get(spanishWord) == null)
                  spanishToEngDictionary.put(spanishWord, new TreeSet<String>());
              spanishToEngDictionary.get(spanishWord).add(key);
          }
      }

      return spanishToEngDictionary;
  }
}

/* Output
ENGLISH TO SPANISH
banana [banana]
celebration [fiesta]
computer [computadora, ordenador]
double [doblar, doble, duplicar]
father [padre]
feast [fiesta]
good [bueno]
hand [mano]
hello [hola]
holiday [fiesta, vacaciones]
party [fiesta]
plaza [plaza]
priest [padre]
program [programa, programar]
sleep [dormir]
son [hijo]
sun [sol]
vacation [vacaciones]
SPANISH TO ENGLISH
banana [banana]
bueno [good]
computadora [computer]
doblar [double]
doble [double]
dormir [sleep]
duplicar [double]
fiesta [celebration, feast, holiday, party]
hijo [son]
hola [hello]
mano [hand]
ordenador [computer]
padre [father, priest]
plaza [plaza]
programa [program]
programar [program]
sol [sun]
vacaciones [holiday, vacation]
*/