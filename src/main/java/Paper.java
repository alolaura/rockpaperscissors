public class Paper implements Shape {
    private final String type = "paper";
    @Override
    public String getType() {
        return type;
    }

    @Override
    public String versus(Shape shape) {
        if(shape.getType().equals(type)){
            return "DRAW";
        }
        else if(shape.getType().equals("scissors")){
            return "LOSER";
        }
        else {
            return "WINNER";
        }
    }
}
