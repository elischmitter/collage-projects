import java.util.ArrayList;

public class Week8Program implements FindPi {

    @Override
    /**
     * @param num1
     * @param num2
     * @return the greastes common divisor of num1 and num2
     */
    public long gcd(long num1, long num2) {
        if (num2 % num1 == 0)
                return num1;
        return gcd(num2, num1 % num2);
    }

    /**
     * @param max the random numbers selected are in the range [0, max]
     * @param range is the number of calls to GCD with random inputs
     * @return
     */
    public double calculatePi(long max, int range){
        ArrayList<Long> rngList = new ArrayList<>();
        for (int i=0; i<max;i++) {
            rngList.add(1+ (long) (Math.random() * (max - 1)));
        }
        int numbcp=0;
        for(long l=0;l<range;l++){
            long num1=  rngList.get(1+(int) (Math.random() * (rngList.size() - 2)));
            long num2=  rngList.get(1+(int) (Math.random() * (rngList.size() - 2)));
            long x=gcd(num1,num2);
            if (x==1){
                numbcp++;
            }
        }
        return Math.sqrt(6/(((double)numbcp)/((double)(range))));
    }
    public static void main(String[] args){
        Week8Program test = new Week8Program();
        System.out.println(test.calculatePi(1000000L,200000));
    }
        }
