/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test1;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Stephen
 */
public class Test2 {

    private static final char STRIKE = 'X';
    private static final char SPARE = '/';
    private static final char BAR = '|';

    private static final int NUMBER_OF_PANELS = 10;

    private static final String bowlingBoard = "X|7/|9-|X|-8|8/|-6|X|X|X||81";

    public static void main(String[] args) {

        List<ScorePanel> scorePanels = new ArrayList<ScorePanel>();

        int currentPanelPosition = 0;
        char lastChar = ' ';

        boolean isBonus = false;

        for (char c : bowlingBoard.toCharArray()) {

            switch (c) {

                case STRIKE:
                    ScorePanel s1 = new ScorePanel(10, currentPanelPosition, ScorePanel.IS_STRIKE);
                    scorePanels.add(currentPanelPosition, s1);
                    printScorePanel(s1);
                    break;

                case SPARE:
                    ScorePanel s2 = scorePanels.get(currentPanelPosition);
                    s2.score = 10;
                    s2.state = ScorePanel.IS_SPARE;
                    printScorePanel(s2);
                    break;

                default:
                    int value = Character.getNumericValue(c);

                    ScorePanel sp;

                    if (scorePanels.size() > currentPanelPosition) {
                        sp = scorePanels.get(currentPanelPosition);

                        if (value < 10 && value > 0) {
                            sp.score += value;
                            sp.state = ScorePanel.IS_SCORE;
                        }

                        printScorePanel(sp);

                    } else {
                        sp = new ScorePanel(0, currentPanelPosition, 0);

                        if (value < 10 && value > 0) {
                            sp.score = value;
                            sp.state = ScorePanel.IS_SCORE;
                        }
                        scorePanels.add(currentPanelPosition, sp);

                        printScorePanel(sp);
                    }

                    break;

                case BAR:
                    if (lastChar != '|') {
                        currentPanelPosition++;
                    } else {
                        System.out.println("Bonus Scores");
                        isBonus = true;
                    }
                    break;
            }
            lastChar = c;

        }

        System.out.println("Inhales..........");

        int totalScore = 0;

        for (int i = 0; i < scorePanels.size(); i++) {

            int bonus1 = 0;
            int bonus2 = 0;

            if (i < 10) {
                if (i + 1 < scorePanels.size()) {
                    bonus1 = scorePanels.get(i + 1).score;
                }

                if (i + 2 < scorePanels.size()) {
                    bonus2 = scorePanels.get(i + 2).score;
                }
            }

            ScorePanel currentPanel = scorePanels.get(i);

            printScorePanel(currentPanel);

            int sum = 0;
            
            switch (currentPanel.state) {
                case ScorePanel.IS_STRIKE:

                    sum = currentPanel.score + bonus1 + bonus2;
                    System.out.println(sum);
                    totalScore += sum;
                    break;

                case ScorePanel.IS_SPARE:
                    sum = currentPanel.score + bonus1;
                    System.out.println(sum);
                    totalScore += sum;
                    break;

                case ScorePanel.IS_SCORE:
                    System.out.println(currentPanel.score);
                    totalScore += currentPanel.score;
                    break;
            }
        }
        System.out.println("Total = " + totalScore);
    }

    public static void printScorePanel(ScorePanel scorePanel) {

        System.out.println("POSITION = " + scorePanel.position + ", SCORE = " + scorePanel.score + ", STATE =" + scorePanel.state);

    }

    public void oldSoltution() {

        int NUMBER_OF_FRAMES = 12;

        int IS_STRIKE = 1;
        int IS_SPARE = 2;
        int IS_SCORE = 3;

        //Input String
        //Output Integer
        //10 frames, each with 2 tries, if a strike the frame ends
        //**The score for the frame is ten plus the total of the pins knocked down in the next two balls.
        //**If the second ball in a frame knocks down all ten pins, this is called a "spare". 
        //The frame is over. The score for the frame is ten plus the number of pins knocked down in the next ball.
        //                                                ^
        int[] frameScore = new int[NUMBER_OF_FRAMES];
        int[] frameState = new int[NUMBER_OF_FRAMES];

        int boardPosition = 0;

        char lastChar = ' ';

        for (char c : bowlingBoard.toCharArray()) {

            switch (c) {
                case STRIKE:
                    frameScore[boardPosition] += 10;
                    frameState[boardPosition] = IS_STRIKE;

                    if (boardPosition < NUMBER_OF_FRAMES && boardPosition >= NUMBER_OF_FRAMES - 2) {
                        boardPosition++;
                    }
                    break;

                case SPARE:
                    frameScore[boardPosition] = 10;
                    frameState[boardPosition] = IS_SPARE;
                    break;

                case BAR:
                    if (lastChar != '|' && boardPosition < NUMBER_OF_FRAMES - 1) {
                        boardPosition++;
                    }
                    break;

                default:
                    int value = Character.getNumericValue(c);
                    if (value < 10 && value > 0) {
                        frameScore[boardPosition] += value;
                        frameState[boardPosition] = IS_SCORE;
                    }
                    break;
            }
            lastChar = c;

            try {
                if (c != '|') {
                    System.out.println("Position = " + boardPosition + "LastChar = " + lastChar + ", Score = " + frameScore[boardPosition]);
                }
            } catch (Exception e) {

            }
        }

        int total = 0;

        for (int i = 0; i < NUMBER_OF_FRAMES - 2; i++) {

            /*
            int bonus1 = 0;
            int bonus2 = 0;
            if (i + 1 < NUMBER_OF_FRAMES) {
                bonus1 = frameScore[i + 1];
            }
            if (i + 2 < NUMBER_OF_FRAMES) {
                bonus2 = frameScore[i + 2];
            }

            switch (frameState[i]) {
                case IS_STRIKE:
                    frameScore[i] += bonus1 + bonus2;
                    break;
                case IS_SPARE:
                    frameScore[i] += bonus1;
                    break;
            }*/
            total += frameScore[i];
        }
        System.out.println(total);
    }
}
