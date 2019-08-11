import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Week8ProgramTest {
    Week8Program Prog = new Week8Program();
    @Test
    void calculatePi() {
        double pi=Prog.calculatePi(10000000L,2000000);
        if(!(pi<3.15&&pi>3.14)) {
            fail("pi no work"+pi);
        }
        }
    @Test
    void gcd() {
        if(!(Prog.gcd(3,9)==3&&Prog.gcd(1,9)==1&&Prog.gcd(9,81)==9)){
            fail("gcd does not work");
        }
    }
    @Test
    public void implementsInterfaceTest() {
        if(!(Prog instanceof FindPi)) {
            fail("does not implements findPi");
        }
    }


}