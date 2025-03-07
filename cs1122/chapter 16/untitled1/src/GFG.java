class GFG{
    // Returns nCr % p. In this Lucas Theorem based program,
// this function is only called for n < p and r < p.
    static int nCrModpDP(int n, int r, int p)
    {
        // The array C is going to store last row of
        // pascal triangle at the end. And last entry
        // of last row is nCr
        int[] C=new int[r+1];

        C[0] = 1; // Top row of Pascal Triangle

        // One by constructs remaining rows of Pascal
        // Triangle from top to bottom
        for (int i = 1; i <= n; i++)
        {
            // Fill entries of current row using previous
            // row values
            for (int j = Math.min(i, r); j > 0; j--)

                // nCj = (n-1)Cj + (n-1)C(j-1);
                C[j] = (C[j] + C[j-1])%p;
        }
        return C[r];
    }

    // Lucas Theorem based function that returns nCr % p
// This function works like decimal to binary conversion
// recursive function. First we compute last digits of
// n and r in base p, then recur for remaining digits
    static int nCrModpLucas(int n, int r, int p)
    {
// Base case
        if (r==0)
            return 1;

// Compute last digits of n and r in base p
        int ni = n%p;
        int ri = r%p;

// Compute result for last digits computed above, and
// for remaining digits. Multiply the two results and
// compute the result of multiplication in modulo p.
        return (nCrModpLucas(n/p, r/p, p) * // Last digits of n and r
                nCrModpDP(ni, ri, p)) % p; // Remaining digits
    }
    // Driver program
    public static void main(String[] args)
    {    {
        int total =0;
        for(int x=1; x<=(Math.pow(10,9));x++){
            for(int y=1; y<=x ;y++){
                float  b=nCrModpLucas(x, y, 7);
                if (b==0){
                    System.out.println(b+" "+x+" "+y);
                    total=total+1;
                }
            }
        }
    }
    }
}
// This code is contributed by mits
