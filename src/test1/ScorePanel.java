/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test1;

/**
 *
 * @author Stephen
 */
public class ScorePanel {
    
    int score; 
    int state;
    final int position;
    
    public static final int IS_STRIKE = 1;
    public static final int IS_SPARE = 2;
    public static final int IS_SCORE = 3;
    
    
    
    public ScorePanel(int score, int position, int state){
        this.score = score;
        this.position = position;
        this.state = state;
    }
    
}
