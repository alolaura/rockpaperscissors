public class Scissors implements Shape {
    private final String type = "scissors";
    @Override
    public String getType() {
        return type;
    }

    @Override
    public String versus(Shape shape) {
        if(type.equals(shape.getType())){
            return "DRAW";
        }
        else if("rock".equals(shape.getType())){
            return "LOSER";
        }
        else {
            return "WINNER";
        }
    }
}
