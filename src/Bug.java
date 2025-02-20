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
public class Bug extends GameActor {
    /**An instantiable class maintains data about a Bug in the Froggie Feeding Frenzie game.
     * These bugs do not move, can be drawn to the screen, and detect if it has been hit (eaten) by a Frog.
     */


     private static final String IMG_PATH = "images/bug.png";
     //path to the image used for bugs, all bugs use the same image
    private int points; //how many points this bug gives for being eaten
    public Bug(float x, float y, int points) { //Creates a new Bug object with the provided information
        super(x, y, IMG_PATH);
        this.points = points;

    }
    public int getPoints() { //Gets how many points this Bug is worth
        return points;
    }
    public boolean isEatenBy(Frog f) { //Determines whether or not this bug has been eaten by the Frog.
        if (f.getTongueHitbox() != null && this.getHitbox().doesCollide(f.getTongueHitbox())) {
            return true;
        }
        return false;
    }

}
