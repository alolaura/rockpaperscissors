package shapes;

public class Rock implements Shape {
    private final String type = "rock";
    @Override
    public String getType() {
        return type;
    }

    @Override
    public String versus(Shape shape) {
        if(shape.getType().equals(type)){
            return "DRAW";
        }
        else if(shape.getType().equals("paper")){
            return "LOSER";
        }
        else {
            return "WINNER";
        }
    }
}
