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
public class Bowling {

    private static final char STRIKE = 'X';
    private static final char SPARE = '/';
    private static final char BAR = '|';

    public int getBowlingScore(String bowlingScoreString) {
        return getTotalScore(getScorePanels(bowlingScoreString));
    }

    /**
     * Get Score Panels for a given Score String
     *
     * @param bowlingScoreString
     * @return
     */
    public List<Panel> getScorePanels(String bowlingScoreString) {

        System.out.println(bowlingScoreString);

        List<Panel> scorePanels = new ArrayList<Panel>();

        int currentPanelPosition = 0;
        char lastChar = ' ';

        boolean isBonus = false;

        for (char c : bowlingScoreString.toCharArray()) {

            switch (c) {

                case STRIKE:
                    Panel s1 = new Panel(currentPanelPosition);
                    s1.firstRoll = Panel.ALL_PINS;

                    if (isBonus) {
                        s1.state = Panel.IS_BONUS;
                    } else {
                        s1.state = Panel.IS_STRIKE;
                    }

                    scorePanels.add(currentPanelPosition, s1);
                    break;

                case SPARE:
                    Panel s2 = scorePanels.get(currentPanelPosition);
                    s2.secondRoll = Panel.ALL_PINS - s2.firstRoll;

                    if (isBonus) {
                        s2.state = Panel.IS_BONUS;
                    } else {
                        s2.state = Panel.IS_SPARE;
                    }

                    break;

                default:
                    int value = Character.getNumericValue(c);

                    Panel sp;
                    if (scorePanels.size() > currentPanelPosition) {
                        //If still on the same panel
                        sp = scorePanels.get(currentPanelPosition);

                        if (value < Panel.ALL_PINS && value > 0) {
                            sp.secondRoll = value;

                            if (isBonus) {
                                sp.state = Panel.IS_BONUS;
                            } else {
                                sp.state = Panel.IS_SCORE;
                            }
                        }
                    } else {

                        //If in a new panel
                        sp = new Panel(currentPanelPosition);
                        if (value < Panel.ALL_PINS && value > 0) {
                            sp.firstRoll = value;

                            if (isBonus) {
                                sp.state = Panel.IS_BONUS;
                            } else {
                                sp.state = Panel.IS_SCORE;
                            }
                        }
                        scorePanels.add(currentPanelPosition, sp);
                    }
                    break;

                case BAR:
                    if (lastChar != '|') {
                        currentPanelPosition++;
                    } else {
                        isBonus = true;
                    }
                    break;
            }
            lastChar = c;
        }

        return scorePanels;
    }

    /**
     * Get total score for a set of score panels
     *
     * @param scorePanels
     * @return
     */
    public int getTotalScore(List<Panel> scorePanels) {
        int totalScore = 0;

        for (int i = 0; i < scorePanels.size(); i++) {

            int bonus1 = 0;
            int bonus2 = 0;

            Panel currentPanel = scorePanels.get(i);
            printScorePanel(currentPanel);
            
            if (i < 10) {
                if (i + 1 < scorePanels.size()) {

                    Panel bonusPanel = scorePanels.get(i + 1);

                    bonus1 = bonusPanel.firstRoll;

                    //If the bonus panel is a strike, 
                    //Use the next panel's roll as the 2nd bonus
                    if (bonusPanel.firstRoll == Panel.ALL_PINS) {
                        if (i + 2 < scorePanels.size()) {
                            Panel secondBonusPanel = scorePanels.get(i + 2);
                            bonus2 = secondBonusPanel.firstRoll;
                        }
                    } else {
                        //Else, use the current panels second roll as the 2nd bonus
                        bonus2 = bonusPanel.secondRoll;
                    }
                }
            }

            int sum;
            switch (currentPanel.state) {
                case Panel.IS_STRIKE:
                    sum = currentPanel.firstRoll + currentPanel.secondRoll + bonus1 + bonus2;
                    System.out.println("Total = " + sum);
                    totalScore += sum;
                    break;

                case Panel.IS_SPARE:
                    sum = currentPanel.firstRoll + currentPanel.secondRoll + bonus1;
                    System.out.println("Total = " + sum);
                    totalScore += sum;
                    break;

                case Panel.IS_SCORE:
                    System.out.println("Total = " + currentPanel.firstRoll);
                    totalScore += currentPanel.firstRoll + currentPanel.secondRoll;
                    break;
            }
        }
        System.out.println("Sum = " + totalScore);

        System.out.println(System.getProperty("line.separator"));
        return totalScore;
    }

    /**
     * Print out the details on a given Score Panel
     *
     * @param scorePanel
     */
    public void printScorePanel(Panel scorePanel) {
        System.out.println("POSITION = " + scorePanel.position + ", SCORE = " + scorePanel.firstRoll + " and " + scorePanel.secondRoll + ", STATE =" + scorePanel.state);

    }

}
