package test.kev.LowLine;

import kev.LowLine.LowLine;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * LowLine Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>01/04/2018</pre>
 */
public class LowLineTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: setWidth()
     */
    @Test
    public void testSetWidth() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: merge_left(LowLine left_l)
     */
    @Test
    public void testMerge_left() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: merge_right(LowLine right_l)
     */
    @Test
    public void testMerge_right() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: updateY(LowLine leftLine, LowLine rightLine)
     */
    @Test
    public void testUpdateY() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: compareTo(LowLine o)
     */
    @Test
    public void testCompareTo() throws Exception {

        List<LowLine> listLowLine = new ArrayList<LowLine>();
        for (int i = 10; i > 0; i--) {
            listLowLine.add(new LowLine(i, i, i));
        }
        for (LowLine o : listLowLine) {
            System.out.println(o.toString());
        }
        Collections.sort(listLowLine);
        System.out.println("After Sort:________");
        for (LowLine o : listLowLine) {
            System.out.println(o.toString());
        }
    }


} 
