import java.util.Stack;
import java.util.Scanner;

class Test
{
    public static void stackafy(int x){
        Stack<Integer>s=new Stack<>();
        while(x!=0){
            s.push(x%10);
            x=(int)Math.floor(x/10);
        }
        while(s.size()!=0){
            System.out.print(s.pop()+" ");
        }
    }
    public static void main(String args[]) {
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter a number: ");
        int n = reader.nextInt();
        stackafy(n);
    }
}

