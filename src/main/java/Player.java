import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player implements Runnable{
    private String name;
    private List<MatchHistory> syncList;

    public String getName() {
        return name;
    }

    private Strategy strategy;
    private int rounds;
    private Shape shape;

    public Player(String name, Strategy strategy, int rounds){
        this.name = name;
        this.strategy = strategy;
        this.rounds = rounds;
    }

    @Override
    public synchronized void run() {
        while(true) {
            //System.out.println("IN THREAD " + name);
//            strategy.setHistory(syncList); //muta l in getPlayerShape
//            shape = strategy.getShape();
            try{
                //strategy.setHistory(syncList);
            //    shape = strategy.getShape();
                wait();
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
            //strategy.setHistory(syncList); //muta l in getPlayerShape
            shape = strategy.getShape();
            //System.out.println("Player " + name + " reveals a " + shape.getType());
            notify();

            rounds--;
            if(rounds <= 0) {
                //System.out.println("LIMIT REACHED!");
                break;
            }
        }
    }


    public synchronized Shape getPlayerShape(List<MatchHistory> historyList) throws InterruptedException{
        this.syncList = historyList;
        strategy.setHistory(syncList);

        //System.out.println(name + " ... now can generate value");
        notify();
        //strategy.setHistory(syncList);
        //System.out.println(name + " ... wait until value is generated");
        wait();
        //System.out.println(name + " ... returning generated value: " + shape.getType());
        return shape;
    }

//    public synchronized void setSyncList(List<MatchHistory> syncList){
//        this.syncList = syncList;
//    }
}

