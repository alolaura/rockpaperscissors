import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player implements Runnable {
    private final String name;
    private final Strategy strategy;
    private int rounds;
    private Shape shape;

    public String getName() {
        return name;
    }

    public Player(String name, Strategy strategy, int rounds) {
        this.name = name;
        this.strategy = strategy;
        this.rounds = rounds;
    }

    @Override
    public synchronized void run() {
        while (rounds > 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            shape = strategy.getShape();
            notify();
            rounds--;
        }
    }

    public synchronized Shape getPlayerShape(List<MatchHistory> historyList) throws InterruptedException {
        strategy.setHistory(historyList);
        notify();
        wait();
        return shape;
    }
}
