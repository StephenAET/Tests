import java.util.HashMap;
import junit.framework.TestCase;
import static junit.framework.TestCase.assertEquals;
import org.junit.Assert;
import org.junit.Test;
import test1.WordCounter;

public class WordCounterTest {

    
    WordCounter wordCounter = new WordCounter();
    HashMap<String, Integer> map = wordCounter.getRailWayChildrenHashMap();

    @Test
    public void testReady() {
        assertEquals(19, (int)map.get("ready"));
    }

    @Test
    public void testAnd() {
        assertEquals(2473, (int)map.get("and"));
    }
    
    @Test
    public void testAndPrime() {
        assertEquals(true, wordCounter.isPrime(map.get("and")));
    }

}
