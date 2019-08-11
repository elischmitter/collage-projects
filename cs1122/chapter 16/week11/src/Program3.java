import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Program3 {
    public static void main(String[] args) {
        File file = new File(args[0]);
        try (Scanner in = new Scanner(file)) {
            in.useDelimiter("");
            int size = in.nextInt();
            Queue<Character> queue = new Queue<>(size);
            while (in.hasNext()) {
                try {
                    queue.enqueue(in.next().charAt(0));
                } catch (QueueFullException e) {
                    while (queue.size() != 0) {
                        try {
                            System.out.print(queue.dequeue());
                        } catch (QueueEmptyException v) {
                            System.out.print("");
                        }
                    }
                }
            }
            while (queue.size() != 0) {
                try {
                    System.out.print(queue.dequeue());
                } catch (QueueEmptyException v) {
                    System.out.print("");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }
}
