//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Froggie Feeding Frenzie
// Course:   CS 300 Fall 2023
//
// Author:   Anirudh Sunil
// Email:    sunil3@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    Saurabh Abhyankar
// Partner Email:   abhyankar3@wisc.edu
// Partner Lecturer's Name:  Hobbes LeGault
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
import java.util.Random;
public class BouncingBug extends Bug implements Moveable {
    /** A subclass of Bug that is Movable. These bugs bounce around the screen like a DVD player logo. **/
    private boolean goDown; //keeps track if bug is moving up or down
    private boolean goLeft; //keeps track if bug is moving left or right
    private static final int POINTS = 100; //a constant, number of points ALL BouncingBugs are worth, 100
    private Random randGen; //a random generator to determine the initial directions
    private int[] speedNums; //the number of pixels to move horizontally and vertically, formatted [dx,dy]

    public BouncingBug(float x, float y, int dx, int dy) { //Creates a new Bouncing Bug object using the given parameters.
        super(x, y, POINTS);
        speedNums = new int[2];
        speedNums[0] = dx;
        speedNums[1] = dy;
        randGen = new Random();
        this.moveHitbox(getX(), getY());
        goLeft = randGen.nextBoolean();
        goDown = randGen.nextBoolean();

    }
    public void move() {
        //Moves this BouncingBug dx pixels left or right
        // (depending on the current horizontal direction) and dy pixels up or down (depending on the current vertical direction).
        if (goLeft) {
            this.setX(getX()-speedNums[0]);
        }
        else {
            this.setX(getX()+speedNums[0]);
        }
        if (goDown) {
            this.setY(getY()+speedNums[1]);
        }
        else {
            this.setY(getY()-speedNums[1]);
        }
        this.getHitbox().setPosition(getX(), getY());
        if (getX() <= 0 || getX() >= 800) {
            goLeft = !goLeft;
        }
        if (getY() <= 0 || getY() >= 600) {
            goDown = !goDown;
        }

    }
    public boolean shouldMove() { //Reports if the BouncingBug needs to move.
        return true;
    }

}
