package shapes;
import org.junit.Test;
import static org.junit.Assert.*;

public class RockTest {

    Rock underTest = new Rock();

    @Test
    public void testGetType(){
        assertEquals("rock", underTest.getType());
    }

    @Test
    public void testVersus() {
        Rock underTest = new Rock();

        String outputVsScissors = underTest.versus(new Scissors());
        assertEquals("WINNER", outputVsScissors);

        String outputVsRock = underTest.versus(new Rock());
        assertEquals("DRAW", outputVsRock);

        String outputVsPaper= underTest.versus(new Paper());
        assertEquals("LOSER", outputVsPaper);
    }
}