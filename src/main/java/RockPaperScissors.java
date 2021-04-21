import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.config.PropertyNamingStrategy;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RockPaperScissors {

    public static void sendListToJson(List<MatchHistory> historyList){
        JsonbConfig jsonbConfig = new JsonbConfig()
                .withPropertyNamingStrategy(PropertyNamingStrategy.CASE_INSENSITIVE)
                .withNullValues(true);

        Jsonb jsonb = JsonbBuilder.create(jsonbConfig);
        String result = jsonb.toJson(historyList);
        try {
            String cwd = System.getProperty("user.dir");
            Files.write( Paths.get(cwd + "\\result.json"),result.getBytes());
            System.out.println("File result.json generated in " + cwd);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int rounds = 10;
        List<MatchHistory> syncList = Collections.synchronizedList(new ArrayList<>());

        Strategy randomStrategy = new RandomStrategy();
        Strategy copyMoveStrategy = new CopyLastMoveStrategy();

        Player p1 = new Player("Player1", randomStrategy, rounds);
        Player p2 = new Player("Player2", copyMoveStrategy, rounds);

        Thread thread1 = new Thread(p1);
        Thread thread2 = new Thread(p2);

        thread1.start();
        thread2.start();

        for(int roundNo = 0; roundNo < rounds; roundNo++){
            MatchHistory history;
            Shape p1Shape;
            Shape p2Shape;
            String winner;

            while(thread1.getState() != Thread.State.WAITING) {};
            p1Shape = p1.getPlayerShape(syncList);

            while(thread2.getState() != Thread.State.WAITING) {};
            p2Shape = p2.getPlayerShape(syncList);

            if(p1Shape.versus(p2Shape).equals("WINNER")){
                winner = p1.getName();
            }
            else if(p1Shape.versus(p2Shape).equals("LOSER")){
                winner = p2.getName();
            }
            else{
                winner = null;
            }

            history = new MatchHistory(roundNo + 1, winner, new Inputs(p1Shape.getType(), p2Shape.getType()));
            syncList.add(history);
        }

        sendListToJson(syncList);
//        JsonbConfig jsonbConfig = new JsonbConfig()
//                .withPropertyNamingStrategy(PropertyNamingStrategy.CASE_INSENSITIVE)
//                .withNullValues(true);
//
//        Jsonb jsonb = JsonbBuilder.create(jsonbConfig);
//        String result = jsonb.toJson(syncList);
//        try (FileWriter file = new FileWriter("result.json")) {
//            file.write(result);
//            file.flush();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


    }
}
