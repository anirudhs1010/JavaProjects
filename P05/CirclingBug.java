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
public class CirclingBug extends Bug implements Moveable {
    /**A subclass of Bug that is Movable and moves in a circle.
     When drawn to the screen they also have a tint applied to the image **/
    private float[] circleCenter; //the x,y-coordinates for the center of the circle path
    private static final int POINTS = 200; //constant, number of points ALL CirclingBugs are worth 200
    private double radius; //the radius of the circle path this bug moves on
    private double ticks = 0.0; //keeps track of how long the bug has been moving
    private int[] tintColor; //color used to tint the image when being drawn [Red,Green,Blue]
    public CirclingBug(float circleX, float circleY, double radius, int[] tintColor) {
        //Creates a new CirclingBug object using the provided parameters.
        super(circleX, circleY, POINTS);
        circleCenter = new float[2];
        circleCenter[0] = circleX;
        this.tintColor = tintColor;
        circleCenter[1] = circleY;
        this.radius = radius;

    }
    public void draw() { //Draws the image to the screen, tinting it with the tintColor before drawing it.
        processing.tint(tintColor[0], tintColor[1], tintColor[2]);
        super.draw();
        processing.tint(255, 255, 255);

    }
    public void move() { //Moves this CirclingBug to the next position on its circular path.
        ticks+=0.05;
        double newX = radius * Math.cos(ticks) + circleCenter[0];
        double newY = radius * Math.sin(ticks) + circleCenter[1];
        this.setY((float)newY);
        this.setX((float)newX);
        this.getHitbox().setPosition(getX(), getY());

    }
    public boolean shouldMove() {
        return true;
    } //Reports if the CirclingBug needs to move.


}
