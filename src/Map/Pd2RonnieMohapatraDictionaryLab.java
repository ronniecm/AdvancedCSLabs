package Map;
//*********************************************************************************************************************************
//Name:   
// Period:                                                 
// Date:
// What I learned:
// How I feel about this lab:
// What I wonder:
//***********************************************************************************************************************************

import java.io.*;
import java.util.*;
public class Pd2RonnieMohapatraDictionaryLab
{
    public static void main(String[] args) throws Exception
    {
        Map<String, Set<String>> eng2spn = new TreeMap<String, Set<String>>();
        Scanner infile = new Scanner(new File("spanglish.txt"));
        while(infile.hasNext())
        {
            add(eng2spn, infile.next(), infile.next());
        }
        infile.close();
        System.out.println("ENGLISH TO SPANISH");
        display(eng2spn);
      
        System.out.println();
        
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
      /********************
	INPUT:
   	holiday
		fiesta
		holiday
		vacaciones
		party
		fiesta
		celebration
		fiesta
     <etc.>
  *********************************** 
	OUTPUT:
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

**********************/