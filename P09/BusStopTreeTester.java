//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    BusStopTreeTester
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
import java.time.LocalTime;
import java.util.Stack;


public class BusStopTreeTester {

  /**
   * Tests that compareTo returns the correct value when comparing a bus with a different arrival.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testBusCompareToDifferentArrivalTime() {
    int[] stopIds1 = {1, 2, 3, 4, 5};
    String[] stopTimes1 = {"05:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes2 = {"06:00", "06:30", "07:00", "07:30", "08:00"};
    // routes are different objects, but otherwise identical
    BusRoute route1 =
            BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    BusRoute route2 =
            BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes2);
    Bus bus1 = new Bus(BusStop.getStop(2), route1);
    Bus bus2 = new Bus(BusStop.getStop(2), route2);

    // compare bus1 to bus2 and vice versa
    boolean correctComparison1 = bus1.compareTo(bus2) == 1; // should return 0
    boolean correctComparison2 = bus2.compareTo(bus1) == -1;
    // TODO: Default return value.

    return correctComparison1 && correctComparison2;
  }

  /**
   * For two buses with the same arrival time but different routes, test that compareTo returns the
   * correct value.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testBusCompareToSameArrivalTimeDifferentRoute() {
    int[] stopIds1 = {1, 2, 3, 4, 5};

    String[] stopTimes1 = {"05:00", "07:00", "09:00", "11:00", "13:00"};
    // routes are different objects, but otherwise identical
    BusRoute route1 =
            BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    BusRoute route2 =
            BusRoute.dummyRoute("ROUTE 2", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    Bus bus1 = new Bus(BusStop.getStop(2), route1);
    Bus bus2 = new Bus(BusStop.getStop(2), route2);

    // compare bus1 to bus2 and vice versa
    boolean correctComparison1 = bus1.compareTo(bus2) == -1; // should return 0
    boolean correctComparison2 = bus2.compareTo(bus1) == 1;
    // TODO: Default return value.
    return correctComparison1 && correctComparison2;
  }


  /**
   * For two buses with the same arrival time and route name, but different directions, test that
   * compareTo returns the correct value.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testBusCompareToSameArrivalTimeSameRouteDifferentDirection() {
    int[] stopIds1 = {1, 2, 3, 4, 5};
    String[] stopTimes1 = {"05:00", "07:00", "09:00", "11:00", "13:00"};
    // routes are different objects, but otherwise identical
    BusRoute route1 =
            BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    BusRoute route2 =
            BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.INCOMING, stopIds1, stopTimes1);
    Bus bus1 = new Bus(BusStop.getStop(2), route1);
    Bus bus2 = new Bus(BusStop.getStop(2), route2);

    // compare bus1 to bus2 and vice versa
    boolean correctComparison1 = bus1.compareTo(bus2) == -1;
    boolean correctComparison2 = bus2.compareTo(bus1) == 1;
    // TODO: Default return value.
    return correctComparison1 && correctComparison2;
  }

  /**
   * Tests that compareTo returns the correct value (0) when comparing a bus with the same arrival
   * time, route name, and direction.
   * 
   * @return true if the test passes, false otherwise.
   */
  private static boolean testBusCompareToSameBus() {
    int[] stopIds1 = {1, 2, 3, 4, 5};
    String[] stopTimes1 = {"05:00", "07:00", "09:00", "11:00", "13:00"};
    // routes are different objects, but otherwise identical
    BusRoute route1 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    BusRoute route2 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    Bus bus1 = new Bus(BusStop.getStop(2), route1);
    Bus bus2 = new Bus(BusStop.getStop(2), route2);

    // compare bus1 to bus2 and vice versa
    boolean correctComparison1 = bus1.compareTo(bus2) == 0; // should return 0
    boolean correctComparison2 = bus2.compareTo(bus1) == 0; // should return 0

    // test passes if both comparisons return 0
    return correctComparison1 && correctComparison2;
  }

  /**
   * Tests that isValidBST returns true for an empty BST.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testIsValidBSTEmpty() {
    if (BusStopTree.isValidBST(null))
      return true;
    // TODO: Default return value.
    return false;
  }


  /**
   * Tests that isValidBST returns false for an invalid BST.
   * 
   * Should use a tree with depth > 2. Make sure to include a case where the left subtree contains a
   * node that is greater than the right subtree. (See the example in the spec for more details.)
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testIsValidBSTInvalid() {
    int[] stopIds1 = {1, 2, 3, 4, 5};
    String[] stopTimes4 = {"09:00", "11:00", "13:00", "15:00", "15:00"};
    String[] stopTimes3 = {"07:00", "09:00", "11:00", "13:00", "15:00"};
    String[] stopTimes2 = {"05:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes1 = {"06:00", "06:30", "07:00", "07:30", "08:00"};
    // routes are different objects, but otherwise identical
    BusRoute route1 =
            BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    BusRoute route2 =
            BusRoute.dummyRoute("ROUTE 2", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes2);
    BusRoute route3 =
            BusRoute.dummyRoute("ROUTE 3", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes3);
    BusRoute route4 =
            BusRoute.dummyRoute("ROUTE 4", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes4);
    Bus bus1 = new Bus(BusStop.getStop(2), route1);
    Bus bus2 = new Bus(BusStop.getStop(2), route2);
    Bus bus3 = new Bus(BusStop.getStop(2), route3);
    Bus bus4 = new Bus(BusStop.getStop(2), route4);
    Node<Bus> b = new Node<Bus>(bus1);
    Node<Bus> c = new Node<Bus>(bus2);
    Node<Bus> d = new Node<Bus>(bus3);
    Node<Bus> e = new Node<Bus>(bus4);
    b.setLeft(c);
    b.setRight(d);
    d.setRight(e);
    if (!BusStopTree.isValidBST(b))
      return true;
    // TODO: Default return value.
    return false;
  }


  /**
   * Tests that isValidBST returns true for a valid BST.
   * 
   * Should use a tree with depth > 2.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testIsValidBSTValid() {
    int[] stopIds1 = {1, 2, 3, 4, 5};
    String[] stopTimes4 = {"09:00", "11:00", "13:00", "15:00", "15:00"};
    String[] stopTimes3 = {"07:00", "09:00", "11:00", "13:00", "15:00"};
    String[] stopTimes2 = {"05:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes1 = {"06:00", "06:30", "07:00", "07:30", "08:00"};
    // routes are different objects, but otherwise identical
    BusRoute route1 =
            BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    BusRoute route2 =
            BusRoute.dummyRoute("ROUTE 2", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes2);
    BusRoute route3 =
            BusRoute.dummyRoute("ROUTE 3", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes3);
    BusRoute route4 =
            BusRoute.dummyRoute("ROUTE 4", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes4);
    Bus bus1 = new Bus(BusStop.getStop(2), route1);
    Bus bus2 = new Bus(BusStop.getStop(2), route2);
    Bus bus3 = new Bus(BusStop.getStop(2), route3);
    Bus bus4 = new Bus(BusStop.getStop(2), route4);
    Node<Bus> b = new Node<Bus>(bus1);
    Node<Bus> c = new Node<Bus>(bus2);
    Node<Bus> d = new Node<Bus>(bus3);
    Node<Bus> e = new Node<Bus>(bus4);
    c.setLeft(b);
    c.setRight(d);
    d.setLeft(null);
    d.setRight(e);

    if (BusStopTree.isValidBST(b))
      return true;
    // TODO: Default return value.
    return false;
    // TODO: Default return value.
  }


  /**
   * Tests that addBus correctly adds a bus to an empty BST.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testAddBusEmpty() {
    BusStopTree t = new BusStopTree(2);
    int[] stopIds1 = {1, 2, 3, 4, 5};
    String[] stopTimes1 = {"06:00", "06:30", "07:00", "07:30", "08:00"};
    // routes are different objects, but otherwise identical
    BusRoute route1 =
            BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    Bus bus1 = new Bus(BusStop.getStop(2), route1);
    Node<Bus> b = new Node<Bus>(bus1);
    return t.addBus(bus1) && bus1.compareTo(t.getRoot().getValue()) == 0;
  }

  /**
   * Tests that addBus correctly adds a bus to a non-empty BST.
   * 
   * Each time you add a bus, make sure that 1) addBus() returns true, 2) the BST is still valid, 3)
   * the BST size has been incremented.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testAddBus() {
    BusStopTree t = new BusStopTree(2);
    int[] stopIds1 = {1, 2, 3, 4, 5};
    String[] stopTimes4 = {"09:00", "11:00", "13:00", "15:00", "15:00"};
    String[] stopTimes3 = {"07:00", "09:00", "11:00", "13:00", "15:00"};
    String[] stopTimes2 = {"05:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes1 = {"06:00", "06:30", "07:00", "07:30", "08:00"};
    // routes are different objects, but otherwise identical
    BusRoute route1 =
            BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    BusRoute route2 =
            BusRoute.dummyRoute("ROUTE 2", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes2);
    BusRoute route3 =
            BusRoute.dummyRoute("ROUTE 3", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes3);
    BusRoute route4 =
            BusRoute.dummyRoute("ROUTE 4", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes4);
    Bus bus1 = new Bus(BusStop.getStop(2), route1);
    Bus bus2 = new Bus(BusStop.getStop(2), route2);
    Bus bus3 = new Bus(BusStop.getStop(2), route3);
    Bus bus4 = new Bus(BusStop.getStop(2), route4);
    t.addBus(bus2);
    t.addBus(bus1);
    t.addBus(bus3);
    return t.addBus(bus4);
    // TODO: Default return value.
  }

  /**
   * Tests that addBus returns false when adding a duplicate bus. The BST should not be modified
   * (same size).
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testAddBusDuplicate() {
    BusStopTree t = new BusStopTree(2);
    int[] stopIds1 = {1, 2, 3, 4, 5};
    String[] stopTimes4 = {"09:00", "11:00", "13:00", "15:00", "15:00"};
    String[] stopTimes3 = {"07:00", "09:00", "11:00", "13:00", "15:00"};
    String[] stopTimes2 = {"05:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes1 = {"06:00", "06:30", "07:00", "07:30", "08:00"};
    // routes are different objects, but otherwise identical
    BusRoute route1 =
            BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    BusRoute route2 =
            BusRoute.dummyRoute("ROUTE 2", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes2);
    BusRoute route3 =
            BusRoute.dummyRoute("ROUTE 3", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes3);
    BusRoute route4 =
            BusRoute.dummyRoute("ROUTE 4", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes4);
    Bus bus1 = new Bus(BusStop.getStop(2), route1);
    Bus bus2 = new Bus(BusStop.getStop(2), route2);
    Bus bus3 = new Bus(BusStop.getStop(2), route3);
    Bus bus4 = new Bus(BusStop.getStop(2), route4);
    Bus bus5 = new Bus(BusStop.getStop(2), route4);
    t.addBus(bus2);
    t.addBus(bus1);
    t.addBus(bus3);
    t.addBus(bus4);
    return !t.addBus(bus5) && t.size() == 4;
    // TODO: Default return value.
  }


  /**
   * Tests that contains returns true when the BST contains the Bus, and false otherwise.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testContains() {
    BusStopTree t = new BusStopTree(2);
    int[] stopIds1 = {1, 2, 3, 4, 5};
    String[] stopTimes4 = {"09:00", "11:00", "13:00", "15:00", "15:00"};
    String[] stopTimes3 = {"07:00", "09:00", "11:00", "13:00", "15:00"};
    String[] stopTimes2 = {"05:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes1 = {"06:00", "06:30", "07:00", "07:30", "08:00"};
    // routes are different objects, but otherwise identical
    BusRoute route1 =
            BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    BusRoute route2 =
            BusRoute.dummyRoute("ROUTE 2", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes2);
    BusRoute route3 =
            BusRoute.dummyRoute("ROUTE 3", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes3);
    BusRoute route4 =
            BusRoute.dummyRoute("ROUTE 4", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes4);
    Bus bus1 = new Bus(BusStop.getStop(2), route1);
    Bus bus2 = new Bus(BusStop.getStop(2), route2);
    Bus bus3 = new Bus(BusStop.getStop(2), route3);
    Bus bus4 = new Bus(BusStop.getStop(2), route4);
    t.addBus(bus2);
    t.addBus(bus1);
    t.addBus(bus4);
    return !t.contains(bus3) && t.contains(bus4);
    // TODO: Default return value.

  }


  /**
   * Tests that getFirstNodeAfter returns the correct <code>Node<Bus></code> when the correct
   * <code>Node<Bus></code> is the node passed in as the root node parameter.
   * 
   * @return
   */
  public static boolean testGetFirstNodeAfterRoot() {
    BusStopTree t = new BusStopTree(2);
    int[] stopIds1 = {1, 2, 3, 4, 5};
    String[] stopTimes4 = {"09:00", "11:00", "13:00", "15:00", "15:00"};
    String[] stopTimes3 = {"07:00", "09:00", "11:00", "13:00", "15:00"};
    String[] stopTimes2 = {"05:00", "07:05", "09:00", "11:00", "13:00"};
    String[] stopTimes1 = {"06:00", "06:30", "07:00", "07:30", "08:00"};
    // routes are different objects, but otherwise identical
    BusRoute route1 =
            BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    BusRoute route2 =
            BusRoute.dummyRoute("ROUTE 2", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes2);
    BusRoute route3 =
            BusRoute.dummyRoute("ROUTE 3", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes3);
    BusRoute route4 =
            BusRoute.dummyRoute("ROUTE 4", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes4);
    Bus bus1 = new Bus(BusStop.getStop(2), route1);
    Bus bus2 = new Bus(BusStop.getStop(2), route2);
    Bus bus3 = new Bus(BusStop.getStop(2), route3);
    Bus bus4 = new Bus(BusStop.getStop(2), route4);
    t.addBus(bus2);
    t.addBus(bus1);
    t.addBus(bus3);
    t.addBus(bus4);
    return t.getRoot() == t.getFirstNodeAfter(LocalTime.of(7, 1), t.getRoot())
            && t.getRoot() == t.getFirstNodeAfter(LocalTime.of(7, 5), t.getRoot());
    // TODO: Default return value.
  }

  /**
   * Tests that getFirstNodeAfter returns the correct <code>Node<Bus></code> when the correct
   * <code>Node<Bus></code> is in the left subtree.
   *
   * @return
   */
  public static boolean testGetFirstNodeAfterLeftSubtree() {
    BusStopTree t = new BusStopTree(2);
    int[] stopIds1 = {1, 2, 3, 4, 5};
    String[] stopTimes4 = {"09:00", "11:00", "13:00", "15:00", "15:00"};
    String[] stopTimes3 = {"07:00", "09:00", "11:00", "13:00", "15:00"};
    String[] stopTimes2 = {"05:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes1 = {"06:00", "06:35", "07:00", "07:30", "08:00"};
    String[] stopTimes5 = {"04:00", "05:35", "07:00", "07:30", "08:00"};

    // routes are different objects, but otherwise identical
    BusRoute route1 =
            BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    BusRoute route2 =
            BusRoute.dummyRoute("ROUTE 2", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes2);
    BusRoute route3 =
            BusRoute.dummyRoute("ROUTE 3", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes3);
    BusRoute route4 =
            BusRoute.dummyRoute("ROUTE 4", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes4);
    BusRoute route5 =
            BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes5);
    Bus bus1 = new Bus(BusStop.getStop(2), route1);
    Bus bus2 = new Bus(BusStop.getStop(2), route2);
    Bus bus3 = new Bus(BusStop.getStop(2), route3);
    Bus bus4 = new Bus(BusStop.getStop(2), route4);
    Bus bus5 = new Bus(BusStop.getStop(2), route5);
    t.addBus(bus2);
    t.addBus(bus1);
    t.addBus(bus3);
    t.addBus(bus4);
    t.addBus(bus5);
    return t.getRoot().getLeft().getValue().compareTo((t.getFirstNodeAfter(LocalTime.of(6, 20), t.getRoot())).getValue()) == 0
            && t.getRoot().getLeft().getValue().compareTo(t.getFirstNodeAfter(LocalTime.of(6, 35), t.getRoot()).getValue()) == 0;
    // TODO: Default return value.

  }

  /**
   * Tests that getFirstNodeAfter returns the correct <code>Node<Bus></code> when the correct
   * <code>Node<Bus></code> is in the right subtree.
   *
   * @return
   */
  public static boolean testGetFirstNodeAfterRightSubtree() {
    BusStopTree t = new BusStopTree(2);
    int[] stopIds1 = {1, 2, 3, 4, 5};
    String[] stopTimes4 = {"09:00", "11:00", "13:00", "15:00", "15:00"};
    String[] stopTimes3 = {"07:00", "09:01", "11:00", "13:00", "15:00"};
    String[] stopTimes2 = {"05:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes1 = {"06:00", "06:30", "07:00", "07:30", "08:00"};
    // routes are different objects, but otherwise identical
    BusRoute route1 =
            BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    BusRoute route2 =
            BusRoute.dummyRoute("ROUTE 2", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes2);
    BusRoute route3 =
            BusRoute.dummyRoute("ROUTE 3", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes3);
    BusRoute route4 =
            BusRoute.dummyRoute("ROUTE 4", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes4);
    Bus bus1 = new Bus(BusStop.getStop(2), route1);
    Bus bus2 = new Bus(BusStop.getStop(2), route2);
    Bus bus3 = new Bus(BusStop.getStop(2), route3);
    Bus bus4 = new Bus(BusStop.getStop(2), route4);
    t.addBus(bus2);
    t.addBus(bus1);
    t.addBus(bus3);
    t.addBus(bus4);
    return t.getRoot().getRight().getRight() == t.getFirstNodeAfter(LocalTime.of(9, 30), t.getRoot())
            && t.getRoot().getRight() == t.getFirstNodeAfter(LocalTime.of(9, 1), t.getRoot());

  }

  /**
   * Tests that removeBus correctly removes a Bus that is a LEAF NODE. Make sure that 1) removeBus
   * returns the removed Bus, 2) the BST is still valid, 3) the BST size has been decremented.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testRemoveBusLeaf() {
    // TODO: Default return value.
    return false;
  }

  /**
   * Tests that removeBus correctly removes a Bus that is a non-leaf node with ONE child. Make sure
   * that 1) removeBus returns the removed Bus, 2) the BST is still valid, 3) the BST size has been
   * decremented.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testRemoveBusNodeOneChild() {
    // TODO: Default return value.
    return false;
  }

  /**
   * Tests that removeBus correctly removes a Bus that is a non-leaf node with TWO children. Make
   * sure that 1) removeBus returns the removed Bus, 2) the BST is still valid, 3) the BST size has
   * been decremented.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testRemoveBusNodeTwoChildren() {
    return false;
  }


  /**
   * Tests that removeBus returns false when removing a Bus that is not in the BST. The BST should
   * not be modified.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testRemoveBusNodeNotInBST() {
    // TODO: Default return value.
    return false;
  }

  /**
   * Tests the creation of an BusFilteredIterator where NONE of the buses go to the destination.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testGetNextBusesToEmpty() {
    BusStopTree t = new BusStopTree(2);
    int[] stopIds1 = {1, 2, 3, 4, 5};
    String[] stopTimes4 = {"09:00", "11:00", "13:00", "15:00", "15:00"};
    String[] stopTimes3 = {"07:00", "09:01", "11:00", "13:00", "15:00"};
    String[] stopTimes2 = {"05:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes1 = {"06:00", "06:30", "07:00", "07:30", "08:00"};
    // routes are different objects, but otherwise identical
    BusRoute route1 =
            BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    BusRoute route2 =
            BusRoute.dummyRoute("ROUTE 2", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes2);
    BusRoute route3 =
            BusRoute.dummyRoute("ROUTE 3", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes3);
    BusRoute route4 =
            BusRoute.dummyRoute("ROUTE 4", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes4);
    Bus bus1 = new Bus(BusStop.getStop(2), route1);
    Bus bus2 = new Bus(BusStop.getStop(2), route2);
    Bus bus3 = new Bus(BusStop.getStop(2), route3);
    Bus bus4 = new Bus(BusStop.getStop(2), route4);
    t.addBus(bus2);
    t.addBus(bus1);
    t.addBus(bus3);
    t.addBus(bus4);
    BusForwardIterator bIter = new BusForwardIterator(t.getRoot(),
            t.getRoot().getRight());
    BusFilteredIterator b = new BusFilteredIterator(bIter, BusStop.getStop(1));
    int count = 0;
    while (b.hasNext()) {
      count++;
      b.next();
    }

    // TODO: Default return value.
    return count == 0;
  }

  /**
   * Tests the creation of an BusFilteredIterator where SOME of the buses go to the destination.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testGetNextBusesToSome() {

    BusStopTree t = new BusStopTree(2);
    int[] stopIds1 = {1, 2};
    int[] stopIds2 = {1, 2, 3};

    BusRoute route1 = BusRoute.dummyRoute("route 1", BusRoute.BusDirection.OUTGOING, stopIds2, new String[] {"08:00", "08:10", "08:30"});
    BusRoute route2 = BusRoute.dummyRoute("route 2", BusRoute.BusDirection.INCOMING, stopIds1, new String[] {"09:00", "09:05"});
    BusRoute route3 = BusRoute.dummyRoute("route 3", BusRoute.BusDirection.OUTGOING, stopIds1, new String[] {"05:00", "05:30"});
    BusRoute route4 = BusRoute.dummyRoute ("route 4", BusRoute.BusDirection.INCOMING, stopIds2, new String[] {"06:00", "06:30", "06:35"});

    Bus bus1 = new Bus(BusStop.getStop(1), route1);
    Bus bus2 = new Bus(BusStop.getStop(1), route2);
    Bus bus3 = new Bus(BusStop.getStop(1), route3);
    Bus bus4 = new Bus(BusStop.getStop(1), route4);
    t.addBus(bus1);
    t.addBus(bus2);
    t.addBus(bus3);
    t.addBus(bus4);

    BusForwardIterator bIter = new BusForwardIterator(t.getRoot(), t.getRoot().getLeft());
    BusFilteredIterator bFI = new BusFilteredIterator(bIter, BusStop.getStop(3));

    boolean a=false;
    boolean b=false;

    if(bFI.hasNext()) {
      a=bFI.next().compareTo(bus4)==0;
      if(bFI.hasNext()) {
        b=bFI.next().compareTo(bus1)==0;
      }
    }
    return a && b;
  }

  /**
   * Tests the creation of an BusFilteredIterator where ALL of the buses go to the destination.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testGetNextBusesToAll() {
    BusStopTree t = new BusStopTree(2);
    int[] stopIds1 = {1, 2, 3, 4, 5};
    String[] stopTimes4 = {"09:00", "11:00", "13:00", "15:00", "15:00"};
    String[] stopTimes3 = {"07:00", "09:01", "11:00", "13:00", "15:00"};
    String[] stopTimes2 = {"05:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes1 = {"06:00", "06:30", "07:00", "07:30", "08:00"};
    // routes are different objects, but otherwise identical
    BusRoute route1 =
            BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    BusRoute route2 =
            BusRoute.dummyRoute("ROUTE 2", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes2);
    BusRoute route3 =
            BusRoute.dummyRoute("ROUTE 3", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes3);
    BusRoute route4 =
            BusRoute.dummyRoute("ROUTE 4", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes4);
    Bus bus1 = new Bus(BusStop.getStop(2), route1);
    Bus bus2 = new Bus(BusStop.getStop(2), route2);
    Bus bus3 = new Bus(BusStop.getStop(2), route3);
    Bus bus4 = new Bus(BusStop.getStop(2), route4);
    t.addBus(bus2);
    t.addBus(bus1);
    t.addBus(bus3);
    t.addBus(bus4);
    BusForwardIterator bIter = new BusForwardIterator(t.getRoot(), t.getRoot().getLeft());
    BusFilteredIterator b = new BusFilteredIterator(bIter, BusStop.getStop(2));
    int count = 0;
    while (b.hasNext()) {
      count++;
      b.next();
    }
    // TODO: Default return value.
    return count == 4;
  }

  public static void main(String[] args) {
    // Populate BusStop with dummy data. This only has to be done once.
    BusStop.createDummyStops();
    System.out
        .println("testBusCompareToDifferentArrivalTime: " + testBusCompareToDifferentArrivalTime());
    System.out.println("testBusCompareToSameArrivalTimeDifferentRoute: "
        + testBusCompareToSameArrivalTimeDifferentRoute());
    System.out.println("testBusCompareToSameArrivalTimeSameRouteDifferentDirection: "
        + testBusCompareToSameArrivalTimeSameRouteDifferentDirection());
    System.out.println("testBusCompareToSameBus" + testBusCompareToSameBus());
    System.out.println("testIsValidBSTEmpty: " + testIsValidBSTEmpty());
    System.out.println("testIsValidBSTInvalid: " + testIsValidBSTInvalid());
    System.out.println("testIsValidBSTValid: " + testIsValidBSTValid());

    System.out.println("testAddBusEmpty: " + testAddBusEmpty());
    System.out.println("testAddBus: " + testAddBus());
    System.out.println("testAddBusDuplicate: " + testAddBusDuplicate());
    //System.out.println("testRemoveBusLeaf: " + testRemoveBusLeaf());
    //System.out.println("testRemoveBusNodeOneChild: " + testRemoveBusNodeOneChild());
    //System.out.println("testRemoveBusNodeTwoChildren: " + testRemoveBusNodeTwoChildren());
    //System.out.println("testRemoveBusNodeNotInBST: " + testRemoveBusNodeNotInBST());
    System.out.println("testContains: " + testContains());
    System.out.println("testGetFirstNodeAfterRoot: " + testGetFirstNodeAfterRoot());
    System.out.println("testGetFirstNodeAfterLeftSubtree: " + testGetFirstNodeAfterLeftSubtree());
    System.out.println("testGetFirstNodeAfterRightSubtree: " + testGetFirstNodeAfterRightSubtree());
    System.out.println("testGetNextBusesToEmpty: " + testGetNextBusesToEmpty());
    System.out.println("testGetNextBusesToSome: " + testGetNextBusesToSome());
    System.out.println("testGetNextBusesToAll: " + testGetNextBusesToAll());
  }

}
