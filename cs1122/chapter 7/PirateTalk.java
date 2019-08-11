/**
*  this class is to translate words in to pirate speek
*
*  @author Eli Schmitter
*  CS1121, Fall 2018
*
*  Date Last Modified: 09/08/2018
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class PirateTalk {

   // You must use these two arrays to store your pirate dictionary data.
   private String [ ] englishWords = null; // English words that have a dictionary entry for a pirate word
   private String [ ] pirateWords  = null; //

   /**
    * This Method takes a file name as a string an loads that file in to in to the two arrays englishWords and pirateWords.
    *
    * @param a file name in string form of the dictionary location
    *
    * @return if the file load in to the dictionary
    */
   public boolean loadDictionary( String filename ) {
     try {
   File fileIn = new File(filename);
   Scanner in = new Scanner(fileIn);
   int num = Integer.parseInt( in.nextLine());
   englishWords = new String[num];
   pirateWords = new String[num];
   int i = 0;
   while ( in.hasNextLine()) {
       String[] libin = in .nextLine().split(":");
       englishWords[i] = libin[0];
       pirateWords[i] = libin[1];
       i++;
   }

   in.close();
   return true;
} catch (FileNotFoundException e) {
   return false;
}
   }

   /**
    *translate a word for english speek to pirate speek
    *
    * @param DESCRIBE  A english word
    *
    * @return The english word translated to pitate speek
    */
   public String translateWord( String word ) {
     int i = 0;
     if (word == null) {
         return "Splice the mainbrace!";
     }
     while (!(englishWords[i].toUpperCase().equals(word.toUpperCase()))) {
         i++;
         if (i >= englishWords.length) {
             i = -1;
             break;
         }
     }
     if (i != -1) {
         if ((word.length() != 0 && Character.isUpperCase(word.charAt(0))) || word.contains("\\p{Punct}")) {
             return Character.toString(pirateWords[i].charAt(0)).toUpperCase() + pirateWords[i].substring(1);
         } else {
             return pirateWords[i];

         }
     } else {
         word = word.replace("v", "\'");
         word = word.replace("V", "\'");
         if (word.length() > 3 && word.substring(word.length() - 3).equals("ing")) {
             return (word.substring(0, word.length() - 1)+"'");
         }
         return word;
     }
   } // END OF METHOD

   /**
    * this method takes a sentence and translate it to pirateWords.
    *
    * @param DESCRIBE sentence to be translated
    *
    * @return the translated sentence
    */
   public String translatePassage( String sentence ) {
     String[] pass = sentence.replaceAll("\\p{Punct}", " $0").split(" ");
       String[] finpass = new String[pass.length];
       for (int i = 0; i < pass.length; i++) {
           finpass[i] = translateWord(pass[i]);
       }
       return String.join(" ", finpass).replaceAll("\\s+(\\p{Punct})", "$1");
   } // END OF METHOD

   /**
    * the main mathod
    *
    * @param DESCRIBE command line args
    *
    * @return DESCRIBE THE RESULT
    */
   public static void main( String [ ] args ) {
      PirateTalk yeOldeObject = new PirateTalk( );

      // Test loadDictionary
      // NOTE: to use the assert keyword for testing,
      //       you must use the -ea compiler command line option.
      assert yeOldeObject.loadDictionary( "pirate_dictionary.txt" );

      // Test translateWord( )
      assert yeOldeObject.translateWord( "hello" ).equals( "ahoy" );
      assert yeOldeObject.translateWord( "Hello" ).equals( "Ahoy" );
      assert yeOldeObject.translateWord( "coins" ).equals( "pieces of eight" );

      // Test translatePassage( )
      assert yeOldeObject.translatePassage( "Hello! I am Professor Leo." ).equals( "Ahoy! Shiver me timbers! I be Cap'n Leo. Arrr." );
      assert yeOldeObject.translatePassage( "The student spent a lot of money of books!" ).equals( "Th' swab spent a briny lot o' booty o' books! Shiver me timbers!" );
      assert yeOldeObject.translatePassage( "You can call me anything you want. Just do not call me late for dinner!" ).equals( "Ye can call me anythin' ye be needin'. Arrr. Jus' do nah call me late for grub! Shiver me timbers!" );
   } // END OF METHOD

} // END OF CLASS: PirateTalk
