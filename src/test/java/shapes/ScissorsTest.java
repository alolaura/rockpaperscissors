package shapes;
import org.junit.Test;
import static org.junit.Assert.*;

public class ScissorsTest {
    Scissors underTest = new Scissors();

    @Test
    public void testGetType(){
        assertEquals("scissors", underTest.getType());
    }

    @Test
    public void testVersus() {

        String outputVsPaper = underTest.versus(new Paper());
        assertEquals("WINNER", outputVsPaper);

        String outputVsScissors = underTest.versus(new Scissors());
        assertEquals("DRAW", outputVsScissors);

        String outputVsRock= underTest.versus(new Rock());
        assertEquals("LOSER", outputVsRock);
    }
}