import javax.json.bind.annotation.JsonbProperty;

public class Inputs {
    @JsonbProperty("Player1")
    private final String player1;
    @JsonbProperty("Player2")
    private final String player2;

    public Inputs(String player1, String player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public String getPlayer1() {
        return player1;
    }

    public String getPlayer2() {
        return player2;
    }
}
