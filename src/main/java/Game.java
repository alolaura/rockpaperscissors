import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.config.PropertyNamingStrategy;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {
    private final List<MatchHistory> matchHistoryList = Collections.synchronizedList(new ArrayList<>());
    private static final int rounds = 100;

    private final Strategy randomStrategy = new RandomStrategy();
    private final Strategy copyMoveStrategy = new CopyLastMoveStrategy();

    private final Player p1 = new Player("Player1", randomStrategy, rounds);
    private final Player p2 = new Player("Player2", copyMoveStrategy, rounds);

    private final Thread t1 = new Thread(p1);
    private final Thread t2 = new Thread(p2);

    public void init() {
        t1.start();
        t2.start();
    }

    public void run() throws InterruptedException {
        for (int roundNo = 0; roundNo < rounds; roundNo++) {
            MatchHistory matchHistory;
            String winner;

            while (t1.getState() != Thread.State.WAITING) {
            }
            p1.setPlayerShape(matchHistoryList);

            while (t2.getState() != Thread.State.WAITING) {
            }
            p2.setPlayerShape(matchHistoryList);

            winner = getWinner(p1, p2);
            matchHistory = new MatchHistory(roundNo + 1, winner, new Inputs(p1.getShape().getType(), p2.getShape().getType()));
            matchHistoryList.add(matchHistory);
        }
    }

    public void end() throws InterruptedException {
        t1.join();
        t2.join();
        writeFile(matchHistoryList);
    }

    private String getWinner(Player p1, Player p2) {
        String winner;
        String matchResult = p1.getShape().versus(p2.getShape());
        if (matchResult.equals("WINNER")) {
            winner = p1.getName();
        } else if (matchResult.equals("LOSER")) {
            winner = p2.getName();
        } else {
            winner = null;
        }
        return winner;
    }

    private void writeFile(List<MatchHistory> matchHistoryList) {
        JsonbConfig jsonbConfig = new JsonbConfig()
                .withPropertyNamingStrategy(PropertyNamingStrategy.CASE_INSENSITIVE)
                .withNullValues(true);
        String fileName = "result.json";
        Jsonb jsonb = JsonbBuilder.create(jsonbConfig);
        String result = jsonb.toJson(matchHistoryList);
        try {
            String cwd = System.getProperty("user.dir");
            Files.write(Paths.get(cwd + File.separator + fileName), result.getBytes());
            System.out.println("File " + fileName + " generated in " + cwd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
