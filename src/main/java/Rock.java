public class Rock implements Shape {
    private final String type = "rock";
    @Override
    public String getType() {
        return type;
    }

    @Override
    public String versus(Shape shape) {
        if(type.equals(shape.getType())){
            return "DRAW";
        }
        else if("paper".equals(shape.getType())){
            return "LOSER";
        }
        else {
            return "WINNER";
        }
    }
}
