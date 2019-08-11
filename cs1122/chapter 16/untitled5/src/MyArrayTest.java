//Wesley Grove, Eric chandler, Eli Schmitter

import static org.junit.Assert.*;

public class MyArrayTest {

    @org.junit.Test
    public void get() {
        MyArray<Integer> testing = new MyArray<Integer>(10);
        for (int i = 0; i < 10; i++) {
            testing.set(i, i);
        }
        if (testing.get(5) != 5){
            fail("gtuy");
        }
    }

    @org.junit.Test
    public void set() {
        MyArray<Integer> testing = new MyArray<Integer>(10);
        for (int i = 0; i < 10; i++) {
            testing.set(i, i);
        }
        try {
            testing.set(12,3);

        }catch (Exception e){
            fail(e.toString());
        }
        if (testing.get(12) != 3){
            fail("gtuy");
        }
    }
}