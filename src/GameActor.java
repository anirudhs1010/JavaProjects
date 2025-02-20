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
public class GameActor {
    /**An instantiable class for all game actors in the Froggie Feeding Frenzie game.
     * Game actors are images that are drawn the screen that also have hitboxes.**/
    private float[] coordinates; //the x,y-coordinates of the center stored as [x,y]
    private Hitbox hitbox; //the x,y-coordinates of the center stored as [x,y]
    protected processing.core.PImage image; //the image associated with this GameActor
    protected static processing.core.PApplet processing; //PApplet to use to draw GameActors to the screen

    public GameActor(float x, float y, String imgPath) {
        //Constructor for a new GameActor object by setting the coordinates, loading the image, and creating the hitbox.
        image = processing.loadImage(imgPath);
        hitbox = new Hitbox(x, y, image.width, image.height);
        coordinates = new float[]{x,y};
        if (processing == null)
            throw new IllegalStateException("Processing cannot be null");

    }

    public static void setProcessing(processing.core.PApplet processing) {
        GameActor.processing = processing;
    }

    public float getX() { //Getter for the x-coordinate.
        return coordinates[0];
    }

    public float getY() { //Getter for the y-coordinate.
        return coordinates[1];
    }

    public void setX(float newX) { //Setter for the x-coordinate.
        coordinates[0] = newX;

    }

    public void setY(float newY) { //Setter for the y-coordinate.
        coordinates[1] = newY;

    }
    public Hitbox getHitbox() { //Getter for the Hitbox.
        return hitbox;
    }
    public void moveHitbox(float x, float y) { //Moves this GameActors Hitbox to the provided x,y-coordinates
        this.hitbox.setPosition(x, y);

    }
    public void draw() { //Draws the image of the GameActor to the screen.
        GameActor.processing.image(image, coordinates[0], coordinates[1]);
    }

}
