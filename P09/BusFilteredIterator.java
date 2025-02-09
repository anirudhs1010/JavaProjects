//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    BusFilteredIterator
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
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         (identify each by name and describe how they helped)
// Online Sources:  (identify each by URL and describe how it helped)
//
///////////////////////////////////////////////////////////////////////////////
import java.util.Iterator;
/**
 * An iterator that does an <b>in-order traversal</b> of the BST starting at the
 given node and
 * iterates forward through buses that only have the specified BusStop.
 */

public class BusFilteredIterator implements Iterator<Bus> {
    private Iterator<Bus> baseIterator; //The iterator we are filtering.
    private BusStop destination;
    private Bus next; //The destination BusStop we are filtering by.
    /**
     * Construct a new BusForwardIterator that iterates over all filtered nodes in the tree
     with root `root`
     * starting from the node `node`.
     *
     * @param b the Iterator<bus>
     * @param a BusStop
     *
     */
    public BusFilteredIterator(Iterator<Bus> b, BusStop a) {
        this.baseIterator = b;
        this.destination = a;
        advanceToNext();
    }
    /**
     * advanceToNext() has a while loop and that iterates and sets next only when it goes to the destination.
     *
     */
    private void advanceToNext() {
        while (baseIterator.hasNext()) {
            Bus b = baseIterator.next();
            if (b.goesTo(destination)) {
                next = b;
                return;
            }
        }
        next = null;
    }
    /**
     * hasNext() checks if there is a next parameter is not null.
     *
     */
    public boolean hasNext() {
        return next != null;
    }
    /**
     * next() returns the removed node and calls advanceToNext() to continue iterate.
     *
     */
    public Bus next() {
        Bus result = next;
        advanceToNext();
        //baseIterator
        return result;
    }




}
