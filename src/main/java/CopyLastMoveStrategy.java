import shapes.Paper;
import shapes.Rock;
import shapes.Scissors;
import shapes.Shape;

import java.util.List;
import java.util.Random;

public class CopyLastMoveStrategy implements Strategy {
    private Shape shape;
    private final Random random = new Random();
    private List<MatchHistory> historyList;

    @Override
    public Shape getShape() {
        if (!historyList.isEmpty()) {
            String shapeStr = historyList.get(historyList.size() - 1).getInputs().getPlayer1();
            switch (shapeStr) {
                case "scissors":
                    shape = new Scissors();
                    break;
                case "rock":
                    shape = new Rock();
                    break;
                case "paper":
                    shape = new Paper();
                    break;
            }
        } else {
            int shapeId = random.nextInt(3);
            switch (shapeId) {
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
        }
        return shape;
    }

    @Override
    public void setHistory(List<MatchHistory> historyList) {
        this.historyList = historyList;
    }
}
