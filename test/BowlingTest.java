import junit.framework.TestCase;
import static junit.framework.TestCase.assertEquals;
import org.junit.Test;
import test1.Bowling;

public class BowlingTest {
    
    Bowling bowling = new Bowling();
    
    @Test  
    public void allStrikes(){
        assertEquals(300, bowling.getBowlingScore("X|X|X|X|X|X|X|X|X|X||XX"));
    }
    
    
    @Test
    public void allSpares(){
        assertEquals(150, bowling.getBowlingScore("5/|5/|5/|5/|5/|5/|5/|5/|5/|5/||5"));
    }
    
    @Test
    public void allNormal(){
        assertEquals(90, bowling.getBowlingScore("9-|9-|9-|9-|9-|9-|9-|9-|9-|9-||"));
    }
    
    @Test
    public void normalGame(){
        assertEquals(167, bowling.getBowlingScore("X|7/|9-|X|-8|8/|-6|X|X|X||81"));
    }
    
}
