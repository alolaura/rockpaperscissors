import shapes.Shape;

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

public class RockPaperScissors {

    public static String getWinner(Player p1, Player p2){
        String winner;
        if(p1.getShape().versus(p2.getShape()).equals("WINNER")){
            winner = p1.getName();
        }
        else if(p1.getShape().versus(p2.getShape()).equals("LOSER")){
            winner = p2.getName();
        }
        else{
            winner = null;
        }
        return winner;
    }

    public static void writeFile(List<MatchHistory> matchHistoryList){

        JsonbConfig jsonbConfig = new JsonbConfig()
                .withPropertyNamingStrategy(PropertyNamingStrategy.CASE_INSENSITIVE)
                .withNullValues(true);
        String fileName = "result.json";
        Jsonb jsonb = JsonbBuilder.create(jsonbConfig);
        String result = jsonb.toJson(matchHistoryList);
        try {
            String cwd = System.getProperty("user.dir");
            Files.write( Paths.get(cwd + File.separator +  fileName),result.getBytes());
            System.out.println("File " + fileName + " generated in " + cwd);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int rounds = 100;
        List<MatchHistory> matchHistoryList = Collections.synchronizedList(new ArrayList<>());

        Strategy randomStrategy = new RandomStrategy();
        Strategy copyMoveStrategy = new CopyLastMoveStrategy();

        Player p1 = new Player("Player1", randomStrategy, rounds);
        Player p2 = new Player("Player2", copyMoveStrategy, rounds);

        Thread t1 = new Thread(p1);
        Thread t2 = new Thread(p2);

        t1.start();
        t2.start();

        for(int roundNo = 0; roundNo < rounds; roundNo++){
            MatchHistory matchHistory;
            Shape p1Shape;
            Shape p2Shape;
            String winner;

            while(t1.getState() != Thread.State.WAITING) {}
            p1.setPlayerShape(matchHistoryList);

            while(t2.getState() != Thread.State.WAITING) {}
            p2.setPlayerShape(matchHistoryList);

            winner = getWinner(p1, p2);

//            if(p1Shape.versus(p2Shape).equals("WINNER")){
//                winner = p1.getName();
//            }
//            else if(p1Shape.versus(p2Shape).equals("LOSER")){
//                winner = p2.getName();
//            }
//            else{
//                winner = null;
//            }
            matchHistory = new MatchHistory(roundNo + 1, winner, new Inputs(p1.getShape().getType(), p2.getShape().getType()));
            matchHistoryList.add(matchHistory);
        }
        t1.join();
        t2.join();
        writeFile(matchHistoryList);
    }
}
