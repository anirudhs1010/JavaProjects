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
 * This helper class implements an iterator for the TicketQueue class.
 */
public class TicketQueueIterator  implements Iterator<TicketSiteUser> {
    private TicketQueue userQueue;
    /**
     * @param queue
     * This constructor updates the queue with a deep copy.
     */
    public TicketQueueIterator(TicketQueue queue) {
        if (queue == null) {
            throw new IllegalArgumentException();
        }

        userQueue = queue.deepCopy();
    }
    /**
     * This method checks if the queue is empty or not and if we can move to the next element.
     */
    public boolean hasNext() {
        return !userQueue.isEmpty();
    }
    /**
     * This method iterates to the next element by removing the front of the queue.
     */
    public TicketSiteUser next() {
        return userQueue.dequeue();
    }
}
