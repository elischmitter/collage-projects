/**
 * Interface for finding PI
 * by applying random numbers to GCD algorithm
 */
interface FindPi {
    /**
     * The greatest common divisor is the largest integer that
     * divides both values without producing a remainder.
     *
     * The recursive algorithm is defined as follows:
     *
     * gcd( num1, num2 ) is num1 if num2 evenly divides num1
     * gcd( num1, num2 ) is gcd( num2, num1 % num2 ) otherwise
     *
     * @param num1
     * @param num2
     * @return the greastes common divisor of num1 and num2
     */
    public long gcd ( long num1, long num2 );

    /**
     * Two numbers are relatively prime if their greatest common divisor is 1.
     * Suppose you choose two integers between 1 and max at random.
     * What is the probability that they are relatively prime?
     *
     * As max gets large, that probability approaches 6/(pi*pi),
     * where pi = 3.14159265... So one way to estimate pi is to
     * select pairs of numbers at random from a large range,
     * and to keep a count of the fraction of the pairs that
     * consist of two relatively prime numbers.
     *
     * thus, PI is approximately 6/sqrt(count/range)
     *
     * @param max the random numbers selected are in the range [0, max]
     * @param range is the number of calls to GCD with random inputs
     * @return Pi
     */
    public double calculatePi ( long max, int range );
}
