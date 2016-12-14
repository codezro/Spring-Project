package com.skill.up.helpers;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jpmc on 11/29/2016.
 */
public class HelpersTest extends TestCase {

    @Test
    public void testReturn123(){
        String[] tests = {"chevy","gwapo","kaayo"};
        assertFalse(Helpers.return123("Hello").equals("hello12"));

        for (int i = 0; i <tests.length;i++){
            String actual = Helpers.return123(tests[i]);
            String expected = tests[i]+"123";
            assertEquals(expected,actual);
        }

    }

    @Test
    public void testFebonacci(){
        float expected = (float) 1.6;
        int[] febonacci = Helpers.febonacci();
        float actual;
        for (int i = 1; i < febonacci.length;i++) {
                actual = Float.valueOf(febonacci[i]) / Float.valueOf(febonacci[i-1]);
                assertEquals(expected, actual,0.03f);
        }

    }
}
