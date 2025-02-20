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
import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;
import java.util.Random;

public class FrogGame extends PApplet {
  private ArrayList<GameActor> gameActors; //array list of the gameActors in the game
  private int score; //the player's current score
  private PImage backgroundImg; //the image to use for the background
  private boolean isGameOver; //keeps track if the game is over, is true if the game is over
  private Random randGen; //random number generator
  private static final int BUG_COUNT = 5; //how many bugs should be on the screen at all times

  public static void main(String[] args) {
	  PApplet.main("FrogGame");
  }



  public void settings(){
    //TODO #1 call PApplet's size() method giving it 800 as the width and 600 as the height
    size(800, 600);
  }

  @Override
  public void setup() {
    //TODO #2 add PApplet method calls from write-up
    this.getSurface().setTitle("Froggie Feeding Frenzie");
    //TODO #3 initialize randGen
    Random randGen = new Random();
    //TODO #4 load in and save the backgroundImg
    backgroundImg = loadImage("images/background.jpg");
    this.imageMode(PApplet.CENTER);
    this.rectMode(PApplet.CENTER); // rectangles when drawn will use x,y as their center
    this.focused = true;
    this.textSize(30);
    //TODO #5 initialize gameActors to an empty ArrayList
    gameActors = new ArrayList<GameActor>();

    //TODO #7 set the processing variable for all classes where necessary (update this as needed)
    Hitbox.setProcessing(this);
    this.textAlign(PApplet.CENTER); // text written to screen will have center alignment
    GameActor.setProcessing(this);
    Tongue.setProcessing(this);
    this.randGen = new Random();

    //TODO #16 call initGame()
    initGame();


  }
  private int getFrogIndex() { //acccounts for cases when the frog is not the first index.
    for (int i = 0; i < gameActors.size(); i++) {
      if (gameActors.get(i) instanceof Frog) {
        return i;
      }
    }
    return -1;

  }

  @Override
  public void draw() {
    imageMode(PApplet.CENTER);
    image(backgroundImg, 400, 300);


	//TODO #6 call PApplet's image() method to draw the backgroundImg at the center of the screen
	//TODO #8 draw every GameActor in the ArrayList to the screen



	//TODO #9 update the code you wrote for TODO #8 to have all Movable GameActors move

	//TODO #19 run all the game logic checks

    if (!isGameOver) {

      runGameLogicChecks(); // TODO #19

      for (int i = 0; i < gameActors.size(); i++) {

        gameActors.get(i).draw();
        if (gameActors.get(i) instanceof Moveable) {
          ((Moveable) (gameActors.get(i))).move();
        }


      }
      Frog frog = (Frog)(gameActors.get(getFrogIndex()));
      // Print "Health" and "Score" to the screen


      text("Health: " + frog.getHealth(), 80, 40); // TODO #14
      text("Score: " + score, 240, 40); // TODO #14
    } else {
      // Print "GAME OVER" to the center of the screen

      textAlign(PApplet.CENTER);
      text("GAME OVER", 400, 300);
    }


    //TODO #14 print "Health: " + frog's health at (80,40) and "Score: " + score at (240,40)
    //     to the screen
    //     (note in the code logic this step to be performed takes place AFTER TODO #19)

	//TODO #20 update the code you wrote above to do the following:
	  //(1) if the game is over, do NONE of the above steps. Instead print "GAME OVER" to
    //    the center of the screen.
	  //(2) otherwise do the above steps
  }

  private void addNewBug() {

	//TODO #10 implement this method, see below for more details.

    //This creates a bug of a random type and adds it to the list of GameActors.
      //(1) generate a random number in the range [0,4)
      //(2) generate a random x value in the range [0, windowWidth) for the bug
      //(3) generate a random y value in the range [0, windowHeight - 150) for the bug
      //(4) depending on the value generated in step (1)
      //    create the following bug and add it to the arraylist
		 // 0 -> a new regular Bug at (x,y) that is worth 25 points
         // 1 -> a new BouncingBug at (x,y) that has a dx of 2 and a dy of 5
         // 2 -> a new CirclingBug at (x,y) with a radius of 25 and a random set of RGB values [0,256)
         // 3 -> a new StrongBug at (x,y) with an initial health of 3
    int randomNum = randGen.nextInt(4);
    float x = randGen.nextFloat(800);
    float y = randGen.nextFloat(450);

    switch (randomNum) {
      case 0:
        gameActors.add(new Bug(x, y, 25));
        break;
      case 1:
        gameActors.add(new BouncingBug(x, y, 2, 5));
        break;
      case 2:
        int r = randGen.nextInt(0, 256);
        int g = randGen.nextInt(0, 256);
        int b = randGen.nextInt(0, 256);
        gameActors.add(new CirclingBug(x, y, 25, new int[]{r, g, b}));
        break;
      case 3:
        gameActors.add(new StrongBug(x, y, 3));
        break;
      default:
        break;
    }
  }


  @Override
  public void mousePressed() {
    Frog f = (Frog) gameActors.get(getFrogIndex());
    if (f.isMouseOver()) {
      f.mousePressed();
    }
      //TODO #11 if mouse is over the Frog call its mousePressed method

    }


  @Override
  public void mouseReleased() {
    Frog f = (Frog)(gameActors.get(getFrogIndex()));
    f.mouseReleased();
  }
	  //TODO #12 call the Frog's mouseReleased method



  @Override
  public void keyPressed() {
    //TODO #13 if the key is a space, have the frog starts attacking
    Frog f = (Frog)(gameActors.get(getFrogIndex()));
    if (this.key == ' ') {
      f.startAttack();
    }
    if (this.key == 'r') {
      initGame();
    }
	  //TODO #17 if the key is a lowercase 'r', reset the game to its initial state
  }

  public void initGame() {
	///This method sets the game to its initial state before playing.
    //(1) set the score to 0
    //(2) make the game NOT over
    //(3) clear out the arraylist
    //(4) create and add Frog with 100 health to the list. Its x value should be half the
    //     width of the screen. Its y value should be the height of the screen minus 100
    //(5) add new bugs (of random varieties) to the list UP TO the BUG_COUNT
    score = 0;
    isGameOver = false;
    gameActors.clear();
    gameActors.add(new Frog(400,300, 100));
    for (int i = 0; i < BUG_COUNT; i++) {
      addNewBug();
    }

	   //(5) add new bugs (of random varieties) to the list UP TO the BUG_COUNT
  }

  private void runGameLogicChecks() {
    // (1) if the Frog's tongue hits the edge of the screen, then it stops attacking
    Frog frog = (Frog) gameActors.get(getFrogIndex());
    if (frog.tongueHitBoundary()) {
      frog.stopAttack();
    }

    // (2) Check every bug to see if it has been hit by the Frog.
    for (int i = 1; i < gameActors.size(); i++) {
      if (gameActors.get(i) instanceof Bug) {
        Bug bug = (Bug) gameActors.get(i);
        try {
          if (bug.getHitbox().doesCollide(frog.getTongueHitbox())) {
            if (bug instanceof StrongBug) {
              StrongBug strongBug = (StrongBug) bug;
              frog.loseHealth();
              strongBug.loseHealth();
              if (strongBug.isDead()) {
                gameActors.remove(i);
                i--;
                score += bug.getPoints(); // Update score
                addNewBug(); // Add new bug
              }
            } else {
              frog.stopAttack();
              gameActors.remove(i);
              i--;
              score += bug.getPoints(); // Update score
              addNewBug(); // Add new bug
            }
          }
        }
        catch (IllegalStateException e) {
        }
      }
    }

    // (3) check if the frog hits any of the bugs
    for (int i =0; i < gameActors.size(); i++) {
      if (gameActors.get(i) instanceof Bug && frog.isHitBy((Bug)(gameActors.get(i)))) {
          frog.loseHealth();
          if (frog.isDead()) {
            isGameOver = true;

          }
      }
    }
  }

}