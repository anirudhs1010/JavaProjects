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
public class StrongBug extends Bug implements Moveable{
    /**A subclass of Bug that is Movable. These bugs only move when they are not at max health.
     * When hit they become smaller and start moving horizontally across the screen.**/
     private final int MAX_HEALTH; //the max health of this StrongBug
    private static final int POINTS = 500; //the number of points ALL StrongBugs are worth, 500
    private int currentHealth; //the current health of this StringBug, updates when Bug takes damage
    public StrongBug(float x, float y, int health) { //Creates a new StrongBug object at full health using the provided parameters.
        super(x, y, POINTS);
        if (health < 1) {
            throw new IllegalArgumentException("Health should be at least 1.");
        }
        this.MAX_HEALTH = health;
        this.currentHealth = MAX_HEALTH;
        // set the initial position of the StrongBug
    }

    public void move() {
        //Moves this StrongBug 3 units to the right, wrapping around the screen when the center hits the edge of the window.
        //The Hitbox should move with the StrongBug. The x,y-coordinate of only changes if the StrongBug should move.
        if (this.getX() >= 800) {
            this.setX(0);
        }
        else if (currentHealth != MAX_HEALTH) {
            this.setX(getX() + 3);
        }
        this.moveHitbox(getX(), getY());
    }


    public boolean isDead() {
        // Report if the StrongBug is dead
        return currentHealth <= 0;
    }

    public void loseHealth() {
        currentHealth--;
        // Decrease the health of the StrongBug by 1

    }


    public boolean shouldMove() {
        // Report if the StrongBug needs to move
        return currentHealth < MAX_HEALTH;
    }


    public boolean isEatenBy(Frog f) {
       if (super.isEatenBy(f)) {
           loseHealth();
           this.setX((float)0.75*this.getX());
           this.setY((float)0.75*this.getY());
           this.image.resize((int)(this.image.width*0.75), (int)(this.image.height*0.75));
           this.getHitbox().setPosition(getX(), getY());
       }
        // Determine whether or not this bug has been eaten by the Frog
        // If the bug has been hit by the frog:
        // - decrease the StrongBug's health
        // - resize the image to 75% of its current height and width
        // - change the dimensions of the hitbox to match the new image dimensions
        // Use PImage's resize() method for resizing the image.
        return false;
    }
}







