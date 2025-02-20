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

public class Frog extends GameActor implements Moveable {

    private int health; //how much health the frog has
    private static final String IMG_PATH = "images/frog.png"; //path to the image to use for the frog
    private boolean isDragging; //keeps track of if the Frog is being dragged by the mouse
    private float oldMouseX; //the previous x-coordinate of the mouse
    private float oldMouseY; //the previous y-coordinate of the mouse
    private Tongue tongue; //the tongue belonging to this frog

    public Frog(float x, float y, int health) {
        super(x, y, IMG_PATH);
        if (health < 1) {
            throw new IllegalArgumentException("Health cannot be less than 1");
        }
        this.health = health;
        this.isDragging = false; // Changed to false as per typical behavior
        this.oldMouseX = x; // Set the oldMouseX to current x
        this.oldMouseY = y; // Set the oldMouseY to current y
        tongue = new Tongue(x, y);

        // Initialize other fields
    }

    @Override
    public void draw() { //Draws the image of the Frog to the screen.
        super.draw(); //just calls GameActor draw

        if (tongue.isActive()) {
            // Draw the tongue
            tongue.draw();
            tongue.updateStartPoint(getX(), getY());
            tongue.extend(getX(), -2);
            // Extend the tongue


        }


    }

    @Override
    public void move() { //Moves the Frog by dragging it by the mouse, if it should be dragging.

        if (isDragging) {
            // Implement dragging functionality
            float dx = oldMouseX - processing.mouseX;
            float dy = oldMouseY - processing.mouseY;
            this.setX(this.getX()-dx);
            this.setY(this.getY()-dy);
            this.moveHitbox(getX(), getY());
            tongue.updateStartPoint(this.getX(), this.getY());

            // Move the Frog and the tongue's starting point and Hitbox
        }
        if (!tongue.isActive()) {
            tongue.updateEndPoint(this.getX(), this.getX());
            // Move the tongue's endpoint along with the Frog
        }

        oldMouseX = processing.mouseX;
        oldMouseY = processing.mouseY;
    }

    public boolean isHitBy(Bug b) {
        // Implementation of the isHitBy method
        return this.getHitbox().doesCollide(b.getHitbox());
    }

    public Hitbox getTongueHitbox() { //Gets the Hitbox for this Frog's tongue.
        if (!tongue.isActive()) {
            throw new IllegalStateException("Tongue is currently inactive");
        }
        return tongue.getHitbox();

    }

    public void loseHealth() { //Decreases the health of this Frog by 1.
        health--;
    }

    public int getHealth() {
        return health;
    } //Gets the current health of the Frog

    @Override
    public boolean shouldMove() { //Reports if the Frog needs to move on the screen.

        return isDragging;
    }

    public boolean isDead() {
        return health <= 0;
    } //Determines if this frog is dead.

    public void mousePressed() { //Changes this Frog so it is now being dragged.
        if (isMouseOver()) {
            this.isDragging = true;
        }

    }

    public void mouseReleased() { //Changes this Frog so it is no longer being dragged.
        this.isDragging = false;


    }

    public boolean isMouseOver() { //Determines if the mouse is over the Frog's image
        float x = this.getX();
        float y = this.getY();
        if (this.processing.mouseX <= x + (this.image.width / 2) && this.processing.mouseX >= x - (this.image.width / 2)) {
            return this.processing.mouseY <= y + (this.image.height / 2) && this.processing.mouseY >= y - (this.image.height / 2);
        }
        return false;
    }

    public void startAttack() { //Starts an attack by resetting the tongue to its default state and activating the tongue.
        tongue.reset();
        tongue.activate();

    }
    public void stopAttack() { //Stops the attack by deactivating the tongue.
        tongue.deactivate();

    }

    public boolean tongueHitBoundary() { //Reports if this Frog's tongue has hit the top of the screen.
        return tongue.hitScreenBoundary();
    }
}
