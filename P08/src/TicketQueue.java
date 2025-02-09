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
 * This class implements a Queue for storing the tickets of users and uses an iterator for the queue.
 */
public class TicketQueue implements QueueADT<TicketSiteUser>, Iterable<TicketSiteUser> {
    private LinkedNode<TicketSiteUser> back; //tail of the queue
    private int capacity;
    private LinkedNode<TicketSiteUser> front; //head of the queue
    private int size = 0;
    /**
     * @param capacity an integer - throws IllegalArgumentException if below -1
     * constructs a TicketQueue by setting capacity
     */

    public TicketQueue(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException();
        }
        this.capacity = capacity;

    }
    /**
     * here size is the integer for amount of elements in the queue
     * This method counts the amount of elements and sets size as well as returns the count
     */
    public int size() {
        int count = 0;
        LinkedNode<TicketSiteUser> temp = front;
        while (temp != null) {
            count++;
            temp = temp.getNext();
        }
        size = count;
        return count;
    }
    /**
     * This method checks if the queue size is at capacity and returns true if it is.
     */
    public boolean isFull() {
        if (size >= capacity)
            return true;
        return false;
    }
    /**
     * This method removes the front of the queue and updates its size accordingly.
     * It throws a NoSuchElementException if the queue is empty.
     */
    public TicketSiteUser dequeue() {
        if (front == null) {
            throw new NoSuchElementException();
        }
        if (front.toString().equals(back.toString())) {
            back = null;
        }
        LinkedNode<TicketSiteUser> temp = front;
        front = front.getNext();
        size--;
        return temp.getData();
    }
    /**
     * This method returns the front of the queue or throws a NoSuchElementException if
     * the queue is empty.
     */
    public TicketSiteUser peek() {
        if (front == null) {
            throw new NoSuchElementException();
        }
        return front.getData();
    }
    /**
     * This method returns an iterator of the queue.
     */
    public Iterator<TicketSiteUser> iterator() {
        return new TicketQueueIterator(this);
    }
    /**
     * This method returns a deep copy of the queue. It goes through the queue and individually
     * copies each and every data point.
     */
    public TicketQueue deepCopy() {
        TicketQueue nTicketQueue  = new TicketQueue(capacity);
        LinkedNode<TicketSiteUser> temp = front;
        while (temp != null) {
            nTicketQueue.enqueue(temp.getData());
            temp = temp.getNext();
        }
        int count = nTicketQueue.size();
        return nTicketQueue;
    }
    /**
     * This method returns a string of the queue and each of its elements.
     */
    public String toString() {
        String s = "";
        LinkedNode<TicketSiteUser> runner = this.front;
        while (runner != null) {
            s += runner.getData() +"\n";
            runner = runner.getNext();
        }
        return s;
    }
    /**
     * This method returns true if the queue is completely empty.
     */
    public boolean isEmpty() {
        if (front == null && back == null && size == 0) {
            return true;
        }
        return false;
    }
    /**
     * This method returns the capacity of the queue.
     */
    public int capacity() {
        return capacity;
    }
    /**
     * This method sets a new capacity for the queue.
     * It throws an IllegalArgumentException if the capacity is below 1.
     */
    public void setCapacity(int newCapacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException();
        }
        this.capacity = newCapacity;
    }
    /**
     * @param newObject throws an IllegalArgumentException if the object cannot be added to the queue
     * as a ticket has not been bought or the user has not been logged in.
     * This method adds to the back of the queue and updates its size accordingly.
     * It throws an IndexOutOfBoundsException if the queue is full.
     */
    public void enqueue(TicketSiteUser newObject) {
        if (!newObject.canBuyTicket()) {
            throw new IllegalArgumentException();
        }
        if (isFull()) {
            throw new IllegalStateException();
        }
        if (isEmpty()) {
            front = new LinkedNode<TicketSiteUser>(newObject);
            back = front;
            size++;
        }
        else if (size == 1) {
            back.setNext(new LinkedNode<TicketSiteUser>(newObject));
            back = new LinkedNode<TicketSiteUser>(newObject);
            front.setNext(back);
            size++;

        }
        else {
            LinkedNode<TicketSiteUser> temp = back;
            back = new LinkedNode<TicketSiteUser>(newObject);
            temp.setNext(back);
            size++;
        }
    }

}
