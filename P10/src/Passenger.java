//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Passenger
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

/**
 * This class models Passenger objects ready to board an airplane and implements Comparable.
 *
 */
public class Passenger implements Comparable<Passenger> {
  // A Passenger MUST be compared to another Passenger, ONLY.

  // Data fields
  private static int orderGen = 1; // generator of arrival orders of passengers
  private String name; // name of this passenger
  private final int ARRIVAL_ORDER; // arrival order of this passenger
  private Group group; // boarding group assigned to this passenger
  private boolean isCheckedIn; // indicates whether this passenger was checked in before boarding
                               // the airplane


  // CONSTRUCTOR - PROVIDED
  /**
   * Constructs a new Passenger given their name, boarding group, and checkedin status
   * 
   * @param name        name to be assigned to this Passenger
   * @param group       boarding group to be assigned to this Passenger
   * @param isCheckedIn indicates whether this passenger was checked in, or not
   * @throws IllegalArgumentException if name is null or blank or if group is null
   */
  public Passenger(String name, Group group, boolean isCheckedIn) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("name is null or blank!");
    }

    if (group == null) {
      throw new IllegalArgumentException("boarding group is null!");
    }
    this.name = name;
    this.group = group;
    this.isCheckedIn = isCheckedIn;
    this.ARRIVAL_ORDER = nextOrder();
  }

  // nextOrder() PROVIDED
  /**
   * Generates and returns the arrival order to be assigned to the next Passenger object to be
   * created
   * 
   * @return the next generated order
   */
  private static int nextOrder() {
    return orderGen++;
  }

  // resetPassengerOrder() PROVIDED
  /**
   * Resets the arrival order generated to 1. This method can be used for testing purposes, or to
   * reset the system.
   */
  protected static void resetPassengerOrder() {
    orderGen = 1;
  }

  // name() PROVIDED
  /**
   * Gets the name of this passenger
   * 
   * @return the name of this passenger
   */
  public String name() {
    return name;
  }

  // isCheckedIn() PROVIDED
  /**
   * Determines whether this passenger was checked in before boarding the airplane
   * 
   * @return true if this passenger was checked in
   */
  public boolean isCheckedIn() {
    return this.isCheckedIn;
  }



  // toString() PROVIDED
  /**
   * Returns a String representation of this Passenger in the following format:
   * 
   * <name> from Group <group>
   * 
   * @return a String representation of this passenger
   */
  public String toString() {
    return this.name + " from Group " + this.group;
  }



  /**
   * Indicates whether some other object is "equal to" this Passenger.
   * 
   * @param obj the reference object with which to compare.
   * @return true if obj is an instance of Passenger and has the exact same name, group, and arrival
   *         order as this Passenger, otherwise return false.
   */
  @Override
  public boolean equals(Object obj) {
    // TODO Complete the implementation of the Passenger.equals() method with respect to the details
    // provided in its javadocs style method header
    if (this.toString().equals(((Passenger)obj).toString()) && this.compareTo((Passenger)obj) == 0)
      return true;
    return false; // default return statement
  }


  // TODO Add and implement the Passenger.compareTo() method as part of implementation of the
  // Comparable interface.
  // Passenger.compareTo() method MUST take an input parameter of type Passenger.
  //
  // Passengers are compared with respect to the increasing order of their boarding groups.
  // You can use Group.compareTo() method to compare boarding group constants.
  //
  // For instance, if we consider two Passengers p1 and p2 such as: 
  // the boarding group of p1 is Group.A
  // the boarding group of p2 is Group.B
  // Then, we the following statements are true:
  // p1.compareTo(p2) < 0
  // p2.compareTo(p1) > 0
  
  // If two passengers have the same boarding groups, they are compared with respect to the
  // increasing order of their arrival orders. If we consider two Passengers p1 and p2 such as:
  // Both p1 and p2 have the boarding group Group.B, for instance
  // the arrival order of p1 is 3
  // the arrival order of p2 is 7
  // Then, we the following statements are true:
  // p1.compareTo(p2) < 0
  // p2.compareTo(p1) > 0
  // 
  // The Passenger.compareTo method returns:
  // (*) zero if this Passenger and the other input Passenger have the same boarding group and the same
  // arrival order.
  // (*) a negative integer if this Passenger is less than the other input Passenger and, 
  // (*) a positive integer if this Passenger is greater than the other input Passenger.
  /**
   * Compares this Passenger object with another Passenger object based on their group
   * and arrival order.
   *
   * The comparison is first done based on the group. If the groups are not equal,
   * the result is the comparison of the groups. If the groups are equal, the comparison
   * is then based on the arrival order. If the arrival order of the current Passenger
   * is less than the other Passenger, -1 is returned. If the arrival order is greater,
   * 1 is returned. If both the group and arrival order are equal, 0 is returned.
   *
   * @param p The Passenger object to compare with.
   * @return A negative integer, zero, or a positive integer as this object is less than,
   *         equal to, or greater than the specified object.
   */
  public int compareTo(Passenger p) {
    if (this.group.compareTo(p.group) != 0) {
      return this.group.compareTo(p.group);
    }
    if (this.ARRIVAL_ORDER > p.ARRIVAL_ORDER) {
      return 1;
    }
    else if (this.ARRIVAL_ORDER < p.ARRIVAL_ORDER) {
      return -1;
    }
    return 0;
  }
  


}
