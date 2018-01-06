package test.withpattern;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import withpattern.MainWithPattern;

/**
 * MainWithPattern Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>01/06/2018</pre>
 */
public class MainWithPatternTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: main(String args[])
     */
    @Test
    public void testMain() throws Exception {
        MainWithPattern.main(new String[]{"F:\\Kevin\\GitHub\\JavaDesignPattern\\JavaCode\\a.txt",
                "F:\\Kevin\\GitHub\\JavaDesignPattern\\JavaCode\\d.txt "});
    }


} 
