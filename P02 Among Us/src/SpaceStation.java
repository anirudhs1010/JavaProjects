
//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Amogus
// Course:   CS 300 Fall 2023
//
// Author:   Anirudh
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
import java.io.File;
import java.sql.SQLOutput;

import processing.core.PImage;

public class SpaceStation {


    //private static int bgColor = 0;
    //private static PImage sprite;
    private static Amogus[] players;

    private static final int NUM_PLAYERS = 8;
    private static PImage background;
    private static int impostorIndex;
    public static void main(String[] args) {
        Utility.runApplication();
    }
    public static void setup() { //declares all variables and decides the first player and sets the imposter
        //bgColor = Utility.randGen.nextInt();
        //sprite = Utility.loadImage("images"+File.separator+"sprite1.png");
        background= Utility.loadImage("images"+File.separator+"background.jpeg");
        players = new Amogus[NUM_PLAYERS];
        players[0] = new Amogus(Utility.randGen.nextInt(1, 3));
        impostorIndex =  Utility.randGen.nextInt(1, NUM_PLAYERS-1);
        System.out.println("Imposter index: " + impostorIndex);


    }
    public static void draw() {
        //intially draws all players if the a key is pressed for them
        Utility.image(background, 600, 500);
        for (int i = 0; i < NUM_PLAYERS; i++) {
            if (players[i] != null) {
                players[i].draw();
            }
        }
        //then starts modifying the drawing if the impostor is "unaliving" off players
        for (int i = 0; i < NUM_PLAYERS; i++) {
            for (int j = 1; j < NUM_PLAYERS; j++) {
                //if the impostor and player are near proximity, the player is "unalived"
                if (players[i] != null && players[j] != null && players[j].isImpostor() && overlap(players[i], players[j])) {
                    players[i].unalive();
                }
            }

        }
        //Utility.image(sprite, 600, 400);
    }

    public static void keyPressed() {
        //makes sure that a player is added in a random position when a is pressed and the imposter is also added
        if (Utility.key() == 'a') {
            for (int i = 0; i < NUM_PLAYERS; i++) {
                if (i == impostorIndex && players[impostorIndex] == null) {
                    players[impostorIndex] = new Amogus(Utility.randGen.nextInt(1,3), Utility.randGen.nextInt(0, Utility.width()),
                            Utility.randGen.nextInt(0, Utility.height()), true);
                    break;
                }
                else if (players[i] == null) {
                    players[i] = new Amogus(Utility.randGen.nextInt(1,3), Utility.randGen.nextInt(0, Utility.width()),
                            Utility.randGen.nextInt(0, Utility.height()), false);
                    break;
                }

            }


        }
    }
    public static boolean isMouseOver(Amogus one) {
        //Use calculations to find corners of the amogus and compare it to where the mouse is
        float x = one.getX(); //arbitraily store x
        float y = one.getY(); //arbitary storage of y
        if (Utility.mouseX() <= x+(one.image().width/2) && Utility.mouseX() >= x-(one.image().width/2)) {
            if (Utility.mouseY() <= y+(one.image().height/2) && Utility.mouseY() >= y-(one.image().height/2)) {
                return true;
            }

        }
        return false;
    }

    public static void mousePressed() {
        int min = NUM_PLAYERS;
        boolean mouse = false;
        for (int i = 0; i < NUM_PLAYERS; i++) {
            if (players[i] != null && isMouseOver(players[i])) { //makes sure it is pressed and players[i] is not null
                if (min > i) { // min is used to find lowest index
                    min = i;

                }
                mouse = true;
            }
        }
        if (mouse == true) { //for edge case when players[7] is the minimum and mouse is not pressed
            players[min].startDragging();
        }

    }
    public static void mouseReleased() {
        //Makes sure that dragging is not continued for when the mouse is not pressing
        for (int i = 0; i < NUM_PLAYERS; i++) {
            if (players[i] != null) {
                players[i].stopDragging();
            }
        }
    }
    public static boolean overlap(Amogus one, Amogus two) {
        //various calculations to find if any two corners of the Amogus objects are touching
        float x = one.getX();
        float y = one.getY();
        float u = two.getX();
        float v = two.getY();
        //1. compares the side mostly to the left of Amogus and the side mostly to rightof the other
        //2. compares the upper side of one Amogus with the lower side of the other
        if (u-(two.image().width/2) <= x+(one.image().width/2) && u+two.image().width/2 >= x-(one.image().width/2)) {
            if (v - (two.image().height / 2) <= y + (one.image().height / 2) &&
                    v + (two.image().height / 2) >= y - (one.image().height / 2)) {
                return true;
            }

        }

        return false;
    }


}