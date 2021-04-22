package shapes;
import org.junit.Test;
import static org.junit.Assert.*;

public class PaperTest {

    Paper underTest = new Paper();

    @Test
    public void testGetType(){
        assertEquals("paper", underTest.getType());
    }

    @Test
    public void testVersus() {

        String outputVsRock = underTest.versus(new Rock());
        assertEquals("WINNER", outputVsRock);

        String outputVsPaper = underTest.versus(new Paper());
        assertEquals("DRAW", outputVsPaper);

        String outputVsScissors= underTest.versus(new Scissors());
        assertEquals("LOSER", outputVsScissors);
    }

}