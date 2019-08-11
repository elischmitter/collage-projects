/** to do
format and comment code posibly find bugs
*/
import java.io.*;
import java.util.Scanner;
import java.util.Arrays;
public class pirate_talk {
    static String[] englishWords;
    static String[] pirateWords;
    public static boolean loadDictionary(String filename) {

    }
    public static String translateWord(String word) {
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
            if (word.substring(word.length() - 3).equals("ing")) {
                return word.substring(0, word.length() - 1);
            }
            return word;
        }
    }
    public static String translatePassage(String sentence) {
        String[] pass = sentence.replaceAll("\\p{Punct}", " $0").split(" ");
        String[] finpass = new String[pass.length];
        for (int i = 0; i < pass.length; i++) {
            finpass[i] = translateWord(pass[i]);
        }
        return String.join(" ", finpass).replaceAll("\\s+(\\p{Punct})", "$1");
    }
    public static void main(String args[]) {
        System.out.println(loadDictionary("D:\\notes\\cs1122\\pirate_dictionary.txt"));
        System.out.println(translateWord(null));
        System.out.println(translatePassage("Not of Fagot Old."));
    }

}
