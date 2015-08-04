package com.endovectors.uta.presentation.voice.speech_patterns;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

/**
 * Created by asham_000 on 8/1/2015.
 */
public class SpeechFlyweightFactory {

    private static SpeechFlyweightFactory speechFlyweightFactory = null;
    private ArrayList<String> goodMove;
    private ArrayList<String> reallyGoodMove;
    private ArrayList<String> badMove;
    private ArrayList<String> reallyBadMove;
    private ArrayList<String> waitingOnPlayer;
    private ArrayList<String> waitingOnArmMovement;
    private ArrayList<String> waitingOnProcessing;
    private ArrayList<String> invalidMove;

    public static SpeechFlyweightFactory getInstance(){
        if (speechFlyweightFactory == null){
            speechFlyweightFactory = new SpeechFlyweightFactory();
        }
        return speechFlyweightFactory;
    }

    private SpeechFlyweightFactory(){
        goodMove = new ArrayList<String>();
        initGoodMove();
        reallyGoodMove = new ArrayList<String>();
        initReallyGoodMove();
        badMove = new ArrayList<String>();
        initBadMove();
        reallyBadMove = new ArrayList<String>();
        initReallyBadMove();
        waitingOnPlayer = new ArrayList<String>();
        initWaitingOnPlayer();
        waitingOnArmMovement = new ArrayList<String>();
        initWaitingOnArmMovement();
        waitingOnProcessing = new ArrayList<String>();
        initWaitingOnProcessing();
        invalidMove = new ArrayList<String>();
        initInvalidMove();
    }

    private int getString(int length){
        Random random = new Random();
        return random.nextInt(length);
    }

    private void initGoodMove(){
        goodMove.add("That was a good move.");
        goodMove.add("Nice.... move.");
    }

    private void initReallyGoodMove(){
        reallyGoodMove.add("That was a.. really good move.");
        reallyGoodMove.add("I can't believe I did not see that one coming.");
    }

    private void initBadMove(){
        badMove.add("That was a bad move.");
        badMove.add("Did you really mean to move there?");
    }

    private void initReallyBadMove(){
        reallyBadMove.add("That was a horrible move.");
        reallyBadMove.add("Do you even checkers... buh row?");
    }

    private void initWaitingOnPlayer(){
        waitingOnPlayer.add("Are you still there? Please hit pause or end if you are scared to continue.");
        waitingOnPlayer.add("Did you get out the checkers manual or something?");
    }

    private void initWaitingOnArmMovement(){
        waitingOnArmMovement.add("This shouldn't take long.");
        waitingOnArmMovement.add("I am almost finished.");
    }

    private void initWaitingOnProcessing(){
        waitingOnProcessing.add("Give me a moment to look at the board.");
        waitingOnProcessing.add("Let me see.");
        waitingOnProcessing.add("What do we have here.");
    }

    private void initInvalidMove(){
        invalidMove.add("That move is invalid... Please perform a valid move.");
        invalidMove.add("Invalid move made. .... Exterminate... Exterminate");
    }

    public String getGoodMove(){
        int random = getString(goodMove.size());
        return goodMove.get(random);
    }

    public String getReallyGoodMove(){
        int random = getString(reallyGoodMove.size());
        return reallyGoodMove.get(random);
    }

    public String getBadMove(){
        int random = getString(badMove.size());
        return badMove.get(random);
    }

    public String getReallyBadMove(){
        int random = getString(reallyBadMove.size());
        return reallyBadMove.get(random);
    }

    public String getWaitingOnPlayer(){
        int random = getString(waitingOnPlayer.size());
        return waitingOnPlayer.get(random);
    }

    public String getWaitingOnArmMovement(){
        int random = getString(waitingOnArmMovement.size());
        return waitingOnArmMovement.get(random);
    }

    public String getWaitingOnProcessing(){
        int random = getString(waitingOnProcessing.size());
        return waitingOnProcessing.get(random);
    }

    public String getInvalidMove(){
        int random = getString(invalidMove.size());
        return invalidMove.get(random);
    }
}