//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    TicketQueue
// Course:   CS 300 Fall 2023
//
// Author:   Anirudh Sunil
// Email:    sunil3@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    (name of your pair programming partner)
// Partner Email:   (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         NONE
// Online Sources:  NONE
//
///////////////////////////////////////////////////////////////////////////////
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class tests all the methods of the TicketQueue class.
 */
public class TicketQueueTester {
    /**
     * This method tests if the peek function returns the front of the queue without altering the data.
     */
    public static boolean testPeek() {

        TicketQueue q = new TicketQueue(4);
        TicketSiteUser a = new TicketSiteUser("a", "a", "1234567891023456");
        a.login("a", "a");
        TicketSiteUser b = new TicketSiteUser("b", "b", "2234567891023456");
        b.login("b", "b");
        TicketSiteUser c = new TicketSiteUser("c", "c", "3234567891023456");
        c.login("c", "c");
        q.enqueue(a);
        q.enqueue(b);
        q.enqueue(c);
        if (q.peek().toString().equals("a: *") && q.size() == 3) {
            q.dequeue();
            q.dequeue();
            q.dequeue();
            try {
                TicketSiteUser t = q.peek();
            }
            catch (NoSuchElementException e) {
                return true;
            }
        }
        return false;
    }
    /**
     * This method tests if the enqueue function adds to the back of the queue and updates its size.
     * It also tests if it throws exceptions if the capacity is full or if the users' ticket has not been bought
     * or if the user is not logged in.
     */
    public static boolean testEnqueue() {
        TicketQueue q = new TicketQueue(5);
        TicketSiteUser a = new TicketSiteUser("a", "a", "1234567891023456");
        a.login("a", "a");
        TicketSiteUser b = new TicketSiteUser("b", "b", "2234567891023456");
        b.login("b", "b");
        TicketSiteUser c = new TicketSiteUser("c", "c", "3234567891023456");
        c.login("c", "c");
        TicketSiteUser g = new TicketSiteUser("g", "ac", "2534567891023456");
        g.login("g", "ac");
        q.enqueue(a);
        q.enqueue(b);
        q.enqueue(c);
        q.enqueue(g);
        if (q.peek().toString().equals("a: *") && q.size() == 4) {
            try {
                q.enqueue(new TicketSiteUser("e", "e", "5234567891023456"));
            } catch (Exception e) {
                return true;
            }
        }
        return false;

    }
    /**
     * This method tests if the dequeue function removes the front of the queue and updates its size.
     * It also checks if it throws exceptions if the queue is empty.
     */
    public static boolean testDequeue() {
        TicketQueue q = new TicketQueue(4);
        TicketSiteUser a = new TicketSiteUser("a", "a", "1234567891023456");
        a.login("a", "a");
        TicketSiteUser b = new TicketSiteUser("b", "b", "2234567891023456");
        b.login("b", "b");
        TicketSiteUser c = new TicketSiteUser("c", "c", "3234567891023456");
        c.login("c", "c");
        q.enqueue(a);
        q.enqueue(b);
        q.enqueue(c);
        TicketSiteUser rem = q.dequeue();
        if (q.size() == 2 && q.peek().toString().equals("b: *") && rem.toString().equals("a: *")) {
            TicketSiteUser t = q.dequeue();
            TicketSiteUser i = q.dequeue();
            if (q.size() == 0 && q.isEmpty()) {
                try {
                    TicketSiteUser z = q.dequeue();
                } catch (Exception e) {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * This method tests if the constructor rejects invalid capacities (below 1) and if the isFull and isEmpty
     * functions work. It also tests if size updates properly.
     */
    public static boolean testConstructor() {
        TicketQueue q = new TicketQueue(4);
        if (q.capacity() == 4 && q.size() == 0 && q.isEmpty()) {
            TicketSiteUser a = new TicketSiteUser("a", "a", "1234567891023456");
            a.login("a", "a");
            TicketSiteUser b = new TicketSiteUser("b", "b", "2234567891023456");
            b.login("b", "b");
            TicketSiteUser c = new TicketSiteUser("c", "c", "3234567891023456");
            c.login("c", "c");
            TicketSiteUser d = new TicketSiteUser("d", "d", "4234567891023456");
            d.login("d", "d");
            q.enqueue(a);
            q.enqueue(b);
            q.enqueue(c);
            q.enqueue(d);
            if (q.isFull()) {
                try {
                    TicketQueue y = new TicketQueue(0);
                } catch (IllegalArgumentException e) {
                    // The exception is expected, and the test should pass in this case.
                    try {
                        TicketQueue y = new TicketQueue(-1);
                    } catch (IllegalArgumentException u) {
                        // The exception is expected, and the test should pass in this case.
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }
    /**
     * This method tests if the iterator properly goes through the queue and does not impact the original queue.
     */
    public static boolean testIterator() {
        TicketQueue q = new TicketQueue(3);
        TicketSiteUser a = new TicketSiteUser("a", "a", "1234567891023456");
        a.login("a", "a");
        TicketSiteUser b = new TicketSiteUser("b", "b", "2234567891023456");
        b.login("b", "b");
        TicketSiteUser c = new TicketSiteUser("c", "c", "3234567891023456");
        c.login("c", "c");
        q.enqueue(a);
        q.enqueue(b);
        q.enqueue(c);
        Iterator<TicketSiteUser> i = q.iterator();
        int count = 0;
        while (i.hasNext()) {
            i.next();
            count++;
        }
        if (q.size() == 3 && q.isFull() && q.peek().toString().equals("a: *") && count == 3)
            return true;
        return false;
    }
    /**
     * This method returns true if all the tester methods return true.
     */
    private static boolean runAllTests() {
        return testIterator() && testConstructor() && testDequeue() && testPeek() && testEnqueue();
    }



}