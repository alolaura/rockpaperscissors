public class Scissors implements Shape {
    private final String type = "scissors";
    @Override
    public String getType() {
        return type;
    }

    @Override
    public String versus(Shape shape) {
        if(shape.getType().equals(type)){
            return "DRAW";
        }
        else if(shape.getType().equals("rock")){
            return "LOSER";
        }
        else {
            return "WINNER";
        }
    }
}
