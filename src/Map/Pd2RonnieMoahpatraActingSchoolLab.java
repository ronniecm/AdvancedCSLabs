package Map;
//Name: Ronnie Mohapatra  
//Date: 22 March 2019
// What I learned: a) How to generate a reverse map
// How I feel about this lab: It was not a terrible lab but it was confusing going through the logic. Overall it was pretty easy.
// I am wondering:
import java.util.*;
public class Pd2RonnieMoahpatraActingSchoolLab
{
    public static void main(String[] args)
    {
        Map<String, String> sGrades = new TreeMap<String, String>();     //HashMap
   
        sGrades.put("Jack Nicholson", "A-");
        sGrades.put("Humphrey Bogart", "A+");
        sGrades.put("Audrey Hepburn", "A");
        sGrades.put("Meryl Streep", "A-");
        sGrades.put("Jimmy Stewart", "A");
   
       //display initial data
        Set<String> keySet = sGrades.keySet();
        for(String key : keySet)
            System.out.println(key + " (" + sGrades.get(key) + ")");

        System.out.println();
       //reverse the map    //TreeMap
        Map<String, ArrayList<String>> reverseMap = new TreeMap<String, ArrayList<String>>(); 
        for(String key : keySet) {
            if(reverseMap.get(sGrades.get(key)) == null) {
                reverseMap.put(sGrades.get(key), new ArrayList<String>()); //if no ArrayList at key, put one there
            } 
            reverseMap.get(sGrades.get(key)).add(key); //name to key
        }
      //display the reversed map
      Set<String> reverseKeySet = reverseMap.keySet();
      for(String key : reverseKeySet)
        System.out.println(key + ": " + reverseMap.get(key));
    }
}
 
/* Output
 Audrey Hepburn (A)
 Humphrey Bogart (A+)
 Jack Nicholson (A-)
 Jimmy Stewart (A)
 Meryl Streep (A-)
 
 A: [Audrey Hepburn, Jimmy Stewart]
 A+: [Humphrey Bogart]
 A-: [Jack Nicholson, Meryl Streep]
*/