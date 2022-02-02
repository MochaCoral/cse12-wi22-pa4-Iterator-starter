
/**
 * TODO: Add your file header
 * Name: Morales, Kyle
 * ID: a16162998
 * Email: kmmorale@ucsd.edu
 * Sources used: Put "None" if you did not have any external help
 * Some example of sources used would be Tutors, Zybooks, and Lecture Slides
 * 
 * 2-4 sentence file description here
 */

import static org.junit.Assert.*;

import java.net.http.WebSocket.Listener;
import java.rmi.server.ExportException;
import java.util.NoSuchElementException;

import org.junit.*;

/**
 * TODO: Add your class header
 * 
 * IMPORTANT: Do not change the method headers and points are awarded
 * only if your test cases cover cases that the public tester file
 * does not take into account.
 */
public class MyLinkedListCustomTester {
    private MyLinkedList listEmpty, list3Elements;
    private boolean exceptionThrown;
    /**
     * This sets up the test fixture. JUnit invokes this method before
     * every testXXX method. The @Before tag tells JUnit to run this method
     * before each test.
     */
    @Before
    public void setUp() throws Exception {
        listEmpty = new MyLinkedList();
        list3Elements = new MyLinkedList();
        list3Elements.add("Ruby");
        list3Elements.add("Sapphire");
        list3Elements.add("Emerald");
        exceptionThrown = false;
    }

    /**
     * TODO: test the hasNext method when [fill in a possible edge case here]
     */
    @Test
    public void testHasNext() {
        //test if sentinal node returns true/empty list test 
        assertFalse("Empty List should return false", listEmpty.hasNext());        
    }

    /**
     * TODO: test the next method when [...]
     */
    @Test
    public void testNext() {
        //test if method throws NoSuchElementException
        try {
            list3Elements.next();
            list3Elements.next();
            list3Elements.next();
        }
        catch(NoSuchElementException e) {
            exceptionThrown = true;
        }
        assertTrue("method should throw No Such ELement exception(next)",
            exceptionThrown);

    }

    /**
     * TODO: test the hasPrevious method when [fill in another one here]
     */
    @Test
    public void testHasPrevious() {
        //test if sentinel node returns true/empty list
        assertFalse("Empty list should return false(hasPrevious)", 
            listEmpty.hasPrevious());
    }

    /**
     * TODO: test the previous method when [...]
     */
    @Test
    public void testPrevious() {
        //test if method throws NoSuchElementException
        try {
            list3Elements.previous();
        }
        catch (NoSuchElementException e) {
            exceptionThrown = true;
        }
        assertTrue("No Such Element Exception thrown (previous)", exceptionThrown);
    }

    /**
     * TODO: test the nextIndex method when [...]
     */
    @Test
    public void testNextIndex() {
        int index = listEmpty.nextIndex();
        assertEquals("nextIndex should return -1", -1, index);
    }

    /**
     * TODO: test the previousIndex method when [...]
     */
    @Test
    public void testPreviousIndex() {
        //test on empty MLL list
        int index = listEmpty.previousIndex();
        assertEquals("previousIndex should return -1", -1, index);
    }

    /**
     * TODO: test the set method when [...]
     */
    @Test
    public void testSet() {
        //test exception throws
        try {
            
        }
        catch (NullPointerException e){
            System.out.println("NullPointerException");
            exceptionThrown = true;
        }
        catch (IllegalStateException e) {
            System.out.println("IllegalStateExcpetion");
            exceptionThrown = true;
        }
        assertTrue("has been thrown (set test)", exceptionThrown);
    }

    /**
     * TODO: test the remove method when [...]
     */
    @Test
    public void testRemoveTestOne() {
        //test if IllegalStateExcpetion is thrown for 
        try {
            list3Elements.remove();
        }
        catch (IllegalStateException e) {
            exceptionThrown = true;
        }
        assertTrue("IllegalStateException thrown (Remove test 1)", 
            exceptionThrown);
    }

    /**
     * TODO: test the remove method when [fill in another one here]
     */
    @Test
    public void testRemoveTestTwo() {
        //remove from empty list 
        MyLinkedList init = new MyLinkedList();
        try {
            listEmpty.remove();
        }
        catch (Exception e) {
            exceptionThrown = true;
        }
        assertTrue("an exception should be thrown (Remove test 2)",
            exceptionThrown);
        assertEquals("Empty List should remain the same", init, listEmpty);

    }

    /**
     * TODO: test the add method when [...]
     */
    @Test
    public void testAdd() {
        //test if method throws NullPointerExcpetion if element is null
        try {
            list3Elements.add(null);
        }
        catch (NullPointerException e) {
            exceptionThrown = true;
        }
        assertTrue("Add throws exception for null element", exceptionThrown);
    }

}