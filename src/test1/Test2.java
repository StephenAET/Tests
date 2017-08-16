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

        for (char c : bowlingBoard.toCharArray()) {

            switch (c) {

                case STRIKE:
                    ScorePanel s1 = new ScorePanel(10, currentPanelPosition, ScorePanel.IS_STRIKE);
                    scorePanels.add(currentPanelPosition, s1);
                    break;

                case SPARE:
                    ScorePanel s2 = scorePanels.get(currentPanelPosition);
                    s2.score = 10;
                    s2.state = ScorePanel.IS_SPARE;
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
                    } else {
                        sp = new ScorePanel(0, currentPanelPosition, 0);

                        if (value < 10 && value > 0) {
                            sp.score = value;
                            sp.state = ScorePanel.IS_SCORE;
                        }
                        scorePanels.add(currentPanelPosition, sp);
                    }
                    break;

                case BAR:
                    if (lastChar != '|') {
                        currentPanelPosition++;
                    }
                    break;
            }
            lastChar = c;
        }

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
                    System.out.println("Total = "+ sum);
                    totalScore += sum;
                    break;

                case ScorePanel.IS_SPARE:
                    sum = currentPanel.score + bonus1;
                    System.out.println("Total = "+ sum);
                    totalScore += sum;
                    break;

                case ScorePanel.IS_SCORE:
                    System.out.println("Total = "+ currentPanel.score);
                    totalScore += currentPanel.score;
                    break;
            }
        }
        System.out.println("Total = " + totalScore);
    }

    public static void printScorePanel(ScorePanel scorePanel) {

        System.out.println("POSITION = " + scorePanel.position + ", SCORE = " + scorePanel.score + ", STATE =" + scorePanel.state);

    }
}
