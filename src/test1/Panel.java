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
public class Panel {
    
    
    int firstRoll = 0;
    int secondRoll = 0;
    int state;
    final int position;
    
    public static final int IS_STRIKE = 1;
    public static final int IS_SPARE = 2;
    public static final int IS_SCORE = 3;
    public static final int IS_BONUS = 4;
    
    public static final int ALL_PINS = 10;
    
    
    public Panel(int position){
        this.position = position;
    }
    
}
