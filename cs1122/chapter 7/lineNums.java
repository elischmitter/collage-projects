import java.io.*;
import java.util.Scanner;
import java.util.*;
public class lineNums {
    public static void main(String args[]) {
        Scanner userIn = new Scanner(System.in);
        System.out.print("Enter input file name\n>");
        String path = userIn.nextLine();
        try {
            File fileIn = new File(path);
            Scanner scnr = new Scanner(fileIn);
            System.out.print("Enter output file name\n>");
            String fileOut = userIn.nextLine();
            File fileOutPath = new File(fileOut);
            userIn.close();
            PrintWriter out = new PrintWriter(fileOutPath);
            int lineNumber = 1;
            while (scnr.hasNextLine()) {
                System.out.println(lineNumber);
                String line = scnr.nextLine();
                out.println("\\* " + lineNumber + " *\\ " + line);
                lineNumber++;

            }
            out.close();
            scnr.close();
        } catch (FileNotFoundException e) {
            System.out.print("File not found");
        }

    }

}
