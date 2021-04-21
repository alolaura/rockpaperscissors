import shapes.Paper;
import shapes.Rock;
import shapes.Scissors;
import shapes.Shape;

import java.util.List;
import java.util.Random;

public class RandomStrategy implements Strategy {
    private final Random random = new Random();
    private Shape shape;
    private List<MatchHistory> historyList;

    @Override
    public Shape getShape(){
        int shapeId = random.nextInt(3);
        switch (shapeId){
            case 0:
                shape = new Rock();
                break;
            case 1:
                shape = new Paper();
                break;
            case 2:
                shape = new Scissors();
                break;
        }
        return shape;
    }

    @Override
    public void setHistory(List<MatchHistory> historyList) {
        this.historyList = historyList;
    }
}
