//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    BoardingSystemTester
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
// Persons:         (identify each by name and describe how they helped)
// Online Sources:  (identify each by URL and describe how it helped)
//
///////////////////////////////////////////////////////////////////////////////
import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * This is a Utility class which implements tester methods to ensure the correctness of the
 * implementation of the main operations defined in cs300 fall 2023 p10 Airplane Boarding System.
 *
 */
public class BoardingSystemTester {


  /**
   * Ensures the correctness of Passenger.compareTo() method when called to compare two Passenger
   * objects having different boarding groups.
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testPassengerCompareToDifferentGroup() {
    Passenger.resetPassengerOrder();
    Passenger p = new Passenger("Kelly", Group.A, false);
    Passenger c = new Passenger("Mark", Group.B, false);
    if (!(p.compareTo(c) < 0)) {
      return false;
    }
    Passenger.resetPassengerOrder();
    Passenger b = new Passenger("Mark", Group.C, false);
    Passenger a = new Passenger("John", Group.B, false);
    if (!(b.compareTo(a) > 0)) {
      return false;
    }


    // [HINT] You can consider at least two Passenger objects, and ensure at least the following:
    // p1.compareTo(p2) < 0, if p1 has a boarding group less than the boarding group of p2.
    // p2.compareTo(p1) > 0
    // where p1, and p2 are references to Passenger objects with different boarding groups.
    // Recall that we defined three boarding groups A, B, and C such that A < B < C.

    return true; // default return statement
  }

  /**
   * Ensures the correctness of Passenger.compareTo() method when called to compare two Passenger
   * objects having the same boarding group, and different arrival orders.
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testPassengerCompareToSameGroupDifferentArrival() {
    Passenger.resetPassengerOrder();
    Passenger p = new Passenger("Kelly", Group.A, true);
    Passenger c = new Passenger("Mark", Group.A, true);
    if (!(p.compareTo(c) < 0)) {
      return false;
    }
    Passenger.resetPassengerOrder();
    Passenger a = new Passenger("John", Group.B, true);
    Passenger b = new Passenger("Mark", Group.B, true);
    if (!(b.compareTo(a) > 0)) {
      return false;
    }

    // [HINT] You can consider at least two Passenger objects, and ensure at least the following:
    // p1.compareTo(p2) < 0, if p1 has a boarding group less than the boarding group of p2.
    // p2.compareTo(p1) > 0
    // where p1, and p2 are references to Passenger objects with different boarding groups.
    // Recall that we defined three boarding groups A, B, and C such that A < B < C.
    return true;
  }

  /**
   * Ensures two passengers having the SAME boarding group and with the SAME order of arrival are
   * equal (compareTo should return 0).
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testPassengerCompareToSameGroupSameArrival() {
    Passenger.resetPassengerOrder();
    Passenger p = new Passenger("Kelly", Group.A, true);
    Passenger c = new Passenger("Mark", Group.A, true);
    if (p.compareTo(p) != 0) {
      return false;
    }
    if (p.compareTo(c) == 0) {
      return false;
    }

    // Even though this case will not be possible in your priority queue, it is required for testing
    // the full functionality of the compareTo() method.
    // [Hint] You can use the resetPassengerOrder() to create equivalent passengers.
    return true; // default return statement
  }

  /**
   * Ensures the correctness of the constructor for BoardingQueue class.
   * 
   * This tester should implement at least the following test cases:
   *
   * - Calling the constructor of the BoardingQueue class with an invalid capacity should throw an
   * IllegalArgumentException - Calling the constructor or the BoardingQueue class with a valid
   * capacity should not throw any errors, and should result in a new BoardingQueue object which is
   * empty, has size 0, a capacity equal to the capacity that was passed as a parameter, and the
   * heap array contains only null references.
   *
   * @return true if the constructor of the BoardingQueue functions properly, false otherwise
   */
  public static boolean testBoardingQueueConstructor() {
    Passenger.resetPassengerOrder();
    BoardingQueue q = new BoardingQueue(3);
    if (q.capacity() != 3) {
      return false;
    }
    try {
      BoardingQueue o = new BoardingQueue(-1);
    } catch (IllegalArgumentException e) {
      return true;
    }

    // [HINT] you can get a copy of the heap array by calling BoardingQueue.toArray() method
    return false; // default return statement
  }

  /**
   * Tests the functionality of BoardingQueue.peekBest() method by calling peekBest on an empty
   * queue and verifying it throws a NoSuchElementException.
   * 
   * @return true if BoardingQueue.peekBest() verifies a correct functionality, false otherwise
   */
  public static boolean testPeekBestEmptyQueue() {
    Passenger.resetPassengerOrder();
    BoardingQueue q = new BoardingQueue(3);
    try {
      q.peekBest();
    } catch (NoSuchElementException e) {
      return true;
    }


    return false; // default return statement
  }

  /**
   * Tests the functionality of BoardingQueue.peekBest() method by calling peekBest on a non-empty
   * queue and ensures it
   * 
   * 1) returns the Passenger having the highest priority (the minimum), and 2) does not remove that
   * Passenger from the boarding queue.
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testPeekBestNonEmptyQueue() {
    Passenger.resetPassengerOrder();
    BoardingQueue q = new BoardingQueue(3);
    Passenger p = new Passenger("Adam", Group.B, true);
    q.enqueue(p);
    Passenger d = new Passenger("Ty", Group.A, true);
    boolean a = q.enqueue(d);
    if (a && q.peekBest().equals(d) && q.size() == 2) {
      return true;
    }


    return false; // default return statement
  }

  /**
   * Tests the functionality of the BoardingQueue.enqueue() method by calling enqueue() on an empty
   * queue and ensuring the method 1) adds the Passenger and 2) increments the size.
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testEnqueueToEmptyQueue() {
    Passenger.resetPassengerOrder();
    BoardingQueue q = new BoardingQueue(3);
    Passenger p = new Passenger("Adam", Group.A, true);
    boolean b = q.enqueue(p);
    if (b && q.peekBest().equals(p) && q.size() == 1) {
      return true;
    }


    return false;



  }


  /**
   * Tests the functionality of the BoardingQueue.enqueue() method by calling enqueue() on a
   * non-empty queue and ensuring it
   * 
   * 1) inserts the Passenger at the proper position of the heap, increments the size by one, and
   * returns true, if the queue was not already full.
   * 
   * 2) returns false, without making any changes to the size of the queue or the array heap, if the
   * method is called on a full queue.
   * 
   * Try adding at least 5 Passengers.
   * 
   * @return true if tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testEnqueueToNonEmptyQueue() {
    Passenger.resetPassengerOrder();
    BoardingQueue q = new BoardingQueue(6);
    Passenger p = new Passenger("Adam", Group.B, true);
    q.enqueue(p);
    Passenger d = new Passenger("Ty", Group.A, true);
    q.enqueue(d);
    Passenger b = new Passenger("Tyler", Group.A, true);
    q.enqueue(b);
    Passenger dan = new Passenger("Dan", Group.C, true);
    q.enqueue(dan);
    Passenger be = new Passenger("Be", Group.B, true);
    q.enqueue(be);
    Passenger f = new Passenger("Fong", Group.B, true);
    boolean a = q.enqueue(f);
    Passenger h[] = q.toArray();
    if (a && q.size() == 6 && h[1].equals(p) && h[4].equals(be) && q.peekBest().equals(d)) {
      return true;
    }

    return false;
  }

  /**
   * Tests the functionality of BoardingQueue.dequeue() method by calling dequeue() on an empty
   * queue and ensures a NoSuchElementException is thrown in that case.
   * 
   * @return true if tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testDequeueEmpty() {
    Passenger.resetPassengerOrder();
    BoardingQueue q = new BoardingQueue(3);
    try {
      q.dequeue();
    } catch (NoSuchElementException e) {
      return true;
    }


    return false; // default return statement
  }


  /**
   * Tests the functionality of BoardingQueue.dequeue() method by calling dequeue() on a queue of
   * size one and ensures the method call returns the correct Passenger, size is zero, and the array
   * heap contains null references, only.
   * 
   * @return true if tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testDequeueBoardingQueueSizeOne() {
    Passenger.resetPassengerOrder();
    BoardingQueue q = new BoardingQueue(3);
    Passenger p = new Passenger("Adam", Group.A, true);
    q.enqueue(p);
    if (q.dequeue().equals(p))
      return true;

    // [HINT] you can get a copy of the heap array by calling BoardingQueue.toArray() method

    return false; // default return statement added to resolve compiler errors
  }

  /**
   * Tests the functionality of BoardingQueue.dequeue() when called on a non-empty boarding queue.
   * 
   * This tests ensures the dequeue() method removes, and returns the passenger with the highest
   * boarding priority in the queue, the size of the queue must be decremented by one, and the
   * contents of the heap array is as expected.
   * 
   * @return true if PriorityCareAdmissions.dequeue() returns the correct Passenger
   *         each time it is called and size is appropriately decremented, false otherwise
   */
  public static boolean testDequeueBoardingQueueNonEmpty() {
    Passenger.resetPassengerOrder();
    BoardingQueue q = new BoardingQueue(6);
    Passenger p = new Passenger("Adam", Group.B, true);
    q.enqueue(p);
    Passenger d = new Passenger("Ty", Group.A, true);
    q.enqueue(d);
    Passenger b = new Passenger("Tyler", Group.A, true);
    q.enqueue(b);
    Passenger dan = new Passenger("Dan", Group.C, true);
    q.enqueue(dan);
    Passenger be = new Passenger("Be", Group.B, true);
    q.enqueue(be);
    Passenger f = new Passenger("Fong", Group.B, true);
    q.enqueue(f);
    Passenger a = q.dequeue();
    Passenger [] h = q.toArray();
    if (q.size() == 5 && a.equals(d) && h[0].equals(b) && h[4].equals(be)) {
      return true;
    }




    // [HINT] Try considering calling dequeue from a boarding queue whose size is at least 6.
    // Consider cases where percolate-down recurses on left and right.
    // You can call dequeue multiple times to cover multiple operational cases of percolate down
    // method (for instance percolate down can reach the bottom level of the heap or not)

    return false; // default return statement
  }

  /**
   * Tests the functionality of the clear() method. Should implement at least the following
   * scenarios:
   * 
   * - clear can be called on an empty queue with no errors.
   * 
   * - clear can be called on a non-empty queue with no errors.
   * 
   * After calling clear(), this tester ensures that the queue is empty, meaning its size is zero
   * and its heap array contains only null references.
   *
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testBoardingQueueClear() {
    Passenger.resetPassengerOrder();
    BoardingQueue q = new BoardingQueue(3);
    Passenger p = new Passenger("Adam", Group.B, true);
    q.enqueue(p);
    Passenger d = new Passenger("Ty", Group.A, true);
    q.enqueue(d);
    q.enqueue(new Passenger("Steph", Group.C, true));
    q.clear();
    if (q.isEmpty())
      return true;



    return false; // default return statement
  }

  /**
   * Main method to run this tester class.
   * 
   * @param args list of input arguments if any
   */
  public static void main(String[] args) {
    System.out.println("testPassengerCompareToDifferentGroup: "
        + (testPassengerCompareToDifferentGroup() ? "Pass" : "Failed!"));
    System.out.println("testPassengerCompareToSameGroupDifferentArrival: "
        + (testPassengerCompareToSameGroupDifferentArrival() ? "Pass" : "Failed!"));
    System.out.println("testPassengerCompareToSameGroupSameArrival: "
        + (testPassengerCompareToSameGroupSameArrival() ? "Pass" : "Failed!"));
    System.out.println(
        "testBoardingQueueConstructor: " + (testBoardingQueueConstructor() ? "Pass" : "Failed!"));
    System.out
        .println("testPeekBestEmptyQueue: " + (testPeekBestEmptyQueue() ? "Pass" : "Failed!"));
    System.out.println(
        "testPeekBestNonEmptyQueue: " + (testPeekBestNonEmptyQueue() ? "Pass" : "Failed!"));
    System.out
        .println("testEnqueueToEmptyQueue: " + (testEnqueueToEmptyQueue() ? "Pass" : "Failed!"));
    System.out.println(
        "testEnqueueToNonEmptyQueue: " + (testEnqueueToNonEmptyQueue() ? "Pass" : "Failed!"));
    System.out.println("testDequeueEmpty: " + (testDequeueEmpty() ? "Pass" : "Failed!"));
    System.out.println("testDequeueBoardingQueueSizeOne: "
        + (testDequeueBoardingQueueSizeOne() ? "Pass" : "Failed!"));
    System.out.println("testDequeueBoardingQueueNonEmpty: "
        + (testDequeueBoardingQueueNonEmpty() ? "Pass" : "Failed!"));
    System.out
        .println("testBoardingQueueClear: " + (testBoardingQueueClear() ? "Pass" : "Failed!"));
  }

}
