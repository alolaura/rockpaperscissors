import javax.json.bind.annotation.JsonbProperty;

public class MatchHistory {
    @JsonbProperty("Round")
    private final int round;
    @JsonbProperty(value = "Winner", nillable = true)
    private final String winner;
    @JsonbProperty("Inputs")
    private final Inputs inputs;

    public MatchHistory(int round, String winner, Inputs inputs) {
        this.round = round;
        this.winner = winner;
        this.inputs = inputs;
    }

    public Inputs getInputs() { return inputs;}
    public int getRound() { return round;}
    public String getWinner() { return winner;}

    public String toString(){
        return "Round: " + round + "\nWinner: " + winner + "\nInputs: " +
                "\nPlayer1: "+ inputs.getPlayer1() + "\nPlayer2: "+ inputs.getPlayer2();
    }
}
