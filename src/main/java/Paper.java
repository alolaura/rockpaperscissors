public class Paper implements Shape {
    private final String type = "paper";
    @Override
    public String getType() {
        return type;
    }

    @Override
    public String versus(Shape shape) {
        if(type.equals(shape.getType())){
            return "DRAW";
        }
        else if("scissors".equals(shape.getType())){
            return "LOSER";
        }
        else {
            return "WINNER";
        }
    }
}
