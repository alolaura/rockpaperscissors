import java.util.List;
import java.util.Random;

public class CopyLastMoveStrategy implements Strategy{
    //private Random random = new Random();
    //private int shapeId;
    private Shape shape;
    private final Random random = new Random();
    private List<MatchHistory> historyList;
    @Override
    public Shape getShape(){
        if(!historyList.isEmpty()) {
            if(historyList.get(historyList.size() - 1).getInputs().getPlayer1().equals("scissors")){
                shape = new Scissors();
            }
            if(historyList.get(historyList.size() - 1).getInputs().getPlayer1().equals("rock")){
                shape = new Rock();
            }
            if(historyList.get(historyList.size() - 1).getInputs().getPlayer1().equals("paper")){
                shape = new Paper();
            }
        }
        else{
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
        }

        return shape;
    }

    @Override
    public void setHistory(List<MatchHistory> historyList) {
        this.historyList = historyList;
//        if(!historyList.isEmpty() && playerName == "Player1") {
//            System.out.println("Player 2's last move: " + historyList.get(historyList.size() - 1).getInputs().getPlayer2());
//        }
//        if(!historyList.isEmpty() && playerName == "Player2") {
//            System.out.println("Player 1's last move: " + historyList.get(historyList.size() - 1).getInputs().getPlayer1());
//        }
    }
}
