package flik;

import org.junit.Test;
import static org.junit.Assert.*;

public class FlikTest {

    @Test
    public void testOne() {
        System.out.println(Flik.isSameNumber(1, 1));
        assertTrue(Flik.isSameNumber(1, 1));
    }

    @Test
    public void test128() {
        System.out.println(Flik.isSameNumber(128, 128));
        assertTrue(Flik.isSameNumber(128, 128));
    }

    @Test
    public void test129() {
        assertTrue(Flik.isSameNumber(130, 130));
    }
}
