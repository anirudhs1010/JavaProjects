//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    BoardingQueue
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
import java.util.PriorityQueue;

/**
 * Array-based min-heap implementation of a priority boarding queue storing elements of type
 * Passenger. This class guarantees the min-heap invariant, so that:
 * 
 * The Passenger at the root should be the smallest Passenger in the queue, which corresponds to the
 * passenger having the highest priority.
 * 
 * Children always are greater than their parent.
 * 
 * The Passenger at the root of this min-heap priority queue must be dequeued (board the airplane)
 * next, meaning removed and returned by the dequeue method.
 * 
 * The BoardingQueue.peekBest() must return without removing the Passenger at the root of this
 * min-heap queue, if the queue is not empty.
 * 
 * We rely on the Passenger.compareTo() method to compare Passengers.
 * 
 * The root of a non-empty queue is always at index 0 of this array-heap.
 */
public class BoardingQueue implements PriorityQueueADT<Passenger> {
  // This BoardingQueue stores elements of type Passenger, ONLY.

  // oversize array
  private Passenger[] heap;// array min-heap of Passengers representing this priority queue
  private int size; // size of this priority queue
  // The heap is an oversize array, meaning that the following should be ensured:
  // heap[i] == null for all valid indexes and size == 0 when the heap is EMPTY
  // If the heap is NOT empty:
  // heap[i] != null when i >= 0 && i < size
  // heap[i] == null when i >= size && i < heap.length

  /**
   * Constructs an empty BoardingQueue with the given capacity
   * 
   * @param capacity Capacity of this boarding queue
   * @throws IllegalArgumentException with a descriptive error message if the capacity is not a
   *                                  positive integer (greater than zero)
   */
  public BoardingQueue(int capacity) {
    if (capacity < 0) {
      throw new IllegalArgumentException("capacity must be greater than 0");
    }
    this.size = 0;
    heap = new Passenger[capacity];



  }


  /**
   * Returns the capacity of this BoardingQueue
   * 
   * @return the capacity of this BoardingQueue
   */
  public int capacity() {
    return heap.length;
  }



  /**
   * Removes all the elements from this Boarding Queue
   */
  public void clear() {
    for (int i = 0; i < size; i++) {
      heap[i] = null;
    }
    size = 0;

  }


  /**
   * Checks whether this BoardingQueue is full
   * 
   * @return true if this boarding queue is full
   */
  public boolean isFull() {
    if (size == capacity())
      return true;


    return false; // default return statement
  }


  // toArray() method PROVIDED
  /**
   * Returns a deep copy of the array-heap of this BoardingQueue. This method can be used for
   * testing purposes.
   * 
   * This method can be used for testing purposes.
   * 
   * @return a deep copy of the array-heap storing the Passengers in this queue
   * @throws NullPointerException if the heap array of this BoardingQueue is null
   */
  public Passenger[] toArray() {
    return Arrays.copyOf(this.heap, this.heap.length);
  }


  /**
   * Returns a deep copy of this BoardingQueue containing all of its elements in the same order.
   * This method does not return the deepest copy, meaning that you do not need to duplicate
   * Passengers. Only the instance of the heap (including the array and its size) will be
   * duplicated.
   * 
   * @return a deep copy of this BoardingQueue. The returned new boarding queue (the deep copy) has
   *         the same length and size as this queue.
   */
  public BoardingQueue deepCopy() {
    Passenger ans[] = this.toArray();
    BoardingQueue p = new BoardingQueue(capacity());
    for (int i = 0; i < size; i++) {
      p.enqueue(ans[i]);
    }

    return p; // default return statement
  }

  @Override
  public Passenger dequeue() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    Passenger temp = heap[0];
    heap[0] = heap[size-1];
    heap[size-1] = null;
    size--;
    if (!isEmpty())
      percolateDown(0);
    return temp;
  }

  @Override
  public Passenger peekBest() {
    if (heap[0] == null)
        throw new NoSuchElementException();
    return heap[0];
  }

  @Override
  public boolean isEmpty() {
    return size == 0 && heap[0] == null;
  }

  @Override
  public boolean enqueue(Passenger e) {
    if (isFull())
      return false;
    heap[size] = e;
    size++;
    percolateUp(size - 1);
    return true;
  }

  @Override
  public int size() {
    return size;
  }
  // toString() method PROVIDED, but commented out

  // you implement the isEmpty() and dequeue() methods
  /**
   * Returns a String representing this boarding queue, where each Passenger in the queue is listed
   * on a separate line, in order from smallest to greatest, meaning in their boarding order.
   * 
   * @return a String representing this boarding queue, and an empty String "" if this queue is
   *         empty.
   */

   public String toString() {
      String s = "";
      BoardingQueue deepCopy = this.deepCopy();
      while (!deepCopy.isEmpty()) {
        s += deepCopy.dequeue().toString() + "\n";
      }
      return s.trim();
   }

  /**
   * Restores the min-heap of the priority queue by percolating its root down the tree. If the
   * element at the given index does not violate the min-heap ordering property (it is smaller than
   * its smallest child), then this method does not modify the heap. Otherwise, if there is a heap
   * violation, then swap the element with the correct child and continue percolating the element
   * down the heap.
   * 
   * We assume that index is in bounds (greater or equal to zero and less than size).
   * 
   * @param index index of the element in the heap to percolate downwards
   */
  protected void percolateDown(int index) {
    int childIndex = 2 * index + 1;
    Passenger value = heap[index];

    while (childIndex < size) {
      // Find the max among the node and all the node's children
      Passenger maxValue = value;
      int maxIndex = -1;
      for (int i = 0; i < 2 && i + childIndex < size; i++) {
        if (heap[i + childIndex].compareTo(maxValue) < 0) {
          maxValue = heap[i + childIndex];
          maxIndex = i + childIndex;
        }
      }

      if (maxValue == value) {
        return;
      }
      else {
        Passenger temp = heap[index];
        heap[index] = heap[maxIndex];
        heap[maxIndex]= temp;
        index = maxIndex;
        childIndex = 2 * index + 1;
      }
    }

    // header
    // We recommend implementing a recursive version of this method to get more practice on
    // recursive thinking

  }


  /**
   * Restores the min-heap invariant of this priority queue by percolating a leaf up the heap. If
   * the element at the given index does not violate the min-heap invariant (it is greater than its
   * parent), then this method does not modify the heap. Otherwise, if there is a heap violation,
   * swap the element with its parent and continue percolating the element up the heap. We assume
   * that index is in bounds (greater or equal to zero and less than size).
   * 
   * @param index index of the element in the heap to percolate upwards
   */
  protected void percolateUp(int index) {

    // header
    // We recommend implementing ae recursive version of this method to get more practice on
    // recursive thinking
    while (index > 0) {
      int parentIndex = (index - 1) / 2;
      if (heap[index].compareTo(heap[parentIndex]) >= 0)
        return;
      else {
        Passenger temp = heap[index];
        heap[index] = heap[parentIndex];
        heap[parentIndex] = temp;
        index = parentIndex;
      }
    }
  }

  // in their javadocs.
  // Be sure that the enqueue() method takes an input of type Passenger
  // The return type of peekBest() and dequeue() methods MUST be Passenger
  // The details of what every method is supposed to do is provided in the details of the javadocs
  // style method headers of the abstract methods defined in the PriorityQueueADT generic interface.


}
